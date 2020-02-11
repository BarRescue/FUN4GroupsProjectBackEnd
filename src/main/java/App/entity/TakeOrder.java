package App.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "order_done")
@Getter @Setter
public class TakeOrder {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    @NotBlank(message = "User cannot be blank")
    private User user;

    @JoinColumn(name = "order_id")
    @OneToOne(cascade = CascadeType.ALL)
    @NotBlank(message = "Order cannot be blank")
    private Order order;

    public TakeOrder() {}

    public TakeOrder(User user, Order order) {
        this.user = user;
        this.order = order;
    }
}
