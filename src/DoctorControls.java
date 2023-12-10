import java.util.List;
import java.util.Scanner;

public class DoctorControls {
    public static void doctorControls(Doctor currentDoctor, List<Doctor> doctors, List<Appointment> appointments, Scanner scanner) {
        int choice;
//        Визуализиране на всички запазени часове от пациентите за даден лекар (ако не е въведено doctor_id, по подразбиране се извежда информация за текущия лекар, влязъл в системата)
//        Сортиране на всички запазени часове на даден лекар (ако не е въведено doctor_id, по подразбиране се извежда информация за текущия лекар, влязъл в системата) по:
//        име на пациента (във възходящ и низходящ ред)
//        записан час за преглед на пациента (във възходящ и низходящ ред)
//        пациентско ид (във възходящ и низходящ ред)
//        Системата трябва да поддържа опцията за групиране на пациентите по:
//        име на специалиста, при който са запазили час
//        отделение
//        дата на посещение


        do {
            System.out.println("Please select an option:");
            System.out.println("1. See all appointments booked with me");
            System.out.println("2. Sort appointments by patient name - ascending order");
            System.out.println("3. Sort appointments by patient name - descending order");
            System.out.println("4. Sort appointments by time and date - ascending order");
            System.out.println("5. Sort appointments by time and date - descending order");
            System.out.println("6. Sort appointments by patient ID - ascending order");
            System.out.println("7. Sort appointments by patient ID - descending order");
            System.out.println("8. See all booked appointments - sort by doctor, examination type or date");
            System.out.println("9. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    AppointmentsViewer.viewCurrentDoctorsAppointments(currentDoctor, appointments);
                    break;
                case 2:
                    List<Appointment> sortedByName = AppointmentsViewer.sortByPatientNameAscending(currentDoctor, appointments);
                    System.out.println(sortedByName);
                    break;
                case 3:
                    List<Appointment> sortedByNameReversed = AppointmentsViewer.sortByPatientNameDescending(currentDoctor, appointments);
                    System.out.println(sortedByNameReversed);
                    break;
                case 4:
                    List<Appointment> sortedByTimeAndDate = AppointmentsViewer.sortByDateAndTimeAscending(currentDoctor, appointments);
                    System.out.println(sortedByTimeAndDate);
                    break;
                case 5:
                    List<Appointment> sortedByTimeAndDateDescending = AppointmentsViewer.sortByDateAndTimeDescending(currentDoctor, appointments);
                    System.out.println(sortedByTimeAndDateDescending);
                    break;
                case 6:
                    List<Appointment> sortedByPatientId = AppointmentsViewer.sortByPatientIdAscending(currentDoctor, appointments);
                    System.out.println(sortedByPatientId);
                    break;
                case 7:
                    List<Appointment> sortedByPatientIdDescending = AppointmentsViewer.sortByPatientIdDescending(currentDoctor, appointments);
                    System.out.println(sortedByPatientIdDescending);
                    break;
                case 8:
                    AppointmentsViewer.changeSortingType(appointments, scanner);
                    break;
                case 9:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the options below.");
            }
        } while (choice != 9);
    }
}
