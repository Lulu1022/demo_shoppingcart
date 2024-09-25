package lulu.com.demo_shpooingcart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private int productId;
    private String productName;
    private int stock;

    // TODO: 可能需要增加商品圖片
}
