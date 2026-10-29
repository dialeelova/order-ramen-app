public class AddToppingCommand implements Command {
    private Order order;
    private RamenItem previousBowl;
    private ToppingDecorator topping;

    public AddToppingCommand(Order order, int toppingChoice) {
        this.order = order;
        this.previousBowl = order.getBowl();
        this.topping = createTopping(toppingChoice);
    }

    private ToppingDecorator createTopping(int toppingChoice) {
        if (toppingChoice == 1) {
            return new EggTopping(previousBowl);
        } else if (toppingChoice == 2) {
            return new ExtraCheeseTopping(previousBowl);
        } else if (toppingChoice == 3) {
            return new SausageTopping(previousBowl);
        } else if (toppingChoice == 4) {
            return new NoriTopping(previousBowl);
        } else if (toppingChoice == 5) {
            return new MildSauceTopping(previousBowl);
        } else if (toppingChoice == 6) {
            return new SpicySauceTopping(previousBowl);
        }
        return null;
    }

    public String getToppingName() {
        if (topping == null) {
            return "Unknown topping";
        }
        return topping.getToppingName();
    }

    public boolean isAllowed() {
        if (topping == null) {
            return false;
        }
        return order.canAddTopping(topping.getToppingName());
    }

    @Override
    public void execute() {
        if (isAllowed()) {
            order.setBowl(topping);
            order.addToppingName(topping.getToppingName());
        }
    }

    @Override
    public void undo() {
        order.setBowl(previousBowl);
        order.removeLastToppingName();
    }
}
