package lulu.com.demo_shpooingcart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutItem {
    private Integer productId;
    private Integer quantity;
    private Integer price;
    private Integer vendorId;
}
