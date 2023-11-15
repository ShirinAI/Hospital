import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Patient[] patients = new Patient[5];
        patients[0] = new Patient(1, "Sarah", "Jones", 40);
        patients[1] = new Patient(2, "John", "Snow", 35);
        patients[2] = new Patient(3, "Tim", "Danes", 77);
        patients[3] = new Patient(4, "Philip", "Summer", 56);
        patients[4] = new Patient(5, "Donna", "Matthews", 57);
        File patientsList =  new File("patients.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientsList))){
            writer.write("patient_id, first_name, last_name, age");
            writer.newLine();
            for(Patient patient: patients){
                writer.write(patient.getPatientId() + "," + patient.getFirstName() + "," + patient.getLastName() + "," + patient.getAge());
                writer.newLine();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}