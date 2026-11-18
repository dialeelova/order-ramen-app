import java.util.List;
import java.util.function.Supplier;

public class RamenFactory {
    private final List<Supplier<Ramen>> options = List.of(
            CheeseRamen::new,
            ChickenRamen::new,
            CarbonaraRamen::new
    );

    public int getOptionCount() {
        return options.size();
    }

    public boolean isValidChoice(int choice) {
        return choice >= 1 && choice <= options.size();
    }

    public Ramen createRamen(int choice) {
        if (!isValidChoice(choice)) {
            throw new IllegalArgumentException("Unknown ramen choice: " + choice);
        }
        return options.get(choice - 1).get();
    }
}
