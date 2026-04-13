package StaffSystem;

import java.time.LocalDate;
import GuestandRoomSystem.Reservation;
import  GuestandRoomSystem.ReservationStatus;



public class Receptionist extends Staff{

    Receptionist(){
        super();
        this.role=Role.RECEPTIONIST;
    }

    Receptionist(String username, String password, LocalDate dateOfBirth, int workingHours){
        super(username, password, dateOfBirth, workingHours);
        this.role=Role.RECEPTIONIST;
    }




}