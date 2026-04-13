package StaffSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import GuestandRoomSystem.HotelDatabase;
import GuestandRoomSystem.Amenity;
import GuestandRoomSystem.Reservation;
import GuestandRoomSystem.Room;



public class Admin extends Staff{

        Admin(){
            super();
            this.role=Role.ADMIN;
        }

        Admin(String username, String password, LocalDate dateOfBirth, int workingHours){
            super(username,password,dateOfBirth,workingHours);
            this.role=Role.ADMIN;
        }

        public void addRoom(Room newRoom){
            for (Room r : HotelDatabase.rooms){
                if(newRoom.getRoomid() == r.getRoomid()){
                    throw new IllegalArgumentException("Room Already Exists");
                }
            }
           HotelDatabase.rooms.add(newRoom);
        }

        public void deleteRoom(Room roomToDelete){
            for(int i =0 ; i<HotelDatabase.rooms.size(); i++){
                if(HotelDatabase.rooms.get(i).getRoomid() == roomToDelete.getRoomid()){
                    HotelDatabase.rooms.remove(i);
                    return;
                }
                throw new IllegalArgumentException("Room Not Found!");
            }

        }

        public void updateRoomPrice(Room room, double newPrice){
            for ( Room r : HotelDatabase.rooms){
                if (r.getRoomid()==room.getRoomid()){
                    r.setPricepernight(newPrice);
                    return;
                }
            }
            throw new IllegalArgumentException("Room Not Found!");
        }

        public void updateRoomAmenities(Room room, ArrayList<Amenity> newAmenities){
            for(Room r : HotelDatabase.rooms){
                if( room.getRoomid()==r.getRoomid()) {
                    r.getAmenities().clear();
                    r.getAmenities().addAll(newAmenities);
                    return;
                }
            }
            throw new IllegalArgumentException("Room Not Found!");
        }

        public void addAmenities(){/* amenities database needed*/ }
        public void readAmenities(){/* amenities database needed*/ }
        public void updateAmenities(){/* amenities database needed*/ }
        public void deleteAmenities(){/* amenities database needed*/ }

        public void addRoomTypes(){/* room type database needed*/ }
        public void ReadRoomTypes(){/* room type database needed*/ }
        public void updateRoomTypes(){/* room type database needed*/ }
        public void DeleteRoomTypes(){/* room type database needed*/ }









}