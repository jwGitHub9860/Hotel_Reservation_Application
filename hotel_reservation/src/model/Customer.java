package model;

import service.ReservationService;

import java.util.Scanner;

public class Customer {
    String firstName, lastName, email;

    // Constructor
    public Customer(String firstName, String lastName, String email) { // Checks if email format is correct
        // Defines "firstName", "lastName", and "email" in "toString()" function
        this.firstName = firstName; // set current "firstName" as "firstName"
        this.lastName = lastName; // set current "lastName" as "lastName"
        this.email = email; // set current "email" as "email"

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
    }

    // Takes User Input for Room Information
    public static String inputEmail() {
        Scanner scanner = new Scanner(System.in); // allows user input to be read

        // Takes User Input for Room Number
        System.out.println("Enter room number: ");
        while (!isValid) { // ensures that user inputted "roomNumberInput"
            try {
                roomNumberInput = String.valueOf(scanner.nextInt()); // takes User Input for "roomNumberInput"

                // Checks if "roomNumberCollection" is NOT empty
                if (!ReservationService.roomNumberCollection.isEmpty()) {
                    // Prevents user from creating two Hotel Rooms with the Same Room Number
                    for (String number : ReservationService.roomNumberCollection) {
                        // Checks if user inputted "roomNumberInput" that is the Same as a Previous "roomNumberInput"
                        if (roomNumberInput.equals(number)) {
                            throw new IllegalArgumentException("There cannot be two hotel rooms with the same room number.");
                        }
                        else {
                            isValid = true;
                        }
                    }
                } else {
                    isValid = true;
                }
                ReservationService.roomNumberCollection.add(roomNumberInput); // adds "roomNumberInput" to "roomNumberCollection"
            } catch (IllegalArgumentException e) { // if user enters Room Number that is the SAME AS PREVIOUS HOTEL NUMBER
                System.out.println("Please enter a different room number: ");
                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false; // ensures that user inputted "roomNumberInput"
            } catch (Exception e) { // if user does NOT ENTER INTEGER FOR HOTEL NUMBER
                System.out.println("Please enter an integer for the room number: ");
                scanner.next(); // uses & Deletes invalid input; Prevents Infinite While Loop
                isValid = false; // ensures that user inputted "roomNumberInput"
            }
        }

        // Reverts "isValid" back to "false"
        isValid = false; // "isValid" was CHANGED TO "true" After "inputRoomNumber()" method FINISHED

        return roomNumberInput;
    }

    @Override
    public String toString() {
        return "Customer First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email;
    }
}
