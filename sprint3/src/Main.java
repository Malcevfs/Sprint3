import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

public class Main {
    public static void main(String[] args) {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        InMemoryTaskManager manager = new InMemoryTaskManager();

        manager.createTask(new Task("TaskName", "taskDescription", Task.Status.NEW));
        manager.createNewEpicTask(new Epic("epicTaskMap", "EpicDescription"));
        manager.createNewSubTask(new SubTask("SubTaskName", "SubtaskDescription", Task.Status.IN_PROGRESS, 2));
        manager.createNewSubTask(new SubTask("SubTaskNameTwo", "SubtaskDescriptionTwo", Task.Status.DONE, 2));


        System.out.println(manager.getEpicById(2));
        System.out.println(manager.getTaskById(1));

        //System.out.println(manager.getSubtaskMap());

        //System.out.println(manager.getEpicById(2));
        System.out.println(InMemoryHistoryManager.getInstance().getHistory());
    }
}