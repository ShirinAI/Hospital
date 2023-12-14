import java.util.Scanner;

public class ValidatorTime {
    public static String validateTimeFormat(Scanner scanner) {
        String time;

        do {
            System.out.println("Enter a time in the format HH:mm:");
            time = scanner.nextLine();
            try {
                if (time.matches("10:00|10:30|11:00|11:30|12:00|13:30|14:00|14:30|15:00|15:30|16:00|16:30")) {
                    System.out.println("Selected time:" + time);
                    return time;
                } else {
                    System.out.println("Please select from the following timeslots: " +
                            "10:00, 10:30, 11:00, 11:30, 12:00, 13:30, 14:00, 14:30, 15:00, 15:30, 16:00, 16:30");
                }
            } catch (Exception e) {
                System.out.println("Invalid time format. Please enter a time in the format HH:mm.");
            }

        } while (true);
    }
}