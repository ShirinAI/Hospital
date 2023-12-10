import javax.print.Doc;
import java.util.List;
import java.util.Scanner;

public class AppointmentManager {
    public static void bookAppointment(Patient currentPatient, List<Appointment> appointments, List<Doctor> doctors, Scanner scanner) {
        int appointmentId = appointments.size()+1;
        System.out.println("Choose examination type: INITIAL, SECONDARY, CONSULTATION, OR PROCEDURE");
        scanner.nextLine();
        String examination = "";
        ExaminationType examinationType = null;
        while (examinationType == null) {
            try {
                examination = scanner.nextLine().toUpperCase();
                examinationType = ExaminationType.valueOf(examination);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid examination type. Please enter a valid value.");
            }
        }

        System.out.println("You selected: " + examinationType);

        String appointmentSpecialty = "";
        DoctorSpecialty specialty = null;

        while (specialty == null) {
            try {
                System.out.println("Choose doctor specialty: ANESTHESIOLOGY, INTERNAL_DISEASES, GASTROENTEROLOGY, ENDOCRINOLOGY, CARDIOLOGY, DERMATOLOGY, NEUROLOGY, PSYCHIATRY, RHEUMATOLOGY, GYNECOLOGY, ORTHOPEDICS, OPHTHALMOLOGY, UROLOGY, SURGERY");
                appointmentSpecialty = scanner.nextLine().toUpperCase();
                specialty = DoctorSpecialty.valueOf(appointmentSpecialty);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid doctor specialty. Please enter a valid value from the provided list.");
            }
        }

        Doctor selectedDoctor = null;
        for (Doctor doctor : doctors) {
            if (specialty.equals(doctor.getSpecialty())) {
                selectedDoctor = doctor;
                break;
            }
        }

        if (selectedDoctor == null) {
            System.out.println("Doctor not found with the specified specialty. Unable to book appointment.");
            return;
        }

        System.out.println("Please select date and time for the appointment:\nDate DD.MM.YYYY:");
        String date = scanner.nextLine();
        System.out.println("Time HH:mm:");
        String time = scanner.nextLine();

        appointments.add(new Appointment(appointmentId, currentPatient, examinationType, date, time, selectedDoctor));
        DataWriter.writeAppointmentsToFile(appointments, "appointments.csv");
        System.out.println("Appointment successfully added: \n" + appointments.get(appointments.size()-1));
    }

    public static void displayAppointments(int currentPatientId, List<Appointment> appointments) {
        List<Appointment> currentPatientAppointments = appointments.stream()
                .filter(Appointment -> Appointment.getPatient().getPatientId() == currentPatientId)
                .toList();
        System.out.println(currentPatientAppointments);
    }
    public static void modifyAppointment(int patientId, List<Appointment> appointments, int appointmentId, Scanner scanner) {
        System.out.println("Current appointments: ");
        displayAppointments(patientId, appointments);
        Appointment appointmentToModify = new Appointment();
        for(Appointment appointment : appointments){
            if(patientId == appointment.getPatient().getPatientId() && appointmentId == appointment.getAppointmentId()){
                appointmentToModify = appointment;
            }
            System.out.println("Please select new date and time for the appointment: \n Date DD.MM.YYYY:");
            String date = scanner.nextLine();
            System.out.println("Time HH:mm:");
            String time = scanner.nextLine();
            //should validate if available
            appointmentToModify.setDate(date);
            appointmentToModify.setTime(time);
            System.out.print("Appointment has successfully been updated: \n" + appointment);
            DataWriter.writeAppointmentsToFile(appointments, "appointments.csv");
            break;
        }
    }

    public static void deleteAppointment(int patientId, int appointmentId, List<Appointment> appointments) {
        System.out.println("Current appointments: ");
        displayAppointments(patientId, appointments);
        boolean appointmentDeleted = appointments.removeIf(appointment ->
                appointment.getPatient().getPatientId() == patientId &&
                        appointment.getAppointmentId() == appointmentId);
        if (appointmentDeleted) {
            DataWriter.writeAppointmentsToFile(appointments, "appointments.csv");
            System.out.println("Appointment ID: " + appointmentId + " for Patient ID: " + patientId + " has been deleted successfully.");
        } else {
            System.out.println("Appointment ID: " + appointmentId + " does not exist for Patient ID: " + patientId + ". No appointment deleted.");
        }
    }
}
