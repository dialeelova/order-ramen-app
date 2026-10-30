public class UpdateNotification extends Notification {
    private double price;

    public UpdateNotification(String message, double price) {
        super(message);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void send() {
        System.out.println("Updated: " + getMessage());
        System.out.println("Price: " + price);
    }
}
