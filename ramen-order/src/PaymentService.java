public class PaymentService {
    public boolean pay(Order order, PaymentMethod paymentMethod) {
        if (order == null || paymentMethod == null) {
            System.out.println("Sorry, the payment could not be made.");
            return false;
        }
        return paymentMethod.pay(order.getTotalCost());
    }
}
