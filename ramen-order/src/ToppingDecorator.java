public abstract class ToppingDecorator implements RamenItem {
    private RamenItem ramen;
    private String toppingName;
    private double price;

    public ToppingDecorator(RamenItem ramen, String toppingName, double price) {
        this.ramen = ramen;
        this.toppingName = toppingName;
        this.price = price;
    }

    public RamenItem getRamen() {
        return ramen;
    }

    public String getToppingName() {
        return toppingName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return ramen.getDescription() + " + " + toppingName;
    }

    @Override
    public double getCost() {
        return ramen.getCost() + price;
    }
}
