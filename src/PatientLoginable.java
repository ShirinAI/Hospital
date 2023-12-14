import java.util.List;
import java.util.Scanner;

public interface PatientLoginable {
    void patientLogin(Patient currentPatient, int id, String firstName, List<Patient> patients, List<Appointment> appointments, List<Doctor> doctors, Scanner scanner);
}
