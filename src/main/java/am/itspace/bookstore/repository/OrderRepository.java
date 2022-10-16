package am.itspace.bookstore.repository;

import am.itspace.bookstore.entity.Order;
import am.itspace.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUser(User user);

}
