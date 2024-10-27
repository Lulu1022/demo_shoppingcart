package lulu.com.demo_shpooingcart.controller;

import io.swagger.v3.oas.annotations.Operation;
import lulu.com.demo_shpooingcart.entity.Order;
import lulu.com.demo_shpooingcart.entity.Orderitem;
import lulu.com.demo_shpooingcart.service.OrderItemService;
import lulu.com.demo_shpooingcart.vo.CheckoutRequest;
import lulu.com.demo_shpooingcart.vo.MyOrderItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/orderitem/{orderitemId}")
    @Operation(summary = "查看單筆訂單明細",description = "會回傳orderitemVO 包含產品id、數量、價格")
    public ResponseEntity<List<Orderitem>> getAllOrderitem(@PathVariable Integer orderitemId){
        List<Orderitem> orderitem = orderItemService.getAll(orderitemId);
        return ResponseEntity.ok(orderitem);
    }

    @GetMapping("/order/{userId}")
    @Operation(summary = "我的訂單列表",description = "")
    public ResponseEntity<List<Order>>getMyOrder(@PathVariable Integer userId){
        List<Order> myOrders = orderItemService.getMyOrder(userId);
        System.out.println("123");
        return ResponseEntity.ok(myOrders);
    }

    @GetMapping("/order/findOrderDetailsByOrderId/{orderId}")
    @Operation(summary = "我的訂單明細",description = "")
    public ResponseEntity<List<MyOrderItemResponse>>findOrderDetailsByOrderId(@PathVariable Integer orderId){
        List<MyOrderItemResponse> myOrderitems = orderItemService.getmyOrderitems(orderId);
        return ResponseEntity.ok(myOrderitems);
    }
}
