public class Notifier {
    public void notifyBowlChanged(Order order) {
        Notification notification = new UpdateNotification(order.getDescription(), round(order.getTotalCost()));
        notification.send();
    }

    public void notifyUndo(Order order) {
        Notification notification = new UpdateNotification("Last change was undone - " + order.getDescription(), round(order.getTotalCost()));
        notification.send();
    }

    public void notifyNothingToUndo() {
        System.out.println("There is nothing to undo.");
    }

    public void notifyToppingNotAllowed(String toppingName) {
        System.out.println(toppingName + " cannot be added - it does not go with the toppings you chose.");
    }

    public void notifyOrderPlaced(Order order) {
        Notification notification = new OrderNotification("Your order has been placed: " + order.getDescription(), round(order.getTotalCost()));
        notification.send();
    }

    public void notifyOrderCancelled() {
        Notification notification = new OrderNotification("Your order has been cancelled.", 0);
        notification.send();
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}