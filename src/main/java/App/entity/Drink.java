package App.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "drinks")
@Getter
@Setter
public class Drink implements Serializable {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(name = "has_sugar", columnDefinition = "BOOLEAN")
    private Boolean hasSugar;

    @Column(name = "has_milk", columnDefinition = "BOOLEAN")
    private Boolean hasMilk;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "drink",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<DrinkOrder> drinks = new HashSet<>();

    public Drink() {}

    public Drink(String name, Boolean hasSugar, Boolean hasMilk) {
        this.name = name;
        this.hasSugar = hasSugar;
        this.hasMilk = hasMilk;
    }
}
