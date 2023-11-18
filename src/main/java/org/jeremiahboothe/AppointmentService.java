package org.jeremiahboothe;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Created as a Singleton, AppointmentService, handles the operations and creation of the map and hashmap. Upon initialization the constructor instantiates the Hashmap.
 */
public class AppointmentService {
    private final HashMap<String, Appointment> appointmentMap;
    private static final AppointmentService APPOINTMENT_SERVICE = new AppointmentService();
    String formattedDateString;
    public static LocalDateTime minimum() {
        return LocalDateTime.now();
    }
    /**
     * Constructor for AppointmentService instantiates appointmentMap when constructed.
     */
    private AppointmentService() {
        this.appointmentMap = new HashMap<>();
    }

    /**
     * Retrieves appointmentService to operate as a singleton.
     * @return AppointmentService appointmentService
     */
    static AppointmentService getInstance() {
        return APPOINTMENT_SERVICE;
    }

    /**
     * Adds new Appointment to appointmentMap. Finally has 1 job and is decoupled entirely from the Appointment creation.
     * @param appointment User Input Id of Appointment
     */
    void addAppointment(Appointment appointment) {
        if (appointmentMap.containsKey(appointment.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment Id: " + appointment.getAppointmentId() + " already exists!");
        }
        appointmentMap.put(appointment.getAppointmentId(), appointment);
    }

    /**
     * Separated the creation of the appointment from the adding it to the map.
     * Creates appointment and nothing more!
     * @param appointmentId User Input ID for Appointment to be created
     * @param appointmentDate User Input Date to be formatted and checked
     * @param appointmentDescription User Input Description for Appointment
     * @return Appointment!
     */
    Appointment createNewAppointmentToAddToMap(String appointmentId, String appointmentDate, String appointmentDescription){
        return new Appointment(appointmentId,
                appointmentDate,
                appointmentDescription);
    }

    /**
     * Added method that formats the date/time at the test level, then provides the result to the adding, creating, and updating methods.
     * Takes all the Date/Time parameters and formats them to an appropriate string standard to be used for adding and updating appointments. In my vision
     * I imagine this being called against the user input.
     * @param year 4 character year
     * @param month 2 character month
     * @param day 2 character day
     * @param hour 2 character hour
     * @param minute 2 character minute
     * @param second 2 character second
     * @return formattedString to fit DateTimeFormat
     */
    String dateFormatter(String year, String month, String day, String hour, String minute, String second) {
        formattedDateString = null;
        if(year.length() <= 4
        && month.length()<= 2
        && day.length() <= 2
        && hour.length() <= 2
        && minute.length() <= 2
        && second.length() <= 2){
       formattedDateString = year
               + "-"
               + month
               + "-"
               + day
               + " "
               + hour
               + ":"
               + minute
               + ":"
               + second;
        }
        return formattedDateString;
    }

    /**
     * Deletes an Appointment by Id or throws an exception if the Id does not exist in the HashMap
     * @param appointmentId Appointment Id to delete
     * @throws NullPointerException When Id is not in the map.
     */
    void deleteAppointment(String appointmentId) throws NullPointerException {
        if (appointmentMap.containsKey(appointmentId)) {
            appointmentMap.remove(appointmentId);
            System.out.println("Appointment with Id: "
                    + appointmentId
                    + " deleted successfully!");
        } else {
            throw new NullPointerException("Appointment Id: "
                    + appointmentId
                    + " does not exist");
        }
    }

    /**
     * Retrieves Appointment by Id.
     * @param appointmentId Id of Appointment.
     * @return Appointment at Id appointmentId.
     */
    Appointment getAppointmentById(String appointmentId) {
        return appointmentMap.get(appointmentId);
    }

    /**
     * Retrieves Appointment Date of Appointment
     * @param appointmentId Id of Appointment
     * @return appointmentDate Date of Appointment at appointmentId.
     */
    String getAppointmentDate(String appointmentId) {
        return getAppointmentById(appointmentId).getAppointmentDate();
    }

    /**
     * Retrieves Appointment Description of Appointment.
     * @param appointmentId Id of Appointment
     * @return appointmentDescripton Date of Appointment Description at appointmentId.
     */
    String getAppointmentDescription(String appointmentId) {
        return getAppointmentById(appointmentId).getAppointmentDescription();
    }

    /**
     * Updates Appointment Date in the map, by Id.
     * @param appointmentId Appointment Id to update
     * @param appointmentDate Appointment Date to update
     */
    void updateAppointmentDate(String appointmentId, String appointmentDate){
        Appointment appointment = appointmentMap.get(appointmentId);
        appointment.setAppointmentDate(appointmentDate);
    }

    /**
     * Updates Appointment Description in the map, by Id.
     * @param appointmentId Appointment Id to update
     * @param appointmentDescription New Appointment Description
     */
    void updateAppointmentDescription(String appointmentId, String appointmentDescription){
        Appointment appointment = appointmentMap.get(appointmentId);
        appointment.setAppointmentDescription(appointmentDescription);
    }

    /**
     * Displays values of current Appointment
     */
    void displayValues(String appointmentId) {
        getAppointmentById(appointmentId).displayValues();
    }

    /**
     * Iterates through the hashmap and prints all appointments .
     */
    void printAllAppointments() {
        for (HashMap.Entry<String, Appointment> entry : APPOINTMENT_SERVICE.getAllAppointments().entrySet()) {
            String appointmentId = entry.getKey();
            Appointment retrievedAppointment = entry.getValue();
            System.out.println("Retrieved Appointment from Index [" + appointmentId + "]:");
            retrievedAppointment.displayValues();
            System.out.println("\n");
        }
    }

    /**
     * Function to retrieve the appointment map for printing all appointments. Made Private since nothing external accesses it.
     * @return HashMap
     */
    private HashMap<String, Appointment> getAllAppointments() {
        return appointmentMap;
    }
}