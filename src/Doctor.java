import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Doctor {
    private int doctorId;
    private String firstName;
    private String lastName;
    private String specialty;

    public Doctor(int doctorId, String firstName, String lastName, String specialty) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
    }
    public static void writeDoctorListToFile(Doctor[] doctorsList){
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
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                '}' + "\n";
    }
}
