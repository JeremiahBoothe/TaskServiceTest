package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskTest {
    private Task task;

    /**
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     * @param testInfo - to pull the display name off each test to display.
     */
    @BeforeEach
    void testFormattingPrintBefore(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        int totalLength = 60; // Adjust the total length as needed
        int paddingLength = (totalLength - displayName.length() - 2) / 2;
        int extraPadding = (totalLength - displayName.length() - 2) % 2;

        String padding = "*".repeat(paddingLength);
        String padding2 = "=".repeat(totalLength);
        String extraPaddingStr = (extraPadding == 1) ? "*": "";

        System.out.println(padding2);
        System.out.printf("%s %s %s%s\n\n", padding, displayName, padding, extraPaddingStr);
    }

    /**
     * Sets up initial task to check values against
     */
    @BeforeEach
    void setUpTestCaseTask() {
        // Set up a new Task object before each test
        task = new Task("1", "Perform a back flip.", "Try not to knock myself out this time!");
    }

    /**
     * After test Formatting for viewing pleasure!
     */
    @AfterEach
    void testFormattingPrintAfter() {
        int totalLength = 60; // Adjust the total length as needed
        String padding2 = "=".repeat(totalLength);
        System.out.println(padding2 + "\n\n");
    }

    /**
     * Retrieves Name of setUpTestCaseTask
     */
    @Test
    @Order(1)
    @DisplayName("Retrieve Task Name:")
    void testGetTaskName() {
        assertEquals("Perform a back flip.", task.getTaskName());
        System.out.println(task.getTaskName());
    }

    /**
     * Retrieves Description of setUpTestCaseTask
     */
    @Test
    @Order(2)
    @DisplayName("Retrieve Task Description:")
    void testGetTaskDescription() {
        assertEquals("Try not to knock myself out this time!", task.getTaskDescription());
        System.out.println(task.getTaskDescription());
    }

    /**
     * Displays all values of setUpTestCaseTask
     */
    @Test
    @Order(3)
    @DisplayName("Display Values of Current Task:")
    void testDisplayValues() {
        // You can redirect System.out to capture the output for testing
        // For simplicity, let's just test if it runs without errors
        assertDoesNotThrow(() -> task.displayValues());
    }

    /**
     * Null Failure Check on the Task Constructor.  Checks each parameter.
     */
    @Test
    @Order(4)
    @DisplayName("Constructor Null Check: ID, Name, Description & Fail:")
    void testConstructorNullCheck() {
        // Test that the constructor throws NullPointerException for null values
        NullPointerException idNull = assertThrows(NullPointerException.class, () -> {
            new Task(null, "Some Task", "Stuff to Do");
        }, "Illegal Argument was expected");
        assertEquals("Task Id cannot be null!", idNull.getMessage());
        System.out.println(idNull.getMessage());

        NullPointerException nameNull = assertThrows(NullPointerException.class, () -> {
            new Task("2", null, "Stuff to Do");
        }, "Illegal Argument was expected");
        assertEquals("Task Name cannot be null!", nameNull.getMessage());
        System.out.println(nameNull.getMessage());

        NullPointerException descriptionNull = assertThrows(NullPointerException.class, () -> {
            new Task("2", "Some Task", null);
        }, "Illegal Argument was expected");
        assertEquals("Task Description cannot be null!", descriptionNull.getMessage());
        System.out.println(descriptionNull.getMessage());
    }

    /**
     * Length Failure Checks on Constructor. Checks each parameter.
     */
    @Test
    @Order(5)
    @DisplayName("Constructor Length Check: ID, Name, Description & Fail:")
    void testConstructorLengthCheck() {
        // Test that the constructor throws IllegalArgumentException for values exceeding length limits
        IllegalArgumentException idLong = assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "aseriopiyiokjhgpiuio", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Task Id cannot be longer than 10!", idLong.getMessage());
        System.out.println(idLong.getMessage());

        IllegalArgumentException nameLong = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiy1iokjhgpiuio", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Task Name cannot be longer than 20!", nameLong.getMessage());
        System.out.println(nameLong.getMessage());

        IllegalArgumentException descriptionLong = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiyiokjhgpiuio", "dfasdfasdfasdfa1sdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Task Description cannot be longer than 50!", descriptionLong.getMessage());
        System.out.println(descriptionLong.getMessage());
    }

    /**
     * Starts with a new task, updates the name and checks the update, then updates the description and checks the update.
     */
    @Test
    @Order(6)
    @DisplayName("Test Update Task Name then Description:")
    void testUpdateTask() {
        String taskId = "53354";

        Task updatedTask = new Task(taskId, "Too Much Work", "All the live long day");

        assertEquals("Too Much Work", updatedTask.getTaskName());
        assertEquals("All the live long day", updatedTask.getTaskDescription());
        updatedTask.displayValues();
        System.out.println("\n");

        updatedTask.setTaskName("UPDATED");
        assertEquals("UPDATED", updatedTask.getTaskName());
        updatedTask.displayValues();
        System.out.println("\n");

        updatedTask.setTaskDescription("UPDATED");
        assertEquals("UPDATED", updatedTask.getTaskDescription());
        updatedTask.displayValues();
        System.out.println("\n");
    }

    /**
     * Id should be 10 or less and not null.
     */
    @Test
    @Order(7)
    @DisplayName("Create Task & Task Id Fails: Length and Null:")
    void testCreateTaskIdFails() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "aseriopiyiokjhgpiuio", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Task Id cannot be longer than 10!", thrown.getMessage());
        System.out.println(thrown.getMessage());

        NullPointerException thrownI = assertThrows(NullPointerException.class, () -> {
            new Task(null, "Wash Laundry", "Scrub-a-dub-dub");
        }, "Illegal Argument was expected");
        assertEquals("Task Id cannot be null!", thrownI.getMessage());
        System.out.println(thrownI.getMessage());
    }

    /**
     * Name should be 20 or less.
     */
    @Test
    @Order(8)
    @DisplayName("Create Task & Task Name Fails:")
    void testCreateTaskNameFails() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiyiokjhgfpiuio", "dfasdfasdafasdfasdfasdfasdfasdfasdfasdfasdfasdfasd");
        }, "IllegalArgumentException was expected");
        assertEquals("Task Name cannot be longer than 20!", thrown.getMessage());
        System.out.println(thrown.getMessage());
    }

    /**
     * Description should be 50 or less.
     */
    @Test
    @Order(9)
    @DisplayName("Create Task & Task Description Fails:")
    void testCreateTaskDescriptionFails() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiyiokjhgfpiu3", "dfasdfasdfasdfasdfaseedfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Task Description cannot be longer than 50!", thrown.getMessage());
        System.out.println(thrown.getMessage());
    }
}