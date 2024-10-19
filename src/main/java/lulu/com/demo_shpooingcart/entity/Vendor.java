package lulu.com.demo_shpooingcart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vender_id", nullable = false)
    private Integer id;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

}