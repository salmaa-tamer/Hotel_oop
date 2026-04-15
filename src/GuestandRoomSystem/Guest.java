package GuestandRoomSystem;
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
            System.out.println("Invalid input.");
            return null;
        }

        for(Guest g :HotelDatabase.guests){
            if (username.equals(g.username) && password.equals(g.password)){
                System.out.println("Login successful.");
                return g;
            }
        }
        System.out.println("Login failed.");
        return null;
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
    public void makeReservation(Room room,LocalDate checkIn, LocalDate checkOut) throws RoomNotAvailableException {

        if (room==null){
            System.out.print("Invalid room.");
            return;
        }
        if (!room.Isavailable()){
            throw new exceptions.RoomNotAvailableException("Room is not available for booking.");
        }
        Reservation reservation =new Reservation(this,room,checkIn,checkOut);
        HotelDatabase.reservations.add(reservation);
        System.out.println("Reservation created.");
        room. setAvailablity(false);

    }
    public void cancelReservation(Reservation reservation){
        if (reservation == null){
            System.out.println("No reservation to cancel.");
            return;
        }
        reservation.cancel();
        System.out.println("Reservation cancelled.");
    }
    public void inPersonCheckout(Reservation reservation,PaymentMethod paymentMethod){

        if (reservation==null){
            System.out.println("No reservation.");
            return;
        }
        if (paymentMethod==PaymentMethod.ONLINE){
            System.out.println("Online payment is not available.");
            return;
        }
        if (reservation.getStatus()==ReservationStatus.CONFIRMED) {
            Bill bill = reservation.generateBill(paymentMethod);
            //person 4
            double total = bill.getTotalAmount();
            if (balance >= total) {
                balance -= total;
                reservation.complete();
                System.out.println("Payment successful.");
            }
            else {
                throw new exceptions.InvalidPaymentException("Guest balance is not enough to cover the bill.");
            }
        }
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        if (dateOfBirth.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Invalid date of birth");
        }
  this.dateOfBirth=dateOfBirth;
    }

public void setUsername(String username){
        if (username.length() <= 4|| username==null )
        {throw new IllegalArgumentException("username must be at least 4 characters"); }
        this.username=username;
}

    public void setBalance(double balance) {
        if (balance <=0){
            throw new IllegalArgumentException("Invalid balance");
        }
        this.balance = balance;
    }

    public void setAddress(String address) {
        if (address.length() <= 5 || address==null)
        {throw new IllegalArgumentException("Address must be at least 5 characters"); }
        this.address = address;
    }

    public void setRoomPreference(String roomPreference) {
        if (roomPreference.length() <= 4 || roomPreference==null)
        {throw new IllegalArgumentException("Room pereference must be at least 4 characters"); }
        this.roomPreference=roomPreference;

    }
    public void setPassword(String password){
        if (password.length() <= 5 || password==null)
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
