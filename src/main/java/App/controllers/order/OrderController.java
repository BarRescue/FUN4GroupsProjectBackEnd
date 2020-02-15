package App.controllers.order;

import App.controllers.enums.OrderResponse;
import App.controllers.enums.RoomResponse;
import App.entity.*;
import App.entity.enums.Size;
import App.jwt.TokenProvider;
import App.logic.OrderLogic;
import App.models.drink.DrinkDTO;
import App.models.order.OrderModel;
import App.service.DepartmentService;
import App.service.DrinkService;
import App.service.OrderService;
import App.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderLogic orderLogic;

    @Autowired
    public OrderController(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @PostMapping
    public ResponseEntity createOrder(@AuthenticationPrincipal User user, @Valid @RequestBody OrderModel orderModel) {
        try {
            // Check if order has been made
            Order order = this.orderLogic.createOrder(user, orderModel.getRoomId(), orderModel.getDepartmentId(), orderModel.getDrinks());

            // If order isnt created, return error
            if(order == null) {
                return new ResponseEntity<>(OrderResponse.ERROR.toString(), HttpStatus.BAD_REQUEST);
            }

            // Return order
            return ok(order);

        } catch(Exception ex) {
            return new ResponseEntity<>(OrderResponse.ERROR.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
