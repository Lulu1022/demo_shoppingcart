package lulu.com.demo_shpooingcart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "demo_shoppingcart")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @NotNull
    @Column(name = "vendor_id", nullable = false)
    private Integer vendorId;

    @Column(name = "stock")
    private Integer stock;

    @Size(max = 50)
    @Column(name = "product_name", length = 50)
    private String productName;

}