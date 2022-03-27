package manager;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryInterface {

    protected List<Task> historyList = new ArrayList<>();
    protected int id;
    protected String taskMap;
    protected String description;
    protected Task.Status status;

    public List<Task> getHistory() {
        return historyList;
    }

    public List<Task> add(Task task) {
        if (historyList.size() > 10) {
            historyList.remove(0);
            historyList.add(task);
        } else {
            historyList.add(task);
        }
        return historyList;
    }

    private static class Service {
        public static final InMemoryHistoryManager INSTANCE = new InMemoryHistoryManager();
    }

    public static InMemoryHistoryManager getInstance() {
        return InMemoryHistoryManager.Service.INSTANCE;
    }

}
