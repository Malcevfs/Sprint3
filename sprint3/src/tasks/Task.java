package tasks;



public class Task {
    protected int id;
    protected String taskMap;
    protected String description;
    protected Status status;

    public Task() {
    }

    public Task(String taskMap, String description, Status status) {

        this.taskMap = taskMap;
        this.description = description;
        this.status= status;
    }

    public String getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(String taskMap) {
        this.taskMap = taskMap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tasks.Task{" +
                "id=" + id +
                ", taskMap='" + taskMap + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {

        return id;

    }

    public enum Status {
        NEW,
        IN_PROGRESS,
        DONE;

        public boolean isEmpty() {
            return true;
        }

    }
}



