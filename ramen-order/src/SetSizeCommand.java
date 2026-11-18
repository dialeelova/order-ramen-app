public class SetSizeCommand implements Command {
    private final Order order;
    private final Size newSize;
    private Size previousSize;

    public SetSizeCommand(Order order, Size newSize) {
        if (order == null || newSize == null) {
            throw new IllegalArgumentException("Order and size are required");
        }
        this.order = order;
        this.newSize = newSize;
    }

    @Override
    public boolean execute() {
        if (order.getSize() == newSize) {
            return false;
        }
        previousSize = order.getSize();
        order.setSize(newSize);
        return true;
    }

    @Override
    public void undo() {
        if (previousSize != null) {
            order.setSize(previousSize);
        }
    }
}
