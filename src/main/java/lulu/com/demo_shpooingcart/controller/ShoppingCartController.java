package lulu.com.demo_shpooingcart.controller;

import io.swagger.v3.oas.annotations.Operation;
import lulu.com.demo_shpooingcart.service.ShoppingCartService;
import lulu.com.demo_shpooingcart.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    // 加入商品
    @PostMapping("/add")
    @Operation(summary = "加入購物車")
    public ResponseEntity<String> addProductToCart(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        shoppingCartService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok("商品成功加入購物車");
    }

    // 移除商品
    @DeleteMapping("/remove")
    @Operation(summary = "商品從購物車中刪除")
    public ResponseEntity<String> removeProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        shoppingCartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok("商品成功移出購物車");
    }

    // 更新商品數量
    @PutMapping("/update")
    @Operation(summary = "更新商品在購物車中的數量")
    public ResponseEntity<String> updateProductQuantity(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        shoppingCartService.updateProductQuantity(userId, productId, quantity);
        return ResponseEntity.ok("商品在購物車中的數量成功更新");
    }

    // 查看購物車
    @GetMapping("/view")
    @Operation(summary = "查看購物中的商品")
    public ResponseEntity<List<ProductVO>> viewCart(@RequestParam Integer userId) {
        List<ProductVO> cart = shoppingCartService.viewCart(userId);
        return ResponseEntity.ok(cart);
    }
}
