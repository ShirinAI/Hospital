import java.util.Scanner;

public class ValidatorNumber {
    public static int validateNumber(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid NUMBER");
            }
        }
    }
}
