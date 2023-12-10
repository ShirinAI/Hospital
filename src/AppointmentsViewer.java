import java.util.*;
import java.util.stream.Collectors;

public class AppointmentsViewer {
    public static void viewCurrentDoctorsAppointments(Doctor currentDoctor, List<Appointment> appointments) {
        System.out.println("All booked appointments for  Dr. " + currentDoctor.getLastName());
        List<Appointment> CurrentDoctorsAppointments = appointments.stream()
                .filter(appointment -> appointment.getDoctor().equals(currentDoctor))
                .toList();
        System.out.println(CurrentDoctorsAppointments);
    }

    public static List<Appointment> sortByPatientNameAscending(Doctor currentDoctor, List<Appointment> appointments) {
        System.out.println("Appointments for Dr. "+ currentDoctor.getLastName() + "sorted by patient name - ascending order");
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().equals(currentDoctor))
                .sorted(Comparator.comparing(appointment -> appointment.getPatient().getFirstName()))
                .toList();

    }
    public static List<Appointment> sortByPatientNameDescending(Doctor currentDoctor, List<Appointment> appointments) {
        System.out.println("Appointments for Dr. "+ currentDoctor.getLastName() + "sorted by patient name - descending order");
        List<Appointment> sortedByName = sortByPatientNameAscending(currentDoctor, appointments);
        List<Appointment> reversedList = new ArrayList<>(sortedByName);
        Collections.reverse(reversedList);
        return reversedList;
    }
    public static List<Appointment> sortByDateAndTimeAscending(Doctor currentDoctor, List<Appointment> appointments) {
        System.out.println("Appointments for Dr. "+ currentDoctor.getLastName() + "sorted by time and date - ascending order");
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().equals(currentDoctor))
                .sorted(Comparator.comparing(Appointment::getDate))
                .sorted(Comparator.comparing(Appointment::getTime))
                .toList();
    }
    public static List<Appointment> sortByDateAndTimeDescending(Doctor currentDoctor, List<Appointment> appointments) {
        System.out.println("Appointments for Dr. "+ currentDoctor.getLastName() + "sorted by time and date - descending order");
        List<Appointment> sortedByDateAndTime= sortByDateAndTimeAscending(currentDoctor, appointments);
        List<Appointment> reversedList = new ArrayList<>(sortedByDateAndTime);
        Collections.reverse(reversedList);
        return reversedList;
    }
    public static List<Appointment> sortByPatientIdAscending(Doctor currentDoctor, List<Appointment> appointments) {
        System.out.println("Appointments for Dr. "+ currentDoctor.getLastName() + "sorted by patient ID - ascending order");
        return appointments.stream()
                .filter(appointment -> appointment.getDoctor().equals(currentDoctor))
                .sorted(Comparator.comparing(appointment -> appointment.getPatient().getPatientId()))
                .toList();
    }
    public static List<Appointment> sortByPatientIdDescending(Doctor currentDoctor, List<Appointment> appointments) {
        System.out.println("Appointments for Dr. "+ currentDoctor.getLastName() + "sorted by patient ID - descending order");
        List<Appointment> sortedByDateAndTime= sortByPatientIdAscending(currentDoctor, appointments);
        List<Appointment> reversedList = new ArrayList<>(sortedByDateAndTime);
        Collections.reverse(reversedList);
        return reversedList;
    }
    public static void changeSortingType(List<Appointment>appointments, Scanner scanner) {
        int choice;
        do {
            System.out.println("Select in what order to sort the appointments:");
            System.out.println("1. Appointment ID number");
            System.out.println("2. Doctor");
            System.out.println("3. Examination Type");
            System.out.println("3. Date and time");
            System.out.println("5. Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Please fill in all fields to book an appointment: ");
                    System.out.println(appointments);
                    break;
                case 2:
                    List<Appointment> sortByDoctor = appointments.stream()
                            .sorted(Comparator.comparing(appointment -> appointment.getDoctor().getFirstName()))
                            .toList();
                    System.out.println(sortByDoctor);
                    break;
                case 3:
                    List<Appointment> sortByExaminationType = appointments.stream()
                            .sorted(Comparator.comparing(Appointment::getExaminationType))
                            .toList();
                    System.out.println(sortByExaminationType);
                    break;
                case 4:
                    List<Appointment> sortedByDateAndTime = appointments.stream()
                            .sorted(Comparator.comparing(Appointment::getDate))
                            .sorted(Comparator.comparing(Appointment::getTime))
                            .toList();
                    System.out.println(sortedByDateAndTime);
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the options below.");
            }
        } while (choice != 5);
    }
}
