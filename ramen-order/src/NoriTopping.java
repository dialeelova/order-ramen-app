public class NoriTopping extends ToppingDecorator {
    public NoriTopping(RamenItem ramen) {
        super(ramen, "Nori", 0.80);
    }
}
