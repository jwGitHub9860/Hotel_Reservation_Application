package model;

import api.HotelResource;

import java.util.Scanner;

public class Customer {
    final private String firstName, lastName, email;

    // Allows User Input to be Read in ALL Methods WITHIN "Customer" class
    final static Scanner scanner = new Scanner(System.in);

    // Constructor
    private Customer(String firstName, String lastName, String email) { // MUST BE "private" TO IMPLEMENT SINGLETON PATTERN
        // Defines "firstName", "lastName", "email", and "nonExistentEmail" in "toString()" function
        this.firstName = firstName; // set current "firstName" to "firstName"
        this.lastName = lastName; // set current "lastName" to "lastName"
        this.email = email; // set current "email" to "email"
    }

    // Customer Method Definitions
    final public String getFirstName() { return firstName; }
    final public String getLastName() { return lastName; }
    final public String getEmail() { return email; }

    // Checks if Email Format is Correct
    public static String inputEmail() {
        // Takes User Input for Email
        while (true) { // ensures that user inputted "emailInput"
            try {
                String emailInput = scanner.nextLine(); // takes User Input for "emailInput"

                // Checks if "emailInput" is NOT empty
                if (!emailInput.isEmpty()) {
                    // Checks if there's only 1 @ in emailInput
                    int atSymbolAmount = 0; // amount of @ in emailInput
                    for (int i = 0; i < emailInput.length(); i++) { // finds amount of @ in "emailInput"
                        if (String.valueOf(emailInput.charAt(i)).equals("@")){
                            atSymbolAmount++;
                        }
                    }
                    if (atSymbolAmount != 1) { throw new IllegalArgumentException("Email must have 1 \"@\""); }

                    // Checks if last 4 characters are ".com"
                    if (!emailInput.substring((emailInput.length() - 4)).equals(".com")) {
                        throw new IllegalArgumentException("Email must end in \".com\"");
                    }

                    String[] emailArray = emailInput.split("@"); // splits "emailInput" string at "@"

                    // Checks if emailInput has name, does NOT start with "@"
                    if (emailArray[0] == null) {
                        throw new IllegalArgumentException("Email must have a name and cannot start with \"@\"");
                    }

                    String[] domainArray = emailArray[1].split(".com"); // splits "domain" string at ".com"

                    // Checks if emailInput has domain, does NOT have "@.com"
                    if (domainArray[0] == null) {
                        throw new ArrayIndexOutOfBoundsException("Email must have a domain and cannot have \"@.com\"");
                    }
                    return emailInput;
                }
            } catch (IllegalArgumentException e) { // if user does NOT ENTER EMAIL IN CORRECT FORMAT
                System.out.println("Email must be in \"name@domain.com\" format: ");
                //scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Email must have a domain and cannot have \"@.com\": ");
            }
        }
    }

    // Takes User Input for Customer Information
    public static void inputAccountInformation() {
        // Obtains User Input for "email" for "HotelResource" constructor & "createACustomer()" method
        System.out.println("Enter email (format: name@domain.com): ");
        String email = Customer.inputEmail(); // calls "inputEmail()" method to take User Input for "email"

        // Obtains User Input for "firstName" for "HotelResource" constructor & "createACustomer()" method
        System.out.println("\nEnter first name: ");
        String firstName = scanner.nextLine(); // takes User Input for "firstName"

        // Ensures User Inputs "firstName" BEFORE Inputting "lastName"
        if (firstName.isEmpty()) { firstName = scanner.nextLine(); } // takes User Input for "firstName"

        // Obtains User Input for "lastName" for "HotelResource" constructor & "createACustomer()" method
        System.out.println("\nEnter last name: ");
        String lastName = scanner.nextLine(); // takes User Input for "lastName"

        // Calls "createACustomer()" method
        HotelResource.createACustomer(email, firstName, lastName);
    }

    // Create Instance of "Customer" class
    public static synchronized Customer getInstance(String firstName, String lastName, String email) {
        // Calls "Customer" constructor to obtain WHOLE Customer Information
        return new Customer(firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Customer First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email;
    }
}
