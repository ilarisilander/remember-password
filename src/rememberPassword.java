import java.sql.*;
import java.util.Scanner;

public class rememberPassword {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean active = true;

        printMenu();

        while(active) {

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    addAccount();
                    break;
                case 2:
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

    private static void addAccount() {

    }
}
