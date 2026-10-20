import java.util.ArrayList;
import java.util.List;

public class Order {
    private RamenItem bowl;
    private Size size;
    private List<String> toppingNames;

    public Order(RamenItem bowl) {
        setBowl(bowl);
        this.toppingNames = new ArrayList<>();
    }

    public RamenItem getBowl() {
        return bowl;
    }

    public void setBowl(RamenItem bowl) {
        if (bowl != null) {
            this.bowl = bowl;
        } else {
            System.out.println("Bowl cannot be empty");
        }
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<String> getToppingNames() {
        return new ArrayList<>(toppingNames);
    }

    public boolean canAddTopping(String toppingName) {
        if (toppingName == null || toppingName.trim().isEmpty()) {
            return false;
        }
        if (toppingName.equals("Mild sauce") && toppingNames.contains("Spicy sauce")) {
            return false;
        }
        if (toppingName.equals("Spicy sauce") && toppingNames.contains("Mild sauce")) {
            return false;
        }
        return true;
    }

    public void addToppingName(String toppingName) {
        if (canAddTopping(toppingName)) {
            toppingNames.add(toppingName);
        }
    }

    public void removeLastToppingName() {
        if (!toppingNames.isEmpty()) {
            toppingNames.remove(toppingNames.size() - 1);
        }
    }

    public double getTotalCost() {
        double total = bowl.getCost();
        if (size != null) {
            total += size.getExtraPrice();
        }
        return total;
    }

    public String getDescription() {
        String text = bowl.getDescription();
        if (size != null) {
            text += ", size " + size.getName();
        }
        return text;
    }
}