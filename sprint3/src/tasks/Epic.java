package tasks;

import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, SubTask> newSubTask = new HashMap<>();

    public Epic(String taskMap, String description) {
        super(taskMap, description, Task.Status.NEW);
    }


    public void setSubTask(HashMap<Integer, SubTask> subTask) {
        this.newSubTask = subTask;
    }

    public void put(int id, SubTask subTask) {

        newSubTask.put(id, subTask);

    }

    public HashMap<Integer, SubTask> getNewSubTask() {
        return newSubTask;
    }
}





