package App.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department implements Serializable {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Department name cannot be blank")
    private String departmentName;

    public Department() {}

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
}
