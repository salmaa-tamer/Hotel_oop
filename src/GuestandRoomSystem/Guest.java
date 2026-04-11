package GuestandRoomSystem;
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
     this.password=password;
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
        }

        for(Guest g :HotelDatabase.guests){
            if (username.equals(g.getUsername()) && password.equals(g.getPassword())){
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
    public void makeReservation(Room room){

        if (room==null){
            System.out.print("Invalid room.");
            return;
        }
        if (!room.Isavailable()){
            System.out.println("Room is not available.");
            return;
        }
        Reservation reservation =new Reservation(this,room);         //person 4
        HotelDatabase.reservation.add(reservation);
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
    public void checkout(Reservation reservation){
        if (reservation==null){
            System.out.println("No reservation.");
            return;
        }
        Bill bill =reservation.generateBill();                            //person 4
        double total =bill.getTotalAmount();                              //person 4
        if (balance>=total){
            balance -= total;
            reservation.complete();                                        //person 4
            System.out.println("Payment successful.");
        }
        else {
            System.out.println("Balance is not enough.");
        }
    }


    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public double getBalance(){
        return balance;
    }


}
