import java.util.List;
import java.util.Scanner;

public class AppointmentManager {
    public static void displayAppointments(int currentPatientId, List<Appointment> appointments) {
        List<Appointment> currentPatientAppointments = appointments.stream()
                .filter(Appointment -> Appointment.getPatient().getPatientId() == currentPatientId)
                .toList();
        System.out.println(currentPatientAppointments);
    }
    public static void modifyAppointment(int patientId, List<Appointment> appointments, int appointmentId, Scanner scanner) {

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
