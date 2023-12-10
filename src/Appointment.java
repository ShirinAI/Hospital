import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Appointment {
    private int appointmentId;
    private Patient patient;
    private ExaminationType examinationType;
    private String date;
    private String time;
    private Doctor doctor;


    public Appointment(int appointmentId, Patient patient, ExaminationType examinationType, String date, String time, Doctor doctor) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.examinationType = examinationType;
        this.date = date;
        this.time = time;
        this.doctor = doctor;
    }

    public Appointment() {
        this.appointmentId = 0;
        this.patient = null;
        this.examinationType = null;
        this.date = "0";
        this.time = "0";
        this.doctor = null;
    }
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public ExaminationType getExaminationType() {
        return examinationType;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setExaminationType(ExaminationType examinationType) {
        this.examinationType = examinationType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Appointment for current patient " +
                "appointment id: " +
                appointmentId + ", " + "for patient: " +
               patient.getFirstName() + " " + patient.getLastName()+ ", " + "examination type: "+
                examinationType +", " + "date and time: " +
                date +", " +
                time +", " +
                " Dr." + doctor.getLastName() +"\n";
    }
}
