import java.util.Scanner;

public class AdminMenu {
    public static void Admin() {
        // Admin Menu
        boolean runAdmin = true;
        try (Scanner scanner = new Scanner(System.in)) { // allows user input to be read
            while (runAdmin) {
                try {
                    // Admin Menu Items
                    System.out.println("\nAdmin Menu");
                    System.out.println("-------------------------------------------------");
                    System.out.println("1. See all Customers");
                    System.out.println("2. See all Rooms");
                    System.out.println("3. See all Reservations");
                    System.out.println("4. Add a Room");
                    System.out.println("5. Add Test Data");
                    System.out.println("6. Back to Main Menu");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Please select a number for the menu option");

                    // Takes User Input
                    int userInput = Integer.parseInt(scanner.nextLine()); // reads User Input & takes ONLY INTEGER from full line of user input
                    if (userInput == 1) {
                        System.out.println("ADMINuserInput 1 WORKS"); // TESTING CODE
                    } else if (userInput == 2) {
                        System.out.println("ADMINuserInput 2 WORKS"); // TESTING CODE
                    } else if (userInput == 3) {
                        System.out.println("ADMINuserInput 3 WORKS"); // TESTING CODE
                    } else if (userInput == 4) {
                        System.out.println("ADMINuserInput 4 WORKS"); // TESTING CODE
                    } else if (userInput == 5) {
                        System.out.println("ADMINuserInput 5 WORKS"); // TESTING CODE
                    } else if (userInput == 6) {
                        runAdmin = false;
                        scanner.close(); // avoid memory leaks
                    } else {
                        System.out.println("Please enter an integer between 1 and 6");
                    }
                } catch (Exception e) { // if user does NOT ENTER A NUMBER
                    System.out.println("\nPlease enter a number");
                }
            }
        } catch (Exception ex) {
            ex.getLocalizedMessage(); // prints any messages or exceptions to console
        }
    }
}
