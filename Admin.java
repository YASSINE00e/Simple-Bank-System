import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Admin extends User {

    public Admin() {
    }

    public Admin(String id, String firstName, String lastName, String cardNum, String pin, double balance,
            String perm) {
        super(id, firstName, lastName, cardNum, pin, balance, perm);
    }

    public void AdminOptions() {
        System.out.println("Please choose from one of the following options...");
        System.out.println("1. Show all users");
        System.out.println("2. Show a specific user");
        System.out.println("3. Add user");
        System.out.println("4. Edit user");
        System.out.println("5. Delete user");
        System.out.println("6. Exit");
    }

    public void ShowAllUsers() {
        String db = "DB.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(db))) {
            // Read each line from the file
            int i = 0;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] user = currentLine.split("#");
                i++;
                System.out.println("User N°" + i);
                System.out.println("Id: " + user[0]);
                System.out.println("FirstName: " + user[1]);
                System.out.println("LastName: " + user[2]);
                System.out.println("CardNum: " + user[3]);
                System.out.println("Pin: " + user[4]);
                System.out.println("Balance: " + user[5]);
                System.out.println("Perm: " + user[6]);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Scanner scanner = new Scanner(System.in);

    public void ShowASpecificUser() {
        System.out.println("ID of the user you want to show his informations: ");
        String id = scanner.nextLine();
        String db = "DB.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(db))) {
            // Read each line from the file
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] user = currentLine.split("#");
                if (user[0].equals(id)) {
                    System.out.println("Id: " + user[0]);
                    System.out.println("FirstName: " + user[1]);
                    System.out.println("LastName: " + user[2]);
                    System.out.println("CardNum: " + user[3]);
                    System.out.println("Pin: " + user[4]);
                    System.out.println("Balance: " + user[5]);
                    System.out.println("Perm: " + user[6]);
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddUser() {

        String db = "db.txt";
        System.out.println("Id: ");
        String id = scanner.nextLine();
        System.out.println("FirstName: ");
        String firstName = scanner.nextLine();
        System.out.println("LastName: ");
        String lastName = scanner.nextLine();
        String cardNum = generateCardNumber();
        String pin = generatePin();
        System.out.println("Balance: ");
        double balance = scanner.nextDouble();
        System.out.println("Perm: ");
        scanner.next();
        String perm = scanner.nextLine();

        String newUser = "\n" + id + "#" + firstName + "#" + lastName + "#" + cardNum + "#" + pin + "#"
                + balance + "#" + perm;

        try (BufferedWriter writer = java.nio.file.Files.newBufferedWriter(
                Paths.get(db), StandardOpenOption.APPEND)) {
            // Write the text to the file
            writer.write(newUser);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EditUser() {
        String db = "db.txt";
        System.out.println("ID of the user you want to edit his informations: ");
        String iduser = scanner.nextLine();
        System.out.println("Now put the new informations.");
        System.out.println("Id: ");
        String id = scanner.nextLine();
        System.out.println("FirstName: ");
        String firstName = scanner.nextLine();
        System.out.println("LastName: ");
        String lastName = scanner.nextLine();
        System.out.println("cardNum: ");
        String cardNum = scanner.nextLine();
        System.out.println("pin: ");
        String pin = scanner.nextLine();
        System.out.println("Balance: ");
        double balance = scanner.nextDouble();
        System.out.println("Perm: ");
        String perm = scanner.nextLine();
        String newinfo = id + "#" + firstName + "#" + lastName + "#" + cardNum + "#" + pin + "#"
                + balance + "#" + perm;

        try {
            // Read the file into a StringBuilder
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(db))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // If the line matches the line to update, replace it
                    if (currentLine.startsWith(iduser)) {
                        currentLine = newinfo;
                    }
                    fileContent.append(currentLine).append("\n");
                }
            }

            // Write the modified content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(db))) {
                writer.write(fileContent.toString());
                System.out.println("User updated!");
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DeleteUser() {
        String db = "db.txt";
        System.out.println("ID of the user you want to delete his informations: ");
        String iduser = scanner.nextLine();
        try {
            // Read the file into a StringBuilder
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(db))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // If the line matches the line to update, replace it
                    if (currentLine.startsWith(iduser)) {
                        continue;
                    }
                    fileContent.append(currentLine).append("\n");
                }
            }

            // Write the modified content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(db))) {
                writer.write(fileContent.toString());
                System.out.println("User deleted!");
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}