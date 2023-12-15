import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ValidatorNumberTest {
    @Test
    void testValidateNumber(){
        String input = "4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int actualResult = ValidatorNumber.validateNumber(new Scanner(System.in));
        Assertions.assertEquals(4, actualResult);
    }

    @Test
    void testValidateNumberWhenFirstLineIsIncorrect(){
        String input = "Sarah\n6";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int actualResult = ValidatorNumber.validateNumber(new Scanner(System.in));
        Assertions.assertEquals(6, actualResult);
    }

}
