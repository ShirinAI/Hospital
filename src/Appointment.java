import java.util.Date;

public class Appointment {
    //appointment_id, patient_id, type_of_examination, date, time, doctor_id
    private int appointmentId;
    private int patientId;
  //  private String typeOfExamination;
    private ExaminationType examinationType;
    private String date;
    private String time;
    private int doctorId;


    public Appointment(int appointmentId, int patientId, ExaminationType examinationType, String date, String time, int doctorId) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.examinationType = examinationType;
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public ExaminationType getExaminationType() {
        return examinationType;
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", typeOfExamination='" + examinationType + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", doctorId=" + doctorId +
                '}';
    }
}
