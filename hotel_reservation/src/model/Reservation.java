package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    // Initializes "dateInput1Array" and "dateInput2Array" for code section that Checks if Check-In Date is Later Than Check-Out Date & MUST BE "inputCheckInAndCheckOutDates()" Method to Prevent "dateInput1Array" and "dateInput2Array" From Resetting Their Data Everytime "inputCheckInAndCheckOutDates()" Method Runs
    static String[] dateInput1Array = new String[0]; // holds "checkInDate" IN "String" FORM
    static String[] dateInput2Array = new String[0]; // holds "checkOutDate" IN "String" FORM

    // String holders for "dateInput1Array" & MUST BE OUTSIDE "inputCheckInAndCheckOutDates()" Method to Prevent "dateInput1Array" and "dateInput2Array" From Resetting Their Data Everytime "inputCheckInAndCheckOutDates()" Method Runs
    static int month1 = 0, day1 = 0, year1 = 0;

    // Constructor
    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Reservation Method Definitions
    public Customer getCustomer() { return customer; }
    public IRoom getRoom() { return room; }
    public Date getCheckInDate() { return checkInDate; }
    public Date getCheckOutDate() { return checkOutDate; }

    // Takes User Input for Reservation Information
    public static Date inputCheckInAndCheckOutDates() {
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

    @Override
    public String toString() { return "Reservation:\nCustomer: " + customer.getFirstName() + " " + customer.getLastName() + "\nRoom: " + room.getRoomNumber() + " - " + room.getRoomType() + " bed\nPrice: " + room.getRoomPrice() + " price per night\nCheck-In Date: " + checkInDate + "\nCheck-Out Date: " + checkOutDate; }
}
