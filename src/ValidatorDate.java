import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ValidatorDate {
    public static String validateDateFormatAndDate(Scanner scanner) {
        while (true) {
            System.out.println("Enter a future date in the format dd-MM-yyyy:");
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String dateInput = scanner.nextLine();
                LocalDate enteredDate = LocalDate.parse(dateInput, formatter);
                LocalDate currentDate = LocalDate.now();

                if (enteredDate.isBefore(currentDate)) {
                    System.out.println("Invalid date. Please enter a future date.");
                } else {
                    System.out.println("Valid date: " + dateInput);
                    return dateInput; // Return the validated date as a String
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a date in the format dd-MM-yyyy.");
            }
        }
    }
}
