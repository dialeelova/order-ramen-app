public class CashPayment implements PaymentMethod {
    private final double amountGiven;

    public CashPayment(double amountGiven) {
        if (amountGiven < 0) {
            throw new IllegalArgumentException("Cash amount cannot be negative");
        }
        this.amountGiven = amountGiven;
    }

    public double getAmountGiven() {
        return amountGiven;
    }

    @Override
    public boolean pay(double amount) {
        long totalCents = toCents(amount);
        long givenCents = toCents(amountGiven);
        if (givenCents < totalCents) {
            System.out.println("Sorry, " + PriceFormatter.format(amountGiven) + " is not enough. The total is "
                    + PriceFormatter.format(amount) + ".");
            return false;
        }
        System.out.println("Paid " + PriceFormatter.format(amountGiven) + " in cash. Your change is "
                + PriceFormatter.format((givenCents - totalCents) / 100.0) + ".");
        return true;
    }

    private long toCents(double value) {
        return Math.round(value * 100.0);
    }
}
