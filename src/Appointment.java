import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Appointment {
    private final int appointmentId;
    private final Patient patient;
    private final ExaminationType examinationType;
    private String date;
    private String time;
    private final Doctor doctor;


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

    public ExaminationType getExaminationType() {
        return examinationType;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
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
    public String getSortableDate() {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
            return outputFormat.format(inputFormat.parse(date));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date, e);
        }
    }
    @Override
    public String toString() {
        return "Appointment for current patient " +
                "appointment id: " +
                appointmentId + ", " + "for patient id:" + patient.getPatientId() + " name: " +
                patient.getFirstName() + " " + patient.getLastName() + ", " + "examination type: " +
                examinationType + ", " + "date and time: " +
                date + ", " +
                time + ", " +
                " Dr." + doctor.getLastName() + "\n";
    }
}
