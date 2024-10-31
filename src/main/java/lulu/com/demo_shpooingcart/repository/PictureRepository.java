package lulu.com.demo_shpooingcart.repository;
import lulu.com.demo_shpooingcart.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findByProduct_ProductId(Integer productId);


}
