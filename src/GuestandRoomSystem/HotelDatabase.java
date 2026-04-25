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
        public static ArrayList<Room>unaddedRooms=new ArrayList<>();

        // 2. The Dummy Data
        public static void loadDummyData() {
            // Room Types
            RoomType single = new RoomType("Single", 1, 100.0, 1);
            RoomType dbl = new RoomType("Double", 2, 200.0, 2);
            RoomType triple = new RoomType("Triple", 3, 350.0,3);
            RoomType suite = new RoomType("Suite", 4, 500.0, 4);
            roomTypes.add(single);
            roomTypes.add(dbl);
            roomTypes.add(triple);
            roomTypes.add(suite);

            // Amenities
            Amenity wifi = new Amenity(1, "WiFi", 0);
            Amenity pool = new Amenity(2, "Pool Access", 25.0);
            Amenity breakfast = new Amenity(3, "Buffet Breakfast", 10.0);
            Amenity Balcony = new Amenity(4, "Private Balcony", 50.0);
            Amenity OceanView = new Amenity(5, "OceanView", 60.0);
            Amenity Gym = new Amenity(6,"Gym and fitness center", 75);
            amenities.add(wifi);
            amenities.add(pool);
            amenities.add(breakfast);
            amenities.add(Balcony);
            amenities.add(OceanView);
            amenities.add(Gym);

            // Rooms
            // First floor
            Room room1 = new Room(101, true, 1, single, new ArrayList<>(Arrays.asList(wifi)), 100.0);
            Room room2 = new Room(102, true, 1, dbl, new ArrayList<>(Arrays.asList(wifi, breakfast)), 200.0);
            Room room9 = new Room(103, false, 1, single, new ArrayList<>(Arrays.asList(wifi, pool)), 200.0);

            // Second floor
            Room room3 = new Room(201, true, 2, dbl, new ArrayList<>(Arrays.asList(wifi)), 200.0);
            Room room4 = new Room(202, true, 2, dbl, new ArrayList<>(Arrays.asList(wifi, pool)), 200.0);
            Room room5 = new Room(203, true, 2, triple, new ArrayList<>(Arrays.asList(wifi, breakfast, Balcony)), 350.0);

            // Third floor
            Room room6 = new Room(301, false, 3, triple, new ArrayList<>(Arrays.asList(wifi, breakfast)), 350.0);
            Room room7 = new Room(302, true, 3, suite, new ArrayList<>(Arrays.asList(wifi, breakfast, OceanView)), 500.0);
            Room room8 = new Room(303, true, 3, suite, new ArrayList<>(Arrays.asList(wifi, breakfast, Gym, OceanView)), 500.0);
            rooms.add(room1);
            rooms.add(room2);
            rooms.add(room3);
            rooms.add(room4);
            rooms.add(room5);
            rooms.add(room6);
            rooms.add(room7);
            rooms.add(room8);
            rooms.add(room9);


            // Guests
            Guest guest1 = new Guest("Ahmed", "password123", LocalDate.of(1984, 4, 4), 3000.0, "5th settlement New Cairo , Egypt", Gender.MALE, "Single");
            Guest guest2 = new Guest("Fady", "246012", LocalDate.of(1999, 8, 15), 5000.0, "EL Sheikh Zayed Cairo , Egypt", Gender.MALE, "Suite");
            guests.add(guest1);
            guests.add(guest2);

            // Staff
            Staff receptionist1 = new Receptionist("rec_adam123", "receptionist1", LocalDate.of(1998, 5, 20), 8);
            Staff receptionist2 = new Receptionist("rec_ali123", "receptionist2", LocalDate.of(2000, 1, 1), 8);
            Staff receptionist3 = new Receptionist("rec_doaa123", "receptionist3", LocalDate.of(1982, 8, 15), 8);
            Staff admin1 = new Admin("admin1234", "adminpassword", LocalDate.of(1985, 11, 2), 12);
            staff.add(receptionist1);
            staff.add(receptionist2);
            staff.add(receptionist3);
            staff.add(admin1);

            // Reservations
            Reservation res1 = new Reservation(guest1, room1, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));
            Reservation res2 = new Reservation(guest2, room2, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
            reservations.add(res1);
            reservations.add(res2);

            // Bills
            Bill bill1 = new Bill(res1, PaymentMethod.Credit_Card);
            Bill bill2 = new Bill(res2, PaymentMethod.CASH);
            bills.add(bill1);
            bills.add(bill2);

            // Unadded Rooms
            Room unaddedRoom1=new Room(104);
            Room unaddedRoom2=new Room(105);
            Room unaddedRoom3=new Room(204);
            Room unaddedRoom4=new Room(205);
            Room unaddedRoom5=new Room(304);
            Room unaddedRoom6=new Room(305);
            unaddedRooms.add(unaddedRoom1);
            unaddedRooms.add(unaddedRoom2);
            unaddedRooms.add(unaddedRoom3);
            unaddedRooms.add(unaddedRoom4);
            unaddedRooms.add(unaddedRoom5);
            unaddedRooms.add(unaddedRoom6);
        }
    }