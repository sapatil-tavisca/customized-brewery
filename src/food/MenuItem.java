package food;

import java.util.HashSet;
import java.util.Set;

public class MenuItem {

    private Set<Ingredient> mandatoryIngredients;

    private Set<Ingredient> customizableIngredients;

    private Set<Ingredient> finalSetOfIngredients = new HashSet<>();

    private Double price;

    public MenuItem(Set<Ingredient> mandatoryIngredients, Set<Ingredient> customizableIngredients, Double price) {
        this.mandatoryIngredients = mandatoryIngredients;
        this.customizableIngredients = customizableIngredients;
        this.price = price;
    }

    public Set<Ingredient> getCustomizableIngredients() {
        return customizableIngredients;
    }

    public Double getPrice() {
        return price;
    }

    public Set<Ingredient> getMandatoryIngredients() {
        return mandatoryIngredients;
    }


    public Set<Ingredient> getFinalSetOfIngredients() {
        finalSetOfIngredients.addAll(mandatoryIngredients);
        finalSetOfIngredients.addAll(customizableIngredients);
        return finalSetOfIngredients;
    }
}


