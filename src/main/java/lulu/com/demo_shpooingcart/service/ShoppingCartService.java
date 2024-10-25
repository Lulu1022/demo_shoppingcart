package lulu.com.demo_shpooingcart.service;

import lulu.com.demo_shpooingcart.entity.Picture;
import lulu.com.demo_shpooingcart.entity.Product;
import lulu.com.demo_shpooingcart.entity.Vendor;
import lulu.com.demo_shpooingcart.repository.PictureRepository;
import lulu.com.demo_shpooingcart.repository.ProductRepository;
import lulu.com.demo_shpooingcart.repository.VendorRepository;
import lulu.com.demo_shpooingcart.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ShoppingCartService {

    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private PictureRepository pictureRepository;


    // 加入商品到購物車
    public void addProductToCart(Integer userId, Integer productId, int quantity) {
        String cartKey = "cart:" + userId;
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("找不到商品"));

        if (product.getStock() >= quantity) {
            redisTemplate.opsForHash().put(cartKey, productId.toString(), quantity);
        } else {
            throw new RuntimeException("商品數量不夠");
        }
        redisTemplate.opsForHash().put(cartKey, productId.toString(), quantity);
    }

    // 從購物車中移除商品
    public void removeProductFromCart(Integer userId, Integer productId) {
        String cartKey = "cart:" + userId;
        redisTemplate.opsForHash().delete(cartKey, productId.toString());

        // TODO: 檢查是否還有庫存


    }

    // 更新購物車中的商品數量
    public void updateProductQuantity(Integer userId, Integer productId, Integer quantity) {
        String cartKey = "cart:" + userId;
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("找不到商品"));

        if (product.getStock() >= quantity) {
            redisTemplate.opsForHash().put(cartKey, productId.toString(), quantity);
        } else {
            throw new RuntimeException("庫存不夠");
        }
    }

    // 查看購物車內容
//    public List<ProductVO> viewCart(Integer userId) {
//        String cartKey = "cart:" + userId;
//        Map<Object, Object> cartResult = redisTemplate.opsForHash().entries(cartKey);
//
//
//        // 將 Redis 中的 Hash 組成 ProductVO
//        return cartResult.entrySet().stream()
//                .map(entry -> {
//                    int productId = Integer.parseInt(entry.getKey().toString());
//                    int stock = Integer.parseInt(entry.getValue().toString());
//
//                    // 從資料庫查詢 productName
//                    Product product = productRepository.findByProductId(productId);
//
//                    // 返回 ProductVO
//                    return new ProductVO(productId, product.getProductName(), stock);
//                })
//                .collect(Collectors.toList());
//    }

    public List<ProductVO> viewCart(Integer userId) {
        String cartKey = "cart:" + userId;

        Map<Object, Object> cartResult = redisTemplate.opsForHash().entries(cartKey);

        // 查詢所有產品的資料1
        List<Integer> productIds = cartResult.keySet().stream()
                .map(key -> Integer.parseInt(key.toString()))
                .collect(Collectors.toList()); //[2,1,3,4]
        List<Product> products = productRepository.findAllById(productIds); //[Product2, Product1, Product3, Product4]

        // 查詢所有廠商資料
        Map<Integer, Vendor> vendorMap = products.stream()
                .map(Product::getSupplierId)
                .distinct()
                .collect(Collectors.toMap(vendorId -> vendorId, vendorId -> vendorRepository.getReferenceById(vendorId)));
        // key:1, value:VendorA,     key:2, value:VendorB

        List<Picture> pictures = pictureRepository.findAllById(productIds);

        // 將 productIds 作為 key，從 Picture 中提取 picture 屬性作為值
//        Map<Integer, byte[]> pictureMap = IntStream.range(0, productIds.size())
//                .boxed()
//                .collect(Collectors.toMap(productIds::get, i -> pictures.get(i).getPicture()));

//        Map<Integer, byte[]> pictureMap = IntStream.range(0, Math.min(productIds.size(), pictures.size()))
//                .boxed()
//                .collect(Collectors.toMap(productIds::get, i -> pictures.get(i).getPicture()));


        // 整合資料
        Map<Integer, ProductVO> productVOMap = new HashMap<>();

        for (Map.Entry<Object, Object> entry : cartResult.entrySet()) {
            Integer productId = Integer.parseInt(entry.getKey().toString());
            Integer quantity = Integer.parseInt(entry.getValue().toString());

            Product product = products.stream()
                    .filter(p -> p.getProductId().equals(productId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // 廠商資料
            Vendor vendor = vendorMap.get(product.getSupplierId());
            String vendorName = vendor.getShopName();


            // 建立 ProductItem
            Integer price = product.getPrice();
            ProductVO.ProductItem item = new ProductVO.ProductItem(
                    productId,
                    product.getProductName(),
                    product.getProductSpec(),
                    "pic",  // 用"pic"替代真實圖片的Base64，根據需求可以切換
                    product.getPrice(),
                    quantity
            );

            // 檢查該廠商的 ProductVO 是否已存在
            ProductVO productVO = productVOMap.get(product.getSupplierId());
            if (productVO == null) {
                productVO = new ProductVO(product.getSupplierId(), vendorName, new ArrayList<>());
                productVOMap.put(product.getSupplierId(), productVO);
            }

            // 加入商品至對應廠商的 productList
            productVO.getProductList().add(item);
        }

        // 返回所有廠商的 ProductVO 列表
        return new ArrayList<>(productVOMap.values());
    }

    public List<Picture> viewProduct(Integer productId) {
        return pictureRepository.findByProduct_ProductId(productId);
    }
}
