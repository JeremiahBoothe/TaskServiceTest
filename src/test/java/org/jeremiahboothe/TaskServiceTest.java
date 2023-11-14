package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

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
     * Parameterized test to run null check and length check, ensuring values that are too long or null are rejected.
     * @param taskId CSV Task Id's
     * @param taskName CSV Task Names
     * @param taskDescription CSV Task Descriptions
     */
    @CsvSource({
            "Isabella,Anderson,4325555678,123 Oak St",
            ",Smith,4325551234,456 Pine Ln",
            "Sophia,,4325559275,789 Elm Blvd",
            "Mason,Clark, ,890 Cedar Rd",
            "Amelia,Cooper,4325558765,",
            "EmmaGenopolis,Evans,4325553456,567 Maple Dr",
            "Elijah,TurnerBakerson,4325556789,890 Cedar Rd",
            "Emma,Miller,14325554321,456 Oak Ave",
            "Olivia,Davis,4325553456,8000 Taumatawhakatangihangakoauauo tamateaturipukakapikimaungahoronuku pokaiwhenuakitanatahu",
            "Sophia,Clark,4325559275,789 Elm Blvd"})
    @ParameterizedTest
    @DisplayName("Test Exceptions for Null and Length")
    void testAddTaskAndGetTaskById(String taskId,
                                         String taskName,
                                         String taskDescription) {
        try {
            taskService.addTask(taskId,
                    taskName,
                    taskDescription);

            System.out.println("\n");

            taskService.displayValues();
            System.out.println("\n");
            //Task task = taskService.getTaskById(taskService.getCurrentTaskId());
            assertNotNull(taskService.getTaskById(taskService.getCurrentTaskId()), "The added Task Should not be Null");
            //assertEquals(task, taskService.getTaskById(taskService.getCurrentTaskId()));
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof NullPointerException,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }

    /**
     * Tests User Input Id's, and checks to ensure overwrites to not occur after user Input Id is created.
     */
    /*
    @Test
    void testCustomId() {
        assertDoesNotThrow(() -> {
            /* taskService
                    .addTask("1000000000",
                    "Billy",
                    "Kurilko",
                    "4325556789",
                    "7523 Waterstreet");
        });

        assertDoesNotThrow(() -> {
            taskService
                    .addTask("JJJ*#$@$%J",
                            "Benny",
                            "Barton",
                            "4325356389",
                            "7432 Waterstreet");
        });

        IllegalArgumentException exceptionDuplicateId = assertThrows(IllegalArgumentException.class, () -> {
            taskService
                    .addTask("1000000000",
                            "Billy",
                            "Kurilko",
                            "4325556789",
                            "7523 Waterstreet");

        });
        assertEquals("Task Id: 1000000000 already exists!", exceptionDuplicateId.getMessage());

        IllegalArgumentException exceptionDuplicateId2 = assertThrows(IllegalArgumentException.class, () -> {
            taskService
                    .addTask("JJJ*#$@$%J",
                            "Billy",
                            "Kurilko",
                            "4325556789",
                            "7523 Waterstreet");

        });
        assertEquals("Task Id: JJJ*#$@$%J already exists!", exceptionDuplicateId2.getMessage());
        taskService.displayValues();

    }*/

    /**
     * todo:
     *//*
    @Test
    void testUpdateTask() {
        assertDoesNotThrow(() -> {
            taskService.addTask("1000",
                    "But",
                    "why",
                    "too",
                    "high a number");
            assertNotNull(taskService.getTaskById(taskService.getCurrentTaskId()), "The added Task Should not be Null");

        });

        // Use assertThrows to check for exceptions during the task addition
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask("1000",
                    "meh",
                    "beh",
                    "too",
                    "high");
        });

        //
        assertDoesNotThrow(() -> {
            taskService.updateTask("1000",
                    "murp",
                    "Durp",
                    "butter",
                    "cookies");
        });
    }*/

    /**
     * Tests updating task that does not exist, to make sure it's not added instead.
     *//*
    @Test
    void updateTaskNonExistent() {
    try {
            taskService.updateTask("75",
                    "murp",
                    "Durp",
                    "butter",
                    "cookies");
    }catch (NullPointerException e) {
            assertTrue(true,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }*/

    /**
     * Tests and verifies deleting task and not being able to again delete a task after it has been deleted.
     *//*
    @Test
    void testDeleteTask() {
        assertDoesNotThrow(() -> {
            taskService.addTask("1",
                    "asdf",
                    "asdfgh",
                    "340531",
                    "234345");
            taskService.deleteTask("1");
            assertNull(taskService.getTaskById("1"));
        });

        try {
            taskService.deleteTask("1");

        } catch (NullPointerException e) {
            assertTrue(true,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }*/

    /**
     * Tests print allTasks to make sure it's not throwing an error, the tasks currently in the map are printed to console.
     */
    /*
    @Test
    void testPrintAllTasks() {
        assertDoesNotThrow(() -> {
            taskService.printAllTasks();
        });
    }*/

    /**
     * Tests retrieval of Id for current task
     *//*
    @Test
    void getTaskId() {
        taskService.addTask("599","Lennry",
                "Balthazor",
                "4325559275",
                "333 Happy Place");
        assertEquals(taskService.getTaskId(), "599");
    }*/

    /**
     * Tests retrieval of Task Name of current Task.
     *//*
    @Test
    void getTaskName() {
        taskService.addTask("499","Lennry",
                "Balthazor",
                "4325559275",
                "333 Happy Place");
        assertEquals(taskService.getTaskName(), "Balthazor");
    }*/

    /**
     * Tests retrieval of Task Name of current Task
     *//*
    @Test
    void getTaskName() {
        taskService.addTask("399",
                "Lennry",
                "Balthazor",
                "4325559275",
                "333 Happy Place");
        assertEquals(taskService.getTaskName(), "Lennry");
    }*/

}