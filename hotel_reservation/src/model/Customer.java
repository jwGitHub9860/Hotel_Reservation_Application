package model;

import api.HotelResource;
import service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Customer {
    String firstName, lastName, email, nonExistentEmail;

    // Initializes "dateInput1Array" and "dateInput2Array" for code section that Checks if Check-In Date is Later Than Check-Out Date & MUST BE "inputCheckInAndCheckOutDates()" Method to Prevent "dateInput1Array" and "dateInput2Array" From Resetting Their Data Everytime "inputCheckInAndCheckOutDates()" Method Runs
    static String[] dateInput1Array = new String[0]; // holds "checkInDate" IN "String" FORM
    static String[] dateInput2Array = new String[0]; // holds "checkOutDate" IN "String" FORM

    // String holders for "dateInput1Array" & MUST BE OUTSIDE "inputCheckInAndCheckOutDates()" Method to Prevent "dateInput1Array" and "dateInput2Array" From Resetting Their Data Everytime "inputCheckInAndCheckOutDates()" Method Runs
    static int month1 = 0, day1 = 0, year1 = 0;

    // Allows User Input to be Read in ALL Methods WITHIN "Customer" class
    final static Scanner scanner = new Scanner(System.in);

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
    private static Date inputCheckInAndCheckOutDates() {
        // Allows user to input "Date" as input
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Sets "lenient" to "false" to apply strict Date Parsing
        simpleDateFormat.setLenient(false);

        // String holders for "dateInput2Array"
        int month2, day2, year2;

        // Checks if user inputted "dateInput" in "MM/dd/yyyy" format
        while (true) {
            try {
                // Takes User Input for "dateInput" AS STRING
                String dateInput = scanner.nextLine();

                // Checks if Month, Day, and Year are ALL Integers
                String[] dateInputArray = dateInput.split("/"); // splits "dateInput" at "/"
                for (String datePart : dateInputArray) {
                    Integer.parseInt(datePart);
                }

                // Checks if "dateInput" Ends With ANYTHING BUT A NUMBER, "0-9"
                if (dateInput.endsWith("[^0-9]")) {
                    throw new IllegalArgumentException("Date cannot end with anything, but a number, 0-9"); // Throws Error to Prevent Malicious Attacks (ex. SQL Injection)
                }

                // Splits "dateInput" into EITHER "dateInput1Array" OR "dateInput2Array"
                if (dateInput1Array.length == 0) {
                    dateInput1Array = dateInput.split("/"); // splits "checkInDate" into "dateInput1Array" IN "String" FORM
                } else {
                    dateInput2Array = dateInput.split("/"); // splits "checkOutDate" into "dateInput2Array" IN "String" FORM
                }

                // Inputs String holders for "dateInput1Array" IF "dateInput1Array" Is NOT EMPTY
                if (!((dateInput1Array.length) == 0)) {
                    // Input "dateInput1Array" Elements into String holders
                    month1 = Integer.parseInt(dateInput1Array[0]);
                    day1 = Integer.parseInt(dateInput1Array[1]);
                    year1 = Integer.parseInt(dateInput1Array[2]);
                }

                // Inputs String holders for "dateInput2Array" IF "dateInput2Array" Is NOT EMPTY
                if (!((dateInput2Array.length) == 0)) {
                    // Input "dateInput2Array" Elements into String holders
                    month2 = Integer.parseInt(dateInput2Array[0]);
                    day2 = Integer.parseInt(dateInput2Array[1]);
                    year2 = Integer.parseInt(dateInput2Array[2]);

                    // Checks if Check-In Date is Later Than Check-Out Date
                    if (year1 > year2) { // Ex. Check-In Date: 2/1/2020 & Check-Out Date: 2/1/2019
                        dateInput1Array = new String[0]; // REINITIALIZE "dateInput1Array"
                        dateInput2Array = new String[0]; // REINITIALIZE "dateInput2Array"
                        throw new IllegalArgumentException("Check-in date cannot be later than check-out date.");
                    } else if ((year1 == year2) && (month1 > month2)) { // Ex.1: Check-In Date: 7/1/2020 & Check-Out Date: 3/11/2020 & Ex.2: Check-In Date: 7/11/2020 & Check-Out Date: 3/1/2020
                        dateInput1Array = new String[0]; // REINITIALIZE "dateInput1Array"
                        dateInput2Array = new String[0]; // REINITIALIZE "dateInput2Array"
                        throw new IllegalArgumentException("Check-in date cannot be later than check-out date.");
                    } else if ((year1 == year2) && (month1 == month2) && (day1 > day2)) { // Ex. Check-In Date: 5/9/2020 & Check-Out Date: 5/1/2020
                        dateInput1Array = new String[0]; // REINITIALIZE "dateInput1Array"
                        dateInput2Array = new String[0]; // REINITIALIZE "dateInput2Array"
                        throw new IllegalArgumentException("Check-in date cannot be later than check-out date.");
                    }
                }

                // Checks if "dateInput" is in "MM/dd/yyyy" format
                return simpleDateFormat.parse(dateInput);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Month, day, and year must all be integers: ");
            } catch (IllegalArgumentException e) {
                System.out.println("Check-in date cannot be later than check-out date: ");
            } catch (Exception e) {
                System.out.println("Please enter date in \"MM/dd/yyyy\" format: ");
            }
        }
    }
    private static void inputAccountInformation() {
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

    @Override
    public String toString() {
        return "Customer First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email;
    }
}
