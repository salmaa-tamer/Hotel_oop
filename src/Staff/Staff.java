package Staff;

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
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
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
        // yes: succesful login
        // no: throw an exception
        return true;  //temporary return to avoid errors until logic is implemented
    }

    public void viewGuests(){
        //print guests from arraylist in database class
    }

    public void viewRooms(){
        //print rooms from arraylist in database class
    }

    public void viewReservations(){
        //print reservations from arraylist in database class
    }
}