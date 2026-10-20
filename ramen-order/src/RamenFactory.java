public class RamenFactory {
    public static Ramen createRamen(int choice) {
        switch (choice) {
            case 1:
                return new CheeseRamen();
            case 2:
                return new ChickenRamen();
            case 3:
                return new CarbonaraRamen();
            default:
                return null;
        }
    }
}
