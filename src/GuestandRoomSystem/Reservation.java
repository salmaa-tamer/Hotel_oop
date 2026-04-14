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
setCheckOutDate(checkOutDate);
status=ReservationStatus.PENDING;
}

    private void setCheckInDate(LocalDate checkInDate) {
        if (checkInDate==null){
            throw new InvalidReservationException("Check in date can't be empty");
        }
        this.checkInDate = checkInDate;
    }


    public void setGuest(Guest guest) {
    if (guest==null){
        throw new InvalidReservationException("invalid guest");
    }
        this.guest = guest;}

    public void setRoom(Room room) {
        if (room==null){
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
    if (status==ReservationStatus.COMPLETED){
        throw new InvalidReservationException("Cannot cancel a completed reservation");
    }
            this.status= ReservationStatus.CANCELLED ; }

    public void confirm(){
        if (status==ReservationStatus.COMPLETED){
            throw new InvalidReservationException("Cannot confirm a completed reservation");
        }
        if (status==ReservationStatus.CANCELLED){
            throw new InvalidReservationException("Cannot confirm a cancelled reservation");
        }
            this.status= ReservationStatus.CONFIRMED ; }

    public void pending(){
            this.status= ReservationStatus.PENDING ;}

    public void complete(){
        if (status==ReservationStatus.CANCELLED){
            throw new InvalidReservationException("Cannot complete a cancelled reservation");
        }
        if (status==ReservationStatus.PENDING){
            throw new InvalidReservationException("Cannot complete a reservation that is non confirmed");
        }
            this.status= ReservationStatus.COMPLETED ; }
  public Bill generateBill (PaymentMethod paymentMethod){
      if (status==ReservationStatus.CANCELLED || status==ReservationStatus.PENDING) {
          throw new InvalidReservationException("cannot generate a bill for a not confirmed reservation");
      }
      Bill NewBill= new Bill (this,paymentMethod);
      return new  Bill (this,paymentMethod) ;
  }
}


