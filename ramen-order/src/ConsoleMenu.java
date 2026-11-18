import java.util.Scanner;

public class ConsoleMenu {
    private static final int INVALID = -1;

    private final Scanner scanner = new Scanner(System.in);
    private final RamenFactory ramenFactory = new RamenFactory();
    private final ToppingFactory toppingFactory = new ToppingFactory();
    private final Notifier notifier = new Notifier();
    private final PaymentService paymentService = new PaymentService();
    private final CommandHistory history = new CommandHistory();
    private Order order;
    private boolean inputClosed = false;

    public void start() {
        System.out.println("Welcome to our ramen shop!");
        boolean running = chooseRamen();
        while (running && !inputClosed) {
            showMenu();
            int choice = readNumber();
            if (inputClosed) {
                break;
            }
            switch (choice) {
                case 1:
                    addTopping();
                    break;
                case 2:
                    chooseSize();
                    break;
                case 3:
                    undoLastChange();
                    break;
                case 4:
                    showOrder();
                    break;
                case 5:
                    running = !payAndPlace();
                    break;
                case 6:
                    running = !cancelOrder();
                    break;
                default:
                    System.out.println("Please enter a number from 1 to 6.");
            }
        }
        if (inputClosed) {
            System.out.println();
            System.out.println("No more input - your order was not placed.");
        }
        System.out.println("Thank you for visiting!");
    }

    private boolean chooseRamen() {
        int count = ramenFactory.getOptionCount();
        while (true) {
            System.out.println();
            System.out.println("Choose your ramen:");
            for (int i = 1; i <= count; i++) {
                Ramen option = ramenFactory.createRamen(i);
                System.out.println(i + ". " + option.getName() + " - " + PriceFormatter.format(option.getCost()));
            }
            System.out.print("Your choice: ");
            int choice = readNumber();
            if (inputClosed) {
                return false;
            }
            if (ramenFactory.isValidChoice(choice)) {
                order = new Order(ramenFactory.createRamen(choice));
                notifier.notifyRamenChosen(order);
                return true;
            }
            System.out.println("Please enter a number from 1 to " + count + ".");
        }
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
        int count = toppingFactory.getOptionCount();
        System.out.println("Choose a topping (only one sauce per bowl):");
        for (int i = 1; i <= count; i++) {
            ToppingDecorator option = toppingFactory.createTopping(i, order.getBowl());
            System.out.println(i + ". " + option.getToppingName() + " (+" + PriceFormatter.format(option.getPrice()) + ")");
        }
        System.out.print("Your choice: ");
        int choice = readNumber();
        if (inputClosed) {
            return;
        }
        if (!toppingFactory.isValidChoice(choice)) {
            System.out.println("Please enter a number from 1 to " + count + ".");
            return;
        }
        ToppingDecorator topping = toppingFactory.createTopping(choice, order.getBowl());
        if (order.isAlreadyAdded(topping)) {
            notifier.notifyToppingAlreadyAdded(topping.getToppingName());
        } else if (order.hasSauceConflict(topping)) {
            notifier.notifySauceConflict(topping.getToppingName());
        } else if (history.executeCommand(new AddToppingCommand(order, topping))) {
            notifier.notifyBowlChanged(order);
        }
    }

    private void chooseSize() {
        Size[] sizes = Size.values();
        System.out.println("Choose a size:");
        for (int i = 0; i < sizes.length; i++) {
            String extra = "";
            if (sizes[i].getExtraPrice() > 0) {
                extra = " (+" + PriceFormatter.format(sizes[i].getExtraPrice()) + ")";
            }
            System.out.println((i + 1) + ". " + sizes[i].getName() + extra);
        }
        System.out.print("Your choice: ");
        int choice = readNumber();
        if (inputClosed) {
            return;
        }
        if (choice < 1 || choice > sizes.length) {
            System.out.println("Please enter a number from 1 to " + sizes.length + ".");
            return;
        }
        Size size = sizes[choice - 1];
        if (history.executeCommand(new SetSizeCommand(order, size))) {
            notifier.notifyBowlChanged(order);
        } else {
            notifier.notifySizeUnchanged(size);
        }
    }

    private void undoLastChange() {
        if (!history.canUndo()) {
            notifier.notifyNothingToUndo();
            return;
        }
        history.undoLast();
        notifier.notifyUndo(order);
    }

    private void showOrder() {
        Ramen base = order.getBaseRamen();
        System.out.println("Your order:");
        printLine(base.getName(), base.getCost());
        for (ToppingDecorator topping : order.getToppings()) {
            printLine("+ " + topping.getToppingName(), topping.getPrice());
        }
        printLine("Size: " + order.getSize().getName(), order.getSize().getExtraPrice());
        printLine("Total", order.getTotalCost());
    }

    private void printLine(String label, double price) {
        System.out.println(String.format("  %-20s %8s", label, PriceFormatter.format(price)));
    }

    private boolean payAndPlace() {
        System.out.println("Your total is " + PriceFormatter.format(order.getTotalCost()) + ".");
        System.out.println("How would you like to pay?");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. Go back");
        System.out.print("Your choice: ");
        int method = readNumber();
        if (inputClosed || method == 3) {
            return false;
        }
        PaymentMethod paymentMethod;
        if (method == 1) {
            System.out.print("How much cash are you giving? ");
            double amount = readAmount();
            if (inputClosed) {
                return false;
            }
            if (amount == INVALID) {
                System.out.println("Please enter a valid amount, for example 10 or 12.50.");
                return false;
            }
            paymentMethod = new CashPayment(amount);
        } else if (method == 2) {
            System.out.print("Please enter your 16-digit card number: ");
            String cardNumber = readLine();
            if (inputClosed) {
                return false;
            }
            paymentMethod = new CardPayment(cardNumber);
        } else {
            System.out.println("Please enter 1, 2 or 3.");
            return false;
        }
        boolean paid = paymentService.pay(order, paymentMethod);
        if (paid) {
            notifier.notifyOrderPlaced(order);
        }
        return paid;
    }

    private boolean cancelOrder() {
        System.out.print("Are you sure you want to cancel your order? (y/n): ");
        String answer = readLine().trim().toLowerCase();
        if (inputClosed) {
            return false;
        }
        if (answer.equals("y") || answer.equals("yes")) {
            notifier.notifyOrderCancelled();
            return true;
        }
        System.out.println("Your order was not cancelled.");
        return false;
    }

    private String readLine() {
        if (inputClosed || !scanner.hasNextLine()) {
            inputClosed = true;
            return "";
        }
        return scanner.nextLine();
    }

    private int readNumber() {
        try {
            return Integer.parseInt(readLine().trim());
        } catch (NumberFormatException e) {
            return INVALID;
        }
    }

    private double readAmount() {
        try {
            double amount = Double.parseDouble(readLine().trim().replace(',', '.'));
            if (amount < 0 || Double.isNaN(amount) || Double.isInfinite(amount)) {
                return INVALID;
            }
            return amount;
        } catch (NumberFormatException e) {
            return INVALID;
        }
    }
}
