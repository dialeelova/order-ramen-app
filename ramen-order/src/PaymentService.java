public class PaymentService {
    public boolean pay(Order order, int method) {
        if (order == null) {
            System.out.println("There is no order - payment was not made.");
            return false;
        }
        double total = Math.round(order.getTotalCost() * 100.0) / 100.0;
        if (method == 1) {
            System.out.println("Paid " + total + " in cash.");
            return true;
        } else if (method == 2) {
            System.out.println("Paid " + total + " by card.");
            return true;
        }
        System.out.println("Unknown payment method - payment was not made.");
        return false;
    }
}