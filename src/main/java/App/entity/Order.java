package App.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    @NotBlank(message = "User cannot be blank")
    private User user;

    @JoinColumn(name = "department_id")
    @OneToOne(cascade = CascadeType.ALL)
    @NotBlank(message = "Department cannot be blank")
    private Department department;

    @JoinColumn(name = "room_id")
    @OneToOne(cascade = CascadeType.ALL)
    @NotBlank(message = "Room cannot be blank")
    private Room room;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    @Getter
    private Set<DrinkOrder> drinks = new HashSet<>();

    public Order() {}

    public Order(User user, Department department, Room room) {
        this.user = user;
        this.department = department;
        this.room = room;
    }
}
