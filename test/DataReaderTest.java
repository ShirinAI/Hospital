import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReaderTest {
    private List<Patient> patientsList;
    private List<Doctor> doctorsList;
    private List<Appointment> appointments;

    @BeforeEach
    void setUp() {
        patientsList = new ArrayList<>();
        doctorsList = new ArrayList<>();
        appointments = new ArrayList<>();
        doctorsList.add(new Doctor(1, "John", "Doe", DoctorSpecialty.CARDIOLOGY));
        doctorsList.add(new Doctor(2, "Jane", "Jameson", DoctorSpecialty.INTERNAL_DISEASES));
        patientsList.add(new Patient(1, "Sarah", "Jones", 40));
        patientsList.add(new Patient(2, "John", "Snow", 35));
        appointments.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-01-2024", "12:00", doctorsList.get(0)));
        appointments.add(new Appointment(2, patientsList.get(1), ExaminationType.SECONDARY, "31-02-2024", "12:30", doctorsList.get(1)));
    }

    @Test
    void testReadDoctorsFromFile() {
        String testFileName = "test-doctors.txt";
        DataWriter.writeToFile(doctorsList, testFileName);
        List<Doctor> doctors = DataReader.readDoctorFromFile(new Scanner(System.in), testFileName);
        Assertions.assertEquals(2, doctors.size());
        Doctor firstDoctor = doctors.get(0);
        Assertions.assertEquals(1, firstDoctor.getDoctorId());
        Assertions.assertEquals("John", firstDoctor.getFirstName());
        Assertions.assertEquals("Doe", firstDoctor.getLastName());
        Assertions.assertEquals(DoctorSpecialty.CARDIOLOGY, firstDoctor.getSpecialty());
        Doctor secondDoctor = doctors.get(1);
        Assertions.assertEquals(2, secondDoctor.getDoctorId());
        Assertions.assertEquals("Jane", secondDoctor.getFirstName());
        Assertions.assertEquals("Jameson", secondDoctor.getLastName());
        Assertions.assertEquals(DoctorSpecialty.INTERNAL_DISEASES, secondDoctor.getSpecialty());
    }

    @Test
    void testReadPatientsFromFile() {
        String testFileName = "test-patients.txt";
        DataWriter.writeToFile(patientsList, testFileName);
        List<Patient> patients = DataReader.readPatientsFromFile(new Scanner(System.in), testFileName);
        Assertions.assertEquals(2, patients.size());
        Patient firstPatient = patients.get(0);
        Assertions.assertEquals(1, firstPatient.getPatientId());
        Assertions.assertEquals("Sarah", firstPatient.getFirstName());
        Assertions.assertEquals("Jones", firstPatient.getLastName());
        Assertions.assertEquals(40, firstPatient.getAge());
        Patient secondPatient = patients.get(1);
        Assertions.assertEquals(2, secondPatient.getPatientId());
        Assertions.assertEquals("John", secondPatient.getFirstName());
        Assertions.assertEquals("Snow", secondPatient.getLastName());
        Assertions.assertEquals(35, secondPatient.getAge());
    }

    @Test
    void testReadAppointmentsFromFile() {
        String testFileName = "appointments-patients.txt";
        DataWriter.writeAppointmentsToFile(appointments, testFileName);
        List<Appointment> appointments = DataReader.readAppointmentsFromFile(new Scanner(System.in), testFileName, patientsList, doctorsList);
        Assertions.assertEquals(2, appointments.size());
        Assertions.assertEquals(1, appointments.get(0).getAppointmentId());
        Assertions.assertEquals(patientsList.get(0), appointments.get(0).getPatient());
        Assertions.assertEquals(doctorsList.get(0), appointments.get(0).getDoctor());
        Assertions.assertEquals( "31-01-2024", appointments.get(0).getDate());
        Assertions.assertEquals( "12:00", appointments.get(0).getTime());

        Assertions.assertEquals(2, appointments.get(1).getAppointmentId());
        Assertions.assertEquals(patientsList.get(1), appointments.get(1).getPatient());
        Assertions.assertEquals(doctorsList.get(1), appointments.get(1).getDoctor());
        Assertions.assertEquals( "31-02-2024", appointments.get(1).getDate());
        Assertions.assertEquals( "12:30", appointments.get(1).getTime());
    }
}
