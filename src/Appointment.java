import java.util.Date;

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
                "appointment id: " + appointmentId +
                ", patient: " + patient.getFirstName() + " " + patient.getLastName() +
                ", typeOfExamination: " + examinationType +
                ", date: " + date +
                ", time: " + time +
                ", doctor: " + " Dr." + doctor.getLastName() +"\n";

    }
}
