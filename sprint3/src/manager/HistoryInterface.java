package manager;

import tasks.Task;

import java.util.List;

public interface HistoryInterface {
    public List<Task> add(Task task);

    public List<Task> getHistory();
}

