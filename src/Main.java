import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
                    displayAppointments(patientId, appointments);
                    break;
                case 3:
                    // Логика за промяна на датата/часа на записан час
                    // modifyAppointment(patientId);
                    break;
                case 4:
                    // cancelAppointment(patientId, appointments);
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
                .filter(Appointment -> Appointment.getPatient().getPatientId() == currentPatientId)
                .toList();
        System.out.println(currentPatientAppointments);
    }
//    public static void cancelAppointment(int currentPatientId, List<Appointment> appointments) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the appointment ID to cancel:");
//        int appointmentIdToCancel = scanner.nextInt();
//
//        Appointment appointmentToCancel = findAppointmentById(appointmentIdToCancel, appointments);
//
//        if (appointmentToCancel != null && appointmentToCancel.getPatient().getPatientId() == currentPatientId) {
//            // Cancel the appointment
//            appointments.remove(appointmentToCancel);  // Assuming appointments is a List
//            System.out.println("Appointment canceled successfully.");
//        } else {
//            System.out.println("Invalid appointment ID or you don't have permission to cancel this appointment.");
//        }
//    }
//    private static Appointment findAppointmentById(int appointmentId, List<Appointment> appointments) {
//        return appointments.stream()
//                .filter(appointment -> appointment.getAppointmentId() == appointmentId)
//                .findFirst()
//                .orElse(null);
//    }

    public static void main(String[] args) {
        Patient[] patientsList = new Patient[5];
        patientsList[0] = new Patient(1, "Sarah", "Jones", 40);
        patientsList[1] = new Patient(2, "John", "Snow", 35);
        patientsList[2] = new Patient(3, "Tim", "Danes", 77);
        patientsList[3] = new Patient(4, "Philip", "Summer", 56);
        patientsList[4] = new Patient(5, "Donna", "Matthews", 57);
        File patientsListFile = new File("patients.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientsListFile))) {
            writer.write("patient_id, first_name, last_name, age");
            writer.newLine();
            for (Patient patient : patientsList) {
                writer.write(patient.getPatientId() + "," + patient.getFirstName() + "," + patient.getLastName() + "," + patient.getAge());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Doctor[] doctorsList = new Doctor[12];
        doctorsList[0] = new Doctor(1, "Gergana", "Georgieva", "Anesthesiology");
        doctorsList[1] = new Doctor(2, "Georgi", "Georgiev", "Internal diseases");
        doctorsList[2] = new Doctor(3, "Ivanka", "Ivanova", "Gastroenterology");
        doctorsList[3] = new Doctor(4, "Ivan", "Ivanov", "Endocrinology");
        doctorsList[4] = new Doctor(5, "Iordanka", "Iordanova", "Cardiology");
        doctorsList[5] = new Doctor(6, "Iordan", "Iordanov", "Dermatology");
        doctorsList[6] = new Doctor(7, "Simona", "Simeonova", "Anesthesiology");
        doctorsList[7] = new Doctor(8, "Simeon", "Simeonov", "Internal diseases");
        doctorsList[8] = new Doctor(9, "Dimitar", "Dimitrov", "Gastroenterology");
        doctorsList[9] = new Doctor(10, "Denitza", "Dimitrova", "Endocrinology");
        doctorsList[10] = new Doctor(11, "Slavka", "Slovakova", "Cardiology");
        doctorsList[11] = new Doctor(12, "Sirma", "Krasimireva", "Dermatology");
        File doctorList = new File("doctors.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(doctorList))) {
            writer.write("doctor_id, first_name, last_name, specialty");
            writer.newLine();
            for (Doctor doctor : doctorsList) {
                writer.write(doctor.getDoctorId() + "," + doctor.getFirstName() + "," + doctor.getLastName() + "," + doctor.getSpecialty());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment(1, patientsList[0], ExaminationType.INITIAL, "28-06-2021", "10:00", doctorsList[0]));
        appointmentList.add(new Appointment(2, patientsList[1], ExaminationType.SECONDARY, "21-06-2021", "12:30", doctorsList[1]));
        appointmentList.add(new Appointment(3, patientsList[3], ExaminationType.CONSULTATION, "15-06-2021", "10:30", doctorsList[2]));
        appointmentList.add(new Appointment(4, patientsList[4], ExaminationType.INITIAL, "15-06-2021", "12:30", doctorsList[3]));
        appointmentList.add(new Appointment(5, patientsList[3], ExaminationType.PROCEDURE, "15-06-2021", "12:30", doctorsList[4]));

        File appointmentsListFile = new File("appointments.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(appointmentsListFile))) {
            writer.write("appointment_id,patient_id,type_of_examination,date,time,doctor_id");
            writer.newLine();
            for (int i = 0; i < appointmentList.size(); i++) {
                writer.write(appointmentList.get(i).getAppointmentId() + "," + appointmentList.get(i).getPatient().getPatientId() + ","
                        + appointmentList.get(i).getExaminationType() + "," + appointmentList.get(i).getDate() + "," + appointmentList.get(i).getTime()
                        + "," + appointmentList.get(i).getDoctor().getDoctorId());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Doctor> doctors = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(System.in);
            File file = new File("doctors.csv");
            scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arr = line.split(",");
                int doctorId = Integer.parseInt(arr[0].trim());
                String firstName = arr[1].trim();
                String lastName = arr[2].trim();
                String specialty = arr[3].trim();
                Doctor doctor = new Doctor(doctorId, firstName, lastName, specialty);
                doctors.add(doctor);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        List<Patient> patients = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(System.in);
            File file = new File("patients.csv");
            scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                //    public Patient(int patientId, String firstName, String lastName, int age)
                String line = scanner.nextLine();
                String[] arr = line.split(",");
                int patientId = Integer.parseInt(arr[0].trim());
                String firstName = arr[1].trim();
                String lastName = arr[2].trim();
                int age = Integer.parseInt(arr[0].trim());
                Patient patient = new Patient(patientId, firstName, lastName, age);
                patients.add(patient);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        List<Appointment> appointments = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(System.in);
            File file = new File("appointments.csv");
            scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arr = line.split(",");
                int appointmentId = Integer.parseInt(arr[0].trim());
                Patient patient = patients.stream()
                        .filter(p -> Integer.toString(p.getPatientId()).equals(arr[1]))
                        .findFirst()
                        .orElse(null);
                ExaminationType examinationType = ExaminationType.valueOf(arr[2].trim());
                String date = arr[3].trim();
                String time = arr[4].trim();
                Doctor doctor = doctors.stream()
                        .filter(p -> Integer.toString(p.getDoctorId()).equals(arr[5]))
                        .findFirst()
                        .orElse(null);
                Appointment appointment = new Appointment(appointmentId, patient, examinationType, date, time, doctor);
                appointments.add(appointment);
            }
        } catch (Exception e) {
            System.out.println(e);
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
