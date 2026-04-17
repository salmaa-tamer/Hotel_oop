package GuestandRoomSystem;

import StaffSystem.Admin;
import StaffSystem.Receptionist;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String []args){

        HotelDatabase.loadDummyData();
        Receptionist receptionist = (Receptionist)HotelDatabase.staff.get(0);
        Room room = HotelDatabase.rooms.get(0);
        Reservation reservation1 =HotelDatabase.reservations.get(0);
        System.out.println("Are you a staff member or a guest?");
        System.out.println("1. Guest \n2. Staff");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            boolean run = true;
            do {
                try {
                    System.out.println("Enter Username : ");
                    String username = scanner.nextLine();
                    System.out.println("Enter Password : ");
                    String password = scanner.nextLine();

                    Guest guest = Guest.login(username, password);


                    if (guest != null) {
                        System.out.println("View Available Rooms");
                        guest.viewAvailableRooms();

                        System.out.println("Guest makes reservation test");
                        System.out.println("Enter check in date (YYYY-MM-DD): ");
                        String checkinDateInput = scanner.nextLine();
                        LocalDate checkinDate = LocalDate.parse(checkinDateInput);

                        System.out.println("Enter check out date (YYYY-MM-DD): ");
                        String checkoutDateInput = scanner.nextLine();
                        LocalDate checkoutDate = LocalDate.parse(checkoutDateInput);

                        guest.makeReservation(room, checkinDate, checkoutDate);

                        Reservation reservation = new Reservation(guest, room, checkinDate, checkoutDate);

                        System.out.println("Guest in reception");
                        System.out.println("Guest's current balance : $" + guest.getBalance());
                        System.out.println("Guest paying in CASH...");

                        receptionist.manageCheckIn(reservation);
                        guest.inPersonCheckout(reservation, PaymentMethod.CASH);

                        System.out.println("Guest's current balance: $" + guest.getBalance());

                        System.out.println("\nPrinting all system invoices:");
                        for (Bill b : HotelDatabase.bills) {
                            b.PrintBill();
                            System.out.println();
                        }
                    }

                }
                catch (IllegalArgumentException e) {
                    System.out.println("Invalid username or password\n");
                    run=false;
                }
            }while (run==false);
        }
        else if (choice == 2) {
            System.out.println("Are you a receptionist or an admin?");
            System.out.println("1. Receptionist \n2. Admin");
            int choice2 = scanner.nextInt();
            scanner.nextLine();

            if (choice2 == 1) {
                System.out.println("Receptionist managing system");
                System.out.println("Enter username: ");
                String receptionistUsername = scanner.nextLine();
                System.out.println("Enter password: ");
                String receptionistPassword = scanner.nextLine();

                receptionist.login(receptionistUsername, receptionistPassword);

                System.out.println("Guest checking in or checking out?");
                System.out.println("1. Checking in\n2. Checking out");
                int choice3 = scanner.nextInt();
                scanner.nextLine();

                if (choice3 == 1) {
                    receptionist.manageCheckIn(reservation1);
                    System.out.println("Welcome.");
                }
                else if (choice3 == 2) {
                    System.out.println("Receptionist managing check-out...");
                    receptionist.manageCheckOut(reservation1, PaymentMethod.Credit_Card);
                    System.out.println("Checkout complete. Bill generated.");
                }
            }
            else if (choice2 == 2) {
                System.out.println("Admin checking system");
                Admin admin = (Admin) HotelDatabase.staff.get(1);

                System.out.println("Enter the Room Id: ");
                int roomId = scanner.nextInt();
                System.out.println("Room Availability (true/false): ");
                boolean availability = scanner.nextBoolean();
                System.out.println("Room Floor: ");
                int floor = scanner.nextInt();
                System.out.println("Price per night: ");
                double pricePerNight = scanner.nextDouble();

                RoomType roomType = HotelDatabase.roomTypes.get(0);
                List<Amenity> amenities = HotelDatabase.amenities;

                Room newRoom = new Room(roomId, availability, floor, roomType, amenities, pricePerNight);
                admin.createRoom(newRoom);
                admin.readAllRooms();

                System.out.println("Enter the new price of the room: ");
                double newPrice = scanner.nextDouble();
                admin.updateRoomPrice(room, newPrice);

                System.out.println("Deleting room to test functionality...");
                admin.deleteRoom(room);
                admin.readAllRooms();
            }
        }
    }
}