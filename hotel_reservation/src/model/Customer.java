package model;

import java.util.Objects;

public class Customer {
    String firstName, lastName, email;

    // Constructor
    public Customer(String email) { // Checks if email format is correct
        // Checks if there's only 1 @ in email
        int atSymbolAmount = 0; // amount of @ in email
        for (int i = 0; i < email.length(); i++) { // finds amount of @ in "email"
            if (Objects.equals(email.charAt(i), "@")){
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

        // Checks if email has domain, does NOT have "@.com"
        if (emailArray[1].contains(".com")) {
            throw new IllegalArgumentException("Email must have a domain and cannot have \"@.com\"");
        }
    }

    @Override
    public String toString() {
        return "Customer Name: " + firstName + " " + lastName + " and Customer Email: " + email;
    }
}
