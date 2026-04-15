package GuestandRoomSystem;

import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public  static void main(String []args){

        HotelDatabase.loadDummyData();
        System.out.println("Enter Username : ");
        String username = scanner.nextLine();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        Guest guest = Guest.login(username ,password);
        guest = HotelDatabase.guests.get(0);
        guest.register();
        guest.viewAvailableRooms();
        System.out.println("Guest makes reservation test");
        System.out.println("Enter check in date");
        String checkinDateInput = scanner.nextLine();
        LocalDate checkinDate = LocalDate.parse(checkinDateInput);
        System.out.println("Enter check out date");
        String checkoutDateInput = scanner.nextLine();
        LocalDate checkoutDate = LocalDate.parse(checkoutDateInput);
        guest.makeReservation(room.getroomid,checkinDate,checkoutDate);


    }
}
