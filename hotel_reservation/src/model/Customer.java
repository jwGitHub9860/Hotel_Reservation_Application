package model;

import service.ReservationService;

import java.util.Scanner;

public class Customer {
    String firstName, lastName, email, nonExistentEmail;

    // Constructor
    public Customer(String firstName, String lastName, String email) { // Checks if email format is correct
        // Defines "firstName", "lastName", "email", and "nonExistentEmail" in "toString()" function
        this.firstName = firstName; // set current "firstName" to "firstName"
        this.lastName = lastName; // set current "lastName" to "lastName"
        this.email = email; // set current "email" to "email"
    }

    // Customer Method Definitions
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }

    // Takes User Input for Room Information
    public static String inputEmail() {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

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

    @Override
    public String toString() {
        return "Customer First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email;
    }
}
