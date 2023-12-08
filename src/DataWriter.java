import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

    public class DataWriter {
        public static <T> void writeToFile(List<T> dataList, String fileName) {
            File dataFile = new File(fileName);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {

                Field[] fields = dataList.get(0).getClass().getDeclaredFields();
                for (Field field : fields) {
                    writer.write(field.getName() + ",");
                }
                writer.newLine();

                for (T data : dataList) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object value = field.get(data);
                        writer.write(value + ",");
                    }
                    writer.newLine();
                }
            } catch (IOException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public static void writeAppointmentsToFile(List<Appointment>appointmentList, String file){
            File appointmentsListFile = new File(file);
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
        }
    }


