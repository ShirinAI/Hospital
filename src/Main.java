import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Patient> patientsList = new ArrayList<>();
        patientsList.add(new Patient(1, "Sarah", "Jones", 40));
        patientsList.add(new Patient(2, "John", "Snow", 35));
        patientsList.add(new Patient(3, "Tim", "Danes", 77));
        patientsList.add(new Patient(4, "Philip", "Summer", 56));
        patientsList.add(new Patient(5, "Donna", "Matthews", 57));

        List<Doctor> doctorsList = new ArrayList<>();
        doctorsList.add(new Doctor(1, "Gergana", "Georgieva", DoctorSpecialty.CARDIOLOGY));
        doctorsList.add(new Doctor(2, "Georgi", "Georgiev", DoctorSpecialty.INTERNAL_DISEASES));
        doctorsList.add(new Doctor(3, "Ivanka", "Ivanova", DoctorSpecialty.GASTROENTEROLOGY));
        doctorsList.add(new Doctor(4, "Ivan", "Ivanov", DoctorSpecialty.ENDOCRINOLOGY));
        doctorsList.add(new Doctor(5, "Iordanka", "Iordanova",DoctorSpecialty.CARDIOLOGY));
        doctorsList.add(new Doctor(6, "Iordan", "Iordanov", DoctorSpecialty.DERMATOLOGY));
        doctorsList.add(new Doctor(7, "Simona", "Simeonova", DoctorSpecialty.GYNECOLOGY));
        doctorsList.add(new Doctor(8, "Simeon", "Simeonov", DoctorSpecialty.NEUROLOGY));
        doctorsList.add(new Doctor(9, "Dimitar", "Dimitrov", DoctorSpecialty.OPHTHALMOLOGY));
        doctorsList.add(new Doctor(10, "Denitza", "Dimitrova", DoctorSpecialty.ANESTHESIOLOGY));
        doctorsList.add(new Doctor(11, "Slavka", "Slovakova", DoctorSpecialty.SURGERY));
        doctorsList.add(new Doctor(12, "Sirma", "Krasimireva", DoctorSpecialty.ORTHOPEDICS));

        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "28-06-2021", "10:00", doctorsList.get(0)));
        appointmentList.add(new Appointment(2, patientsList.get(2), ExaminationType.SECONDARY, "21-06-2021", "12:30", doctorsList.get(1)));
        appointmentList.add(new Appointment(3, patientsList.get(3), ExaminationType.CONSULTATION, "15-06-2021", "10:30", doctorsList.get(2)));
        appointmentList.add(new Appointment(4, patientsList.get(1), ExaminationType.INITIAL, "15-06-2021", "12:30", doctorsList.get(3)));
        appointmentList.add(new Appointment(5, patientsList.get(4), ExaminationType.PROCEDURE, "15-06-2021", "12:30", doctorsList.get(4)));

        DataWriter.writeToFile(patientsList, "patients.csv");
        DataWriter.writeToFile(doctorsList, "doctors.csv");
        DataWriter.writeAppointmentsToFile(appointmentList, "appointments.csv");

        Scanner scanner = new Scanner(System.in);
        List<Doctor> doctors = DataReader.readDoctorFromFile(scanner, "doctors.csv");
        List<Patient> patients = DataReader.readPatientsFromFile(scanner, "patients.csv");
        List<Appointment> appointments = DataReader.readAppointmentsFromFile(scanner, "appointments.csv", patients, doctors);

        int option;
        do {
            System.out.println("Select an option:");
            System.out.println("1. Login as doctor");
            System.out.println("2. Login as patient");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Please enter your doctor's ID: ");
                    int id = NumberValidator.validateNumber(scanner);
                    System.out.println("Please enter your last name: ");
                    String surname = scanner.nextLine();
                    LoginManager.doctorLogin(id, surname, doctors, appointments, scanner);
                    break;
                case 2:
                    System.out.println("Please enter your patient ID: ");
                    int patientId = NumberValidator.validateNumber(scanner);
                    System.out.println("Please enter your first name: ");
                    String patientName = scanner.nextLine();
                    LoginManager.patientLogin(patientId, patientName, patients, appointments, doctors, scanner);
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the options below.");
                    break;
            }
        } while (option != 0);
        scanner.close();
    }
}
