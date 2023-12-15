import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ValidatorTimeTest {
    @Test
   void testValidateTimeFormat(){
        String input = "11:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String actualResult = ValidatorTime.validateTimeFormat(new Scanner(System.in));
        Assertions.assertEquals("11:00", actualResult);
    }

    @Test
    void testValidateTimeFormatWhenFirstTwoLineIsIncorrect(){
        String input = "19.00\n19:00\n11:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String actualResult = ValidatorTime.validateTimeFormat(new Scanner(System.in));
        Assertions.assertEquals("11:00", actualResult);
    }
}
