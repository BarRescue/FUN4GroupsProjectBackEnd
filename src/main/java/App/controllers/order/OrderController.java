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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderLogic orderLogic;

    @Autowired
    public OrderController(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> orders = this.orderLogic.findAll(false);

        if(orders.isEmpty()) {
            return new ResponseEntity<>(OrderResponse.NO_ORDERS.toString(), HttpStatus.BAD_REQUEST);
        }

        return ok(orders);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity getOrder(@Valid @PathVariable String id) {
        Optional<Order> order = this.orderLogic.findById(UUID.fromString(id));

        if(!order.isPresent()) {
            return new ResponseEntity<>(OrderResponse.NO_ORDER.toString(), HttpStatus.BAD_REQUEST);
        }

        return ok(order);
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
