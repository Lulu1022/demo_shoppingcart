package lulu.com.demo_shpooingcart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {

    private Integer supplierId;
    private String supplierName;
    private List<ProductItem> productList;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductItem{
        private Integer productId;
        private String productName;
        private String productSpec;
        private String picture;
        private Integer price;
        private Integer quantity;

    }

}
