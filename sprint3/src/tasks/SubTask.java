package tasks;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String taskMap, String description, Status status, int epicId) {
        super(taskMap, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Tasks.SubTask{" +
                "epicId=" + epicId +
                ", id=" + id +
                ", taskMap='" + taskMap + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}