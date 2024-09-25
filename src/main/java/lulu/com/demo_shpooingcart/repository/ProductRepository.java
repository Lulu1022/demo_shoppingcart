package lulu.com.demo_shpooingcart.repository;

import lulu.com.demo_shpooingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // 查詢 productName 根據 productId
    @Query("select p from Product p where p.productId = ?1")
    Product findByProductId(Integer productId);

}
