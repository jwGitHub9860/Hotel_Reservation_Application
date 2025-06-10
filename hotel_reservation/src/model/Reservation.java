package model;

import service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    // Allows user to input "Date" as input
    final public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    // Allows User Input to be Read in ALL Methods WITHIN "Reservation" class
    final static Scanner scanner = new Scanner(System.in);

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
    public static String inputCheckInAndCheckOutDates() {
        // Sets "lenient" to "false" to apply strict Date Parsing
        simpleDateFormat.setLenient(false);

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

                // Checks if "dateInput" Has TWO "/" to Indicate that "dateInput" Has Month, Day, and Year
                if (dateInputArray.length != 3) { throw new RuntimeException("Date must have 2 \"/\""); }

                // Checks if "dateInput" Ends With ANYTHING BUT A NUMBER, "0-9"
                if (dateInput.endsWith("[^0-9]")) {
                    throw new IllegalArgumentException("Date cannot end with anything, but a number, 0-9"); // Throws Error to Prevent Malicious Attacks (ex. SQL Injection)
                }

                return dateInput;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Month, day, and year must all be integers: ");
            } catch (IllegalArgumentException e) {
                System.out.println("Date cannot end with anything, but a number (0-9): ");
            } catch (Exception e) {
                System.out.println("Please enter date in \"MM/dd/yyyy\" format: ");
            }
        }
    }

    // Checks if Check-In Date is Later Than Check-Out Date
    public static boolean findLaterDate(String dateInput1, String dateInput2) {
        // Splits "dateInput1" into "dateInput1Array" IN "String" FORM
        String[] dateInput1Array = dateInput1.split("/"); // holds "checkInDate" IN "String" FORM

        // Splits "dateInput2" into "dateInput2Array" IN "String" FORM
        String[] dateInput2Array = dateInput2.split("/"); // holds "checkOutDate" IN "String" FORM

        // Input "dateInput1Array" Elements into String Holders for "dateInput1Array"
        int month1 = Integer.parseInt(dateInput1Array[0]);
        int day1 = Integer.parseInt(dateInput1Array[1]);
        int year1 = Integer.parseInt(dateInput1Array[2]);

        // Input "dateInput2Array" Elements into String holders for "dateInput2Array"
        int month2 = Integer.parseInt(dateInput2Array[0]);
        int day2 = Integer.parseInt(dateInput2Array[1]);
        int year2 = Integer.parseInt(dateInput2Array[2]);

        // Checks if Check-In Date is Later Than Check-Out Date
        if (year1 > year2) { // Ex. Check-In Date: 2/1/2020 & Check-Out Date: 2/1/2019
            throw new IllegalArgumentException("Check-in date cannot be later than check-out date.");
        } else if ((year1 == year2) && (month1 > month2)) { // Ex.1: Check-In Date: 7/1/2020 & Check-Out Date: 3/11/2020 & Ex.2: Check-In Date: 7/11/2020 & Check-Out Date: 3/1/2020
            throw new IllegalArgumentException("Check-in date cannot be later than check-out date.");
        } else if ((year1 == year2) && (month1 == month2) && (day1 > day2)) { // Ex. Check-In Date: 5/9/2020 & Check-Out Date: 5/1/2020
            throw new IllegalArgumentException("Check-in date cannot be later than check-out date.");
        }
        return true;
    }

    // Sorts "reservationCollection" by USER'S CHOICE, organizes String Numbers by FIRST DIGIT in Number
    public static void sortReservations() {
        System.out.println("How would you like to sort reservations (customer first name, customer last name, room number, room type, room price, check-in date, or check-out date): ");
        while (true) {
            try {
                // Takes User Input for "sortChoice" AS STRING
                String sortChoice = scanner.nextLine();

                // Checks if User chose "Customer First Name", "Customer Last Name", "Room Number", "Room Type", "Room Price", "Check-in Date", or "Check-out Date"
                if (sortChoice.equals("customer first name") || sortChoice.equals("first name")) {
                    // Sorts "reservationCollection" by First Names in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                    ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> reservation.getCustomer().getFirstName()));
                } else if (sortChoice.equals("customer last name") || sortChoice.equals("last name")) {
                    // Sorts "reservationCollection" by Last Names in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                    ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> reservation.getCustomer().getLastName()));
                } else if (sortChoice.equals("room number")) {
                    // Sorts "reservationCollection" by Room Numbers & ONLY WORKS FOR "STRING NUMBERS", organizes String Numbers by WHOLE NUMBER
                    ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> {
                        String string = reservation.getRoom().getRoomNumber(); // obtains "roomNumber"
                        String[] roomRoomNumbers = string.split("\\."); // "\\." - match the character
                        int firstRoomNumber = Integer.parseInt(roomRoomNumbers[0]); // obtains 1st Room Number
                        int secondRoomNumber = roomRoomNumbers.length > 1 ? Integer.parseInt(roomRoomNumbers[1]) : 0; // finds which Room Number is greater
                        return firstRoomNumber * 1000 + secondRoomNumber; // returns "roomNumber1" and "roomNumber2" in Ascending Order
                    }));
                } else if (sortChoice.equals("room type")) {
                    // Sorts "reservationCollection" by Room Type in NUMERIC ORDER (1 -> SINGLE, 2 -> DOUBLE), In ASCENDING ORDER
                    ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> reservation.getRoom().getRoomType()));
                } else if (sortChoice.equals("room price")) {
                    // Sorts "reservationCollection" by Room Price in Alphabetical Order & ONLY WORKS FOR "STRING WORDS", organizes String Numbers by FIRST DIGIT in Number
                    ReservationService.reservationCollection.sort(Comparator.comparing(reservation -> reservation.getRoom().getRoomPrice()));
                } else if (sortChoice.equals("check-in date")) {
                    // Sorts "reservationCollection" by Check-In "Dates" in Ascending Order
                    ReservationService.reservationCollection.sort(Comparator.comparing(Reservation::getCheckInDate));
                } else if (sortChoice.equals("check-out date")) {
                    // Sorts "reservationCollection" by Check-Out "Dates" in Ascending Order
                    ReservationService.reservationCollection.sort(Comparator.comparing(Reservation::getCheckOutDate));
                } else {
                    throw new RuntimeException("Choice must be either Customer First Name, Customer Last Name, Room Number, Room Type, Room Price, Check-in Date, or Check-out Date");
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter Customer First Name, Customer Last Name, Room Number, Room Type, Room Price, Check-in Date, or Check-out Date: ");
            }
        }
    }

    @Override
    public String toString() { return "Reservation:\nCustomer: " + customer.getFirstName() + " " + customer.getLastName() + "\nRoom: " + room.getRoomNumber() + " - " + room.getRoomType() + " bed\nPrice: $" + room.getRoomPrice() + " price per night\nCheck-In Date: " + checkInDate + "\nCheck-Out Date: " + checkOutDate; }
}
