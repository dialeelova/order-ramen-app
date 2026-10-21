public class Notifier {
    public void notifyBowlChanged(Order order) {
        System.out.println("Updated: " + order.getDescription());
        System.out.println("Price: " + round(order.getTotalCost()));
    }

    public void notifyUndo(Order order) {
        System.out.println("Last change was undone.");
        System.out.println("Now: " + order.getDescription());
        System.out.println("Price: " + round(order.getTotalCost()));
    }

    public void notifyNothingToUndo() {
        System.out.println("There is nothing to undo.");
    }

    public void notifyToppingNotAllowed(String toppingName) {
        System.out.println(toppingName + " cannot be added - it does not go with the toppings you chose.");
    }

    public void notifyOrderPlaced(Order order) {
        System.out.println("Your order has been placed: " + order.getDescription());
        System.out.println("Total paid: " + round(order.getTotalCost()));
    }

    public void notifyOrderCancelled() {
        System.out.println("Your order has been cancelled.");
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}