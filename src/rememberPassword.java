import java.sql.*;
import java.util.Scanner;

public class rememberPassword {

    private static Scanner sc = new Scanner(System.in);
    private static Crypto crypto = new Encryption();

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
                    deleteAccount();
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
        String accountSql = "INSERT INTO account(accountLocation, accountUser, password) VALUES(?,?,?)";
        String passSql = "INSERT INTO password(password) VALUES(?)";

        System.out.print("Ange vart kontot finns (hemsida/spel/app): ");
        String location = sc.nextLine();
        System.out.print("Ange användarnamn: ");
        String user = sc.nextLine();
        System.out.print("Ange lösenord: ");
        String toEncrypt = sc.nextLine();
        String pass = new String(crypto.encrypt(toEncrypt.getBytes()));

        try{
            Connection conn = connect();
            PreparedStatement prepAcc = conn.prepareStatement(accountSql);
            PreparedStatement prepPass = conn.prepareStatement(passSql);
            prepAcc.setString(1, location);
            prepAcc.setString(2, user);
            prepAcc.setString(3, pass);
            prepPass.setString(1, pass);
            prepPass.executeUpdate();
            prepAcc.executeUpdate();
            System.out.println("Du lade till ett ny konto");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateAccount() {

    }

    private static void deleteAccount() {
        System.out.print("Skriv in id't på kontot som du vill ta bort: ");


    }

    private static void showAllAccounts() {
        
    }
}
