import model.Customer;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("first", "second", "j@domain.com"); // TESTING CODE

        System.out.printf(String.valueOf(customer)); // TESTING CODE

        System.out.printf("\n\nTest 2:\n"); // TESTING CODE
        Customer customer2 = new Customer("first", "second", "email"); // TESTING CODE

        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-------------------------------------------------");
        System.out.println("Please select a number for the menu option\n");
    }
}