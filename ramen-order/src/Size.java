public enum Size {
    SMALL("Small", 0.00),
    MEDIUM("Medium", 1.00),
    LARGE("Large", 2.00);

    private final String name;
    private final double extraPrice;

    Size(String name, double extraPrice) {
        this.name = name;
        this.extraPrice = extraPrice;
    }

    public String getName() {
        return name;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    @Override
    public String toString() {
        return name;
    }
}
