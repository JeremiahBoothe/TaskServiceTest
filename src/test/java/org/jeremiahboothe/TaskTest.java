package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    /**
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     * @param testInfo - to pull the display name off each test to display.
     */
    @BeforeEach
    void printSpace(TestInfo testInfo) {
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
     * sets up initial task to check values against
     */
    @BeforeEach
    void setUp() {
        // Set up a new Task object before each test
        task = new Task("1", "Perform a backflip.", "Try not to knock myself out this time!");
    }

    /**
     * After test Formatting for viewing pleasure!
     */
    @AfterEach
    void printAfterSpace() {
        int totalLength = 60; // Adjust the total length as needed
        String padding2 = "=".repeat(totalLength);
        System.out.println(padding2 + "\n\n");
    }



    /*========================= Testing for correct Values =========================*/


    @Test
    @DisplayName("Retrieve Task Name:")
    void testGetTaskName() {
        assertEquals("Perform a backflip.", task.getTaskName());
        System.out.println(task.getTaskName());
    }

    @Test
    @DisplayName("Retrieve Task Description:")
    void testGetTaskDescription() {
        assertEquals("Try not to knock myself out this time!", task.getTaskDescription());
        System.out.println(task.getTaskDescription());
    }

    @Test
    @DisplayName("Display Values of Current Task:")
    void testDisplayValues() {
        // You can redirect System.out to capture the output for testing
        // For simplicity, let's just test if it runs without errors
        assertDoesNotThrow(() -> task.displayValues());
    }

    @Test
    @DisplayName("Constructor Null Check:")
    void testConstructorNullCheck() {
        // Test that the constructor throws NullPointerException for null values
        NullPointerException idNull = assertThrows(NullPointerException.class, () -> {
            new Task(null, "Blah", "Doe");
        }, "Illegal Argument was expected");
        assertEquals("Task Id cannot be null!", idNull.getMessage());
        System.out.println(idNull.getMessage());

        NullPointerException nameNull = assertThrows(NullPointerException.class, () -> {
            new Task("1", null, "Doe");
        }, "Illegal Argument was expected");
        assertEquals("Task Name cannot be null!", nameNull.getMessage());
        System.out.println(nameNull.getMessage());

        NullPointerException descriptionNull = assertThrows(NullPointerException.class, () -> {
            new Task("2", "blah", null);
        }, "Illegal Argument was expected");
        assertEquals("Task Description cannot be null!", descriptionNull.getMessage());
        System.out.println(descriptionNull.getMessage());

    }

    @Test
    @DisplayName("Constructor Length Check:")
    void testConstructorLengthCheck() {
        // Test that the constructor throws IllegalArgumentException for values exceeding length limits
        IllegalArgumentException idLong = assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "aseriopiyiokjhgpiuio", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Invalid Task Id: cannot be longer than 10!", idLong.getMessage());
        System.out.println(idLong.getMessage());

        IllegalArgumentException nameLong = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiy1iokjhgpiuio", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Invalid Task Name: cannot be longer than 20!", nameLong.getMessage());
        System.out.println(nameLong.getMessage());

        IllegalArgumentException descriptionLong = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiyiokjhgpiuio", "dfasdfasdfasdfa1sdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Invalid Task Description: cannot be longer than 50!", descriptionLong.getMessage());
        System.out.println(descriptionLong.getMessage());
    }

    /**
     * Starts with a new task, updates the name and checks the update, then updates the description and checks the update.
     */
    @Test
    @DisplayName("Test Update Task:")
    void testUpdateTask() {
        Task updatedTask = new Task("53354", "9876543210", "456 Second St");

        assertEquals("9876543210", updatedTask.getTaskName());
        assertEquals("456 Second St", updatedTask.getTaskDescription());
        updatedTask.displayValues();

        updatedTask.setTaskDescription("UPDATED");
        updatedTask.setTaskName("UPDATED");
        assertEquals("53354", updatedTask.getTaskId());
        assertEquals("UPDATED", updatedTask.getTaskName());
        assertEquals("UPDATED", updatedTask.getTaskDescription());
        updatedTask.displayValues();
    }

    /*========================= Testing for Exceptions =========================*/

    /**
     * Id should be 10 or less.
     */
    @Test
    @DisplayName("Create Task & Task Id Fails: Length and Null:")
    void testCreateTaskIdFails() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "aseriopiyiokjhgpiuio", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Invalid Task Id: cannot be longer than 10!", thrown.getMessage());
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
    @DisplayName("Create Task & Task Name Fails:")
    void testCreateTaskNameFails() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiyiokjhgfpiuio", "dfasdfasdafasdfasdfasdfasdfasdfasdfasdfasdfasdfasd");
        }, "IllegalArgumentException was expected");
        assertEquals("Invalid Task Name: cannot be longer than 20!", thrown.getMessage());
        System.out.println(thrown.getMessage());
    }

    /**
     * Description should be 50 or less.
     */
    @Test
    @DisplayName("Create Task & Task Description Fails:")
    void testCreateTaskDescriptionFails() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiyiokjhgfpiu3", "dfasdfasdfasdfasdfaseedfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Invalid Task Description: cannot be longer than 50!", thrown.getMessage());
        System.out.println(thrown.getMessage());
    }
}
