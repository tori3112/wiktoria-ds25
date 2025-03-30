package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.Meal;
import be.kuleuven.foodrestservice.domain.MealsRepository;
import be.kuleuven.foodrestservice.domain.Order;
import be.kuleuven.foodrestservice.domain.OrdersRepository;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import be.kuleuven.foodrestservice.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class MealsRestRpcStyleController {

    private final MealsRepository mealsRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    MealsRestRpcStyleController(MealsRepository mealsRepository, OrdersRepository ordersRepository) {
        this.mealsRepository = mealsRepository;
        this.ordersRepository = ordersRepository;
    }

    @PostMapping("/test")
    public Map<String, Object> test(@RequestBody Map<String, Object> body) {
        return body;  // Just echo back the request body
    }

    @GetMapping("/restrpc/meals/{id}")
    Meal getMealById(@PathVariable String id) {
        Optional<Meal> meal = mealsRepository.findMeal(id);

        return meal.orElseThrow(() -> new MealNotFoundException(id));
    }

    @GetMapping("/restrpc/meals")
    Collection<Meal> getMeals() {
        return mealsRepository.getAllMeal();
    }

    @GetMapping("/restrpc/orders/{id}")
    Order getOrderById(@PathVariable String id) {
        Optional<Order> order = ordersRepository.findOrder(id);

        return order.orElseThrow(() -> new OrderNotFoundException(id));
    }

    @GetMapping("/restrpc/orders")
    Collection<Order> getOrders() {
        return ordersRepository.getAllOrders();
    }

    @GetMapping("/restrpc/meals/cheapest")
    Meal getCheapestMeal() {
        return mealsRepository.getCheapestMeal()
                .orElseThrow(() -> new RuntimeException("No Meals Available!"));
    }

    @GetMapping("/restrpc/meals/largest")
    Meal getLargestMeal() {
        return mealsRepository.getLargestMeal()
                .orElseThrow(() -> new RuntimeException("No Meals Available!"));
    }

    @PostMapping("/restrpc/meals")
    @ResponseStatus(HttpStatus.CREATED)
    Meal addMeal(@RequestBody Meal meal) {
        return mealsRepository.create(meal);
    }

    @PutMapping("/restrpc/meals/{id}")
    Meal updateMeal(@PathVariable String id, @RequestBody Meal updatedMeal) {
        return mealsRepository.findMeal(id)
                .map(meal -> {
                    updatedMeal.setId(id);
                    return mealsRepository.create(updatedMeal);
                })
                .orElseThrow(() -> new MealNotFoundException(id));
    }

    @DeleteMapping("/restrpc/meals/{id}")
    void deleteMeal(@PathVariable String id) {
        Meal meal = mealsRepository.findMeal(id)
                .orElseThrow(() -> new MealNotFoundException(id));
        mealsRepository.delete(meal);
    }


    @PostMapping("/restrpc/orders")
    @ResponseStatus(HttpStatus.CREATED)
    Order createOrder(@RequestBody Order order) {
        return ordersRepository.create(order);
    }
}
