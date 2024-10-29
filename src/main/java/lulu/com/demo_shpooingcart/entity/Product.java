package lulu.com.demo_shpooingcart.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "products", schema = "demo_shoppingcart")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Size(max = 255)
    @Column(name = "product_spec")
    private String productSpec;

    @Size(max = 255)
    @Column(name = "product_content")
    private String productContent;

    @Column(name = "price")
    private Integer price;

    @Column(name = "supplier_id")
    private Integer supplierId;

    @Column(name = "stock")
    private Integer stock;

    @Size(max = 50)
    @Column(name = "product_name", length = 50)
    private String productName;

}