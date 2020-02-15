package App.models.order;

import App.entity.Drink;
import App.models.drink.DrinkDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderModel {
    @NotEmpty(message = "Please provide: Department ID")
    private String departmentId;

    @NotEmpty(message = "Please provide: Room ID")
    private String roomId;

    @NotEmpty(message = "Please provide: Drinks")
    private List<DrinkDTO> drinks;
}
