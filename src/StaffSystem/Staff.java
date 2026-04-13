package StaffSystem;

import GuestandRoomSystem.Guest;
import GuestandRoomSystem.HotelDatabase;
import GuestandRoomSystem.Reservation;
import GuestandRoomSystem.Room;
import java.time.LocalDate;

public abstract class Staff{

    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private int workingHours;
    protected Role role;


    Staff(){}

    Staff(String username, String password, LocalDate dateOfBirth, int workingHours){
        this.username = username;
        this.password = password;
        this. dateOfBirth = dateOfBirth;
        this.workingHours =workingHours;
    }


    public void setUsername(String username) {
        if (username== null || username.length()<8){
            throw new IllegalArgumentException("Username must be at least 8 characters");
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password){
        if( password == null || password.length()<8){
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

    public void setWorkingHours(int workingHours){
        this.workingHours = workingHours;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public Role getRole() {
        return role;
    }

    public boolean login(String username, String password){
        // check if any staff member has a username and password which match user inputs
        // yes: successful login
        // no: throw an exception
        return true;  //temporary return to avoid errors until logic is implemented
    }

    public void viewGuests(){
        for(Guest guest : HotelDatabase.guests){
            System.out.println(guest.getUsername());
        }
    }

    public void viewRooms(){
        for(Room room : HotelDatabase.rooms){
            System.out.println(room.toString());
        }
    }

    public void viewReservations(){
       for (Reservation reservation : HotelDatabase.reservations){
           System.out.println(reservation);
       }
    }
}