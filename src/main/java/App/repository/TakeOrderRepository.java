package App.repository;

import App.entity.TakeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TakeOrderRepository extends JpaRepository<TakeOrder, UUID> {
}
