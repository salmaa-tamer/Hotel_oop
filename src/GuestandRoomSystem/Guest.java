package GuestandRoomSystem;
import exceptions.RoomNotAvailableException;

import java.time.LocalDate;
public class Guest {
    final String username;
    private String password;
    final LocalDate dateOfBirth;
    private double balance;
    private String address;
    final Gender gender;
    private String roomPreference;


    public Guest(String username, String password, LocalDate dateOfBirth, double balance
               , String address, Gender gender, String roomPreference) {
     this.username=username;
     setPassword(password);
     this.dateOfBirth=dateOfBirth;
     this.balance=balance;
     this.address =address;
     this.gender=gender;
     this.roomPreference=roomPreference;
 }
 public void register(){
        HotelDatabase.guests.add(this);                 //person 5
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
    public void viewAvailableRooms(){                         //person 3
        boolean found = false;
        for (Room r : HotelDatabase.rooms){
            if(r.Isavailable()){                                    //person 3
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
        Reservation reservation =new Reservation(this,room,checkIn,checkOut);         //person 4
        HotelDatabase.reservations.add(reservation);
        System.out.println("Reservation created.");
    }
    public void cancelReservation(Reservation reservation){
        if (reservation == null){
            System.out.println("No reservation to cancel.");
            return;
        }
        reservation.cancel();                                        //person 4
        System.out.println("Reservation cancelled.");
    }
    public void checkout(Reservation reservation,PaymentMethod paymentMethod){
    public void checkout(Reservation reservation, PaymentMethod paymentMethod){
        if (reservation==null){
            System.out.println("No reservation.");
            return;
        }
        Bill bill =reservation.generateBill(paymentMethod);                            //person 4
        Bill bill =reservation.generateBill( paymentMethod);                            //person 4
        double total =bill.getTotalAmount();                              //person 4
        if (balance>=total){
            balance -= total;
            reservation.complete();                                        //person 4
            System.out.println("Payment successful.");
        }
        else {
            throw new exceptions.InvalidPaymentException("Guest balance is not enough to cover the bill.");
        }
    }


    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password=password;
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
