package App.models.drink;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DrinkRegisterModel {
    @NotEmpty(message = "Please provide: Drink Name")
    private String drinkName;

    @NotEmpty(message = "Please provide: Sugar true/false")
    private Boolean hasSugar;

    @NotEmpty(message = "Please provide: Milk true/false")
    private Boolean hasMilk;
}
