package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppointmentServiceTest {
    private static AppointmentService appointmentService;
    private static String appointmentDateConstructed;

    /**
     * Initializes HashMap and AppointmentService Singleton for testing.
     */
    @BeforeAll
    static void setupBeforeAll() {
        appointmentService = AppointmentService.getInstance();
        appointmentDateConstructed = appointmentService.dateFormatter("2200", "12", "12", "12", "12","55");
    }

    /**
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     * @param testInfo - to pull the display name off each test to display.
     */
    @BeforeEach
    void testFormattingPrintBefore(TestInfo testInfo) {
        String displayDate = testInfo.getDisplayName();
        int totalLength = 85; // Adjust the total length as needed
        int paddingLength = (totalLength - displayDate.length() - 2) / 2;
        int extraPadding = (totalLength - displayDate.length() - 2) % 2;

        String padding = "*".repeat(paddingLength);
        String padding2 = "=".repeat(totalLength);
        String extraPaddingStr = (extraPadding == 1) ? "*": "";

        System.out.println(padding2);
        System.out.printf("%s %s %s%s\n\n", padding, displayDate, padding, extraPaddingStr);
    }

    /**
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     */
    @AfterEach
    void testFormattingPrintAfter() {
        int totalLength = 85; // Adjust the total length as needed
        String padding2 = "=".repeat(totalLength);
        System.out.println(padding2 + "\n\n");
    }

    /**
     * Parameterized test to run null check and length check, ensuring values that are too long or null are rejected.
     *
     * @param appointmentId          CSV Appointment Id's
     * @param appointmentDate        CSV Appointment Dates
     * @param appointmentDescription CSV Appointment Descriptions
     */
    @Order(1)
    @CsvSource({
            ",3333-11-26 22:51:34,Buy groceries for the week",
            "2,,Go for a 30-minute jog in the park",
            "3,3333-11-26 22:51:34,",
            "44256456897,3333-11-26 22:51:34,Read the first chapter of a new novel",
            "5,Cook Dinner Cook Dinner,Prepare a homemade dinner for the family",
            "6,3333-11-26 22:51:34,Settle monthly utility bills and go to the poor house!",
            "7,3333-11-26 22:51:34,Catch up with a friend over the phone",
            "7,3333-11-26 22:51:34,Catch up with a friend over the phone",
            "8,3333-11-26 22:51:34,Practice playing the guitar for 20 minutes",
            "9,3333-11-26 22:51:34,Tidy up and clean different rooms in the house",
            "10,3333-11-26 22:51:34,Journal about my experiences and reflections",
            "175,2245-07-12 12:34:33 ,Watch a favorite movie with popcorn"})
    @ParameterizedTest
    @DisplayName("Test Exceptions for Null and Length all Parameters:")
    void testAddAppointmentAndGetAppointmentById(String appointmentId,
                                   String appointmentDate,
                                   String appointmentDescription) {
        try {
            appointmentService.addAppointment(appointmentService.createNewAppointmentToAddToMap(appointmentId,
                    appointmentDate,
                    appointmentDescription));

            System.out.println(AppointmentService.minimum());
            assertNotNull(appointmentService.getAppointmentById(appointmentId), "The added Appointment Should not be Null");
            appointmentService.displayValues(appointmentId);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof NullPointerException,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests print allAppointments to make sure it's not throwing an error, the appointments currently in the map are printed to console.
     */
    @Test
    @Order(2)
    @DisplayName("Prints Parameterized Test Map Values:")
    void testPrintAllMapAppointmentsFromParam() {
        assertDoesNotThrow(() -> {
            appointmentService.printAllAppointments();
        });
    }

    /**
     * Retrieves Description from map, by ID.
     */
    @Test
    @Order(3)
    @DisplayName("Retrieves the Description of a appointment, by id, from the map:")
    void getAppointmentDescriptionFromMapById() {
        String appointmentId = "100";
        String appointmentDescription = "Pump some Iron like I'm Arnold in 1978 ";

                appointmentService.addAppointment(appointmentService
                        .createNewAppointmentToAddToMap(appointmentId,
                                appointmentDateConstructed,
                                appointmentDescription));

        assertEquals(appointmentDescription, appointmentService.getAppointmentDescription(appointmentId));
        System.out.println(appointmentService.getAppointmentDescription(appointmentId));
    }

    /**
     * Deletes appointment from map, succeeds when ID exists, fails when it does not.
     */
    @Test
    @Order(4)
    @DisplayName("Test Deletion if exists, Fails if null:")
    void testDeleteAppointmentFromMapByIDPassFail() {
        String appointmentId = "175";
        assertDoesNotThrow(() -> {
            appointmentService.deleteAppointment(appointmentId);
            assertNull(appointmentService.getAppointmentById(appointmentId));
        });

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            appointmentService.deleteAppointment(appointmentId);
        }, "NullPointerException was expected");

        assertEquals("Appointment Id: " + appointmentId + " does not exist", thrown.getMessage());
        System.out.println(thrown.getMessage());
    }

    /**
     * Tests Map retrieval of Appointment Date of Appointment by Id
     */
    @Test
    @Order(5)
    @DisplayName("Retrieves Appointment Date from the map, by ID:")
    void getAppointmentDateFromMapByID() {
        String appointmentId = "399";

        appointmentService.addAppointment(appointmentService
                .createNewAppointmentToAddToMap(
                        appointmentId,
                        appointmentDateConstructed,
                        "Vet appointment for cats"));
        assertEquals(appointmentService.getAppointmentDate(appointmentId), appointmentDateConstructed);
        System.out.println(appointmentService.getAppointmentDate(appointmentId));
    }

    /**
     * Steps through creating a appointment and updating the Appointment Date, and Appointment Description individually.
     */
    @Test
    @Order(6)
    @DisplayName("Update AppointmentDate and Update Appointment Description:")
    void testUpdateAppointmentDateDescriptionFromMap() {
        String appointmentId = "53354";

        // Found out variables must be final or reference objects for use with assertAll, which I was using for grouping and readability.
        var reference = new Object() {
            String appointmentDate = appointmentDateConstructed;
            String appointmentDescription = "So Many Code Snippets";
        };

        appointmentService.addAppointment(appointmentService
                .createNewAppointmentToAddToMap(appointmentId,
                        reference.appointmentDate,
                        reference.appointmentDescription));

        assertAll(
                () -> assertNotNull(appointmentService.getAppointmentById(appointmentId)),
                () -> assertEquals(reference.appointmentDate, appointmentService.getAppointmentDate(appointmentId)),
                () -> assertEquals(reference.appointmentDescription, appointmentService.getAppointmentDescription(appointmentId))
        );
            appointmentService.getAppointmentById(appointmentId).displayValues();
            System.out.println("\n");

        reference.appointmentDate = appointmentService.dateFormatter("3000", "01", "01", "01", "01","01");
        appointmentService.updateAppointmentDate(appointmentId, reference.appointmentDate);
        assertAll(
                () -> assertNotNull(appointmentService.getAppointmentById(appointmentId)),
                () -> assertEquals(reference.appointmentDate, appointmentService.getAppointmentDate(appointmentId)),
                () -> assertEquals(reference.appointmentDescription, appointmentService.getAppointmentDescription(appointmentId))
        );
            appointmentService.getAppointmentById(appointmentId).displayValues();
            System.out.println("\n");

        reference.appointmentDescription = "UPDATED";
        appointmentService.updateAppointmentDescription(appointmentId, reference.appointmentDescription);
        assertAll(
                () -> assertNotNull(appointmentService.getAppointmentById(appointmentId)),
                () -> assertEquals(reference.appointmentDate, appointmentService.getAppointmentDate(appointmentId)),
                () -> assertEquals(reference.appointmentDescription, appointmentService.getAppointmentDescription(appointmentId))
        );
            appointmentService.getAppointmentById(appointmentId).displayValues();
            System.out.println("\n");
    }

    /**
     * Prints all remaining Map Entries
     */
    @Test
    @Order(7)
    @DisplayName("Prints the final Test Map Values:")
    void testPrintAllMapAppointmentsLast() {
        assertDoesNotThrow(() -> {
            appointmentService.printAllAppointments();
        });
    }
}