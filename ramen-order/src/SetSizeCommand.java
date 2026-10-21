public class SetSizeCommand implements Command {
    private Order order;
    private Size newSize;
    private Size previousSize;

    public SetSizeCommand(Order order, Size newSize) {
        this.order = order;
        this.newSize = newSize;
    }

    @Override
    public void execute() {
        previousSize = order.getSize();
        order.setSize(newSize);
    }

    @Override
    public void undo() {
        order.setSize(previousSize);
    }
}