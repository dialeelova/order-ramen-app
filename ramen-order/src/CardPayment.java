public class CardPayment implements PaymentMethod {
    private static final int CARD_NUMBER_LENGTH = 16;

    private final String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber == null ? "" : cardNumber.replace(" ", "");
    }

    public String getLastFourDigits() {
        if (cardNumber.length() < 4) {
            return cardNumber;
        }
        return cardNumber.substring(cardNumber.length() - 4);
    }

    @Override
    public boolean pay(double amount) {
        if (!isValidCardNumber()) {
            System.out.println("Sorry, the card number must contain exactly " + CARD_NUMBER_LENGTH + " digits.");
            return false;
        }
        System.out.println("Paid " + PriceFormatter.format(amount) + " by card ending in " + getLastFourDigits() + ".");
        return true;
    }

    private boolean isValidCardNumber() {
        if (cardNumber.length() != CARD_NUMBER_LENGTH) {
            return false;
        }
        for (int i = 0; i < cardNumber.length(); i++) {
            if (!Character.isDigit(cardNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
