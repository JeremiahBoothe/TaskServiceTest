package org.jeremiahboothe;

import java.util.HashMap;

/**
 * Created as a Singleton, TaskService, handles the operations and creation of the map and hashmap. Upon initialization the constructor instantiates the Hashmap.
 */
public class TaskService {
    private final HashMap<String, Task> taskMap;
    private static final TaskService TASK_SERVICE = new TaskService();

    /**
     * Constructor for TaskService instantiates taskMap when constructed.
     */
    private TaskService() {
        this.taskMap = new HashMap<>();
    }

    /**
     * Retrieves taskService to operate as a singleton.
     * @return TASK_SERVICE The service as a singleton.
     */
    static TaskService getInstance() {
        return TASK_SERVICE;
    }

    /**
     * Adds new Task to taskMap or throws exception if Task already Exists.
     * @param task Task to be added to the map.
     */
    void addTask(Task task) {
        if (taskMap.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Task Id: " + task.getTaskId() + " already exists!");
        }
        taskMap.put(task.getTaskId(), task);
    }

    /**
     * Creates new task.
     * @param taskId Id for new Task
     * @param taskName Name of new Task
     * @param taskDescription Description of new Task
     * @return Task New Task
     */
    Task createNewTaskToAddToMap(String taskId, String taskName, String taskDescription){
        return new Task(
                taskId,
                taskName,
                taskDescription);
    }

    /**
     * Deletes a Task by Id or throws an exception if the Id does not exist in the HashMap
     * @param taskId Task Id to delete
     * @throws NullPointerException When Id is not in the map.
     */
    void deleteTask(String taskId) throws NullPointerException {
        if (taskMap.containsKey(taskId)) {
            taskMap.remove(taskId);
            System.out.println("Task with Id: "
                    + taskId
                    + " deleted successfully!");
        } else {
            throw new NullPointerException("Task Id: "
                    + taskId
                    + " does not exist");
        }
    }

    /**
     * Retrieves Task by Id.
     * @param taskId Id of Task.
     * @return Task at Id taskId.
     */
    Task getTaskById(String taskId) {
        return taskMap.get(taskId);
    }

    /**
     * Retrieves Task Name of Task
     * @param taskId Id of Task
     * @return taskName Name of Task at taskId.
     */
    String getTaskName(String taskId) {
        return getTaskById(taskId).getTaskName();
    }

    /**
     * Retrieves Task Description of Task.
     * @param taskId Id of Task
     * @return taskDescripton Name of Task Description at taskId.
     */
    String getTaskDescription(String taskId) {
        return getTaskById(taskId).getTaskDescription();
    }

    /**
     * Updates Task Name in the map, by Id.
     * @param taskId Id of task to update
     * @param taskName Task Name to update
     */
    void updateTaskName(String taskId, String taskName){
        Task task = taskMap.get(taskId);
        task.setTaskName(taskName);
    }

    /**
     * Updates Task Description in the map, by Id.
     * @param taskId Task Id to update
     * @param taskDescription New Task Description
     */
    void updateTaskDescription(String taskId, String taskDescription){
        Task task = taskMap.get(taskId);
        task.setTaskDescription(taskDescription);
    }

    /**
     * Displays values of current Task
     * @param taskId Id of task to display values from
     */
    void displayValues(String taskId) {
        getTaskById(taskId).displayValues();
    }

    /**
     * Iterates through the hashmap and prints all tasks.
     */
    void printAllTasks() {
        for (HashMap.Entry<String, Task> entry : TASK_SERVICE.getAllTasks().entrySet()) {
            String taskId = entry.getKey();
            Task retrievedTask = entry.getValue();
            System.out.println("Retrieved Task from Index [" + taskId + "]:");
            retrievedTask.displayValues();
            System.out.println("\n");
        }
    }

    /**
     * Function to retrieve the task map for printing all tasks. Made Private since nothing external accesses it.
     * @return HashMap
     */
    private HashMap<String, Task> getAllTasks() {
        return taskMap;
    }
}