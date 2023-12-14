import java.util.Scanner;

public class ValidatorEnum {
    public static ExaminationType validateExaminationType(String examination, Scanner scanner) {
        ExaminationType examinationType = null;
        while (examinationType == null) {
            try {
                System.out.println("Choose examination type: INITIAL, SECONDARY, CONSULTATION, OR PROCEDURE");
                examination = scanner.nextLine().toUpperCase();
                examinationType = ExaminationType.valueOf(examination);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid examination type. Please enter a valid value.");
            }
        }
        return examinationType;
    }

    public static DoctorSpecialty validateDoctorSpecialty(String appointmentSpecialty, Scanner scanner) {
        DoctorSpecialty specialty = null;
        while (specialty == null) {
            try {
                System.out.println("Choose doctor specialty: ANESTHESIOLOGY, INTERNAL_DISEASES, GASTROENTEROLOGY, ENDOCRINOLOGY, CARDIOLOGY, DERMATOLOGY, NEUROLOGY, PSYCHIATRY, RHEUMATOLOGY, GYNECOLOGY, ORTHOPEDICS, OPHTHALMOLOGY, UROLOGY, SURGERY");
                appointmentSpecialty = scanner.nextLine().toUpperCase();
                specialty = DoctorSpecialty.valueOf(appointmentSpecialty);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid doctor specialty. Please enter a valid value from the provided list.");
            }
        }
        return specialty;
    }
}