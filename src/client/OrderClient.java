package client;

import food.MenuItem;
import service.OrderService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static food.Ingredient.*;

public class OrderClient {

    public static void main(String[] args) {

        Map<String, MenuItem> menuItemMap = new HashMap<>();
        menuItemMap.putIfAbsent("Coffee Latte",
                new MenuItem(
                        Set.of(MILK, WATER),
                        Set.of(SUGAR),
                        5.0)
        );
        menuItemMap.putIfAbsent("Chai Latte",
                new MenuItem(
                        Set.of(MILK, WATER),
                        Set.of(SUGAR),
                        4.0)
        );
        menuItemMap.putIfAbsent("Banana Smoothie",
                new MenuItem(
                        Set.of(MILK, WATER),
                        Set.of(SUGAR),
                        6.0)
        );
        menuItemMap.putIfAbsent("Strawberry Shake",
                new MenuItem(
                        Set.of(MILK, WATER),
                        Set.of(SUGAR),
                        7.0)
        );
        menuItemMap.putIfAbsent("Mojito",
                new MenuItem(
                        Set.of(WATER, SODA),
                        Set.of(SUGAR, MINT),
                        7.5)
        );

        OrderService orderService = new OrderService(menuItemMap);
        System.out.println("Total Bill: " + orderService.getFinalPrice("Strawberry Shake, -water, - , -sugar", "Mojito, -sugar, -soda"));
    }
}
