package org.jeremiahboothe;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Appointment {

    private final String appointmentId;
    private String appointmentDate;
    private String appointmentDescription;
    private final int lengthCheckShort = 10;
    private final int lengthCheckLong = 50;

    /**
     * Generic type nullCheck to universally check string values and pass NullPointerExceptions back up the chain if one is thrown.
     * @param genericValue The generic typed value to check for null .
     * @param errorMessage The error message for tracing back to the origin.
     * @param <T> Generic Type.
     */
    private <T> void nullCheck(T genericValue, String errorMessage) {
        if (genericValue == null) {
            throw new NullPointerException(errorMessage + " cannot be null!");
        }
    }

    /**
     * Generic type length check, made it more universal, now it receives an integer value of allowedLength, to be more flexible and redefined in the final lengths at the top or by each setter.
     * @param genericValue The generic typed value to check for length greater than 10 or 30.
     * @param errorMessage The error message for tracing back to the origin.
     * @param allowedLength The allowed maximum not to be exceeded by each setter.
     * @param <T> Generic Type.
     */
    private <T> void lengthCheck(T genericValue, String errorMessage, int allowedLength) {
        int length = String.valueOf(genericValue).length();
        if (length > allowedLength) {
            throw new IllegalArgumentException(errorMessage + " cannot be longer than " + allowedLength + "!");
        }
    }

    /**
     * Constructor for new Appointment Creation. Constructor is designed to prevent object instantiation upon failed null check or failed length check. Passes errors up the chain to be captured at test level.
     * @param appointmentId Generated or UserInputId
     * @param appointmentDate Appointment Date
     * @param appointmentDescription Appointment Date
     * @throws NullPointerException throws NullPointerExceptions up the chain to be captured at test level
     * @throws IllegalArgumentException Passes IllegalArgumentException up the chain to be captured at test level
     */
    Appointment(String appointmentId,
                String appointmentDate,
                String appointmentDescription)
            throws IllegalArgumentException,
            NullPointerException {

        nullCheck(appointmentId, "Appointment Id");
        lengthCheck(appointmentId, "Appointment Id", lengthCheckShort);

        setAppointmentDate(appointmentDate);
        setAppointmentDescription(appointmentDescription);

        this.appointmentId = appointmentId;
    }

    /**
     * Setter for appointmentDate, runs the null check first, then runs checks on date for formatting, if the formatting worked properly then it is compared for future/past.
     * @param appointmentDate Date of Appointment
     */
    void setAppointmentDate(String appointmentDate) {
        nullCheck(appointmentDate, "Appointment Date");

        LocalDateTime inputDateTime;

        try {
            inputDateTime = LocalDateTime.parse(appointmentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for Appointment Date.");
        }

        if (inputDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment Date must be in the future.");
        }
        this.appointmentDate = appointmentDate;
    }

    /**
     * Setter for appointmentDescription
     * @param appointmentDescription Description of Appointment
     */
    void setAppointmentDescription(String appointmentDescription) {
        String errorIdentifier = "Appointment Description";

        nullCheck(appointmentDescription, errorIdentifier);
        lengthCheck(appointmentDescription, errorIdentifier, lengthCheckLong);

        this.appointmentDescription = appointmentDescription;
    }

     /**
     * Retrieves the Appointment Id
     * @return appointmentId The Appointment Id of the Appointment.
     */
    String getAppointmentId(){
        return this.appointmentId;
    }

    /**
     * Retrieves the Appointment Description
     * @return appointmentDescription The Appointment Description of the Appointment.
     */
    String getAppointmentDescription(){
        return this.appointmentDescription;
    }

    /**
     * Retrieves the Appointment Date
     * @return appointmentDate
     */
    String getAppointmentDate(){
        return this.appointmentDate;
    }

    /**
     * Prints Appointment Values of current appointment
     */
    void displayValues() {
        System.out.println("Appointment Id: " + getAppointmentId());
        System.out.println("Appointment Date: " + getAppointmentDate());
        System.out.println("Appointment Description: " + getAppointmentDescription());
    }
}