public class MildSauceTopping extends ToppingDecorator {
    public MildSauceTopping(RamenItem ramen) {
        super(ramen, "Mild sauce", 0.50);
    }
}
