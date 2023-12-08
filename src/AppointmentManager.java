import java.util.List;
import java.util.Scanner;

public class AppointmentManager {
    public static void bookAppointment(int patientId, List<Patient> patients, List<Appointment> appointments, List<Doctor> doctors, Scanner scanner){
        //Appointment(int appointmentId, Patient patient, ExaminationType examinationType, String date, String time, Doctor doctor)
        int appointmentId = appointments.size();
        Patient currentPatient = null;
        for(Patient patient : patients){
            if(patientId == patient.getPatientId()){
                currentPatient = patient;
            }
        }
        System.out.println("Choose examination type: INITIAL, SECONDARY, CONSULTATION OR PROCEDURE");
        String examination = scanner.nextLine();
        ExaminationType examinationType;
        try {
            examinationType = ExaminationType.valueOf(examination);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid examination type. Please enter a valid value.");
            return;
        }
        System.out.println("Choose doctor speciality:");

        Appointment bookNewAppointment = new Appointment();
    }
    public static void displayAppointments(int currentPatientId, List<Appointment> appointments) {
        List<Appointment> currentPatientAppointments = appointments.stream()
                .filter(Appointment -> Appointment.getPatient().getPatientId() == currentPatientId)
                .toList();
        System.out.println(currentPatientAppointments);
    }
    public static void modifyAppointment(int patientId, List<Appointment> appointments, int appointmentId, Scanner scanner) {
        Appointment appointmentToModify = new Appointment();
        for(Appointment appointment : appointments){
            if(patientId == appointment.getPatient().getPatientId() && appointmentId == appointment.getAppointmentId()){
                appointmentToModify = appointment;
            }
            System.out.println("Please select new date and time for the appointment: \n Date DD.MM.YYYY:");
            String date = scanner.nextLine();
            System.out.println("Time HH:mm:");
            String time = scanner.nextLine();
            //should validate if available
            appointmentToModify.setDate(date);
            appointmentToModify.setTime(time);
            System.out.print("Appointment has successfully been updated: \n" + appointment);
            DataWriter.writeAppointmentsToFile(appointments, "appointments.csv");
            System.out.println(appointments);
            break;
        }
    }

    public static void deleteAppointment(int patientId, int appointmentId, List<Appointment> appointments) {
        boolean appointmentDeleted = appointments.removeIf(appointment ->
                appointment.getPatient().getPatientId() == patientId &&
                        appointment.getAppointmentId() == appointmentId);
        if (appointmentDeleted) {
            DataWriter.writeAppointmentsToFile(appointments, "appointments.csv");
            System.out.println("Appointment ID: " + appointmentId + " for Patient ID: " + patientId + " has been deleted successfully.");
        } else {
            System.out.println("Appointment ID: " + appointmentId + " does not exist for Patient ID: " + patientId + ". No appointment deleted.");
        }
    }
}
