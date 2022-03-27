package manager;

import java.util.HashMap;
import java.util.Collection;

import tasks.SubTask;
import tasks.Epic;
import tasks.Task;

public interface TaskManager {


    public Task getTaskById(int id);

    public Collection<SubTask> getAllEpicSubTasksById(int id);

    HashMap<Integer, SubTask> getSubtaskMap();

    public Epic getEpicById(int id);

    public SubTask getSubtaskById(int id);

    public void createTask(Task task);

    public void refreshTask(int id, Task task);

    public void deleteTask(int id);

    public void clearTask();

    public void createNewEpicTask(Epic epic);

    public void refreshEpicTask(int id, Epic epic);

    public void deleteEpicTask(int id);

    public void clearEpicTask();

    public void refreshSubTask(int id, SubTask subTask);

    public void deleteSubTask(int id);

    public void clearSubTasks();

    public void createNewSubTask(SubTask subTask);

    public Task.Status setEpicStatus(HashMap<Integer, SubTask> setTaskSub);
}


