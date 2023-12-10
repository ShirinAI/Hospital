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

    public static void doctorLogin(Doctor currentDoctor, int id, String lastName, List<Doctor> doctors, List<Appointment> appointments, Scanner scanner) {
        Doctor matchingDoctor = null;
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId() == id && doctor.getLastName().equals(lastName)) {
                matchingDoctor = doctor;
                break;
            }
        }
        if (matchingDoctor != null) {
            System.out.println("Login successful. Welcome, Dr. " + matchingDoctor.getFirstName() + " " + matchingDoctor.getLastName());

        } else {
            System.out.println("Invalid login credentials for doctor. Please try again.");
        }
    }

}
