package org.jeremiahboothe;

public class Task {
    private final String taskId;
    private String taskName;
    private String taskDescription;
    private final int lengthCheckShort = 10;
    private final int lengthCheckMid = 20;
    private final int lengthCheckLong = 50;

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
     * Generic type length check, made it more universal, now it receives an integer value of allowedLength, to be more flexible and redefined in the final lengths at the top or by each setter.
     * @param genericValue The generic typed value to check for length greater than 10 or 30.
     * @param errorMessage The error message for tracing back to the origin.
     * @param allowedLength The allowed maximum not to be exceeded by each setter.
     * @param <T> Generic Type.
     */
    private <T> void lengthCheck(T genericValue, String errorMessage, int allowedLength) {
        int length = String.valueOf(genericValue).length();
        if (length > allowedLength) {
            throw new IllegalArgumentException(errorMessage + " cannot be longer than " + allowedLength + "!");
        }
    }

    /**
     * Constructor for new Task Creation. Constructor is designed to prevent object instantiation upon failed null check or failed length check. Passes errors up the chain to be captured at test level.
     * @param taskId User Input ID
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
        lengthCheck(taskId, "Task Id", lengthCheckShort);

        setTaskName(taskName);
        setTaskDescription(taskDescription);

        this.taskId = taskId;
    }
    
    /**
     * Setter for taskName
     * @param taskName Name of Task
     */
    void setTaskName(String taskName) {
        String errorIdentifier = "Task Name";

        nullCheck(taskName, errorIdentifier);
        lengthCheck(taskName, errorIdentifier, lengthCheckMid);

        this.taskName = taskName;
    }

    /**
     * Setter for taskDescription
     * @param taskDescription Description of Task
     */
    void setTaskDescription(String taskDescription) {
        String errorIdentifier = "Task Description";

        nullCheck(taskDescription, "Task Description");
        lengthCheck(taskDescription, errorIdentifier, lengthCheckLong);
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
        return this.taskName;
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