public class Size {
    private String name;
    private double extraPrice;

    public Size(String name, double extraPrice) {
        setName(name);
        setExtraPrice(extraPrice);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            System.out.println("Size name cannot be empty");
        }
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        if (extraPrice >= 0) {
            this.extraPrice = extraPrice;
        } else {
            System.out.println("Extra price cannot be negative");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}