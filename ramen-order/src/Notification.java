public abstract class Notification {
    private final String message;

    protected Notification(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Notification message cannot be empty");
        }
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public abstract void send();
}
