import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserManagerTest {
    private List<Patient> patientsList;
    private List<Doctor> doctorsList;

    @BeforeEach
    void setUp() {
        patientsList = new ArrayList<>();
        doctorsList = new ArrayList<>();
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        patientsList.add(new Patient(2, "2PatientTest", "2PatientTest", 30));
        patientsList.add(new Patient(3, "3PatientTest", "3PatientTest", 20));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        doctorsList.add(new Doctor(2, "2Doctor name test", "2Doctor surname test", DoctorSpecialty.PSYCHIATRY));
        doctorsList.add(new Doctor(3, "3Doctor name test", "3Doctor surname test", DoctorSpecialty.ORTHOPEDICS));
    }

    @Test
    void testFindPatientById() {
        Patient currentPatient = UserManager.findPatientById(1, patientsList);
        assert currentPatient != null;
        Assertions.assertEquals(1, currentPatient.getPatientId());
        Assertions.assertEquals(patientsList.get(0).getFirstName(), currentPatient.getFirstName());
    }

    @Test
    void testFindPatientByIdWhenIdDoesNotExist() {
        //should return null
        Patient currentPatient = UserManager.findPatientById(10, patientsList);
        Assertions.assertNull(currentPatient);
    }

    @Test
    void testFindDoctorById() {
        Doctor currentDoctor = UserManager.findDoctorById(1, doctorsList);
        assert currentDoctor != null;
        Assertions.assertEquals(1, currentDoctor.getDoctorId());
        Assertions.assertEquals(doctorsList.get(0).getFirstName(), currentDoctor.getFirstName());
    }

    @Test
    void testFindDoctorByIdWhenIdDoesNotExist() {
        Doctor currentDoctor = UserManager.findDoctorById(20, doctorsList);
        Assertions.assertNull(currentDoctor);
    }
}
