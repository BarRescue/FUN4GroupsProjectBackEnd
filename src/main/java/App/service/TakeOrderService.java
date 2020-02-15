package App.service;

import App.entity.TakeOrder;
import App.repository.TakeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TakeOrderService {
    private final TakeOrderRepository takeOrderRepository;

    @Autowired
    public TakeOrderService(TakeOrderRepository takeOrderRepository) {
        this.takeOrderRepository = takeOrderRepository;
    }

    public TakeOrder createOrUpdate(TakeOrder takeOrder) {
        return this.takeOrderRepository.save(takeOrder);
    }
}
