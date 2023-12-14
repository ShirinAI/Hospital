import java.util.List;
import java.util.Scanner;

public class Doctor implements DoctorLoginable {
    private final int doctorId;
    private final String firstName;
    private final String lastName;
    private final DoctorSpecialty specialty;
    public Doctor(int doctorId, String firstName, String lastName, DoctorSpecialty specialty) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
    }
    @Override
    public void doctorLogin(Doctor currentDoctor, String lastName, List<Doctor> doctors, List<Appointment> appointments, Scanner scanner) {
        if (currentDoctor != null && currentDoctor.getLastName().equals(lastName)) {
            System.out.println("Login successful. Welcome, Dr. " + getFirstName() + " " + getLastName());
            DoctorControls.doctorControls(currentDoctor, appointments, scanner);
        } else {
            System.out.println("Invalid login credentials for doctor. Please try again.");
        }
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public DoctorSpecialty getSpecialty() {
        return specialty;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                '}' + "\n";
    }
}
