package manager;

import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManagerCustomList implements HistoryInterface {
    private Map<Integer, HistoryNode> nodeMap = new HashMap<>();
    private HistoryNode head;

    public List<Task> getHistory() {
        List<Task> tasks = new ArrayList<>();
        HistoryNode node = head;
        while (node != null){
            tasks.add(node.task);
            node = node.nextNode;
        }
        return tasks;
    }

    public List<Task> add(Task task) {
        addTask(task);

        return getHistory();
    }

    private static class Service {
        public static final InMemoryHistoryManagerCustomList INSTANCE = new InMemoryHistoryManagerCustomList();
    }

    public static HistoryInterface getInstance() {
        //TODO
        return null;
    }

    private void addTask(Task t) {
        HistoryNode node = new HistoryNode();
        node.task = t;
        HistoryNode existedHistoryHistoryNode = nodeMap.get(t.getId());
        if (existedHistoryHistoryNode != null){
            removeTask(existedHistoryHistoryNode.task);
        }
        nodeMap.put(t.getId(), node);
        //TODO link node


    }

    private void removeTask(Task task){
        HistoryNode historyNode = nodeMap.get(task.getId());
        nodeMap.remove(task.getId());
        //TODO remove node from the list
    }


    class HistoryNode {
        Task task;
        HistoryNode previousNode;
        HistoryNode nextNode;
    }


}
