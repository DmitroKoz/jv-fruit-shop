package core.basesyntax.model;

public class FruitCrate {
    private final String name;
    private int quantity;

    public FruitCrate(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
}