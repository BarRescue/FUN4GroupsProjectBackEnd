package App.models.order;

import App.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class TakeOrderModel {
    private List<OrderDTO> orders;
}
