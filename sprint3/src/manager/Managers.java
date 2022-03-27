package manager;

public class Managers {

    public static TaskManager getDefault() {
        return InMemoryTaskManager.getInstance();
    }

    public static HistoryInterface getDefaultHistory() {
        return InMemoryHistoryManager.getInstance();
    }
}

