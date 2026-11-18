import java.util.List;

public interface RamenItem {
    String getDescription();

    double getCost();

    /** Returns the toppings on this item, in the order they were added. */
    List<ToppingDecorator> getToppings();
}
