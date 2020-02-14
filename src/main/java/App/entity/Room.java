package App.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room implements Serializable {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Room number cannot be blank")
    private String roomNumber;

    @NotBlank(message = "Room name cannot be blank")
    private String roomName;

    public Room() {}

    public Room(String roomNumber, String roomName) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
    }
}
