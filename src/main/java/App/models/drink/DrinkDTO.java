package App.models.drink;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrinkDTO {
    public String drinkId;
    public int sugar;
    public int milk;
    public int strength;
    public String size;
}
