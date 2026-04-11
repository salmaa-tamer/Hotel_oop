package Staff;

import RoomSystem.Room;
import RoomSystem.RoomType;
import RoomSystem.Amenity;
import java.time.LocalDate;

public class Admin extends Staff{

        Admin(){
            super();
            this.role=Role.ADMIN;
        }

        Admin(String username, String password, LocalDate dateOfBirth, int workingHours){
            super(username,password,dateOfBirth,workingHours);
            this.role=Role.ADMIN;
        }

}