public class ExtraCheeseTopping extends ToppingDecorator {
    public ExtraCheeseTopping(RamenItem ramen) {
        super(ramen, "Extra cheese", 1.00);
    }
}
