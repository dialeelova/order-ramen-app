public class SausageTopping extends ToppingDecorator {
    public SausageTopping(RamenItem ramen) {
        super(ramen, "Sausage", 2.00);
    }
}
