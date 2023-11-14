package org.jeremiahboothe;

public class Task {
    private final String taskId;
    private String taskName;
    private String taskDescription;

    private final int lengthShort = 10;
    private final int lengthLong = 30;

    /**
     * Constructor for new Task Creation. Constructor is designed to prevent object instantiation upon failed null check or failed length check. Passes errors up the chain to be captured at test level.
     * @param taskId Generated or UserInputId
     * @param taskName Task Name
     * @param taskDescription Task Name
     * @throws NullPointerException throws NullPointerExceptions up the chain to be captured at test level
     * @throws IllegalArgumentException Passes IllegalArgumentException up the chain to be captured at test level
     */
    Task(String taskId,
         String taskName,
         String taskDescription)
            throws NullPointerException,
            IllegalArgumentException {
        nullCheck(taskId, "Task Id");

        nullCheck(taskName, "Task Name");
        nullCheck(taskDescription, "Task Description");
        lengthCheckTen(taskId, "Task Id");
        lengthCheckTen(taskName, "Task Name");
        lengthCheckTen(taskDescription, "Task Description");
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }
    
    /**
     * Generic type nullCheck to universally check string values and pass NullPointerExceptions back up the chain if one is thrown.
     * @param genericValue The generic typed value to check for null.
     * @param errorMessage The error message for tracing back to the origin.
     * @param <T> Generic Type.
     */
     private <T> void nullCheck(T genericValue, String errorMessage) {
        if (genericValue == null) {
            throw new NullPointerException(errorMessage + " cannot be null!");
        }
    }

    /**
     * Generic type length check to not exceed 10 and pass IllegalArgumentExceptions back up the chain if the value is greater than 10.
     * @param genericValue The generic typed value to check for length greater than 10.
     * @param errorMessage The error message for tracing back to the origin.
     * @param <T> Generic Type.
     */
    private <T> void lengthCheckTen(T genericValue, String errorMessage) {
        int length = String.valueOf(genericValue).length();
            if (length > lengthShort) {
                throw new IllegalArgumentException(errorMessage + " cannot be longer than 10!");
            }
    }
     /**
      * Generic type length check to not exceed 30 and pass IllegalArgumentExceptions back up the chain if the value is greater than 30.
      * @param genericValue The generic typed value to check for length greater than 30.
      * @param errorMessage The error message for tracing back to the origin.
      * @param <T> Generic Type.
      */
    private <T> void lengthCheckThirty(T genericValue, String errorMessage) {
        int length = String.valueOf(genericValue).length();
        if (length > lengthLong) {
            throw new IllegalArgumentException(errorMessage + " cannot be longer than 30!");
        }
    }

    void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    /**
     * Retrieves the Task Id
     * @return this.TaskId
     */
    String getTaskId(){
        return this.taskId;
    }

    /**
     * Retrieves the Task Description
     * @return taskDescription
     */
    String getTaskDescription(){
        return this.taskDescription;
    }

    /**
     * Retrieves the Task Task Name
     * @return taskName
     */
    String getTaskName(){
        return taskName;
    }

    /**
     * Prints Task Values
     */
    void displayValues() {
        System.out.println("Task Id: " + getTaskId());
        System.out.println("Task Name: " + getTaskName());
        System.out.println("Task Description: " + getTaskDescription());
    }
}