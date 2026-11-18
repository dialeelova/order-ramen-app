public class WarningNotification extends Notification {
    public WarningNotification(String message) {
        super(message);
    }

    @Override
    public void send() {
        System.out.println("Sorry, " + getMessage());
    }
}
