package GuestandRoomSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class HotelDatabase {
    // Arraylist
    public static ArrayList<Guest> guests = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Bill> bills = new ArrayList<>();

    // The Dummy Data
    public static void loadDummyData() {
        // Room Types
        RoomType single = new RoomType("Single", 1, 100.0, 1);
        RoomType suite = new RoomType("Suite", 4, 350.0, 2);

        // Amenities
        Amenity wifi = new Amenity(1, "WiFi", 10.0);
        Amenity pool = new Amenity(2, "Pool Access", 25.0);
    }
}