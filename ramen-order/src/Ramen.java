import java.util.ArrayList;
import java.util.List;

public abstract class Ramen implements RamenItem {
    private String name;
    private double basePrice;

    protected Ramen(String name, double basePrice) {
        setName(name);
        setBasePrice(basePrice);
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ramen name cannot be empty");
        }
        this.name = name.trim();
    }

    public double getBasePrice() {
        return basePrice;
    }

    public final void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Ramen price must be positive");
        }
        this.basePrice = basePrice;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getCost() {
        return basePrice;
    }

    @Override
    public List<ToppingDecorator> getToppings() {
        return new ArrayList<>();
    }
}
