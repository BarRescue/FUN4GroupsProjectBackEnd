package App.entity;

import App.entity.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "drink_order")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class DrinkOrder implements Serializable {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    private Drink drink;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JsonBackReference
    @JsonUnwrapped
    private Order order;

    @Nullable
    private int sugar;

    @Nullable
    private int milk;

    @NotNull
    private int strength;

    @NotNull
    private Size size;

    public DrinkOrder() {}

    public DrinkOrder(Drink drink, Order order, int sugar, int milk, int strength, Size size) {
        this.drink = drink;
        this.order = order;
        this.sugar = sugar;
        this.milk = milk;
        this.strength = strength;
        this.size = size;
    }
}
