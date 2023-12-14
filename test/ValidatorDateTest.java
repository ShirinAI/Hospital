import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ValidatorDateTest {
    @Test
    void testValidateDateFormatAndDateWhenGivenDateIsCorrectFormat(){
        String input = "31-12-2023";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String actualResult = ValidatorDate.validateDateFormatAndDate(new Scanner(System.in));
        Assertions.assertEquals("31-12-2023", actualResult);
    }
    @Test
    void testValidateDateFormatAndDateWhenFirstDateIsWrongFormat(){
        String input = "31.12.2023\n31-12-2023";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String actualResult = ValidatorDate.validateDateFormatAndDate(new Scanner(System.in));
        Assertions.assertEquals("31-12-2023", actualResult);
    }
    @Test
    void testValidateDateFormatAndDateWhenFirstDateIsBeforeCurrentDate(){
        String input = "13.11.2023\n31-12-2023";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String actualResult = ValidatorDate.validateDateFormatAndDate(new Scanner(System.in));
        Assertions.assertEquals("31-12-2023", actualResult);
    }
}
