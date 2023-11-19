import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void doctorLogin(int id, String lastName, Doctor[] doctors){
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
            System.out.println("Invalid login credentials for doctor.");
        }
    }
    public static void patientLogin(int id, String firstName, Patient[] patients){
        Patient matchingPatient = null;

        for (Patient patient : patients) {
            if (patient.getPatientId() == id && patient.getFirstName().equals(firstName)) {
                matchingPatient = patient;
                break;
            }
        }
        if (matchingPatient != null) {
            System.out.println("Login successful. Welcome, " + matchingPatient.getFirstName() + " " + matchingPatient.getLastName());
        } else {
            System.out.println("Invalid login credentials for patient.");
        }
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
        appointments.add(new Appointment(1, 1, "initial", "28-06-2021", "10:00", 2, true));
        appointments.add(new Appointment(2, 5, "secondary", "21-06-2021", "12:30", 5, true));
        appointments.add(new Appointment(3, 3, "consultation", "15-06-2021", "10:30", 2, true));
        appointments.add(new Appointment(4, 4, "initial", "15-06-2021", "12:30", 4, true));
        appointments.add(new Appointment(5, 2, "procedure", "15-06-2021", "12:30", 7, true));

        File appointmentsList = new File("appointments.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(appointmentsList))) {
            writer.write("appointment_id,patient_id,type_of_examination,date,time,doctor_id");
            writer.newLine();
            for (int i = 0; i < appointments.size(); i++) {
                writer.write(appointments.get(i).getAppointmentId() + "," + appointments.get(i).getPatientId() + ","
                        + appointments.get(i).getTypeOfExamination() + "," + appointments.get(i).getDate() + "," + appointments.get(i).getTime()
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
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter your last name: ");
                    String surname = scanner.nextLine();
                    doctorLogin(id, surname, doctors);
                    System.out.println("I am a doctor");
                    break;
                case 2:
                    System.out.println("Please enter your patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter your first name: ");
                    String patientName = scanner.nextLine();
                    patientLogin(patientId, patientName, patients);
                    System.out.println("I am a patient");
                    break;

                default:
                    System.out.println("Invalid option. Please choose from the options below.");
                    break;
            }
        } while (option != 0);
    }


}
