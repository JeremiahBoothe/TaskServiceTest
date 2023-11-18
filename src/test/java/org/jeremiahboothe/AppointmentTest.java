package org.jeremiahboothe;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppointmentTest {
    private Appointment appointment;

    /**
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     * @param testInfo - to pull the display name off each test to display .
     */
    @BeforeEach
    void testFormattingPrintBefore(TestInfo testInfo) {
        String displayDate = testInfo.getDisplayName();
        int totalLength = 75; // Adjust the total length as needed
        int paddingLength = (totalLength - displayDate.length() - 2) / 2;
        int extraPadding = (totalLength - displayDate.length() - 2) % 2;

        String padding = "*".repeat(paddingLength);
        String padding2 = "=".repeat(totalLength);
        String extraPaddingStr = (extraPadding == 1) ? "*": "";

        System.out.println(padding2);
        System.out.printf("%s %s %s%s\n\n", padding, displayDate, padding, extraPaddingStr);
    }

    /**
     * sets up initial appointment to check values against
     */
    @BeforeEach
    void setUpTestCaseAppointment() {
        String formattedDateTime = "2223-11-26 22:51:34";
        appointment = new Appointment("1", formattedDateTime, "Try not to knock myself out this time!");
    }

    /**
     * After test Formatting for viewing pleasure!
     */
    @AfterEach
    void testFormattingPrintAfter() {
        int totalLength = 75; // Adjust the total length as needed
        String padding2 = "=".repeat(totalLength);
        System.out.println(padding2 + "\n\n");
    }

    /**
     * Should always pass by 1 second, unless the computer is slow :)
     */
    @Test
    @Order(1)
    @DisplayName("Pass one second in the future!")
    void testPassFutureCheckByOneSecond() {
        assertDoesNotThrow(() -> {
            LocalDateTime inputDateTime = LocalDateTime.now().plusSeconds(1);
            // Defines a formatter for date-time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Formats the ZonedDateTime using the formatter
            String formattedDateTime = inputDateTime.format(formatter);
            // Set up a new Appointment object before each test
            appointment = new Appointment("1", formattedDateTime, "Do that back flip!");
            appointment.displayValues();
        });
    }

    /**
     * Should always fail by 1 second.
     */
    @Test
    @Order(2)
    @DisplayName("Fail one second in the past!")
    void failFutureCheckByOneSecond() {
        IllegalArgumentException mustBeFuture = assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime inputDateTime = LocalDateTime.now().minusSeconds(1);
            // Defines a formatter for date-time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Formats the ZonedDateTime using the formatter
            String formattedDateTime = inputDateTime.format(formatter);
            new Appointment("1", formattedDateTime, "Try not to knock myself out this time!");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Date must be in the future.", mustBeFuture.getMessage());
        System.out.println(mustBeFuture.getMessage());
    }
    /**
     * Retrieves Date of setUpTestCaseAppointment
     */
    @Test
    @Order(3)
    @DisplayName("Retrieve Appointment Date:")
    void testGetAppointmentDate() {
        assertEquals("2223-11-26 22:51:34", appointment.getAppointmentDate());
        System.out.println(appointment.getAppointmentDate());
    }

    /**
     * Retrieves Description of setUpTestCaseAppointment
     */
    @Test
    @Order(4)
    @DisplayName("Retrieve Appointment Description:")
    void testGetAppointmentDescription() {
        assertEquals("Try not to knock myself out this time!", appointment.getAppointmentDescription());
        System.out.println(appointment.getAppointmentDescription());
    }

    /**
     * Displays all values of setUpTestCaseAppointment
     */
    @Test
    @Order(5)
    @DisplayName("Display Values of Current Appointment:")
    void testDisplayValues() {
          assertDoesNotThrow(() -> appointment.displayValues());
    }

    /**
     * Null Failure Check on the Appointment Constructor
     */
    @Test
    @Order(6)
    @DisplayName("Constructor Null Check: ID, Date, Description & Fail:")
    void testConstructorNullCheck() {
        NullPointerException idNull = assertThrows(NullPointerException.class, () -> {
            new Appointment(null, "Some Appointment", "Stuff to Do");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Id cannot be null!", idNull.getMessage());
        System.out.println(idNull.getMessage());

        NullPointerException nameNull = assertThrows(NullPointerException.class, () -> {
            new Appointment("2", null, "Stuff to Do");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Date cannot be null!", nameNull.getMessage());
        System.out.println(nameNull.getMessage());

        NullPointerException descriptionNull = assertThrows(NullPointerException.class, () -> {
            new Appointment("2", "2223-11-26 22:51:34", null);
        }, "Illegal Argument was expected");
        assertEquals("Appointment Description cannot be null!", descriptionNull.getMessage());
        System.out.println(descriptionNull.getMessage());
    }

    /**
     * Length Failure Checks on constructor
     */
    @Test
    @Order(7)
    @DisplayName("Constructor Length Check: ID, Date, Description & Fail:")
    void testConstructorLengthCheck() {
        IllegalArgumentException idLong = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", "2223-11-26 22:51:34", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Id cannot be longer than 10!", idLong.getMessage());
        System.out.println(idLong.getMessage());

        IllegalArgumentException nameLong = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", "2223-11-3426 22:51:34", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Invalid date format for Appointment Date.", nameLong.getMessage());
        System.out.println(nameLong.getMessage());

        IllegalArgumentException descriptionLong = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", "2233-11-26 22:51:34", "dfasdfasdfasdfa1sdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Description cannot be longer than 50!", descriptionLong.getMessage());
        System.out.println(descriptionLong.getMessage());
    }

    /**
     * Starts with a new appointment, updates the name and checks the update, then updates the description and checks the update.
     */
    @Test
    @Order(8)
    @DisplayName("Test Update Appointment Date then Description:")
    void testUpdateAppointment() {
        String appointmentId = "53354";

        Appointment updatedAppointment = new Appointment(appointmentId, "2223-11-26 22:51:34", "All the live long day");

        assertEquals("2223-11-26 22:51:34", updatedAppointment.getAppointmentDate());
        assertEquals("All the live long day", updatedAppointment.getAppointmentDescription());
        updatedAppointment.displayValues();
        System.out.println("\n");

        updatedAppointment.setAppointmentDate("3333-11-26 22:51:34");
        assertEquals("3333-11-26 22:51:34", updatedAppointment.getAppointmentDate());
        updatedAppointment.displayValues();
        System.out.println("\n");

        updatedAppointment.setAppointmentDescription("UPDATED");
        assertEquals("UPDATED", updatedAppointment.getAppointmentDescription());
        updatedAppointment.displayValues();
        System.out.println("\n");
    }

    /**
     * Id should be 10 or less.
     */
    @Test
    @Order(9)
    @DisplayName("Create Appointment & Appointment Id Fails: Length and Null:")
    void testCreateAppointmentIdFails() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", "aseriopiyiokjhgpiuio", "dfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Id cannot be longer than 10!", thrown.getMessage());
        System.out.println(thrown.getMessage());

        NullPointerException thrownI = assertThrows(NullPointerException.class, () -> {
            new Appointment(null, "Wash Laundry", "Scrub-a-dub-dub");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Id cannot be null!", thrownI.getMessage());
        System.out.println(thrownI.getMessage());
    }

    /**
     * Date Should be Appropriate Format and not null.
     */
    @Test
    @Order(10)
    @DisplayName("Create Appointment & Appointment Date Fails: : Format and Null:")
    void testCreateAppointmentDateFails() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", "aseriopiyiokjhgfpiuio", "dfasdfasdafasdfasdfasdfasdfasdfasdfasdfasdfasdfasd");
        }, "IllegalArgumentException was expected");
        assertEquals("Invalid date format for Appointment Date.", thrown.getMessage());
        System.out.println(thrown.getMessage());

        NullPointerException thrownI = assertThrows(NullPointerException.class, () -> {
            new Appointment("1234567890", null, "Scrub-a-dub-dub");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Date cannot be null!", thrownI.getMessage());
        System.out.println(thrownI.getMessage());
}

    /**
     * Description should be 50 or less and not null.
     */
    @Test
    @Order(11)
    @DisplayName("Create Appointment & Appointment Description Fails: : Length and Null:")
    void testCreateAppointmentDescriptionFails() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", "2223-11-26 22:51:34", "dfasdfasdfasdfasdfaseedfasdfasdfasdfasdfasdfasdfasd");
        }, "Illegal Argument was expected");
        assertEquals("Appointment Description cannot be longer than 50!", thrown.getMessage());
        System.out.println(thrown.getMessage());

        NullPointerException thrownI = assertThrows(NullPointerException.class, () -> {
            new Appointment("1234567890", "2223-11-26 22:51:34", null);
        }, "Illegal Argument was expected");
        assertEquals("Appointment Description cannot be null!", thrownI.getMessage());
        System.out.println(thrownI.getMessage());
    }
}