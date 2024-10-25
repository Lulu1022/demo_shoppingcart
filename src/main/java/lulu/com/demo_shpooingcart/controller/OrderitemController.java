package lulu.com.demo_shpooingcart.controller;

import lulu.com.demo_shpooingcart.service.OrderItemService;
import lulu.com.demo_shpooingcart.vo.CheckoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderitemController {
    @Autowired
    private OrderItemService orderItemService;
    @PostMapping("/order")
    public ResponseEntity<String> processCheckout(@RequestBody CheckoutRequest checkoutRequest) {
        orderItemService.saveOrder(checkoutRequest);
        return ResponseEntity.ok("成功購買");
    }
}
