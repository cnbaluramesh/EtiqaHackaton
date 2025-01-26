package my.etiqa.customer.demo.repository;

import my.etiqa.customer.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
