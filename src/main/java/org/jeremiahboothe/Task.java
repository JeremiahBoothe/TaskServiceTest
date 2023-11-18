package org.jeremiahboothe;

public class Task {
    private final String taskId;
    private String taskName;
    private String taskDescription;

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
            throws IllegalArgumentException,
            NullPointerException {
        nullCheck(taskId, "Task Id");
        lengthCheckTen(taskId, "Task Id");
        setTaskName(taskName);
        setTaskDescription(taskDescription);
        this.taskId = taskId;
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
        int maxLength = 10;
            if (length > maxLength) {
                throw new IllegalArgumentException("Invalid " + errorMessage + ": cannot be longer than " + maxLength + "!");
            }
    }

    /**
     * Setter for taskName
     * @param taskName Name of Task
     */
    void setTaskName(String taskName) {
        nullCheck(taskName, "Task Name");
        int maxLength = 20;
        if(taskName.length() > maxLength){
            throw new IllegalArgumentException("Invalid Task Name: cannot be longer than " + maxLength + "!");
        }
        this.taskName = taskName;
    }

    /**
     * Setter for taskDescription
     * @param taskDescription Description of Task
     */
    void setTaskDescription(String taskDescription) {
        nullCheck(taskDescription, "Task Description");
        int maxLength = 50;
        if(taskDescription.length() > maxLength) {
            throw new IllegalArgumentException("Invalid Task Description: cannot be longer than " + maxLength + "!");
        }
        this.taskDescription = taskDescription;
    }

     /**
     * Retrieves the Task Id
     * @return taskId The Task Id of the Task.
     */
    String getTaskId(){
        return this.taskId;
    }

    /**
     * Retrieves the Task Description
     * @return taskDescription The Task Description of the Task.
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
     * Prints Task Values of current task
     */
    void displayValues() {
        System.out.println("Task Id: " + getTaskId());
        System.out.println("Task Name: " + getTaskName());
        System.out.println("Task Description: " + getTaskDescription());
    }
}