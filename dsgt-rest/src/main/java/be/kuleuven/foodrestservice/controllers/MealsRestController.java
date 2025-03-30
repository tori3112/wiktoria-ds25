package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.Meal;
import be.kuleuven.foodrestservice.domain.MealsRepository;
import be.kuleuven.foodrestservice.domain.Order;
import be.kuleuven.foodrestservice.domain.OrdersRepository;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class MealsRestController {

    private final MealsRepository mealsRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    MealsRestController(MealsRepository mealsRepository, OrdersRepository ordersRepository) {
        this.mealsRepository = mealsRepository;
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/rest/meals/{id}")
    EntityModel<Meal> getMealById(@PathVariable String id) {
        Meal meal = mealsRepository.findMeal(id).orElseThrow(() -> new MealNotFoundException(id));

        return mealToEntityModel(id, meal);
    }

    @GetMapping("/rest/meals")
    CollectionModel<EntityModel<Meal>> getMeals() {
        Collection<Meal> meals = mealsRepository.getAllMeal();

        List<EntityModel<Meal>> mealEntityModels = new ArrayList<>();
        for (Meal m : meals) {
            EntityModel<Meal> em = mealToEntityModel(m.getId(), m);
            mealEntityModels.add(em);
        }
        return CollectionModel.of(mealEntityModels,
                linkTo(methodOn(MealsRestController.class).getMeals()).withSelfRel());
    }

    @GetMapping("/rest/orders/{id}")
    EntityModel<Order> getOrderById(@PathVariable String id) {
        Order order = ordersRepository.findOrder(id).orElseThrow(() -> new MealNotFoundException(id));

        return orderToEntityModel(id, order);
    }

    @GetMapping("/rest/orders")
    CollectionModel<EntityModel<Order>> getOrders() {
        Collection<Order> orders = ordersRepository.getAllOrders();

        List<EntityModel<Order>> orderEntityModels = new ArrayList<>();
        for (Order o : orders) {
            EntityModel<Order> em = orderToEntityModel(o.getId(), o);
            orderEntityModels.add(em);
        }
        return CollectionModel.of(orderEntityModels,
                linkTo(methodOn(MealsRestController.class).getMeals()).withSelfRel());
    }

    private EntityModel<Meal> mealToEntityModel(String id, Meal meal) {
        return EntityModel.of(meal,
                linkTo(methodOn(MealsRestController.class).getMealById(id)).withSelfRel(),
                linkTo(methodOn(MealsRestController.class).getMeals()).withRel("rest/meals"));
    }

    private EntityModel<Order> orderToEntityModel(String id, Order order) {
        return EntityModel.of(order,
                linkTo(methodOn(MealsRestController.class).getOrderById(id)).withSelfRel(),
                linkTo(methodOn(MealsRestController.class).getOrders()).withRel("rest/meals"));
    }

    @GetMapping("/rest/cheapest")
    EntityModel<Meal> getCheapestMeal() {
        Meal meal = mealsRepository.getCheapestMeal()
                .orElseThrow(() -> new RuntimeException("No Meals Available!"));

        return EntityModel.of(meal,
                linkTo(methodOn(MealsRestController.class).getCheapestMeal()).withSelfRel());
//        return mealToEntityModel(meal.getId(), meal);
    }

    @GetMapping("/rest/largest")
    EntityModel<Meal> getLargestMeal() {
        Meal meal = mealsRepository.getLargestMeal()
                .orElseThrow(() -> new RuntimeException("No Meals Available!"));

        return EntityModel.of(meal,
                linkTo(methodOn(MealsRestController.class).getLargestMeal()).withSelfRel());
//        return mealToEntityModel(meal.getId(), meal);
    }

    @PostMapping("/rest/meals")
    EntityModel<Meal> addMeal(@RequestBody Meal meal) {
        Meal new_meal = mealsRepository.create(meal);

        return EntityModel.of(new_meal,
                linkTo(methodOn(MealsRestController.class).addMeal(meal)).withSelfRel());
    }

    @PutMapping("/rest/meals/{id}")
    EntityModel<Meal> updateMeal(@PathVariable String id, @RequestBody Meal meal) {
        mealsRepository.findMeal(id)
                .orElseThrow( () -> new MealNotFoundException(id));

        meal.setId(id);
        Meal updated_meal = mealsRepository.create(meal);
        return EntityModel.of(updated_meal,
                linkTo(methodOn(MealsRestController.class).addMeal(meal)).withSelfRel());
    }

    @DeleteMapping("/rest/meals/{id}")
    void deleteMeal(@PathVariable String id) {
        Meal meal = mealsRepository.findMeal(id)
                .orElseThrow( () -> new MealNotFoundException(id));

        mealsRepository.delete(meal);
    }

    @PostMapping("/rest/orders")
    EntityModel<Order> addOrder(@RequestBody Order order) {
        Order new_order = ordersRepository.create(order);

        return EntityModel.of(new_order,
                linkTo(methodOn(MealsRestController.class).addOrder(order)).withSelfRel());
    }


}
