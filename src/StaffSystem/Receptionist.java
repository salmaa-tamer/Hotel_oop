package StaffSystem;

import java.time.LocalDate;
import GuestandRoomSystem.Reservation;
import GuestandRoomSystem.ReservationStatus;
import GuestandRoomSystem.PaymentMethod;
import GuestandRoomSystem.HotelDatabase;
import GuestandRoomSystem.Bill;


public class Receptionist extends Staff{

    Receptionist(){
        super();
        this.role=Role.RECEPTIONIST;
    }

    public Receptionist(String username, String password, LocalDate dateOfBirth, int workingHours){
        super(username, password, dateOfBirth, workingHours);
        this.role=Role.RECEPTIONIST;
    }

    public void manageCheckIn(Reservation reservation){
        if (reservation.getStatus()==ReservationStatus.PENDING){
            reservation.confirm();
            reservation.getRoom().setAvailablity(false);
            return;
        }
        throw new IllegalArgumentException("Reservation not pending");
    }

    public void manageCheckOut(Reservation reservation, PaymentMethod paymentMethod){
        if (reservation.getStatus()==ReservationStatus.CONFIRMED){
            reservation.complete();
            reservation.getRoom().setAvailablity(true);
            Bill bill=reservation.generateBill(paymentMethod);
            HotelDatabase.bills.add(bill);
            return;
        }
        throw new IllegalArgumentException("Reservation not confirmed");
    }


}