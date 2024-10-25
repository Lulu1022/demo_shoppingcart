package lulu.com.demo_shpooingcart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @NotNull
    @Column(name = "order_status", nullable = false)
    private Integer orderStatus;

    @NotNull
    @Column(name = "created_datetime", nullable = false)
    private Instant createdDatetime;

    @NotNull
    @Column(name = "updated_datetime", nullable = false)
    private Instant updatedDatetime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Column(name = "pickup_date")
    private Instant pickupDate;

}