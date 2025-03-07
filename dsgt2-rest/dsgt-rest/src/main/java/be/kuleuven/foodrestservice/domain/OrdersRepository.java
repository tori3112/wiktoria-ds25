package be.kuleuven.foodrestservice.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class OrdersRepository {
    private static final Map<String, Order> orders = new HashMap<>();

    public Optional<Order> findOrder(String id) {
        Assert.notNull(id, "The meal id must not be null");
        Order order = orders.get(id);
        return Optional.ofNullable(order);
    }

    public Collection<Order> getAllOrders() {
        return orders.values();
    }

    public Order create(Order order) {
        if (order.getId() == null) {
            order.setId(UUID.randomUUID().toString());
        }
        if (order.getOrderTime() == null) {
            order.setOrderTime(LocalDateTime.now());
        }
        orders.put(order.getId(), order);
        return order;
    }

}
