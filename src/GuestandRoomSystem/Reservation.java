package GuestandRoomSystem;
import exceptions.InvalidReservationException;
import java.time.LocalDate;
public class Reservation {
    private Guest guest ;
    private Room room ;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status ;
public Reservation(Guest guest,Room room,LocalDate checkInDate,LocalDate checkOutDate){
setGuest (guest);
setRoom (room);
setCheckInDate(checkInDate);
setCheckOutDate(checkOutDate);}

    private void setCheckInDate(LocalDate checkInDate) {
        if (checkInDate==null){
            throw new InvalidReservationException("Check in date can't be empty");
        }
    }


    public void setGuest(Guest guest) {
    if (guest==null){
        throw new InvalidReservationException("invalid guest");
    }
        this.guest = guest;}

    public void setRoom(Room room) {
        if (guest==null){
            throw new InvalidReservationException("invalid room");
        }
        this.room = room;}

    public void setCheckOutDate( LocalDate checkOutDate) {
        if (checkOutDate==null){
            throw new InvalidReservationException("Check out date can't be empty");
        }
        if (checkOutDate.isBefore(this.checkInDate)){
            throw new InvalidReservationException("Check out date can't be before check in date");}
        this.checkOutDate = checkOutDate; }

    public Room getRoom() {
        return room; }

    public LocalDate getCheckInDate() {
        return checkInDate; }

    public Guest getGuest() {
        return guest; }

    public LocalDate getCheckOutDate() {
         return checkOutDate; }

    public void cancel(){
            this.status= ReservationStatus.CANCELLED ; }

    public void confirm(){
            this.status= ReservationStatus.CONFIRMED ; }

    public void pending(){
            this.status= ReservationStatus.PENDING ;}

    public void complete(){
            this.status= ReservationStatus.COMPLETED ; }
    public void calculateTotalPrice(){


    }

}


