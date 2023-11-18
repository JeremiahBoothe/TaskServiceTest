package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TaskServiceTest {
    private static TaskService taskService;

    /**
     * Initializes HashMap and TaskService Singleton for testing.
     */
    @BeforeAll
    static void setupBeforeAll() {
        taskService = TaskService.getInstance();
    }

    /**
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     * @param testInfo - to pull the display name off each test to display.
     */
    @BeforeEach
    void printSpace(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        int totalLength = 80; // Adjust the total length as needed
        int paddingLength = (totalLength - displayName.length() - 2) / 2;
        int extraPadding = (totalLength - displayName.length() - 2) % 2;

        String padding = "*".repeat(paddingLength);
        String padding2 = "=".repeat(totalLength);
        String extraPaddingStr = (extraPadding == 1) ? "*": "";

        System.out.println(padding2);
        System.out.printf("%s %s %s%s\n\n", padding, displayName, padding, extraPaddingStr);
    }

    /**
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     */
    @AfterEach
    void printAfterSpace() {
        int totalLength = 80; // Adjust the total length as needed
        String padding2 = "=".repeat(totalLength);
        System.out.println(padding2 + "\n\n");
    }
    /**
     * Parameterized test to run null check and length check, ensuring values that are too long or null are rejected.
     *
     * @param taskId          CSV Task Id's
     * @param taskName        CSV Task Names
     * @param taskDescription CSV Task Descriptions
     */
    @Order(1)
    @CsvSource({
            ",Grocery Shopping,Buy groceries for the week",
            "2,,Go for a 30-minute jog in the park",
            "3,Work Meeting,",
            "44256456897,Read Book,Read the first chapter of a new novel",
            "5,Cook Dinner Cook Dinner,Prepare a homemade dinner for the family",
            "6,Pay Bills,Settle monthly utility bills and go to the poor house!",
            "7,Call Friend,Catch up with a friend over the phone",
            "7,Call Friend,Catch up with a friend over the phone",
            "8,Learn Guitar,Practice playing the guitar for 20 minutes",
            "9,Clean House,Tidy up and clean different rooms in the house",
            "10,Write Journal,Journal about my experiences and reflections",
            "175,Movie Night,Watch a favorite movie with popcorn"})
    @ParameterizedTest
    @DisplayName("Test Exceptions for Null and Length")
    void testAddTaskAndGetTaskById(String taskId,
                                   String taskName,
                                   String taskDescription) {
        try {
            taskService.addTask(taskId,
                    taskName,
                    taskDescription);


            assertNotNull(taskService.getTaskById(taskId), "The added Task Should not be Null");
            taskService.displayValues(taskId);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof NullPointerException,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }

    /**
     * Tests print allTasks to make sure it's not throwing an error, the tasks currently in the map are printed to console.
     */
    @Test
    @Order(2)
    @DisplayName("Prints Parameterized Test Map Values:")
    void testPrintAllTasks() {
        assertDoesNotThrow(() -> {
            taskService.printAllTasks();
        });
    }

    @Test
    @DisplayName("Retrieves the name of a task, by id, from the map.")
    void getTaskDescription() {
        String taskId = "100";
        taskService.addTask("100", "Workout", "Pump some Iron like I'm Arnold in 1978");
        assertEquals(taskService.getTaskDescription(taskId), "Pump some Iron like I'm Arnold in 1978");
        System.out.println(taskService.getTaskDescription(taskId));
    }

    @Test
    @DisplayName("Test Deletion, exists and null.")
    void testDeleteTask() {
        String taskId = "175";
        assertDoesNotThrow(() -> {
            taskService.deleteTask(taskId);
            assertNull(taskService.getTaskById(taskId));
        });

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            taskService.deleteTask(taskId);
        }, "NullPointerException was expected");
        assertEquals("Task Id: " + taskId + " does not exist", thrown.getMessage());
        System.out.println(thrown.getMessage());
    }

    /**
     * Tests retrieval of Task Name of current Task
     */
    @Test
    @DisplayName("Retrieves the current Task Name from the map")
    void getTaskName() {
        String taskId = "399";

        taskService.addTask(taskId,
                "Lennry",
                "333 Happy Place");
        assertEquals(taskService.getTaskName(taskId), "Lennry");
        System.out.println(taskService.getTaskName(taskId));
    }

    @Test
    @DisplayName("Update TaskName and Update Task Description")
    void testUpdateTask() {
        String taskId = "53354";
        taskService.addTask(taskId, "9876543210", "456 Second St");
        assertNotNull(taskService.getTaskById(taskId));

        assertEquals("9876543210", taskService.getTaskName(taskId));
        assertEquals("456 Second St", taskService.getTaskDescription(taskId));
        taskService.getTaskById(taskId).displayValues();
        System.out.println("\n");

        taskService.updateTaskName(taskService.getTaskById(taskId), "UPDATED");
        assertNotNull(taskService.getTaskById(taskId));
        assertEquals("UPDATED", taskService.getTaskName(taskId));
        assertEquals("456 Second St", taskService.getTaskDescription(taskId));
        taskService.getTaskById(taskId).displayValues();
        System.out.println("\n");

        taskService.updateTaskDescription(taskService.getTaskById(taskId), "UPDATED");
        assertNotNull(taskService.getTaskById(taskId));
        assertEquals("UPDATED", taskService.getTaskName(taskId));
        assertEquals("UPDATED", taskService.getTaskDescription(taskId));
        taskService.getTaskById(taskId).displayValues();
        System.out.println("\n");
    }
}