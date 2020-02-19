import java.sql.*;
import java.util.Scanner;

public class rememberPassword {

    private static Scanner sc = new Scanner(System.in);
    private static Crypto crypto = new Encryption();

    public static void main(String[] args) {
        printMenu();

        while(true) {
            System.out.print("Vad vill du göra?: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    insertAccount();
                    printMenu();
                    break;
                case 2:
                    showAllAccounts();
                    updateAccount();
                    printMenu();
                    break;
                case 3:
                    showAllAccounts();
                    deleteAccount();
                    printMenu();
                    break;
                case 4:
                    showAllAccounts();
                    break;
                case 5:
                    printMenu();
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
                "5. Meny\n" +
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
        String sql = "INSERT INTO account(accountLocation, accountUser, accountPassword) VALUES(?,?,?)";

        System.out.print("Ange hemsidan/plattformen: ");
        String location = sc.nextLine();
        System.out.print("Ange användarnamn: ");
        String user = sc.nextLine();
        System.out.print("Ange lösenord: ");
        String toEncrypt = sc.nextLine();
        String pass = new String(crypto.encrypt(toEncrypt.getBytes()));

        try{
            Connection conn = connect();
            PreparedStatement prepAcc = conn.prepareStatement(sql);

            prepAcc.setString(1, location);
            prepAcc.setString(2, user);
            prepAcc.setString(3, pass);
            prepAcc.executeUpdate();

            System.out.println("\nDu lade till ett ny konto");
        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateAccount() {
        String sql = "UPDATE account SET accountLocation = ? , "
                + "accountUser = ? , "
                + "accountPassword = ? "
                + "WHERE accountId = ?;";

        System.out.print("Vilket ID vill du uppdatera?: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Ange plattform/hemsida: ");
        String location = sc.nextLine();
        System.out.print("Ange användarnamn: ");
        String user = sc.nextLine();
        System.out.print("Ange lösenord: ");
        String toEncrypt = sc.nextLine();
        String pass = new String(crypto.encrypt(toEncrypt.getBytes()));

        try{
            Connection conn = connect();
            PreparedStatement prepAcc = conn.prepareStatement(sql);

            prepAcc.setString(1, location);
            prepAcc.setString(2, user);
            prepAcc.setString(3, pass);
            prepAcc.setInt(4, id);
            prepAcc.executeUpdate();

            System.out.println("Din användare är nu uppdaterad!");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteAccount() {
        String sql = "DELETE FROM account WHERE accountId = ?;";
        System.out.print("Skriv in id't på kontot som du vill ta bort: ");

        int accountId = sc.nextInt();
        sc.nextLine();

        try{
            Connection conn = connect();
            PreparedStatement prepAcc = conn.prepareStatement(sql);

            prepAcc.setInt(1, accountId);
            prepAcc.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Användare borttagen!");
    }

    private static void showAllAccounts() {
        String sql = "SELECT * FROM account";

        try{
            Connection conn = connect();
            Statement stateAcc = conn.createStatement();
            ResultSet rs = stateAcc.executeQuery(sql);

            while(rs.next()) {
                System.out.print(rs.getInt("accountId"));
                System.out.print("\t");
                System.out.print(rs.getString("accountLocation"));
                System.out.print("\t");
                System.out.print(rs.getString("accountUser"));
                System.out.print("\t");
                String dec = rs.getString("accountPassword");
                String pass = new String(crypto.decrypt(dec.getBytes()));
                System.out.println(pass);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
