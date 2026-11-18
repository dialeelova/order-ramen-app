public class UpdateNotification extends Notification {
    private final double price;

    public UpdateNotification(String message, double price) {
        super(message);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void send() {
        System.out.println(getMessage());
        System.out.println("Current price: " + PriceFormatter.format(price));
    }
}
