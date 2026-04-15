package GuestandRoomSystem;

import StaffSystem.Admin;
import StaffSystem.Receptionist;
import StaffSystem.Role;
import StaffSystem.Staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class HotelDatabase {
    // 1. The ArrayLists
    public static ArrayList<Guest> guests = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Bill> bills = new ArrayList<>();
    public static ArrayList<Staff> staff = new ArrayList<>();
    public static ArrayList<RoomType> roomTypes = new ArrayList<>();
    public static ArrayList<Amenity> amenities = new ArrayList<>();

    // 3. The Dummy Data
    public static void loadDummyData() {
        // Room Types
        RoomType single = new RoomType("Single", 1, 100.0, 1);
        RoomType suite = new RoomType("Suite", 4, 350.0, 2);

        roomTypes.add(single);
        roomTypes.add(suite);

        // Amenities
        Amenity wifi = new Amenity(1, "WiFi", 10.0);
        Amenity pool = new Amenity(2, "Pool Access", 25.0);

        amenities.add(wifi);
        amenities.add(pool);

        // Rooms
        Room room1 = new Room(101, true, 1, single, new ArrayList<>(Arrays.asList(wifi)), 120.0);
        Room room2 = new Room(201, true, 2, suite, new ArrayList<>(Arrays.asList(wifi, pool)), 400.0);
        rooms.add(room1);
        rooms.add(room2);


        // Guests
        Guest guest1 = new Guest("Ahmed", "password123", LocalDate.of(1984, 4, 4), 1500.0, "5th settlement New Cairo , Egypt", Gender.MALE, "Single");
        Guest guest2 = new Guest("Fady", "24601", LocalDate.of(1999, 8, 15), 500.0, "EL Sheikh Zayed Cairo , Egypt", Gender.MALE, "Suite");
        guests.add(guest1);
        guests.add(guest2);

        // Staff
        Staff receptionist1 = new Receptionist("rec_adam", "receptionist", LocalDate.of(1998, 5, 20), 8);
        Staff admin1 = new Admin("admin", "adminpassword", LocalDate.of(1985, 11, 2), 8);

        staff.add(receptionist1);
        staff.add(admin1);

        // Reservations
        Reservation res1 = new Reservation(guest1, room1, LocalDate.now().plusDays(1), LocalDate.now().plusDays(5));
        reservations.add(res1);

        // Bills
        Bill bill1 = new Bill(res1, PaymentMethod.Credit_Card);
        bills.add(bill1);
    }
}