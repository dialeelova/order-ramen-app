public interface PaymentMethod {
    /**
     * Imitates a payment of the given amount.
     * @return true if the payment succeeded
     */
    boolean pay(double amount);
}
