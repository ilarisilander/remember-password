import java.sql.*;
import java.util.Scanner;

public class rememberPassword {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean active = true;

        printMenu();

        while(active) {
            System.out.print("Välj nummer: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    insertAccount();
                    break;
                case 2:
                    updateAccount();
                    break;
                case 3:
                    removeAccount();
                    break;
                case 4:
                    showAllAccounts();
                    break;
                case 0:
                    System.exit(1);
                    break;
            }

        }
    }

    private static void printMenu() {
        System.out.println("*******Välj nummer*******");
        System.out.println("\n" +
                "1. Lägg till ett nytt konto\n" +
                "2. Uppdatera ett konto\n" +
                "3. Ta bort ett konto\n" +
                "4. Visa alla konton\n" +
                "0. Avsluta programmet\n" +
                "----------------------------");
    }

    private static Connection connect() {
        String url = "jdbc:sqlite:D:/Javaprojekt/remember-password/database/keypass.db";

        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void insertAccount() {
        String accountSql = "INSERT INTO account(accountLocation, accountUser) VALUES(?,?)";
        String passSql = "INSERT INTO password(password) VALUES(?)";

        System.out.print("Ange vart kontot finns (hemsida/spel/app): ");
        String location = sc.nextLine();
        System.out.print("Ange användarnamn: ");
        String user = sc.nextLine();
        System.out.print("Ange lösenord: ");
        String pass = sc.nextLine();

        try{
            Connection conn = connect();
            PreparedStatement prepAcc = conn.prepareStatement(accountSql);
            PreparedStatement prepPass = conn.prepareStatement(passSql);
            prepAcc.setString(1, location);
            prepAcc.setString(2, user);
            prepPass.setString(1, pass);
            prepAcc.executeUpdate();
            prepPass.executeUpdate();
            System.out.println("Du lade till ett ny konto");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateAccount() {

    }

    private static void removeAccount() {

    }

    private static void showAllAccounts() {

    }
}
