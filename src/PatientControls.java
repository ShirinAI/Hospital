import java.util.List;
import java.util.Scanner;

public class PatientControls {
    public static void patientControls(Patient currentPatient, int patientId, List<Appointment> appointments, List<Patient>patients, List<Doctor>doctors, Scanner scanner) {
        int choice;

        do {
            System.out.println("Please select an option:");
            System.out.println("1. Make a new appointment");
            System.out.println("2. See all booked appointments");
            System.out.println("3. Change date or time of an appointment");
            System.out.println("4. Cancel an appointment");
            System.out.println("5. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Please fill in all fields to book an appointment: ");
                    AppointmentManager.bookAppointment(currentPatient, appointments, doctors, scanner);
                    break;
                case 2:
                    AppointmentManager.displayAppointments(patientId, appointments);
                    break;
                case 3:
                    System.out.print("Please enter appointment ID to change");
                    int appointmentId = NumberValidator.validateNumber(scanner);
                    AppointmentManager.modifyAppointment(patientId,appointments, appointmentId, scanner);
                    break;
                case 4:
                    System.out.print("Please state appointment ID to cancel:");
                    int appointmentIdToDelete = NumberValidator.validateNumber(scanner);
                    AppointmentManager.deleteAppointment(patientId, appointmentIdToDelete, appointments);
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the options below.");
            }
        } while (choice != 5);
    }
}
