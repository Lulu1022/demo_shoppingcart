package lulu.com.demo_shpooingcart.repository;

import lulu.com.demo_shpooingcart.entity.Order;
import lulu.com.demo_shpooingcart.entity.Orderitem;
import lulu.com.demo_shpooingcart.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<Orderitem, Integer> {


}
