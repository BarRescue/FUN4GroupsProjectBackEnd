package App.entity;

import App.entity.enums.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order implements Serializable {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @NotNull
    private Room room;

    @Column(name="order_done", columnDefinition = "boolean default false")
    private Boolean orderDone = false;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<DrinkOrder> drinks = new HashSet<>();

    public Order() {}

    public Order(User user, Department department, Room room) {
        this.user = user;
        this.department = department;
        this.room = room;
    }

    public void addDrink(Drink drink, int sugar, int milk, int strength, Size size) {
        this.drinks.add(new DrinkOrder(drink, this, sugar, milk, strength, size));
    }
}
