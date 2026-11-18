public interface Command {
    /**
     * Applies the change.
     * @return true if the change was applied and can be undone, false otherwise
     */
    boolean execute();

    void undo();
}
