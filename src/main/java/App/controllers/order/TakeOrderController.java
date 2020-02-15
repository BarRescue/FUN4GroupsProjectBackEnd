package App.controllers.order;

import App.controllers.enums.DrinkResponse;
import App.entity.TakeOrder;
import App.entity.User;
import App.logic.OrderLogic;
import App.models.order.TakeOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@RequestMapping(value = "/complete")
public class TakeOrderController {
    private final OrderLogic orderLogic;

    @Autowired
    public TakeOrderController(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @PostMapping
    public ResponseEntity finishOrder(@AuthenticationPrincipal User user, @Valid @RequestBody TakeOrderModel takeOrderModel) {
        try {
            List<TakeOrder> ordersDone = this.orderLogic.finishOrder(user, takeOrderModel.getOrders());

            if(ordersDone.isEmpty()) {
                return new ResponseEntity<>(DrinkResponse.ERROR.toString(), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(ordersDone, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(DrinkResponse.ERROR.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}