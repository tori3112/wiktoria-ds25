package be.kuleuven.foodrestservice.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private String id;
    private String address;
    private List<String> meals;
    private LocalDateTime orderTime;

    public Order() {
    }

    public Order(String id, String address, List<String> meals) {
        this.id = id;
        this.address = address;
        this.meals = meals;
        this.orderTime = LocalDateTime.now();
    }

    public Order(String address, List<String> meals) {
        this.id = UUID.randomUUID().toString();
        this.address = address;
        this.meals = meals;
        this.orderTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getMeals() {
        return meals;
    }

    public void setMeals(List<String> meals) {
        this.meals = meals;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(address, order.address) &&
                Objects.equals(meals, order.meals) &&
                Objects.equals(orderTime, order.orderTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, meals, orderTime);
    }

}
