import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private List<Command> commands;

    public CommandHistory() {
        commands = new ArrayList<>();
    }

    public void executeCommand(Command command) {
        if (command != null) {
            command.execute();
            commands.add(command);
        }
    }

    public boolean canUndo() {
        return !commands.isEmpty();
    }

    public boolean undoLast() {
        if (commands.isEmpty()) {
            return false;
        }
        Command last = commands.remove(commands.size() - 1);
        last.undo();
        return true;
    }
}