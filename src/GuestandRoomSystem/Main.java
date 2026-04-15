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


    }
}
