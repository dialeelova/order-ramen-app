public class OrderNotification extends Notification {
    private double total;

    public OrderNotification(String message, double total) {
        super(message);
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public void send() {
        System.out.println(getMessage());
        if (total > 0) {
            System.out.println("Total: " + total);
        }
    }
}
