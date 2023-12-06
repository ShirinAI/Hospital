import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void doctorLogin(int id, String lastName, Doctor[] doctors, List<Appointment> appointments) {
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

    public static void patientLogin(int id, String firstName, Patient[] patients, List<Appointment> appointments) {
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
                    // Логика за визуализация на всички записани часове
                    displayAppointments(patientId, appointments);
                    break;
                case 3:
                    // Логика за промяна на датата/часа на записан час
                    // modifyAppointment(patientId);
                    break;
                case 4:
                    // Логика за отказване от записан час
                    //cancelAppointment(patientId);
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the options below.");
            }
        } while (choice != 5);
    }

    public static void displayAppointments(int currentPatientId, List<Appointment> appointments) {
        List<Appointment> currentPatientAppointments = appointments.stream()
                .filter(Appointment -> Appointment.getPatientId() == currentPatientId)
                .toList();
        System.out.println(currentPatientAppointments);
    }

    public static void main(String[] args) {
        Patient[] patients = new Patient[5];
        patients[0] = new Patient(1, "Sarah", "Jones", 40);
        patients[1] = new Patient(2, "John", "Snow", 35);
        patients[2] = new Patient(3, "Tim", "Danes", 77);
        patients[3] = new Patient(4, "Philip", "Summer", 56);
        patients[4] = new Patient(5, "Donna", "Matthews", 57);
        File patientsList = new File("patients.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientsList))) {
            writer.write("patient_id, first_name, last_name, age");
            writer.newLine();
            for (Patient patient : patients) {
                writer.write(patient.getPatientId() + "," + patient.getFirstName() + "," + patient.getLastName() + "," + patient.getAge());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Doctor[] doctors = new Doctor[12];
        doctors[0] = new Doctor(1, "Gergana", "Georgieva", "Anesthesiology");
        doctors[1] = new Doctor(2, "Georgi", "Georgiev", "Internal diseases");
        doctors[2] = new Doctor(3, "Ivanka", "Ivanova", "Gastroenterology");
        doctors[3] = new Doctor(4, "Ivan", "Ivanov", "Endocrinology");
        doctors[4] = new Doctor(5, "Iordanka", "Iordanova", "Cardiology");
        doctors[5] = new Doctor(6, "Iordan", "Iordanov", "Dermatology");
        doctors[6] = new Doctor(7, "Simona", "Simeonova", "Anesthesiology");
        doctors[7] = new Doctor(8, "Simeon", "Simeonov", "Internal diseases");
        doctors[8] = new Doctor(9, "Dimitar", "Dimitrov", "Gastroenterology");
        doctors[9] = new Doctor(10, "Denitza", "Dimitrova", "Endocrinology");
        doctors[10] = new Doctor(11, "Slavka", "Slovakova", "Cardiology");
        doctors[11] = new Doctor(12, "Sirma", "Krasimireva", "Dermatology");
        File doctorList = new File("doctors.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(doctorList))) {
            writer.write("doctor_id, first_name, last_name, specialty");
            writer.newLine();
            for (Doctor doctor : doctors) {
                writer.write(doctor.getDoctorId() + "," + doctor.getFirstName() + "," + doctor.getLastName() + "," + doctor.getSpecialty());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1, 1, ExaminationType.INITIAL, "28-06-2021", "10:00", 2));
        appointments.add(new Appointment(2, 5, ExaminationType.SECONDARY, "21-06-2021", "12:30", 5));
        appointments.add(new Appointment(3, 3, ExaminationType.CONSULTATION, "15-06-2021", "10:30", 2));
        appointments.add(new Appointment(4, 4, ExaminationType.INITIAL, "15-06-2021", "12:30", 4));
        appointments.add(new Appointment(5, 2, ExaminationType.PROCEDURE, "15-06-2021", "12:30", 7));

        File appointmentsList = new File("appointments.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(appointmentsList))) {
            writer.write("appointment_id,patient_id,type_of_examination,date,time,doctor_id");
            writer.newLine();
            for (int i = 0; i < appointments.size(); i++) {
                writer.write(appointments.get(i).getAppointmentId() + "," + appointments.get(i).getPatientId() + ","
                        + appointments.get(i).getExaminationType() + "," + appointments.get(i).getDate() + "," + appointments.get(i).getTime()
                        + "," + appointments.get(i).getDoctorId());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //VHOD
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
                    int id = 0;
                    while (true) {
                        try {
                            id = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a NUMBER.");
                        }
                    }
                    System.out.println("Please enter your last name: ");
                    String surname = scanner.nextLine();
                    doctorLogin(id, surname, doctors, appointments);
                    break;
                case 2:
                    System.out.println("Please enter your patient ID: ");
                    int patientId = 0;
                    while (true) {
                        try {
                            patientId = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a NUMBER.");
                        }
                    }
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
