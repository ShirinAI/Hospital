import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class AppointmentManagerTest {
    private List<Patient> patientsList;
    private List<Doctor> doctorsList;
    private List<Appointment> appointments;

    @BeforeEach
    void setUp() {
        patientsList = new ArrayList<>();
        doctorsList = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    @Test
    void testBookAppointmentWhenTimeAndDoctorAreAvailable() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        String input = "\nINITIAL\nCARDIOLOGY\n31-12-2023\n12:00\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        AppointmentManager.bookAppointment(patientsList.get(0), appointments, doctorsList, new Scanner(System.in));
        System.setIn(System.in);
        Assert.assertEquals(1, appointments.size());
        Assert.assertEquals(patientsList.get(0), appointments.get(0).getPatient());
        Assert.assertEquals(doctorsList.get(0), appointments.get(0).getDoctor());
    }

    @Test
    void testBookAppointmentWhenMoreThanOneDoctorAvailableWithDifferentSpecialties() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        doctorsList.add(new Doctor(2, "Doctor name test", "Doctor surname test", DoctorSpecialty.ORTHOPEDICS));
        doctorsList.add(new Doctor(3, "Doctor name test", "Doctor surname test", DoctorSpecialty.PSYCHIATRY));
        String input = "\nINITIAL\nPSYCHIATRY\n31-12-2023\n11:30\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        AppointmentManager.bookAppointment(patientsList.get(0), appointments, doctorsList, new Scanner(System.in));
        System.setIn(System.in);
        Assert.assertEquals(doctorsList.get(2), appointments.get(0).getDoctor());
    }

    @Test
    void testBookAppointmentWhenDoctorIsNotAvailable() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        patientsList.add(new Patient(2, "PatientTest2", "PatientTest2", 30));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        appointments.add(new Appointment(1, patientsList.get(1), ExaminationType.INITIAL, "31-12-2023", "12:00", doctorsList.get(0)));
        String input = "\nINITIAL\nCARDIOLOGY\n31-12-2023\n12:00\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        AppointmentManager.bookAppointment(patientsList.get(0), appointments, doctorsList, new Scanner(System.in));
        System.setIn(System.in);
        Assert.assertEquals(1, appointments.size());
        Assert.assertEquals(patientsList.get(1), appointments.get(0).getPatient());
    }

    @Test
    void testBookAppointmentWhenFirstEnteredDateIsWrongFormat() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        String input = "\nINITIAL\nCARDIOLOGY\n31.12.2023\n31-12-2023\n12:00\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        AppointmentManager.bookAppointment(patientsList.get(0), appointments, doctorsList, new Scanner(System.in));
        System.setIn(System.in);
        Assert.assertEquals(1, appointments.size());
        Assert.assertEquals(patientsList.get(0), appointments.get(0).getPatient());
        Assert.assertEquals(doctorsList.get(0), appointments.get(0).getDoctor());
    }

    @Test
    void testBookAppointmentWhenFirstEnteredExaminationTypeAndSpecialtyAreWrong() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        String input = "\nINIT\nINITIAL\nCARD\nCARDIOLOGY\n31-12-2023\n12:00\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        AppointmentManager.bookAppointment(patientsList.get(0), appointments, doctorsList, new Scanner(System.in));
        System.setIn(System.in);
        Assert.assertEquals(1, appointments.size());
        Assert.assertEquals(patientsList.get(0), appointments.get(0).getPatient());
        Assert.assertEquals(doctorsList.get(0), appointments.get(0).getDoctor());
    }

    @Test
    void testDisplayAppointmentForCurrentPatient() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        patientsList.add(new Patient(2, "PatientTest", "PatientTest", 40));
        patientsList.add(new Patient(3, "PatientTest", "PatientTest", 40));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        appointments.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-12-2023", "12:00", doctorsList.get(0)));
        appointments.add(new Appointment(2, patientsList.get(2), ExaminationType.SECONDARY, "31-12-2024", "12:30", doctorsList.get(0)));
        appointments.add(new Appointment(3, patientsList.get(1), ExaminationType.PROCEDURE, "31-11-2024", "10:30", doctorsList.get(0)));
        appointments.add(new Appointment(4, patientsList.get(0), ExaminationType.SECONDARY, "31-01-2024", "10:30", doctorsList.get(0)));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        AppointmentManager.displayAppointmentsForCurrentPatient(1, appointments);
        String expectedOutput = "[Appointment for current patient appointment id: 1, for patient id:1 name: PatientTest PatientTest, examination type: INITIAL, date and time: 31-12-2023, 12:00,  Dr.Doctor surname test\n" +
                ", Appointment for current patient appointment id: 4, for patient id:1 name: PatientTest PatientTest, examination type: SECONDARY, date and time: 31-01-2024, 10:30,  Dr.Doctor surname test\n]";
        assertLinesMatch(expectedOutput.lines(), outputStream.toString().lines());
    }

    @Test
    void testModifyAppointmentWhenAppointmentIsAvailable() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        appointments.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "24-12-2023", "10:00", doctorsList.get(0)));
        String input = "31-12-2023\n12:00\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        AppointmentManager.modifyAppointment(1, appointments, 1, new Scanner(System.in));
        System.setIn(System.in);
        Assert.assertEquals(patientsList.get(0), appointments.get(0).getPatient());
        Assert.assertEquals(doctorsList.get(0), appointments.get(0).getDoctor());
        Assert.assertEquals("31-12-2023", appointments.get(0).getDate());
        Assert.assertEquals("12:00", appointments.get(0).getTime());
    }

    @Test
    void testModifyAppointmentWhenDoctorIsNotAvailable() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        patientsList.add(new Patient(2, "PatientTest2", "PatientTest2", 30));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        appointments.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-12-2023", "12:00", doctorsList.get(0)));
        appointments.add(new Appointment(2, patientsList.get(1), ExaminationType.SECONDARY, "31-12-2023", "11:00", doctorsList.get(0)));
        String input = "31-12-2023\n12:00\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        AppointmentManager.modifyAppointment(2, appointments, 2, new Scanner(System.in));
        System.setIn(System.in);
        Assert.assertEquals(patientsList.get(0), appointments.get(0).getPatient());
        Assert.assertEquals("12:00", appointments.get(0).getTime());
        Assert.assertEquals("11:00", appointments.get(1).getTime());
    }

    @Test
    void testDeleteAppointmentWhenAppointmentIDMatchesPatientId() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        appointments.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-12-2023", "12:00", doctorsList.get(0)));
        AppointmentManager.deleteAppointment(1, 1, appointments);
        System.setIn(System.in);
        Assert.assertEquals(0, appointments.size());
    }

    @Test
    void testDeleteAppointmentWhenAppointmentIDDoesNotMatchPatientId() {
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        patientsList.add(new Patient(2, "PatientTest2", "PatientTest2", 30));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        appointments.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-12-2023", "12:00", doctorsList.get(0)));
        appointments.add(new Appointment(2, patientsList.get(1), ExaminationType.SECONDARY, "31-12-2023", "11:00", doctorsList.get(0)));
        AppointmentManager.deleteAppointment(1, 2, appointments);
        System.setIn(System.in);
        Assert.assertEquals(2, appointments.size());//both appointments remain
    }

}


