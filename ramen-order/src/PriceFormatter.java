import java.util.Locale;

public final class PriceFormatter {
    private PriceFormatter() {
    }

    public static String format(double amount) {
        return String.format(Locale.US, "$%.2f", amount);
    }
}
