package ba.wave.wavebackend.repository;

import ba.wave.wavebackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
