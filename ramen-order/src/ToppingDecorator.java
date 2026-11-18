import java.util.ArrayList;
import java.util.List;

public abstract class ToppingDecorator implements RamenItem {
    private final RamenItem ramen;
    private final String toppingName;
    private final double price;

    protected ToppingDecorator(RamenItem ramen, String toppingName, double price) {
        if (ramen == null) {
            throw new IllegalArgumentException("A topping must be added to a ramen");
        }
        if (toppingName == null || toppingName.trim().isEmpty()) {
            throw new IllegalArgumentException("Topping name cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Topping price cannot be negative");
        }
        this.ramen = ramen;
        this.toppingName = toppingName.trim();
        this.price = price;
    }

    public RamenItem getRamen() {
        return ramen;
    }

    public String getToppingName() {
        return toppingName;
    }

    public double getPrice() {
        return price;
    }

    /** Sauces are mutually exclusive: only one sauce is allowed per bowl. */
    public boolean isSauce() {
        return false;
    }

    @Override
    public String getDescription() {
        return ramen.getDescription() + " + " + toppingName;
    }

    @Override
    public double getCost() {
        return ramen.getCost() + price;
    }

    @Override
    public List<ToppingDecorator> getToppings() {
        List<ToppingDecorator> toppings = new ArrayList<>(ramen.getToppings());
        toppings.add(this);
        return toppings;
    }
}
