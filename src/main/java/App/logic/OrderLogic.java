package App.logic;

import App.entity.*;
import App.entity.enums.Size;
import App.models.drink.DrinkDTO;
import App.models.order.OrderDTO;
import App.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderLogic {
    private final OrderService orderService;
    private final RoomService roomService;
    private final DepartmentService departmentService;
    private final DrinkService drinkService;
    private final TakeOrderService takeOrderService;

    @Autowired
    public OrderLogic(OrderService orderService, RoomService roomService, DepartmentService departmentService, DrinkService drinkService, TakeOrderService takeOrderService) {
        this.orderService = orderService;
        this.roomService = roomService;
        this.departmentService = departmentService;
        this.drinkService = drinkService;
        this.takeOrderService = takeOrderService;
    }

    public List<Order> findAll(Boolean done) {
        return this.orderService.findAllByOrderDone(done);
    }

    public Optional<Order> findById(UUID id) {
        return this.orderService.findById(id);
    }

    public Order createOrder(User user, String roomId, String departmentId, List<DrinkDTO> drinks) {
        Room room = this.getRoom(roomId);
        Department department = this.getDepartment(departmentId);

        if(user != null && room != null && department != null && !drinks.isEmpty()) {
            Order order = new Order(user, department, room);

            for (DrinkDTO drink : drinks) {
                Drink d = getDrink(drink.drinkId);

                if(d != null) {
                    order.addDrink(d, drink.sugar, drink.milk, drink.strength, Size.valueOf(drink.size));
                } else {
                    return null;
                }
            }

            return this.orderService.createOrUpdate(order);
        }

        return null;
    }

    public List<TakeOrder> finishOrder(User user, List<OrderDTO> orders) {
        List<TakeOrder> ordersDone = new ArrayList<>();

        for(OrderDTO order : orders) {
            Optional<Order> o = this.orderService.findById(UUID.fromString(order.getOrderId()));

            if(o.isPresent()) {
                TakeOrder orderDone = new TakeOrder(user, o.get());
                this.takeOrderService.createOrUpdate(orderDone);

                ordersDone.add(orderDone);

                o.get().setOrderDone(true);
                this.orderService.createOrUpdate(o.get());
            }
        }

        return ordersDone;
    }

    private Room getRoom(String roomId) {
        return this.roomService.findById(UUID.fromString(roomId)).orElse(null);
    }

    private Department getDepartment(String departmentId) {
        return this.departmentService.findById(UUID.fromString(departmentId)).orElse(null);
    }

    private Drink getDrink(String drinkId) {
        return this.drinkService.findById(UUID.fromString(drinkId)).orElse(null);
    }
}
