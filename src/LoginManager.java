import java.util.List;
import java.util.Scanner;

public class LoginManager {
    public static void patientLogin(Patient currentPatient, int id, String firstName, List<Patient> patients, List<Appointment> appointments, List<Doctor> doctors,Scanner scanner) {
        if (currentPatient != null && currentPatient.getFirstName().equals(firstName)) {
            System.out.println("Login successful. Welcome, " + currentPatient.getFirstName() + " " + currentPatient.getLastName());
            PatientControls.patientControls(currentPatient, id, appointments, patients, doctors, scanner);
        } else {
            System.out.println("Invalid login credentials for patient. Please try again");
        }
    }

    public static void doctorLogin(Doctor currentDoctor, String lastName, List<Doctor> doctors, List<Appointment> appointments, Scanner scanner) {
        if (currentDoctor != null && currentDoctor.getLastName().equals(lastName)) {
            System.out.println("Login successful. Welcome, Dr. " + currentDoctor.getFirstName() + " " + currentDoctor.getLastName());
            DoctorControls.doctorControls(currentDoctor, doctors, appointments, scanner);
        } else {
            System.out.println("Invalid login credentials for doctor. Please try again.");
        }
    }

}
