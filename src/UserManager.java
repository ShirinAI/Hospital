import java.util.List;

public class UserManager {
    public static Patient findPatientById(int patientId, List<Patient> patients) {
        for (Patient patient : patients) {
            if (patientId == patient.getPatientId()) {
                return patient;
            }
        }
        return null;
    }
    public static Doctor findDoctorById(int id, List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            if (id == doctor.getDoctorId()) {
                return doctor;
            }
        }
        return null;
    }
}
