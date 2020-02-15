package App.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "order_done")
@Getter @Setter
public class TakeOrder {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;

    public TakeOrder() {}

    public TakeOrder(User user, Order order) {
        this.user = user;
        this.order = order;
    }
}
