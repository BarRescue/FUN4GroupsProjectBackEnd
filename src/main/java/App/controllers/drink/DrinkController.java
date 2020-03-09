package App.controllers.drink;

import App.controllers.enums.AuthResponse;
import App.controllers.enums.DrinkResponse;
import App.controllers.enums.RoomResponse;
import App.entity.Drink;
import App.entity.Room;
import App.entity.User;
import App.models.drink.DrinkRegisterModel;
import App.service.DrinkService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(value = "{id}")
    public ResponseEntity getDrink(@Valid @PathVariable String id) {
        Optional<Drink> drink = this.drinkService.findById(UUID.fromString(id));

        if(!drink.isPresent()) {
            return new ResponseEntity<>(DrinkResponse.NO_DRINK.toString(), HttpStatus.BAD_REQUEST);
        }

        return ok(drink.get());
    }

    @PostMapping
    public ResponseEntity createDrink(@AuthenticationPrincipal User user, @Valid @RequestBody DrinkRegisterModel drinkRegisterModel, HttpServletRequest request) {
        if (user.getRole().toString().equals("ADMIN")) {
            if (drinkService.findByName(drinkRegisterModel.getDrinkName()).isPresent()) {
                return new ResponseEntity<>(DrinkResponse.ALREADY_EXISTS.toString(), HttpStatus.BAD_REQUEST);
            }

            try {
                Drink drink = new Drink(drinkRegisterModel.getDrinkName(), drinkRegisterModel.getHasSugar(), drinkRegisterModel.getHasMilk());
                return ok(this.drinkService.createOrUpdate(drink));
            } catch (Exception ex) {
                return new ResponseEntity<>(DrinkResponse.ERROR.toString(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(AuthResponse.NO_PREMISSIONS.toString(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteDrink(@AuthenticationPrincipal User user, @Valid @PathVariable String id, HttpServletRequest request) {
        if (user.getRole().toString().equals("ADMIN")) {
            Optional<Drink> drink = this.drinkService.findById(UUID.fromString(id));

            if (drink.isPresent()) {
                try {
                    this.drinkService.delete(drink.get());
                    return new ResponseEntity<>(DrinkResponse.SUCCESSFULLY_DELETED.toString(), HttpStatus.OK);
                } catch (Exception ex) {
                    return new ResponseEntity<>(DrinkResponse.ERROR.toString(), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(DrinkResponse.NO_DRINK.toString(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(AuthResponse.NO_PREMISSIONS.toString(), HttpStatus.BAD_REQUEST);
    }
}
