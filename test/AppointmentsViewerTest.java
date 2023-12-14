import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class AppointmentsViewerTest {
    private List<Patient> patientsList;
    private List<Doctor> doctorsList;
    private List<Appointment> appointments;

    @BeforeEach
    void setUp() {
        patientsList = new ArrayList<>();
        doctorsList = new ArrayList<>();
        appointments = new ArrayList<>();
        patientsList.add(new Patient(1, "PatientTest", "PatientTest", 40));
        patientsList.add(new Patient(2, "2PatientTest", "2PatientTest", 40));
        patientsList.add(new Patient(3, "3PatientTest", "3PatientTest", 40));
        doctorsList.add(new Doctor(1, "Doctor name test", "Doctor surname test", DoctorSpecialty.CARDIOLOGY));
        doctorsList.add(new Doctor(2, "2Doctor name test", "2Doctor surname test", DoctorSpecialty.PSYCHIATRY));
        doctorsList.add(new Doctor(3, "3Doctor name test", "3Doctor surname test", DoctorSpecialty.ORTHOPEDICS));
        appointments.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-01-2024", "12:00", doctorsList.get(0)));
        appointments.add(new Appointment(2, patientsList.get(2), ExaminationType.SECONDARY, "31-02-2024", "12:30", doctorsList.get(1)));
        appointments.add(new Appointment(3, patientsList.get(1), ExaminationType.PROCEDURE, "31-03-2024", "10:30", doctorsList.get(2)));
        appointments.add(new Appointment(4, patientsList.get(0), ExaminationType.SECONDARY, "31-04-2024", "10:00", doctorsList.get(0)));

    }

    @Test
    void testViewCurrentDoctorsAppointments() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        AppointmentsViewer.viewCurrentDoctorsAppointments(doctorsList.get(1), appointments);
        String expectedOutput = "All booked appointments for  Dr. 2Doctor surname test\n" +
                "[Appointment for current patient appointment id: 2, for patient id:3 name: 3PatientTest 3PatientTest, examination type: SECONDARY, date and time: 31-02-2024, 12:30,  Dr.2Doctor surname test\n" +
                "]";
        assertLinesMatch(expectedOutput.lines(), outputStream.toString().lines());
    }

    @Test
    void testSortByPatientNameAscending() {
        List<Appointment> resultSortedAppointments = AppointmentsViewer.sortByPatientNameAscending(doctorsList.get(0), appointments);
        List<Appointment> expectedOrder = List.of(
                new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-01-2024", "12:00", doctorsList.get(0)),
                new Appointment(4, patientsList.get(0), ExaminationType.SECONDARY, "31-04-2024", "10:00", doctorsList.get(0))
        );

        Assertions.assertEquals(expectedOrder.size(), resultSortedAppointments.size());
        Assertions.assertEquals(expectedOrder.get(0).getPatient().getFirstName(), resultSortedAppointments.get(0).getPatient().getFirstName());
        Assertions.assertEquals(expectedOrder.get(1).getPatient().getFirstName(), resultSortedAppointments.get(1).getPatient().getFirstName());
    }
    @Test
    void testSortByPatientNameDescending() {
        List<Appointment> resultSortedAppointments = AppointmentsViewer.sortByPatientNameAscending(doctorsList.get(0), appointments);
        List<Appointment> expectedOrder = List.of(
                new Appointment(4, patientsList.get(0), ExaminationType.SECONDARY, "31-04-2024", "10:00", doctorsList.get(0)),
                new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-01-2024", "12:00", doctorsList.get(0))
        );
        Assertions.assertEquals(expectedOrder.size(), resultSortedAppointments.size());
        Assertions.assertEquals(expectedOrder.get(1).getPatient().getFirstName(), resultSortedAppointments.get(1).getPatient().getFirstName());
        Assertions.assertEquals(expectedOrder.get(0).getPatient().getFirstName(), resultSortedAppointments.get(0).getPatient().getFirstName());
    }
    @Test
    void testSortByPatientIdAscending() {
        List<Appointment> resultSortedAppointments = AppointmentsViewer.sortByPatientNameAscending(doctorsList.get(0), appointments);
        List<Appointment> expectedOrder = List.of(
                new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-01-2024", "12:00", doctorsList.get(0)),
                new Appointment(4, patientsList.get(0), ExaminationType.SECONDARY, "31-04-2024", "10:00", doctorsList.get(0))
        );

        Assertions.assertEquals(expectedOrder.size(), resultSortedAppointments.size());
        Assertions.assertEquals(expectedOrder.get(0).getPatient().getFirstName(), resultSortedAppointments.get(0).getPatient().getFirstName());
        Assertions.assertEquals(expectedOrder.get(1).getPatient().getFirstName(), resultSortedAppointments.get(1).getPatient().getFirstName());
    }
    @Test
    void testSortByPatientIdDescending() {
        List<Appointment> resultSortedAppointments = AppointmentsViewer.sortByPatientNameAscending(doctorsList.get(0), appointments);
        List<Appointment> expectedOrder = List.of(
                new Appointment(4, patientsList.get(0), ExaminationType.SECONDARY, "31-04-2024", "10:00", doctorsList.get(0)),
                new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-01-2024", "12:00", doctorsList.get(0))
        );
        Assertions.assertEquals(expectedOrder.size(), resultSortedAppointments.size());
        Assertions.assertEquals(expectedOrder.get(1).getPatient().getFirstName(), resultSortedAppointments.get(1).getPatient().getFirstName());
        Assertions.assertEquals(expectedOrder.get(0).getPatient().getFirstName(), resultSortedAppointments.get(0).getPatient().getFirstName());
    }
    @Test
    void testSortByDateAscending() {
        List<Appointment> resultSortedAppointments = AppointmentsViewer.sortByDateAndTimeAscending(doctorsList.get(0), appointments);
        List<Appointment> expectedOrder = List.of(
                new Appointment(4, patientsList.get(0), ExaminationType.SECONDARY, "31-04-2024", "10:00", doctorsList.get(0)),
                new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-01-2024", "12:00", doctorsList.get(0))
        );
        Assertions.assertEquals(expectedOrder.size(), resultSortedAppointments.size());
        Assertions.assertEquals(expectedOrder.get(1).getPatient().getFirstName(), resultSortedAppointments.get(1).getPatient().getFirstName());
        Assertions.assertEquals(expectedOrder.get(0).getPatient().getFirstName(), resultSortedAppointments.get(0).getPatient().getFirstName());
    }
    @Test
    void testSortByDateDescending() {
        List<Appointment> resultSortedAppointments = AppointmentsViewer.sortByDateAndTimeDescending(doctorsList.get(0), appointments);
        List<Appointment> expectedOrder = List.of(
                new Appointment(4, patientsList.get(0), ExaminationType.SECONDARY, "31-04-2024", "10:00", doctorsList.get(0)),
                new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "31-01-2024", "12:00", doctorsList.get(0))
        );
        Assertions.assertEquals(expectedOrder.size(), resultSortedAppointments.size());
        Assertions.assertEquals(expectedOrder.get(0).getPatient().getFirstName(), resultSortedAppointments.get(0).getPatient().getFirstName());
        Assertions.assertEquals(expectedOrder.get(1).getPatient().getFirstName(), resultSortedAppointments.get(1).getPatient().getFirstName());
    }
}
