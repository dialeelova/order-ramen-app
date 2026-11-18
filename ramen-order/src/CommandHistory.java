import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private final List<Command> commands = new ArrayList<>();

    public boolean executeCommand(Command command) {
        if (command == null) {
            return false;
        }
        boolean applied = command.execute();
        if (applied) {
            commands.add(command);
        }
        return applied;
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
