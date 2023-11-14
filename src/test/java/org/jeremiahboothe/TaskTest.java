package org.jeremiahboothe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        // Set up a new Task object before each test
        task = new Task("1", "John", "Doe");
    }

    @Test
    void testGetTaskId() {
        assertEquals("1", task.getTaskId());
    }

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
        assertThrows(NullPointerException.class, () -> new Task("1", null, "Doe"));
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
}
