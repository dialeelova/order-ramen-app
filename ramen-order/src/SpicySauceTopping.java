public class SpicySauceTopping extends ToppingDecorator {
    public SpicySauceTopping(RamenItem ramen) {
        super(ramen, "Spicy sauce", 0.50);
    }
}
