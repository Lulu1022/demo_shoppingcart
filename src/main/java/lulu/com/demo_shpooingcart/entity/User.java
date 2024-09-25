package lulu.com.demo_shpooingcart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "demo_shoppingcart")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    //TODO [JPA Buddy] generate columns from DB
}