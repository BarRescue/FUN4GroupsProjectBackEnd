package App.controllers.drink;

import App.controllers.enums.DrinkResponse;
import App.entity.Drink;
import App.models.drink.DrinkRegisterModel;
import App.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/drink")
public class DrinkController {
    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public ResponseEntity getDrinks() {
        List<Drink> drinks = this.drinkService.findAll();

        if(drinks.isEmpty()) {
            return new ResponseEntity<>(DrinkResponse.NO_DRINKS.toString(), HttpStatus.BAD_REQUEST);
        }

        return ok(drinks);
    }

    @GetMapping(value = "{number}")
    public ResponseEntity getDrink(@Valid @PathVariable String id) {
        Optional<Drink> drink = this.drinkService.findById(UUID.fromString(id));

        if(!drink.isPresent()) {
            return new ResponseEntity<>(DrinkResponse.NO_DRINK.toString(), HttpStatus.BAD_REQUEST);
        }

        return ok(drink.get());
    }

    @PostMapping
    public ResponseEntity createDrink(@Valid @RequestBody DrinkRegisterModel drinkRegisterModel) {

    }
}
