package GUI.Controllers;

import GuestandRoomSystem.Guest;
import StaffSystem.Staff;

public class SessionManager {
    private static Guest currentGuest=null;
    private static Staff currentStaff= null;

    public static Guest getCurrentGuest(){
        return currentGuest;
    }
    public static void setCurrentGuest(Guest g){
        currentGuest=g;
    }

    public static Staff getCurrentStaff(){
        return currentStaff;
    }
    public static void setCurrentStaff(Staff s){
        currentStaff=s;
    }

    public static void logout() {
        currentGuest = null;
        currentStaff = null;
    }
}
