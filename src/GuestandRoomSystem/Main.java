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
        Reservation reservation1 = HotelDatabase.reservations.get(0);

        System.out.println("Are you a staff member or a guest?");
        System.out.println("1. Guest \n2. Staff");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            boolean loginSuccess = false;
            Guest guest = null;

            do {
                try {

                    System.out.println("Enter Username : ");
                    String username = scanner.nextLine();
                    System.out.println("Enter Password : ");
                    String password = scanner.nextLine();

                    guest = Guest.login(username, password); //law di confirmed + guest not null anymore
                    loginSuccess = true; //el line da hyshta8al
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Invalid username or password\n");
                }
            } while (!loginSuccess);

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
                System.out.println("How would you like to check out?");
                System.out.println("1.In the Reception\n2.Online");
                int checkoutChoice= scanner.nextInt();
                scanner.nextLine();
                if(checkoutChoice==1) {

                    System.out.println("Guest in reception");
                    System.out.println("Guest's initial balance : $" + guest.getBalance());
                    System.out.println("Guest paying in CASH...");
                    receptionist.manageCheckIn(reservation);
                    guest.inPersonCheckout(reservation, PaymentMethod.CASH);
                }
                else if (checkoutChoice==2){

                    System.out.println("Guest checking out online");
                    System.out.println("Guest's initial balance : $" + guest.getBalance());
                    System.out.println("Guest paying ONLINE...");

                    receptionist.manageCheckIn(reservation);
                    guest.onlineCheckout(reservation, PaymentMethod.ONLINE);

                }

                System.out.println("Guest's current balance: $" + guest.getBalance());
                System.out.println("\nPrinting your invoices:");

                for (Bill b : HotelDatabase.bills) {
                    // y3ml print lel bill bta3t el guest eli logged in bs msh kol el invoices + lazm tkoon completed
                    if (b.getReservation().getGuest().getUsername().equals(guest.getUsername()) &&
                            b.getReservation().getStatus() == ReservationStatus.COMPLETED) {
                        b.PrintBill();
                        System.out.println();
                    }
                }
            }

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
                    reservation1.confirm();
                    receptionist.manageCheckOut(reservation1, PaymentMethod.Credit_Card);
                    System.out.println("Checkout complete. Bill generated.");
                }
            }
            else if (choice2 == 2) {

                System.out.println("Admin checking system");
                Admin admin = (Admin) HotelDatabase.staff.get(1);
                System.out.println("Admin Adding a New Room");
                System.out.println("Enter The Id of The New Room: ");
                int roomId = scanner.nextInt();
                System.out.println("Room Availability (true/false): ");
                boolean availability = scanner.nextBoolean();
                System.out.println("Room Floor: ");
                int floor = scanner.nextInt();
                System.out.println("Price Per Night: ");
                double pricePerNight = scanner.nextDouble();
                scanner.nextLine();

                // admin sets new room type
                System.out.println("Select Room Type: ");
                for (int i = 0; i < HotelDatabase.roomTypes.size(); i++) {
                    System.out.println((i + 1) + ". " + HotelDatabase.roomTypes.get(i));
                }
                int typeChoice = scanner.nextInt();
                scanner.nextLine();
                RoomType roomType = HotelDatabase.roomTypes.get(typeChoice - 1);
                //RoomType roomType = HotelDatabase.roomTypes.get(0);
                List<Amenity> amenities = HotelDatabase.amenities;
                Room newRoom = new Room(roomId, availability, floor, roomType, amenities, pricePerNight);
                admin.createRoom(newRoom);
                admin.readAllRooms();

                // update room
                System.out.println("\nEnter the Room ID you want to update: ");
                int idToUpdate = scanner.nextInt();
                Room roomToUpdate = null;
                for (Room r : HotelDatabase.rooms) {
                    if (r.getRoomid() == idToUpdate) {
                        roomToUpdate = r;
                        break;
                    }
                }
                if (roomToUpdate != null) {
                    System.out.println("Enter the new price for Room ");
                    double newPrice = scanner.nextDouble();
                    admin.updateRoomPrice(roomToUpdate, newPrice);
                    System.out.println("Price updated");
                    admin.readAllRooms();
                } else {
                    System.out.println("Could not find a room with that ID");
                }

                // delete room
                System.out.println("\nEnter the Room ID you want to delete: ");
                int idToDelete = scanner.nextInt();
                Room roomToDelete = null;
                for (Room r : HotelDatabase.rooms) {
                    if (r.getRoomid() == idToDelete) {
                        roomToDelete = r;
                        break;
                    }
                }
                if (roomToDelete != null) {
                    System.out.println("Deleting room ");
                    admin.deleteRoom(roomToDelete);
                } else {
                    System.out.println("Could not find room");
                }
                admin.readAllRooms();

                // add new staff memberRee
                System.out.println("Adding a New Staff Member");
                System.out.println("Enter Role: ");
                System.out.println("1.Admin\n2.Receptionist");
                int choice4 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Username : ");
                String username = scanner.nextLine();
                System.out.println("Enter Password : ");
                String password = scanner.nextLine();
                System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
                String dateOfBirthInput = scanner.nextLine();
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthInput);
                System.out.println("Enter Working Hours: ");
                int workingHours = scanner.nextInt();
                scanner.nextLine();

                if (choice4==1){
                    admin.staffRegistration( new Admin (username,password,dateOfBirth,workingHours));
                } else if (choice4==2) {
                    admin.staffRegistration( new Receptionist (username,password,dateOfBirth,workingHours));
                }

                System.out.println("Viewing All Staff Members");
                admin.readStaff();
            }
            }
        }
    }
