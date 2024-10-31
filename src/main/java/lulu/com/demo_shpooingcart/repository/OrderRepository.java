package lulu.com.demo_shpooingcart.repository;

import lulu.com.demo_shpooingcart.entity.Order;
import lulu.com.demo_shpooingcart.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.user.userId = ?1")
    List<Order> findByUser_UserId(Integer userId);


    @Override
    Optional<Order> findById(Integer integer);
}
