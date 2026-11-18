import java.util.List;
import java.util.function.Function;

public class ToppingFactory {
    private final List<Function<RamenItem, ToppingDecorator>> options = List.of(
            EggTopping::new,
            ExtraCheeseTopping::new,
            SausageTopping::new,
            NoriTopping::new,
            MildSauceTopping::new,
            SpicySauceTopping::new
    );

    public int getOptionCount() {
        return options.size();
    }

    public boolean isValidChoice(int choice) {
        return choice >= 1 && choice <= options.size();
    }

    public ToppingDecorator createTopping(int choice, RamenItem bowl) {
        if (!isValidChoice(choice)) {
            throw new IllegalArgumentException("Unknown topping choice: " + choice);
        }
        return options.get(choice - 1).apply(bowl);
    }
}
