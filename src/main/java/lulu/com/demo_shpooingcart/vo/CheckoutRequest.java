package lulu.com.demo_shpooingcart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {
    private Integer userId;
    private List<CheckoutItem> items;
}
