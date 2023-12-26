import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class BankCore {
    Boolean IsAdmin(String id, String[] user) {
        return (id.charAt(0) == '*' && user[6].equals("admin"));
    }

    String[] CorrectLogIn(String id, String pin) {
        String db = "DB.txt";
        if (id.charAt(0) == '*')
            id = id.substring(1);
        try (Scanner fileReader = new Scanner(Paths.get(db))) {
            while (fileReader.hasNextLine()) {
                String[] user = fileReader.nextLine().split("#");
                if (user[0].equals(id) && user[4].equals(pin)) {
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Boolean HasAccount(String id, String pin) {
        String db = "DB.txt";
        Boolean hasAccount = false;
        if (id.charAt(0) == '*')
            id = id.substring(1);
        try (Scanner fileReader = new Scanner(Paths.get(db))) {
            while (fileReader.hasNextLine()) {
                String[] user = fileReader.nextLine().split("#");
                if (user[0].equals(id))
                    hasAccount = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hasAccount;
    }

    public void Run() {
        Scanner scanner = new Scanner(System.in);
        String id, pin;
        String[] userinfo;

        do {
            System.out.println("Welcome to Yassine's Bank, make yourself at home <3");
            System.out.println("Please entre your ID:");
            id = scanner.nextLine();
            System.out.println("Please entre your Pin");
            pin = scanner.nextLine();
            userinfo = CorrectLogIn(id, pin);
            if (userinfo != null && HasAccount(id, pin)) {
                if (IsAdmin(id, userinfo)) {
                    Admin admin = new Admin();
                    int opt = 0;
                    do {
                        admin.AdminOptions();
                        opt = scanner.nextInt();
                        switch (opt) {
                            case 1:
                                admin.ShowAllUsers();
                                break;
                            case 2:
                                admin.ShowASpecificUser();
                                break;
                            case 3:
                                admin.AddUser();
                                break;
                            case 4:
                                admin.EditUser();
                                break;
                            case 5:
                                admin.DeleteUser();
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                    } while (opt != 6);

                } else if (!IsAdmin(id, userinfo) && id.charAt(0) == '*') {
                    System.out.println("You don't have permission.");
                } else {
                    User user = new User(userinfo[0], userinfo[1], userinfo[2], userinfo[3], userinfo[4],
                            Double.parseDouble(userinfo[5]), userinfo[6]);
                    int opt = 0;
                    do {
                        user.UserOptions();
                        opt = scanner.nextInt();
                        switch (opt) {
                            case 1:
                                user.Deposit();
                                break;
                            case 2:
                                user.Withdraw();
                                break;
                            case 3:
                                user.Balanse();
                                break;
                            case 4:

                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                    } while (opt != 4);
                    System.out.println("Thank you! Have a nice day :)");
                }
            } else if (HasAccount(id, pin) && userinfo == null) {
                System.out.println("Incorrect login. Please try again");
            } else {
                System.out.println("You don't have an account here.");
            }
        } while (HasAccount(id, pin) && userinfo == null);
        scanner.close();
    }
}
