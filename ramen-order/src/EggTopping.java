public class EggTopping extends ToppingDecorator {
    public EggTopping(RamenItem ramen) {
        super(ramen, "Egg", 1.50);
    }
}
