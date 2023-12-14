import java.util.List;
import java.util.Scanner;

public class AppointmentManager {
    public static void bookAppointment(Patient currentPatient, List<Appointment> appointments, List<Doctor> doctors, Scanner scanner) {
        int appointmentId = appointments.size() + 1;
        scanner.nextLine();
        String examination = "";
        ExaminationType examinationType = ValidatorEnum.validateExaminationType(examination, scanner);
        System.out.println("You selected: " + examinationType);
        String appointmentSpecialty = "";
        DoctorSpecialty specialty = ValidatorEnum.validateDoctorSpecialty(appointmentSpecialty, scanner);
        System.out.println("You selected: " + specialty);
        Doctor selectedDoctor = findDoctorBySpecialty(specialty, doctors);
        String date = ValidatorDate.validateDateFormatAndDate(scanner);
        System.out.println("Time HH:mm:");
        String time = ValidatorTime.validateTimeFormat(scanner);
        if (!isAppointmentAvailable(date, time, selectedDoctor, appointments)) {
            return;
        }
        appointments.add(new Appointment(appointmentId, currentPatient, examinationType, date, time, selectedDoctor));
        DataWriter.writeAppointmentsToFile(appointments, "appointments.csv");
        System.out.println("Appointment successfully added: \n" + appointments.get(appointments.size() - 1));
    }

    public static void displayAppointmentsForCurrentPatient(int currentPatientId, List<Appointment> appointments) {
        List<Appointment> currentPatientAppointments = appointments.stream()
                .filter(Appointment -> Appointment.getPatient().getPatientId() == currentPatientId)
                .toList();
        System.out.println(currentPatientAppointments);
    }

    public static void modifyAppointment(int patientId, List<Appointment> appointments, int appointmentId, Scanner scanner) {
        Appointment appointmentToModify = null;

        for (Appointment appointment : appointments) {
            if (patientId == appointment.getPatient().getPatientId() && appointmentId == appointment.getAppointmentId()) {
                appointmentToModify = appointment;
                break;
            }
        }
        if (appointmentToModify == null) {
            System.out.println("Appointment not found for modification.");
            return;
        }
        System.out.println("Please select new date and time for the appointment:");
        System.out.println("Date DD-MM-YYYY:");
        String date = ValidatorDate.validateDateFormatAndDate(scanner);
        System.out.println("Time HH:mm:");
        String time = ValidatorTime.validateTimeFormat(scanner);

        if (!isAppointmentAvailable(date, time, appointmentToModify.getDoctor(), appointments)) {
            return;
        }

        appointmentToModify.setDate(date);
        appointmentToModify.setTime(time);
        System.out.println("Appointment has successfully been updated:\n" + appointmentToModify);
        DataWriter.writeAppointmentsToFile(appointments, "appointments.csv");
    }


    public static void deleteAppointment(int patientId, int appointmentId, List<Appointment> appointments) {
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

    private static boolean isAppointmentAvailable(String date, String time, Doctor selectedDoctor, List<Appointment> appointments) {
        for (Appointment existingAppointment : appointments) {
            if (existingAppointment.getDate().equals(date)
                    && existingAppointment.getTime().equals(time)
                    && existingAppointment.getDoctor().equals(selectedDoctor)) {
                System.out.println("Time and date not available for this doctor, please select a different time and date. Booking was NOT added or modified.");
                return false;
            }
        }
        return true;
    }

    private static Doctor findDoctorBySpecialty(DoctorSpecialty specialty, List<Doctor> doctors) {
        Doctor selectedDoctor = null;
        while (selectedDoctor == null) {
            for (Doctor doctor : doctors) {
                if (specialty.equals(doctor.getSpecialty())) {
                    selectedDoctor = doctor;
                    break;
                }
            }
            if (selectedDoctor == null) {
                System.out.println("Doctor not found with the specified specialty. Unable to book appointment. Please choose different specialty");
            }
        }
        return selectedDoctor;
    }
}
