import java.util.List;
import java.util.Scanner;

public interface DoctorLoginable {
    void doctorLogin(Doctor currentDoctor, String lastName, List<Doctor> doctors, List<Appointment> appointments, Scanner scanner);
}
