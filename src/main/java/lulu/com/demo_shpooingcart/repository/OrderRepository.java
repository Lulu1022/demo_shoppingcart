package lulu.com.demo_shpooingcart.repository;

import lulu.com.demo_shpooingcart.entity.Order;
import lulu.com.demo_shpooingcart.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    List<Order> findByUser_Id(Integer id);
}
