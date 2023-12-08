import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    public static List<Doctor> readDoctorFromFile(Scanner scanner, String fileName){
        List<Doctor> doctors = new ArrayList<>();
        try {
            File file = new File(fileName);
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
        return doctors;
    }
    public static List<Patient> readPatientsFromFile(Scanner scan, String fileName){
        List<Patient> patients = new ArrayList<>();
        try {
            File file = new File(fileName);
            scan = new Scanner(file);
            if (scan.hasNextLine()) {
                scan.nextLine();
            }
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
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
        return patients;
    }
    public static List<Appointment> readAppointmentsFromFile(Scanner scan, String fileName, List<Patient> patients, List<Doctor> doctors){
        List<Appointment> appointments = new ArrayList<>();
        try {
            File file = new File(fileName);
            scan = new Scanner(file);
            if (scan.hasNextLine()) {
                scan.nextLine();
            }
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
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
        return appointments;
    }
}
