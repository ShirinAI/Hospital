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
        appointmentList.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "28-06-2024", "10:00", doctorsList.get(0)));
        appointmentList.add(new Appointment(2, patientsList.get(2), ExaminationType.SECONDARY, "21-06-2024", "12:30", doctorsList.get(1)));
        appointmentList.add(new Appointment(3, patientsList.get(3), ExaminationType.CONSULTATION, "15-06-2024", "10:30", doctorsList.get(2)));
        appointmentList.add(new Appointment(4, patientsList.get(1), ExaminationType.INITIAL, "15-06-2024", "12:30", doctorsList.get(3)));
        appointmentList.add(new Appointment(5, patientsList.get(4), ExaminationType.PROCEDURE, "15-06-2024", "12:30", doctorsList.get(11)));
        appointmentList.add(new Appointment(6, patientsList.get(3), ExaminationType.PROCEDURE, "05-07-2024", "14:00", doctorsList.get(11)));
        appointmentList.add(new Appointment(7, patientsList.get(1), ExaminationType.CONSULTATION, "12-08-2024", "11:30", doctorsList.get(3)));
        appointmentList.add(new Appointment(8, patientsList.get(2), ExaminationType.INITIAL, "20-09-2024", "15:30", doctorsList.get(4)));
        appointmentList.add(new Appointment(9, patientsList.get(0), ExaminationType.SECONDARY, "03-10-2024", "16:30", doctorsList.get(0)));
        appointmentList.add(new Appointment(10, patientsList.get(4), ExaminationType.PROCEDURE, "17-11-2024", "10:30", doctorsList.get(1)));
        appointmentList.add(new Appointment(11, patientsList.get(1), ExaminationType.CONSULTATION, "22-01-2024", "14:00", doctorsList.get(2)));
        appointmentList.add(new Appointment(12, patientsList.get(3), ExaminationType.SECONDARY, "11-02-2024", "12:30", doctorsList.get(3)));
        appointmentList.add(new Appointment(13, patientsList.get(0), ExaminationType.INITIAL, "05-03-2024", "13:30", doctorsList.get(4)));
        appointmentList.add(new Appointment(14, patientsList.get(2), ExaminationType.CONSULTATION, "18-04-2024", "11:00", doctorsList.get(0)));
        appointmentList.add(new Appointment(15, patientsList.get(4), ExaminationType.INITIAL, "30-05-2024", "16:00", doctorsList.get(1)));
        appointmentList.add(new Appointment(16, patientsList.get(1), ExaminationType.PROCEDURE, "14-06-2024", "12:00", doctorsList.get(2)));
        appointmentList.add(new Appointment(17, patientsList.get(3), ExaminationType.SECONDARY, "02-08-2024", "14:30", doctorsList.get(8)));
        appointmentList.add(new Appointment(18, patientsList.get(0), ExaminationType.INITIAL, "09-09-2024", "10:30", doctorsList.get(4)));
        appointmentList.add(new Appointment(19, patientsList.get(2), ExaminationType.PROCEDURE, "23-10-2024", "15:00", doctorsList.get(11)));
        appointmentList.add(new Appointment(20, patientsList.get(4), ExaminationType.CONSULTATION, "06-12-2024", "11:30", doctorsList.get(9)));
        appointmentList.add(new Appointment(21, patientsList.get(1), ExaminationType.SECONDARY, "18-01-2024", "13:00", doctorsList.get(8)));
        appointmentList.add(new Appointment(22, patientsList.get(3), ExaminationType.PROCEDURE, "05-02-2024", "14:30", doctorsList.get(6)));
        appointmentList.add(new Appointment(23, patientsList.get(0), ExaminationType.CONSULTATION, "21-03-2024", "12:00", doctorsList.get(7)));
        appointmentList.add(new Appointment(24, patientsList.get(2), ExaminationType.INITIAL, "03-05-2024", "15:30", doctorsList.get(5)));
        appointmentList.add(new Appointment(25, patientsList.get(4), ExaminationType.SECONDARY, "16-06-2024", "10:00", doctorsList.get(9)));

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
                    int id = ValidatorNumber.validateNumber(scanner);
                    Doctor currentDoctor = UserManager.findDoctorById(id, doctors);
                    System.out.println("Please enter your last name: ");
                    String surname = scanner.nextLine();
                    currentDoctor.doctorLogin(currentDoctor, surname, doctors, appointments, scanner);
                    break;
                case 2:
                    System.out.println("Please enter your patient ID: ");
                    int patientId = ValidatorNumber.validateNumber(scanner);
                    Patient currentPatient = UserManager.findPatientById(patientId, patients);
                    System.out.println("Please enter your first name: ");
                    String patientName = scanner.nextLine();
                    currentPatient.patientLogin(currentPatient, patientId, patientName, patients, appointments, doctors, scanner);
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the options below.");
                    break;
            }
        } while (option != 0);
        scanner.close();
    }
}
