public abstract class Ramen implements RamenItem {
    private String name;
    private double basePrice;

    public Ramen(String name, double basePrice) {
        setName(name);
        setBasePrice(basePrice);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            System.out.println("Ramen name cannot be empty");
        }
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice > 0) {
            this.basePrice = basePrice;
        } else {
            System.out.println("Price must be positive");
        }
    }

    @Override
    public double getCost() {
        return basePrice;
    }

    @Override
    public abstract String getDescription();
}
