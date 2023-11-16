package org.jeremiahboothe;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;
    private TestInfo testInfo;

    @BeforeEach
    void printSpace(TestInfo testInfo) {
        this.testInfo = testInfo;
        System.out.println(testInfo.getDisplayName());
        System.out.println("=========================================================\n");
    }

    @BeforeEach
    void setUp() {
        // Set up a new Task object before each test
        task = new Task("1", "John", "Doe");
    }




    /*========================= Testing for correct Values =========================*/


    @Test
    void testGetTaskName() {
        assertEquals("John", task.getTaskName());
    }

    @Test
    void testGetTaskDescription() {
        assertEquals("Doe", task.getTaskDescription());
    }

    @Test
    void testDisplayValues() {
        // You can redirect System.out to capture the output for testing
        // For simplicity, let's just test if it runs without errors
        assertDoesNotThrow(() -> task.displayValues());
    }

    @Test
    void testConstructorNullCheck() {
        // Test that the constructor throws NullPointerException for null values
        assertThrows(IllegalArgumentException.class, () -> new Task("1", null, "Doe"));
    }

    @Test
    void testConstructorLengthCheck() {
        // Test that the constructor throws IllegalArgumentException for values exceeding length limits
        assertThrows(IllegalArgumentException.class, () -> new Task("1", "Johnnnnnnnnnn", null));
    }

    @Test
    void testUpdateTask() {
        // Test updating an existing Task
        //Task updatedTask = new Task(task, null, null, "9876543210", "456 Second St");

        /*assertEquals(task.getTaskId(), updatedTask.getTaskId());
        assertEquals(task.getTaskName(), updatedTask.getTaskName());
        assertEquals(task.getTaskDescription(), updatedTask.getTaskDescription());
        assertEquals("9876543210", updatedTask.getTaskName());
        assertEquals("456 Second St", updatedTask.getTaskDescription());*/
    }

    /*========================= Testing for Exceptions =========================*/

    /**
     * Id should be 10 or less.
     */
    @Test
    @DisplayName("Create Task & Task Id Fails: Length and Null")
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
    @DisplayName("Create Task & Task Name Fails")
    void testCreateTaskNameFails() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiyiokjhgfpiuio", "dfasdfasdafasdfasdfasdfasdfasdfasdfasdfasdfasdfasd");
        }, "IllegalArgumentException was expected");
        assertEquals("Invalid Task Name, cannot be longer than 20!", thrown.getMessage());
        System.out.println(thrown.getMessage());
    }

    /**
     * Description should be 50 or less.
     */
    @Test
    @DisplayName("Create Task & Task Description Fails")
    void testCreateTaskDescriptionFails() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Task("1234567890", "aseriopiyiokjhgfpiu3", "dfasdfasdfasdfasdfaseedfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Invalid Task Description: cannot be longer than 50!", thrown.getMessage());
    }
}
