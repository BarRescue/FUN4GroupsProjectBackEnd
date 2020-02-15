package App.models.drink;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DrinkRegisterModel {
    @NotEmpty(message = "Please provide: Drink Name")
    private String drinkName;

    private Boolean hasSugar;
    private Boolean hasMilk;
}
