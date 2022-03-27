package manager;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class InMemoryTaskManager implements TaskManager {

    private final HistoryInterface history = Managers.getDefaultHistory();
    private int id = 0;

    private HashMap<Integer, Task> taskMap = new HashMap<>();
    private HashMap<Integer, Epic> epicTaskMap = new HashMap<>();
    private HashMap<Integer, SubTask> subTaskMap = new HashMap<>();

    private static class Service {
        public static final InMemoryTaskManager INSTANCE = new InMemoryTaskManager();
    }

    public static InMemoryTaskManager getInstance() {
        return Service.INSTANCE;
    }

    public HashMap<Integer, Task> getTaskMap() {
        return taskMap;
    }

    @Override
    public Task getTaskById(int id) {
        history.add(taskMap.get(id));
        return taskMap.get(id);
    }

    public HashMap<Integer, Epic> getEpicTaskMap() {
        return epicTaskMap;
    }

    @Override
    public Collection<SubTask> getAllEpicSubTasksById(int id) {
        Epic epic = epicTaskMap.get(id);
        return epic.getNewSubTask().values();
    }

    @Override
    public HashMap<Integer, SubTask> getSubtaskMap() {
        return subTaskMap;
    }

    @Override
    public Epic getEpicById(int id) {
        history.add(epicTaskMap.get(id));
        return epicTaskMap.get(id);
    }

    @Override
    public SubTask getSubtaskById(int id) {
        history.add(subTaskMap.get(id));
        return subTaskMap.get(id);
    }

    @Override
    public void createTask(Task task) {
        id++;
        task.setId(id);
        taskMap.put(id, task);
    }

    @Override
    public void refreshTask(int id, Task task) {
        task.setId(id);
        taskMap.put(id, task);
    }

    @Override
    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    @Override
    public void clearTask() {
        taskMap.clear();
    }

    @Override
    public void createNewEpicTask(Epic epic) {
        id++;
        epic.setId(id);
        epicTaskMap.put(id, epic);
    }

    @Override
    public void refreshEpicTask(int id, Epic epic) {
        Epic epicById = getEpicById(id);
        if (epicById == null){
            System.out.println("error");
            return;
        }
        epic.setId(id);
        epic.getNewSubTask().putAll(epicById.getNewSubTask());
        epicTaskMap.put(id, epic);
    }

    @Override
    public void deleteEpicTask(int id) {
        Epic epic = epicTaskMap.get(id);
        for (int i : epic.getNewSubTask().keySet()) {
            subTaskMap.remove(i);
        }
        epicTaskMap.remove(id);
    }

    @Override
    public void clearEpicTask() {
        subTaskMap.clear();
        epicTaskMap.clear();
    }

    @Override
    public void refreshSubTask(int id, SubTask subTask) {
        subTask.setId(id);
        int epicId = subTaskMap.get(id).getEpicId();
        Epic epic = epicTaskMap.get(epicId);
        epic.put(id, subTask);
        subTaskMap.put(id, subTask);
        epic.setStatus(setEpicStatus(epic.getNewSubTask()));
    }

    @Override
    public void deleteSubTask(int id) {
        int num = subTaskMap.get(id).getEpicId();
        subTaskMap.remove(id);
        Epic epic = epicTaskMap.get(num);
        epic.getNewSubTask().remove(id);
        epicTaskMap.get(num).setStatus(setEpicStatus(epic.getNewSubTask()));

    }

    @Override
    public void clearSubTasks() {
        subTaskMap.clear();
    }

    @Override
    public void createNewSubTask(SubTask subTask) {
        id++;
        subTask.setId(id);

        subTaskMap.put(id, subTask);
        int num = subTaskMap.get(id).getEpicId();
        Epic epic = epicTaskMap.get(num);
        epic.put(id, subTask);
        epicTaskMap.get(num).setStatus(setEpicStatus(epic.getNewSubTask()));

    }

    @Override
    public Task.Status setEpicStatus(HashMap<Integer, SubTask> setTaskSub) {

        Task.Status newStatus = null;
        int statusNew = 0;
        int statusInProgress = 0;
        int statusDone = 0;

        for (Task subTaskClass : setTaskSub.values()) {

            if (subTaskClass.getStatus().isEmpty()) {
                newStatus = Task.Status.NEW;
            }
            if (subTaskClass.getStatus().equals(Task.Status.NEW)) {
                statusNew++;
            }
            if (subTaskClass.getStatus().equals(Task.Status.IN_PROGRESS)) {
                statusInProgress++;
            }
            if (subTaskClass.getStatus().equals(Task.Status.DONE)) {
                statusDone++;
            }
            if ((statusDone > 0) && (statusNew == 0) && (statusInProgress == 0)) {
                newStatus = Task.Status.DONE;
            } else if (statusNew > 0 && (statusInProgress == 0)) {
                newStatus = Task.Status.NEW;
            } else {
                newStatus = Task.Status.IN_PROGRESS;
            }
        }
        return newStatus;
    }
}


