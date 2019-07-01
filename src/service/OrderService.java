package service;

import food.Ingredient;
import food.MenuItem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderService {

    private Map<String, MenuItem> menu;

    public OrderService(Map<String, MenuItem> menu) {
        this.menu = menu;
    }

    public double getFinalPrice(String... orders) {
        return Arrays.stream(orders)
                .map(order -> {
                    String[] orderItems = order.split("\\s*,\\s*");
                    MenuItem menuItem = menu.get(orderItems[0]);
                    if (menuItem == null) {
                        throw new RuntimeException("Not a Menu!");
                    }
                    Double finalPrice = menuItem.getPrice();
                    Set<Ingredient> mandatoryIngredients = menuItem.getMandatoryIngredients();
                    Set<Ingredient> customizableIngredients = menuItem.getCustomizableIngredients();
                    Set<Ingredient> finalSetOfIngredients = menuItem.getFinalSetOfIngredients();

                    Set<Ingredient> requestedCustomizableIngredients = Arrays
                            .stream(orderItems)
                            .filter(item -> item.startsWith("-"))
                            .filter(item -> item.length() > 1)
                            .map(item -> Ingredient.fromString(item.substring(1)))
                            .collect(Collectors.toSet());

                    if(requestedCustomizableIngredients.containsAll(mandatoryIngredients)) {
                        throw new RuntimeException("Cannot exclude liquid ingredient from the drink!");
                    }

                    if(customizableIngredients.size() > 1 && requestedCustomizableIngredients.containsAll(customizableIngredients)) {
                        throw new RuntimeException("Atleast one customizable Ingredient is mandatory for the drink!");
                    }

                    return IntStream.range(1, orderItems.length)
                            .filter(i -> orderItems[i].length() > 1)
                            .mapToObj(i -> Ingredient.fromString(orderItems[i].substring(1)))
                            .filter(Objects::nonNull)
                            .filter(finalSetOfIngredients::contains)
                            .distinct()
                            .mapToDouble(Ingredient::getPrice)
                            .reduce(finalPrice, (p1, p2) -> p1 - p2);
                }).reduce(0.0, Double::sum);
    }
}
