import java.util.List;

public class Order {
    private final Ramen baseRamen;
    private RamenItem bowl;
    private Size size;

    public Order(Ramen baseRamen) {
        if (baseRamen == null) {
            throw new IllegalArgumentException("Ramen cannot be empty");
        }
        this.baseRamen = baseRamen;
        this.bowl = baseRamen;
        this.size = Size.SMALL;
    }

    public Ramen getBaseRamen() {
        return baseRamen;
    }

    public RamenItem getBowl() {
        return bowl;
    }

    public void setBowl(RamenItem bowl) {
        if (bowl == null) {
            throw new IllegalArgumentException("Bowl cannot be empty");
        }
        this.bowl = bowl;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        if (size == null) {
            throw new IllegalArgumentException("Size cannot be empty");
        }
        this.size = size;
    }

    public List<ToppingDecorator> getToppings() {
        return bowl.getToppings();
    }

    public boolean isAlreadyAdded(ToppingDecorator topping) {
        for (ToppingDecorator added : getToppings()) {
            if (added.getToppingName().equals(topping.getToppingName())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSauceConflict(ToppingDecorator topping) {
        if (!topping.isSauce()) {
            return false;
        }
        for (ToppingDecorator added : getToppings()) {
            if (added.isSauce()) {
                return true;
            }
        }
        return false;
    }

    public boolean canAddTopping(ToppingDecorator topping) {
        return topping != null && !isAlreadyAdded(topping) && !hasSauceConflict(topping);
    }

    public double getTotalCost() {
        return bowl.getCost() + size.getExtraPrice();
    }

    public String getDescription() {
        return bowl.getDescription() + " (" + size.getName() + ")";
    }
}
