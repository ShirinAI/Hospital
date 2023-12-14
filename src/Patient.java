import java.util.List;
import java.util.Scanner;

public class Patient implements PatientLoginable{
    private final int patientId;
    private final String firstName;
    private final String lastName;
    private int age;

    public Patient(int patientId, String firstName, String lastName, int age) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

    }
    @Override
    public void patientLogin(Patient currentPatient, int id, String firstName, List<Patient> patients, List<Appointment> appointments, List<Doctor> doctors, Scanner scanner){
        if (currentPatient != null && currentPatient.getFirstName().equals(firstName)) {
            System.out.println("Login successful. Welcome, " + currentPatient.getFirstName() + " " + currentPatient.getLastName());
            PatientControls.patientControls(currentPatient, id, appointments, patients, doctors, scanner);
        } else {
            System.out.println("Invalid login credentials for patient. Please try again");
        }
    }
    public int getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age + "}";
    }
}
