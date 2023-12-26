import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String cardNum;
    private String pin;
    private double balance;
    private String perm;

    public User() {
    }

    public User(String id, String firstName, String lastName, String cardNum, String pin, double balance, String perm) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNum = cardNum;
        this.pin = pin;
        this.balance = balance;
        this.perm = perm;
    }

    String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumberBuilder = new StringBuilder("5"); // Starting with 5 for Mastercard

        for (int i = 1; i < 16; i++) {
            cardNumberBuilder.append(random.nextInt(10)); // Append a random digit
        }

        return cardNumberBuilder.toString();
    }

    String generatePin() {
        Random random = new Random();
        StringBuilder Pin = new StringBuilder();

        for (int i = 1; i < 4; i++) {
            Pin.append(random.nextInt(10)); // Append a random digit
        }

        return Pin.toString();
    }

    public void UserOptions() {
        System.out.println("Please choose from one of the following options...");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Show balance");
        System.out.println("4. Exit");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;

    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    Scanner scanner = new Scanner(System.in);

    public void UpdateBalance(double newbalance) {
        String db = "db.txt";
        String newinfo = id + "#" + firstName + "#" + lastName + "#" + cardNum + "#" + pin + "#"
                + newbalance + "#" + perm;

        try {
            // Read the file into a StringBuilder
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(db))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // If the line matches the line to update, replace it
                    if (currentLine.startsWith(id)) {
                        currentLine = newinfo;
                    }
                    fileContent.append(currentLine).append("\n");
                }
            }

            // Write the modified content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(db))) {
                writer.write(fileContent.toString());
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Deposit() {
        System.out.println("How mush $$ would you like to deposit: ");
        double deposit = scanner.nextDouble();
        balance += deposit;
        System.out.println("Thank you for your $$. Your new balanse is: " + balance);
        UpdateBalance(balance);
    }

    public void Withdraw() {
        System.out.println("How mush $$ would you like to withdraw: ");
        double withdraw = scanner.nextDouble();
        // cheack if the user has enough money
        if (balance >= withdraw) {
            balance -= withdraw;
            System.out.println("You're good to go! Thank you :)");
            UpdateBalance(balance);
        } else {
            System.out.println("Insufficient balance :(");
        }

    }

    public void Balanse() {
        System.out.println("Current balance: " + balance);
    }
}