package lulu.com.demo_shpooingcart.service;
import lulu.com.demo_shpooingcart.entity.Order;
import lulu.com.demo_shpooingcart.entity.Orderitem;
import lulu.com.demo_shpooingcart.entity.User;
import lulu.com.demo_shpooingcart.entity.Vendor;
import lulu.com.demo_shpooingcart.repository.OrderItemRepository;
import lulu.com.demo_shpooingcart.repository.OrderRepository;
import lulu.com.demo_shpooingcart.vo.CheckoutItem;
import lulu.com.demo_shpooingcart.vo.CheckoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    public void saveOrder(CheckoutRequest checkoutRequest) {
        Integer userId = checkoutRequest.getUserId();
        List<CheckoutItem> checkoutRequestItems = checkoutRequest.getItems();

        //Key: 廠商 ID, value: 使用者在該廠商下單的所有產品金額
        HashMap<Integer, Integer> map = new HashMap<>();

        // 新增訂單
        for (CheckoutItem item : checkoutRequestItems) {

            // 如果該 vendorId 不存在於 map 中，則初始化金額，否則累加金額
            if (!map.containsKey(item.getVendorId())) {
                map.put(item.getVendorId(), item.getPrice()*item.getQuantity());
            } else {
                map.put(item.getVendorId(), map.get(item.getVendorId()) + item.getPrice()* item.getQuantity());
            }
        }

        // map Key: 廠商 ID, value: 使用者在該廠商下單的所有產品金額
        map.forEach((vendorId, amount) -> {
            Order order = new Order();

            User user = new User();
            user.setId(userId);
            order.setUser(user);

            order.setOrderStatus(0);

            Vendor vendor = new Vendor();
            vendor.setId(vendorId);
            order.setVendor(vendor);
            order.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
            order.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));

            order.setTotalAmount(amount);

            // 訂單 存到資料庫
            orderRepository.save(order);

        });


        // 新增購買商品
        for (CheckoutItem item : checkoutRequestItems) {
            // 創建購買的商品物件
            Orderitem orderitem = new Orderitem();

            Order referenceById = orderRepository.getReferenceById(item.getVendorId());
            orderitem.setOrderId(referenceById.getId());
            orderitem.setProductId(item.getProductId());
            orderitem.setQuantity(item.getQuantity());
            orderitem.setPrice(item.getPrice());

            // 購買的商品 存到資料庫
            orderItemRepository.save(orderitem);
        }

    }

    public List<Orderitem> getAll(Integer orderitemId){
        return orderItemRepository.findAll();
    }


    public List<Order> getMyOrder(Integer userId){
        // 購物車成功下單的商品
        return orderRepository.findByUser_Id(userId);
    }
}
