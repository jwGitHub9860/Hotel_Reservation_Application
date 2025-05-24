import model.Customer;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("first", "second", "j@domain.com"); // TESTING CODE

        System.out.printf(String.valueOf(customer)); // TESTING CODE

        System.out.printf("\n\nTest 2:\n"); // TESTING CODE
        Customer customer2 = new Customer("first", "second", "email"); // TESTING CODE
    }
}