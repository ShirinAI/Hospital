import javax.xml.crypto.Data;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void doctorLogin(int id, String lastName, List<Doctor> doctors, List<Appointment> appointments) {
        Doctor matchingDoctor = null;
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId() == id && doctor.getLastName().equals(lastName)) {
                matchingDoctor = doctor;
                break;
            }
        }
        if (matchingDoctor != null) {
            System.out.println("Login successful. Welcome, Dr. " + matchingDoctor.getFirstName() + " " + matchingDoctor.getLastName());
        } else {
            System.out.println("Invalid login credentials for doctor. Please try again.");
        }
    }

    public static void patientLogin(int id, String firstName, List<Patient> patients, List<Appointment> appointments) {
        Patient matchingPatient = null;

        for (Patient patient : patients) {
            if (patient.getPatientId() == id && patient.getFirstName().equals(firstName)) {
                matchingPatient = patient;
                break;
            }
        }
        if (matchingPatient != null) {
            System.out.println("Login successful. Welcome, " + matchingPatient.getFirstName() + " " + matchingPatient.getLastName());
            patientControls(id, appointments);
        } else {
            System.out.println("Invalid login credentials for patient. Please try again");
        }
    }

    public static void patientControls(int patientId, List<Appointment> appointments) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Please select an option:");
            System.out.println("1. Make a new appointment");
            System.out.println("2. See all booked appointments");
            System.out.println("3. Change date or time of an appointment");
            System.out.println("4. Cancel an appointment");
            System.out.println("5. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Логика за запазване на нов час при лекар
                    break;
                case 2:
                    AppointmentManager.displayAppointments(patientId, appointments);
                    break;
                case 3:
                    System.out.print("Please enter appointment ID to change");
                    int appointmentId = validateNumber(scanner);
                    AppointmentManager.modifyAppointment(patientId,appointments, appointmentId, scanner);
                    break;
                case 4:
                    System.out.print("Please state appointment ID to cancel:");
                    int appointmentIdToDelete = validateNumber(scanner);
                    AppointmentManager.deleteAppointment(patientId, appointmentIdToDelete, appointments);
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the options below.");
            }
        } while (choice != 5);
    }

    private static int validateNumber(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid NUMBER");
            }
        }
    }
    public static void main(String[] args) {
        List<Patient> patientsList = new ArrayList<>();
        patientsList.add(new Patient(1, "Sarah", "Jones", 40));
        patientsList.add(new Patient(2, "John", "Snow", 35));
        patientsList.add(new Patient(3, "Tim", "Danes", 77));
        patientsList.add(new Patient(4, "Philip", "Summer", 56));
        patientsList.add(new Patient(5, "Donna", "Matthews", 57));
        DataWriter.writeToFile(patientsList, "patients.csv");

        List<Doctor> doctorsList = new ArrayList<>();
        doctorsList.add(new Doctor(1, "Gergana", "Georgieva", "Anesthesiology"));
        doctorsList.add(new Doctor(2, "Georgi", "Georgiev", "Internal diseases"));
        doctorsList.add(new Doctor(3, "Ivanka", "Ivanova", "Gastroenterology"));
        doctorsList.add(new Doctor(4, "Ivan", "Ivanov", "Endocrinology"));
        doctorsList.add(new Doctor(5, "Iordanka", "Iordanova", "Cardiology"));
        doctorsList.add(new Doctor(6, "Iordan", "Iordanov", "Dermatology"));
        doctorsList.add(new Doctor(7, "Simona", "Simeonova", "Anesthesiology"));
        doctorsList.add(new Doctor(8, "Simeon", "Simeonov", "Internal diseases"));
        doctorsList.add(new Doctor(9, "Dimitar", "Dimitrov", "Gastroenterology"));
        doctorsList.add(new Doctor(10, "Denitza", "Dimitrova", "Endocrinology"));
        doctorsList.add(new Doctor(11, "Slavka", "Slovakova", "Cardiology"));
        doctorsList.add(new Doctor(12, "Sirma", "Krasimireva", "Dermatology"));
        DataWriter.writeToFile(doctorsList, "doctors.csv");

        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment(1, patientsList.get(0), ExaminationType.INITIAL, "28-06-2021", "10:00", doctorsList.get(0)));
        appointmentList.add(new Appointment(2, patientsList.get(2), ExaminationType.SECONDARY, "21-06-2021", "12:30", doctorsList.get(1)));
        appointmentList.add(new Appointment(3, patientsList.get(3), ExaminationType.CONSULTATION, "15-06-2021", "10:30", doctorsList.get(2)));
        appointmentList.add(new Appointment(4, patientsList.get(1), ExaminationType.INITIAL, "15-06-2021", "12:30", doctorsList.get(3)));
        appointmentList.add(new Appointment(5, patientsList.get(4), ExaminationType.PROCEDURE, "15-06-2021", "12:30", doctorsList.get(4)));
        DataWriter.writeAppointmentsToFile(appointmentList, "appointments.csv");

        Scanner scan = new Scanner(System.in);
        List<Doctor> doctors = DataReader.readDoctorFromFile(scan, "doctors.csv");
        System.out.println("");
        List<Patient> patients = DataReader.readPatientsFromFile(scan, "patients.csv");
        List<Appointment> appointments = DataReader.readAppointmentsFromFile(scan, "appointments.csv", patients, doctors);
        //vhod
        Scanner scanner = new Scanner(System.in);
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
                    int id = validateNumber(scanner);
                    System.out.println("Please enter your last name: ");
                    String surname = scanner.nextLine();
                    doctorLogin(id, surname, doctors, appointments);
                    break;
                case 2:
                    System.out.println("Please enter your patient ID: ");
                    int patientId = validateNumber(scanner);
                    System.out.println("Please enter your first name: ");
                    String patientName = scanner.nextLine();
                    patientLogin(patientId, patientName, patients, appointments);
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the options below.");
                    break;
            }
        } while (option != 0);
        scanner.close();
    }
}
