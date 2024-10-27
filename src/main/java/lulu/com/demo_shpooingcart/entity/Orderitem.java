package lulu.com.demo_shpooingcart.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orderitems")
public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @Column(name = "pick_adress", nullable = false)
    private String pickAddress;

}