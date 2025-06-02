package model;

import service.ReservationService;

import java.util.Scanner;

public class Customer {
    String firstName, lastName;
    static String email;

    // Constructor
    public Customer(String firstName, String lastName, String email) { // Checks if email format is correct
        // Defines "firstName", "lastName", and "email" in "toString()" function
        this.firstName = firstName; // set current "firstName" as "firstName"
        this.lastName = lastName; // set current "lastName" as "lastName"
        this.email = email; // set current "email" as "email"
    }

    // Takes User Input for Room Information
    public static String inputEmail() {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        // Takes User Input for Email
        System.out.println("Enter email (format: name@domain.com): ");
        while (true) { // ensures that user inputted "email"
            try {
                email = scanner.nextLine(); // takes User Input for "email"

                // Checks if "email" is NOT empty
                if (!email.isEmpty()) {
                    // Checks if there's only 1 @ in email
                    int atSymbolAmount = 0; // amount of @ in email
                    for (int i = 0; i < email.length(); i++) { // finds amount of @ in "email"
                        if (String.valueOf(email.charAt(i)).equals("@")){
                            atSymbolAmount++;
                        }
                    }
                    if (atSymbolAmount != 1) { throw new IllegalArgumentException("Email must have 1 \"@\""); }

                    // Checks if last 4 characters are ".com"
                    if (!email.substring((email.length() - 4)).equals(".com")) {
                        throw new IllegalArgumentException("Email must end in \".com\"");
                    }

                    String[] emailArray = email.split("@"); // splits "email" string at "@"

                    // Checks if email has name, does NOT start with "@"
                    if (emailArray[0] == null) {
                        throw new IllegalArgumentException("Email must have a name and cannot start with \"@\"");
                    }

                    String[] domainArray = emailArray[1].split(".com"); // splits "domain" string at ".com"

                    // Checks if email has domain, does NOT have "@.com"
                    if (domainArray[0] == null) {
                        throw new IllegalArgumentException("Email must have a domain and cannot have \"@.com\"");
                    }

                    break;
                }
            } catch (IllegalArgumentException e) { // if user does NOT ENTER EMAIL IN CORRECT FORMAT
                System.out.println("Email must be in \"name@domain.com\" format: ");
                //scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
            }
        }
        return email;
    }

    @Override
    public String toString() {
        return "Customer First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email;
    }
}
