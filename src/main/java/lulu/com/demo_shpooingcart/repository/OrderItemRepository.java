package lulu.com.demo_shpooingcart.repository;

import io.lettuce.core.dynamic.annotation.Param;
import lulu.com.demo_shpooingcart.entity.Order;
import lulu.com.demo_shpooingcart.entity.Orderitem;
import lulu.com.demo_shpooingcart.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<Orderitem, Integer> {

    @Query(value ="SELECT o.order_id, o.user_id, o.total_amount, o.created_datetime, o.vendor_id, o.pickup_date, " +
            "oi.product_id, oi.quantity, oi.price, oi.pick_address," +
            "p.product_name, " +
            "v.shop_name, " +
            "u.username, u.email " +
            "FROM `orders` o " +
            "INNER JOIN `users` u ON o.user_id = u.user_id " +
            "INNER JOIN `vendors` v ON o.vendor_id = v.vendor_id " +
            "INNER JOIN `orderitems` oi ON o.order_id = oi.order_id " +
            "INNER JOIN `products` p ON p.product_id = oi.product_id " +
            "WHERE o.order_id = ?", nativeQuery = true)
    List<Object[]> findOrderDetailsByOrderId(@Param("orderId") Integer orderId);

}
