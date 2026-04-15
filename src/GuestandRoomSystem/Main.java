package GuestandRoomSystem;

import StaffSystem.Admin;
import StaffSystem.Receptionist;

import java.time.LocalDate;
import java.util.List;
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
        Room room= HotelDatabase.rooms.get(0);
        guest.makeReservation(room,checkinDate,checkoutDate);

        for (Bill b : HotelDatabase.bills) {
            b.PrintBill();
            System.out.println();
        }
        Receptionist receptionist = (Receptionist)HotelDatabase.staff.get(0);

        String receptionistUsername = scanner.nextLine();
        String receptionistPassword = scanner.nextLine();
        receptionist.setUsername(receptionistUsername);
        receptionist.setPassword(receptionistPassword);
        receptionist.login(receptionistUsername,receptionistPassword);

        Reservation reservation= HotelDatabase.reservations.get(0);
        receptionist.manageCheckIn(reservation);

        Admin admin = (Admin) HotelDatabase.staff.get(0);


        System.out.println("Enter the name of the room you would like to add: ");
        String roomName = scanner.nextLine();
        System.out.println("Enter the Room Id");
        int roomId=scanner.nextInt();
        System.out.println("Room Availability (true,flase)");
        boolean availability = scanner.nextBoolean();
        System.out.println("Room Floor: ");
        int floor = scanner.nextInt();
        System.out.println("Price per night: ");
        double pricePerNight=scanner.nextDouble();

        RoomType roomType=HotelDatabase.roomTypes.get(0);
        List<Amenity> amenities=HotelDatabase.amenities;


        Room newRoom=new Room(roomId,availability,floor,roomType,amenities,pricePerNight);
        admin.createRoom(newRoom);
        admin.readRoom(room);
        admin.readAllRooms();

        System.out.println("Enter the new price of the room: ");
        double newPrice= scanner.nextDouble();
        admin.updateRoomPrice(room,newPrice);

        admin.deleteRoom(room);
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
