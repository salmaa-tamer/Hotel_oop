package GuestandRoomSystem;
import exceptions.InvalidPaymentException;
import exceptions.RoomNotAvailableException;
import java.time.LocalDate;

public class Guest {
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private double balance;
    private String address;
    final Gender gender;
    private String roomPreference;


    public Guest(String username, String password, LocalDate dateOfBirth, double balance
               , String address, Gender gender, String roomPreference) {
        setUsername(username);
        setPassword(password);
        setAddress(address);
        setBalance(balance);
        setRoomPreference(roomPreference);
        setDateOfBirth(dateOfBirth);
    this.gender =gender;
 }
 public void register(){
        HotelDatabase.guests.add(this);
        System.out.println("Guest registered successfully.");
 }
 public static Guest login(String username,String password){
        if (username==null  ||  password==null){
            throw new IllegalArgumentException("Invalid input.");}
        for(Guest g :HotelDatabase.guests){
            if (username.equals(g.username) && password.equals(g.password)){
                System.out.println("Login successful.");
                System.out.println("Welcome back:)");
                return g;
            }
        }
throw new IllegalArgumentException("Invalid username or password.");
    }

    public void viewAvailableRooms(){
        boolean found = false;
        for (Room r : HotelDatabase.rooms){
            if(r.Isavailable()){
                System.out.println(r);
                found=true;
            }
        }
        if(!found){
            System.out.println("No rooms available.");
        }
    }
    public void makeReservation(Room room,LocalDate checkIn, LocalDate checkOut) {

        if (room==null){
            throw new IllegalArgumentException("Invalid room");
        }
        if (!room.Isavailable()){
            throw new RoomNotAvailableException("Room is not available for booking.");
        }
        Reservation reservation =new Reservation(this,room,checkIn,checkOut);
        HotelDatabase.reservations.add(reservation);
        System.out.println("Reservation created.");
        room. setAvailablity(false);

    }
    public void cancelReservation(Reservation reservation){
        if (reservation == null){
            throw new IllegalArgumentException("Reservation not found");
        }
        reservation.cancel();
        System.out.println("Reservation cancelled.");
    }
    public void inPersonCheckout(Reservation reservation,PaymentMethod paymentMethod){

        if (reservation==null){
            throw new IllegalArgumentException("No reservation.");
        }
        if (paymentMethod==PaymentMethod.ONLINE){
           throw new InvalidPaymentException("Online payment is not allowed for in-person checkout.");
        }
        if (reservation.getStatus()!=ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Reservation is not confirmed");
        }

            Bill bill = reservation.generateBill(paymentMethod);
            //person 4
            double total = bill.getTotalAmount();
            if (balance >= total) {
                balance -= total;
                reservation.complete();
                System.out.println("Payment successful.");
            }
            else {
                throw new InvalidPaymentException("Guest balance is not enough to cover the bill.");
        }
    }
    public void onlineCheckout(Reservation reservation,PaymentMethod paymentMethod){
        if (reservation==null) {
            throw new IllegalArgumentException("No reservation found.");
        }
        if (paymentMethod==PaymentMethod.Credit_Card || paymentMethod==PaymentMethod.CASH){
throw new IllegalArgumentException("Payment type is not available.");
        }
        if (reservation.getStatus()!=ReservationStatus.CONFIRMED) {
            throw new IllegalArgumentException("Reservation is not confirmed.");
        }

                Bill bill = reservation.generateBill(paymentMethod);
                double total = bill.getTotalAmount();
                if (balance < total) {
                    throw new InvalidPaymentException("Insufficient balance.");}
                //person 4
                    balance -= total;
                    reservation.complete();
                    System.out.println("Payment successful.");
                    return;
    }

public void setUsername(String username){
        if ( username==null  || username.length() <= 4)
        {throw new IllegalArgumentException("username must be at least 4 characters"); }
        this.username=username;
}

    public void setBalance(double balance) {
        if (balance <=0){
            throw new IllegalArgumentException("Invalid balance");
        }
        this.balance = balance;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        if ( dateOfBirth==null|| dateOfBirth.isAfter(LocalDate.now()) ){
            throw new IllegalArgumentException("Invalid date of birth");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        if (address==null || address.length() <= 5 )
        {throw new IllegalArgumentException("Address must be at least 5 characters"); }
        this.address = address;
    }

    public void setRoomPreference(String roomPreference) {
        if (roomPreference==null || roomPreference.length() <= 4 )
        {throw new IllegalArgumentException("Room preference must be at least 4 characters"); }
        this.roomPreference=roomPreference;

    }
    public void setPassword(String password){
        if ( password==null || password.length() <= 5 )
        {throw new IllegalArgumentException("Password must be at least 5 characters"); }

        this.password=password;
    }

    public String getUsername(){
           return username;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public double getBalance(){
        return balance;
    }
    public String getAddress(){
        return address;
    }
    public Gender getGender() {
        return gender;
    }
    public String getRoomPreference(){
        return roomPreference;
    }
@Override
    public String toString(){
        return "Guest{" +
                "username= '" +username+'\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", balance=" + balance +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", roomPreference='" + roomPreference + '\'' +
                '}';
    }


}
