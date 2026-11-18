public class Notifier {
    public void notifyRamenChosen(Order order) {
        send(new UpdateNotification("You chose " + order.getDescription() + ".", order.getTotalCost()));
    }

    public void notifyBowlChanged(Order order) {
        send(new UpdateNotification("Your order is now: " + order.getDescription() + ".", order.getTotalCost()));
    }

    public void notifyUndo(Order order) {
        send(new UpdateNotification("Your last change was undone. Your order is now: "
                + order.getDescription() + ".", order.getTotalCost()));
    }

    public void notifyNothingToUndo() {
        send(new WarningNotification("there is nothing to undo."));
    }

    public void notifyToppingAlreadyAdded(String toppingName) {
        send(new WarningNotification(toppingName.toLowerCase() + " is already in your bowl."));
    }

    public void notifySauceConflict(String toppingName) {
        send(new WarningNotification(toppingName.toLowerCase()
                + " cannot be added because your bowl already has a sauce."));
    }

    public void notifySizeUnchanged(Size size) {
        send(new WarningNotification("your bowl is already " + size.getName().toLowerCase() + "."));
    }

    public void notifyOrderPlaced(Order order) {
        send(new OrderNotification("Your order has been placed: " + order.getDescription() + ".",
                order.getTotalCost()));
    }

    public void notifyOrderCancelled() {
        send(new OrderNotification("Your order has been cancelled.", 0));
    }

    private void send(Notification notification) {
        notification.send();
    }
}
