import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ValidatorEnumTest {
    @Test
   void testValidateExaminationType(){
        String input = "initial";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ExaminationType actualResult = ValidatorEnum.validateExaminationType(input, new Scanner(System.in));
        Assertions.assertEquals(ExaminationType.INITIAL, actualResult);
    }
    @Test
    void testValidateExaminationTypeWhenFirstLineIsIncorrect(){
        String input = "init\nsecondary";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ExaminationType actualResult = ValidatorEnum.validateExaminationType(input, new Scanner(System.in));
        Assertions.assertEquals(ExaminationType.SECONDARY, actualResult);
    }
    @Test
    void testValidateDoctorSpecialty(){
        String input = "ANESTHESIOLOGY";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        DoctorSpecialty actualResult = ValidatorEnum.validateDoctorSpecialty(input, new Scanner(System.in));
        Assertions.assertEquals(DoctorSpecialty.ANESTHESIOLOGY, actualResult);
    }
    @Test
    void testValidateDoctorSpecialtyWhenFirstLineIsIncorrect(){
        String input = "ortho\nORTHOPEDICS";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        DoctorSpecialty actualResult = ValidatorEnum.validateDoctorSpecialty(input, new Scanner(System.in));
        Assertions.assertEquals(DoctorSpecialty.ORTHOPEDICS, actualResult);
    }
}
