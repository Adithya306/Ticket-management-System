import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;

    // Constructor
    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters
    public String getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    // Setters
    public void setRow(String row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void save() {
        // Construct the file name using the row and seat number
        String fileName = row + String.valueOf(seat) + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write the header "Ticket Information"
            writer.write("Ticket Information\n");
            // Write the row information
            writer.write("Row: " + row + "\n");
            // Write the seat number information
            writer.write("Seat Number: " + seat + "\n");
            // Write the seat price information
            writer.write("Seat price: " + price + "\n");
            // Write the header "Person info"
            writer.write("Person Information" + "\n");
            // Write the person's name
            writer.write("Name: " + person.getName() + "\n");
            // Write the person's surname
            writer.write("Surname: " + person.getSurname() + "\n");
            // Write the person's email
            writer.write("Email: " + person.getEmail() + "\n");

        } catch (IOException e) {
            // If an IOException occurs, print an error message
            System.out.println("An error occurred");
            ;
        }
    }

    public void delete() {
        // Construct the file name using the row and seat number
        String fileName = row + String.valueOf(seat) + ".txt";
        // Create a File object with the constructed file name
        File file = new File(fileName);
        // Check if the file exists
        if (file.exists()) {
            // If the file exists, attempt to delete it
            if (file.delete()) {
                // If the file is successfully deleted, print a message "Ticket information deleted"
                System.out.println("Ticket information deleted: " + fileName);
            } else {
                // If the file deletion fails, print an error message
                System.out.println("Failed to delete ticket information.");
            }
        } else {
            // If the file does not exist, print a message "ticket file was not found"
            System.out.println("Ticket file not found.");
        }
    }

    // Method to print ticket information
    public void printTicketInfo () {
        System.out.println("Ticket Information:");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        person.printPersonInfo();
    }
}
