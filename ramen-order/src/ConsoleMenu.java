import java.util.Scanner;

public class ConsoleMenu {
    private Scanner scanner = new Scanner(System.in);
    private RamenFactory factory = new RamenFactory();
    private Notifier notifier = new Notifier();
    private PaymentService paymentService = new PaymentService();
    private CommandHistory history = new CommandHistory();
    private Order order;

    public void start() {
        System.out.println("Welcome to our ramen shop!");
        chooseRamen();
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readNumber();
            if (choice == 1) {
                addTopping();
            } else if (choice == 2) {
                chooseSize();
            } else if (choice == 3) {
                undoLastChange();
            } else if (choice == 4) {
                showOrder();
            } else if (choice == 5) {
                boolean paid = payAndPlace();
                if (paid) {
                    running = false;
                }
            } else if (choice == 6) {
                notifier.notifyOrderCancelled();
                running = false;
            } else {
                System.out.println("Please enter a number from 1 to 6.");
            }
        }
        System.out.println("Thank you for visiting!");
    }

    private void chooseRamen() {
        Ramen ramen = null;
        while (ramen == null) {
            System.out.println("Choose your ramen:");
            System.out.println("1. Cheese Ramen");
            System.out.println("2. Chicken Ramen");
            System.out.println("3. Carbonara Ramen");
            System.out.print("Your choice: ");
            ramen = factory.createRamen(readNumber());
            if (ramen == null) {
                System.out.println("Please enter 1, 2 or 3.");
            }
        }
        order = new Order(ramen);
        System.out.println("You chose " + ramen.getDescription() + ".");
    }

    private void showMenu() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1. Add a topping");
        System.out.println("2. Choose a size");
        System.out.println("3. Undo last change");
        System.out.println("4. Show my order");
        System.out.println("5. Pay and place order");
        System.out.println("6. Cancel order");
        System.out.print("Your choice: ");
    }

    private void addTopping() {
        System.out.println("Choose a topping:");
        System.out.println("1. Egg");
        System.out.println("2. Extra cheese");
        System.out.println("3. Sausage");
        System.out.println("4. Nori");
        System.out.println("5. Mild sauce");
        System.out.println("6. Spicy sauce");
        System.out.print("Your choice: ");
        int choice = readNumber();
        if (choice < 1 || choice > 6) {
            System.out.println("Please enter a number from 1 to 6.");
            return;
        }
        AddToppingCommand command = new AddToppingCommand(order, choice);
        if (command.isAllowed()) {
            history.executeCommand(command);
            notifier.notifyBowlChanged(order);
        } else {
            notifier.notifyToppingNotAllowed(command.getToppingName());
        }
    }

    private void chooseSize() {
        System.out.println("Choose a size:");
        System.out.println("1. Small");
        System.out.println("2. Medium (+1.00)");
        System.out.println("3. Large (+2.00)");
        System.out.print("Your choice: ");
        int choice = readNumber();
        Size size = null;
        if (choice == 1) {
            size = new Size("Small", 0);
        } else if (choice == 2) {
            size = new Size("Medium", 1.00);
        } else if (choice == 3) {
            size = new Size("Large", 2.00);
        }
        if (size == null) {
            System.out.println("Please enter 1, 2 or 3.");
            return;
        }
        history.executeCommand(new SetSizeCommand(order, size));
        notifier.notifyBowlChanged(order);
    }

    private void undoLastChange() {
        if (history.undoLast()) {
            notifier.notifyUndo(order);
        } else {
            notifier.notifyNothingToUndo();
        }
    }

    private void showOrder() {
        System.out.println("Your order: " + order.getBowl().getDescription());
        if (order.getSize() != null) {
            System.out.println("Size: " + order.getSize().getName());
        } else {
            System.out.println("Size: not chosen yet");
        }
        System.out.println("Price: " + Math.round(order.getTotalCost() * 100.0) / 100.0);
    }

    private boolean payAndPlace() {
        System.out.println("How would you like to pay?");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.print("Your choice: ");
        int method = readNumber();
        boolean paid = paymentService.pay(order, method);
        if (paid) {
            notifier.notifyOrderPlaced(order);
        }
        return paid;
    }

    private int readNumber() {
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        }
        scanner.nextLine();
        return -1;
    }
}
