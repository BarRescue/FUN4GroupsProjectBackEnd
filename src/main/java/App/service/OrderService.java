package App.service;

import App.entity.Order;
import App.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrUpdate(Order order) {
        return this.orderRepository.save(order);
    }

    public Optional<Order> findById(UUID orderId) {
        return this.orderRepository.findById(orderId);
    }

    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    public List<Order> findAllByOrderDone(Boolean done) {
        return this.orderRepository.findAllByOrderDone(done);
    }
}
