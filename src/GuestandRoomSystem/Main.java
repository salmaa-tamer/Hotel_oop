package GuestandRoomSystem;

import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public  static void main(String []args){
        HotelDatabase.loadDummyData();
        System.out.println("Enter Username : ");
        String name = scanner.nextLine();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        System.out.println("Enter Date of Birth");


        //Mohamed
        //Check out
        System.out.println("Receptionist managing check-out");
        receptionist.manageCheckOut(reservation, PaymentMethod.Credit_Card);
        System.out.println("Checkout complete. Bill generated.");

        //online checkout
        System.out.println("Guest attempting online check-out");
        System.out.println("Guest attempting to pay via ONLINE payment...");
        guest.onlineCheckout(reservation, PaymentMethod.ONLINE);
        System.out.println("Guest's Balance After: $" + guest.getBalance());

        //In-person checkout
        System.out.println("Guest in reception");
        System.out.println("Guest's current balance : $" + guest.getBalance());
        System.out.println("Guest paying in CASH...");
        guest.inPersonCheckout(reservation, PaymentMethod.CASH);
        System.out.println("Guest's current balance: $" + guest.getBalance());

        //admin checking bills
        System.out.println("admin checking invoices");
        System.out.println("Printing all system invoices...");

        for (Bill b : HotelDatabase.bills) {
            b.PrintBill();
            System.out.println();
        }

    }
}
