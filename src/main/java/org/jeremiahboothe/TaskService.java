package org.jeremiahboothe;

import java.util.HashMap;
/**
 * Created as a Singleton, TaskService, handles the operations and creation of the map and hashmap. Upon initialization the constructor instantiates the Hashmap.
 */
public class TaskService {
    // Other fields and methods remain unchanged
    private final HashMap<String, Task> taskMap;
    private String currentTaskId;
    private static final TaskService TASK_SERVICE = new TaskService();

    /**
     * Constructor for TaskService instantiates taskMap when constructed.
     */
    private TaskService() {
        this.taskMap = new HashMap<>();
    }

    /**
     * Retrieves taskService to operate as a singleton.
     * @return TaskService taskService
     */
    static TaskService getInstance() {
        return TASK_SERVICE;
    }

    /**
     * Stores active taskId for reference to add & update Tasks
     * @param newId new TaskId, whether user input or generated
     */
    void setCurrentTaskId(String newId) {
        this.currentTaskId = newId;
    }

    /**
     * Retrieves the Id of the currently active task.
     * @return currentTaskId
     */
    String getCurrentTaskId() {
        return currentTaskId;
    }

    /**
     * Adds Task when user inputs preferred Id
     *
     * @param taskId er Input Id of Task
     * @param taskName   User Input Task Name
     * @param taskDescription    User Input Task Description
     */
    void addTask(String taskId,
                    String taskName,
                    String taskDescription) {
        if (taskMap.containsKey(taskId)) {
            throw new IllegalArgumentException("Task Id: " + taskId + " already exists!");
        }
        Task task = new Task(
                taskId,
                taskName,
                taskDescription);
        taskMap.put(taskId, task);
        setCurrentTaskId(taskId);
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
     * Retrieves Task by Id
     * @param taskId Id of Task
     * @return Task
     */
    
    Task getTaskById(String taskId) {
        return taskMap.get(taskId);
    }

    /**
     * Displays values of current Task
     */
    void displayValues() {
        TASK_SERVICE.getTaskById(TASK_SERVICE.getCurrentTaskId()).displayValues();
    }

    /**
     * Retrieves Id of current Task
     * @return taskId of current Task
     */
    String getTaskId() {
        return TASK_SERVICE.getTaskById(TASK_SERVICE.getCurrentTaskId()).getTaskId();
    }

    /**
     * Retrieves Task Name of current Task
     * @return String
     */
    String getTaskName() {
        return TASK_SERVICE.getTaskById(TASK_SERVICE.getCurrentTaskId()).getTaskName();
    }

    /**
     * Retrieves Task Name of current
     * @return String
     */
    String getTaskDescription() {
        return TASK_SERVICE.getTaskById(TASK_SERVICE.getCurrentTaskId()).getTaskDescription();
    }


    /**
     * Iterates through the hashmap and prints all tasks. Could replace taskService.getAllTasks().entrySet() with taskMap.entrySet() but not doing so for now in case there is further use for getAllTasks, later on.
     */
    void printAllTasks() {
        for (HashMap.Entry<String, Task> entry : TASK_SERVICE.getAllTasks().entrySet()) {
            String taskId = entry.getKey();
            Task retrievedTask = entry.getValue();
            System.out.println("\nRetrieved Task from Index " + taskId + ":");
            retrievedTask.displayValues();
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