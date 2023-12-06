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
    void testFormattingPrintBefore(TestInfo testInfo) {
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
    void testFormattingPrintAfter() {
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
    @DisplayName("Test Exceptions for Null and Length all Parameters:")
    void testAddTaskAndGetTaskById(String taskId,
                                   String taskName,
                                   String taskDescription) {
        try {
            Task task = taskService.createNewTaskToAddToMap(
                    taskId,
                    taskName,
                    taskDescription);

            taskService.addTask(task);

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
    void testPrintAllMapTasksFromParam() {
        assertDoesNotThrow(() -> {
            taskService.printAllTasks();
        });
    }

    /**
     * Retrieves Description from map, by ID.
     */
    @Test
    @Order(3)
    @DisplayName("Retrieves the Description of a task, by id, from the map:")
    void getTaskDescriptionFromMapById() {
        String taskId = "100";
        String taskName = "Workout";
        String taskDescription = "Pump some Iron like I'm Arnold in 1978";

        Task task = taskService.createNewTaskToAddToMap(taskId, taskName, taskDescription);
        taskService.addTask(task);
        assertEquals(taskDescription, taskService.getTaskDescription(taskId));
        System.out.println(taskService.getTaskDescription(taskId));
    }

    /**
     * Deletes task from map, succeeds when ID exists, fails when it does not.
     */
    @Test
    @Order(4)
    @DisplayName("Test Deletion if exists, Fails if null:")
    void testDeleteTaskFromMapByIDPassFail() {
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
     * Tests Map retrieval of Task Name of Task by Id
     */
    @Test
    @Order(5)
    @DisplayName("Retrieves Task Name from the map, by ID:")
    void getTaskNameFromMapByID() {
        String taskId = "399";

        Task task = taskService.createNewTaskToAddToMap(
                taskId,
                "Wash the Car",
                "Looks like someone went muddin' in my car");
        taskService.addTask(task);
        assertEquals(taskService.getTaskName(taskId), "Wash the Car");
        System.out.println(taskService.getTaskName(taskId));
    }

    /**
     * Steps through creating a task and updating the Task Name, and Task Description individually.
     */
    @Test
    @Order(6)
    @DisplayName("Update TaskName and Update Task Description:")
    void testUpdateTaskNameDescriptionFromMap() {
        String taskId = "53354";

        // Found out variables must be final or reference objects for use with assertAll, which I was using for grouping and readability.
        var reference = new Object() {
            String taskName = "Finish CS-320 Paper";
            String taskDescription = "So Many Code Snippets";
        };

        Task task = taskService.createNewTaskToAddToMap(taskId, reference.taskName, reference.taskDescription);

        taskService.addTask(task);
        assertAll(
                () -> assertNotNull(taskService.getTaskById(taskId)),
                () -> assertEquals(reference.taskName, taskService.getTaskName(taskId)),
                () -> assertEquals(reference.taskDescription, taskService.getTaskDescription(taskId))
        );
            taskService.getTaskById(taskId).displayValues();
            System.out.println("\n");

        reference.taskName = "UPDATED";
        taskService.updateTaskName(taskId, reference.taskName);
        assertAll(
                () -> assertNotNull(taskService.getTaskById(taskId)),
                () -> assertEquals(reference.taskName, taskService.getTaskName(taskId)),
                () -> assertEquals(reference.taskDescription, taskService.getTaskDescription(taskId))
        );
            taskService.getTaskById(taskId).displayValues();
            System.out.println("\n");

        reference.taskDescription = "UPDATED";
        taskService.updateTaskDescription(taskId, reference.taskDescription);
        assertAll(
                () -> assertNotNull(taskService.getTaskById(taskId)),
                () -> assertEquals(reference.taskName, taskService.getTaskName(taskId)),
                () -> assertEquals(reference.taskDescription, taskService.getTaskDescription(taskId))
        );
            taskService.getTaskById(taskId).displayValues();
            System.out.println("\n");
    }

    /**
     * Prints all remaining Map Entries
     */
    @Test
    @Order(7)
    @DisplayName("Prints the final Test Map Values:")
    void testPrintAllMapTasksLast() {
        assertDoesNotThrow(() -> {
            taskService.printAllTasks();
        });
    }
}