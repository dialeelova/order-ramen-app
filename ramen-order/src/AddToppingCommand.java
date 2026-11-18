public class AddToppingCommand implements Command {
    private final Order order;
    private final ToppingDecorator topping;
    private RamenItem previousBowl;

    public AddToppingCommand(Order order, ToppingDecorator topping) {
        if (order == null || topping == null) {
            throw new IllegalArgumentException("Order and topping are required");
        }
        this.order = order;
        this.topping = topping;
    }

    @Override
    public boolean execute() {
        if (!order.canAddTopping(topping)) {
            return false;
        }
        previousBowl = order.getBowl();
        order.setBowl(topping);
        return true;
    }

    @Override
    public void undo() {
        if (previousBowl != null) {
            order.setBowl(previousBowl);
        }
    }
}
