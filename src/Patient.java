import java.util.List;

public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private int age;
    private List<Appointment> patientAppointments;

    public Patient(int patientId, String firstName, String lastName, int age) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
//    public void setPatientAppointments(List<Appointment> appointments){
//        List<Appointment> currentPatientAppointments = appointments.stream()
//                .filter(Appointment -> Appointment.getPatientId() == patientId)
//                .toList();
//        System.out.println(currentPatientAppointments);
//    }
//
//    public List<Appointment> getPatientAppointments() {
//        return patientAppointments;
//    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", patientAppointments=" + patientAppointments +
                '}';
    }
}
