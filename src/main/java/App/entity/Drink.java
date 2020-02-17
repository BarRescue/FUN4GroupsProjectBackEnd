package App.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @Column(name="has_sugar", columnDefinition = "boolean default false")
    private Boolean hasSugar = false;

    @Column(name="has_milk", columnDefinition = "boolean default false")
    private Boolean hasMilk = false;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "drink",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    @Getter
    private Set<DrinkOrder> orders = new HashSet<>();

    public Drink() {}

    public Drink(String name, Boolean hasSugar, Boolean hasMilk) {
        this.name = name;
        this.hasMilk = hasMilk;
        this.hasSugar = hasSugar;
    }
}