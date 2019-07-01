package food;

public enum Ingredient {
    MILK("milk", 1.0),
    SUGAR("sugar", 0.5),
    SODA("soda", 0.5),
    MINT("mint", 0.5),
    WATER("water", 0.5);

    private String name;
    private Double price;

    Ingredient(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public static Ingredient fromString(String text) {
        for (Ingredient b : Ingredient.values()) {
            if (b.name.equals(text)) {
                return b;
            }
        }
        return null;
    }


    public Double getPrice() {
        return this.price;
    }
}
