package lulu.com.demo_shpooingcart.service;

import lulu.com.demo_shpooingcart.entity.Product;
import lulu.com.demo_shpooingcart.repository.ProductRepository;
import lulu.com.demo_shpooingcart.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(RedisTemplate<String, Object> redisTemplate, ProductRepository productRepository) {
        this.redisTemplate = redisTemplate;
        this.productRepository = productRepository;
    }

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
    public void removeProductFromCart(Long userId, Long productId) {
        String cartKey = "cart:" + userId;
        redisTemplate.opsForHash().delete(cartKey, productId.toString());
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
    public List<ProductVO> viewCart(Integer userId) {
        String cartKey = "cart:" + userId;
        Map<Object, Object> cartResult = redisTemplate.opsForHash().entries(cartKey);


        // 將 Redis 中的 Hash 組成 ProductVO
        return cartResult.entrySet().stream()
                .map(entry -> {
                    int productId = Integer.parseInt(entry.getKey().toString());
                    int stock = Integer.parseInt(entry.getValue().toString());

                    // 從資料庫查詢 productName
                    Product product = productRepository.findByProductId(productId);

                    // 返回 ProductVO
                    return new ProductVO(productId, product.getProductName(), stock);
                })
                .collect(Collectors.toList());
    }
}
