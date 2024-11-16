import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.nio.file.Files.delete;

public class w2053188_PlaneManagement {

    //Implement the seat management system using array
    public static int seats[][] = new int[4][];

    //Defined rows and columns to array
    static {
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];
    }
    public static Ticket[][]ticket_array = new Ticket[4][];
    static {
        ticket_array[0] = new Ticket[14];
        ticket_array[1] = new Ticket[12];
        ticket_array[2] = new Ticket[12];
        ticket_array[3] = new Ticket[14];
    }

    public static void main(String[] args) {

        //Declaring boolean type variable for loop
        boolean stopLoop = true;

        //Main menu
        while (stopLoop) {

            Scanner input = new Scanner(System.in);

            System.out.println("Welcome to the Plane Management application");
            System.out.println("*******************************************");
            System.out.println("*               MENU OPTIONS              *");
            System.out.println("*******************************************");
            System.out.println("\t1) Buy a seat");
            System.out.println("\t2) Cancel a seat");
            System.out.println("\t3) Find first available seat");
            System.out.println("\t4) Show seating plan");
            System.out.println("\t5) Print tickets information and total sales");
            System.out.println("\t6) Search tickets");
            System.out.println("\t0) Quit");

            System.out.println("Please select an option: ");

            //Using try & catch to display error if user enter invalid input
            try {
                //Get user input selection from the menu
                int num = input.nextInt();
                //Using switch to run the methods according to the user selection from the menu
                switch (num) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 0:
                        //Stop the programme if user select the quit option
                        stopLoop = false;
                        break;
                    //print "Invalid number" if user input a wrong number that not in the menu
                    default:
                        System.out.println("Invalid Number");
                }
            }
            //Showing an error if user not input an integer
            catch (InputMismatchException e) {
                System.out.println("Please Enter a correct value");
            }
        }
    }

    //Create a method called buy_seat
    public static void buy_seat() {
        Scanner input = new Scanner(System.in);


        //Get user input
        System.out.println("Enter the row letter");

        //Assign the user input letter to the variable called "rowLetterInput" and convert that to uppercase letter
        String rowLetterInput = input.nextLine().toUpperCase();

        //Display error message if user input more than a single character
        if (rowLetterInput.length() != 1) {
            System.out.println("Invalid input. Please enter a single letter.");
            return;
        }
        //Covert String type Variable called "rowLetterInput" to char type variable called "rowLetter"
        char rowLetter = rowLetterInput.charAt(0);

        try {
            //Check the user input row letter is between 'A' and 'D'
            if (rowLetter >= 'A' && rowLetter <= 'D') {

                //Get user input
                System.out.println("Enter the seat number");
                int seatNum = input.nextInt();

                //Store the row letter as a integer value using ASCII
                int row = rowLetter - 'A';

                //Check the availability of the seats
                if (seats[row][seatNum - 1] == 1) {
                    System.out.println("This seat is booked");
                }

                //Booked the seat
                else {
                    seats[row][seatNum - 1] = 1;
                    user_info(row,seatNum,rowLetterInput);
                    System.out.println("Successfully booked the seat");
                }

                //Check the user input seat number is in valid range or invalid range
                if (seatNum < 1 || seatNum > 14) {
                    System.out.println("Invalid seat number. Please enter a value between 1 and 14.");
                }
            } else {
                System.out.println("Invalid row letter. Please enter a letter between A and D.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Enter the correct value within the valid range");
        }

    }

    //Create a method called cancel_seat
    public static void cancel_seat() {
        Scanner input = new Scanner(System.in);

        //Get user input
        System.out.println("Enter the row letter");

        //Assign the user input letter to the variable called "rowLetterInput" and convert that to uppercase letter
        String rowLetterInput = input.nextLine().toUpperCase();

        //Display error message if user input more than a single character
        if (rowLetterInput.length() != 1) {
            System.out.println("Invalid input. Please enter a single letter.");
            return;
        }
        //Covert String type Variable called "rowLetterInput" to char type variable called "rowLetter"
        char rowLetter = rowLetterInput.charAt(0);

        try {
            //Check the user input row letter is between 'A' and 'D'
            if (rowLetter >= 'A' && rowLetter <= 'D') {

                //Get user input
                System.out.println("Enter the seat number");
                int seatNum = input.nextInt();

                //Store the row letter as a integer value using ASCII
                int row = rowLetter - 'A';

                //Check the availability of the seats
                if (seats[row][seatNum - 1] == 0) {
                    System.out.println("This seat is already available.");
                }
                //Canceled the seat
                else {
                    seats[row][seatNum - 1] = 0;
                    ticket_array[row][seatNum-1].delete();
                    ticket_array[row][seatNum-1] = null;
                    System.out.println("Successfully canceled the seat");
                }
                //Check the user input seat number is in valid range or invalid range
                if (seatNum < 1 || seatNum > 14) {
                    System.out.println("Invalid seat number. Please enter a value between 1 and 14.");
                }
            } else {
                System.out.println("Invalid row letter. Please enter a letter between A and D.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Enter the correct value within the valid range");
        }
    }

    public static void find_first_available() {

        //Running a nested for loop to get to know the first available seat row and column
        for (int seatRow = 0; seatRow < seats.length; seatRow++) {
            for (int seatCol = 0; seatCol < seats[seatRow].length; seatCol++) {
                //Check the availability
                if (seats[seatRow][seatCol] == 0) {
                    //Store the row label and column number
                    char rowLabel = (char) ('A' + seatRow);
                    int colNumber = seatCol + 1;
                    System.out.println("The first available seat is at row " + rowLabel + " and column " + colNumber);
                    return;
                }
            }
        }

    }

    public static void show_seating_plan() {
        System.out.println("Seating Plan:");

        //Running a nested for loop to print "O" for available seats and print "X" for booked seats
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                if (seats[row][col] == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    public static void print_tickets_info() {
        // initialized double type variable called total to store the total price of all tickets
        double total = 0;

        for (int row = 0; row < ticket_array.length; row++) {
            for (int column = 0; column < ticket_array[row].length; column++) {
                // Check the seat is booked
                if (ticket_array[row][column] != null) {
                    // Print the ticket information
                    ticket_array[row][column].printTicketInfo();
                    // Add the ticket price to the total
                    total += ticket_array[row][column].getPrice();
                    // Print row and seat number
                    System.out.println("Row: " + (char) ('A' + row) + ", Seat: " + (column + 1));

                }
            }
        }
        //Print the total price
        System.out.println("Total Price: £" + total);
    }


    public static void user_info(int row,int seatNum,String rowLetterInput) { // Add parameters to method
        //Declaring variables
        String name;
        String surname;
        String email;

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your name: ");
            name = input.next();
            if (name.matches("[a-zA-Z]+")) {// Check the name contains only alphabets
                break;
            } else {
                System.out.println("Invalid name. Name cannot contain numbers or special characters");
            }
        }

        while (true) {
            System.out.println("Enter your surname: ");
            surname = input.next();
            if (surname.matches("[a-zA-Z]+")) {// Check the name contains only alphabets
                break;
            } else {
                System.out.println("Invalid name. Name cannot contain numbers or special characters");
            }
        }

        while (true) {
            System.out.println("Enter your email: ");
            email = input.next();
            if (email.contains("@") && email.contains(".")) { // Check the email valid or invalid
                break;
            } else {
                System.out.println("Please enter a valid email address");
            }
        }
        // Get the ticket price based on the seat number
        int priceTicket = ticket_price(seatNum);
        // Create a new Person object
        Person person=new Person(name,surname,email);
        // Create a new Ticket object
        Ticket ticket = new Ticket(rowLetterInput, seatNum, priceTicket, person);
        // Store the ticket in the ticket_array
        ticket_array[row][seatNum-1]=ticket;
        ticket.save();
        // Print the ticket information
        ticket.printTicketInfo();


    }

    public static int ticket_price(int seat_num){
        //Check the ticket price according to the seat number
        if (seat_num<=5) {
            return 200;
        }else if(seat_num<=9){
            return 150;
        }else{
            return 180;
       }

    }

    public static void search_ticket(){
        Scanner input = new Scanner(System.in);
        while (true) { // Outer loop to keep prompting for row input until a valid value is entered
            System.out.print("Enter the row letter of the seat you want to Search(A-D):");
            String seat_row = input.next().toUpperCase();
            if (seat_row.equals("A") || seat_row.equals("B") || seat_row.equals("C") || seat_row.equals("D")) {

                while (true) { // Inner loop to keep prompting for seat number input until a valid value is entered
                    try {
                        System.out.print("Enter the seat Number:");
                        int seat_num = input.nextInt();

                        switch (seat_row) {
                            case "A", "D": // Handles cases for rows A and D
                                // Checks seat number is within valid range
                                if (seat_num >= 0 && seat_num <= 14) {
                                    if (seat_row.equals("A")) {
                                        // Call searchTicketing method with row index 0
                                        searchTicketing(0, seat_num);


                                    } else {
                                        // Call searchTicketing method with row index 3
                                        searchTicketing(3,  seat_num);
                                    }
                                    // Exit the method after successful search
                                    return;
                                } else {
                                    // Prints an error message if user enter invalid seat number
                                    System.out.println("Out Of Range");
                                }
                                break;
                            case "B", "C": // Handles cases for rows B and C
                                // Check seat number is within valid range
                                if (seat_num >= 0 && seat_num <= 12) {
                                    if (seat_row.equals("B")) {
                                        // Call searchTicketing method with row index 1
                                        searchTicketing(1,  seat_num);

                                    } else {
                                        // Call searchTicketing method with row index 2
                                        searchTicketing(2, seat_num);
                                    }
                                    // Exit the method after successful search
                                    return;
                                } else {
                                    // Print an error message if user input invalid seat number
                                    System.out.println("Out Of range \n");
                                }break;

                        }

                    } catch (InputMismatchException e) { // Catches an InputMismatchException if the user enters a non-integer value
                        System.out.println("enter valid seat number ");
                        input.nextLine();

                    }
                }
            } else {
                // Print an error message for invalid row input
                System.out.println("Wrong entry.");
            }

        }
    }

    private static void searchTicketing(int row,  int seat_num) {
        // Check if the seat at the given row and seat number is booked (1) or available (0)
        if (seats[row][seat_num - 1] == 1) {
            // If the seat is booked, call the printTicketInfo() method
            ticket_array[row][seat_num-1].printTicketInfo();

        } else {
            // If the seat is available display a message "this seat is available"
            System.out.println("This seat is available" + "\n");
        }
    }

}