package StaffSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import GuestandRoomSystem.HotelDatabase;
import GuestandRoomSystem.Amenity;
import GuestandRoomSystem.Room;
import GuestandRoomSystem.RoomType;


public class Admin extends Staff{

        Admin(){
            super();
            this.role=Role.ADMIN;
        }

        public Admin(String username, String password, LocalDate dateOfBirth, int workingHours){
            super(username,password,dateOfBirth,workingHours);
            this.role=Role.ADMIN;
        }

        public void createRoom(Room newRoom){
            for (Room r : HotelDatabase.rooms){
                if(newRoom.getRoomid() == r.getRoomid()){
                    throw new IllegalArgumentException("Room Already Exists");
                }
            }
           HotelDatabase.rooms.add(newRoom);
        }
        public void readRoom(Room room){
            for(Room r : HotelDatabase.rooms){
                if(r.getRoomid()==room.getRoomid()){
                    System.out.println(r);
                    return;
                }
            }
            throw new IllegalArgumentException("Room Not Found");
        }


    public void deleteRoom(Room roomToDelete){
            for(int i =0 ; i<HotelDatabase.rooms.size(); i++){
                if(HotelDatabase.rooms.get(i).getRoomid() == roomToDelete.getRoomid()){
                    HotelDatabase.rooms.remove(i);
                    return;
                }
            }
            throw new IllegalArgumentException("Room Not Found");
        }

        public void updateRoomPrice(Room room, double newPrice){
            for ( Room r : HotelDatabase.rooms){
                if (r.getRoomid()==room.getRoomid()){
                    r.setPricepernight(newPrice);
                    return;
                }
            }
            throw new IllegalArgumentException("Room Not Found");
        }

        public void updateRoomAmenities(Room room, ArrayList<Amenity> newAmenities){
            for(Room r : HotelDatabase.rooms){
                if(room.getRoomid()==r.getRoomid()) {
                    r.getAmenities().clear();
                    r.getAmenities().addAll(newAmenities);
                    return;
                }
            }
            throw new IllegalArgumentException("Room Not Found");
        }

        public void createAmenity(Amenity newAmenity){
            for(Amenity a : HotelDatabase.amenities){
                if(a.getAmenityid()==newAmenity.getAmenityid()){
                    throw new IllegalArgumentException("Amenity Already Exists");
                }
            }
            HotelDatabase.amenities.add(newAmenity);
        }

        public void readAllAmenities(){
            for (Amenity amenity: HotelDatabase.amenities){
                System.out.println(amenity);
            }
        }

        public void readAmenity(Amenity amenity){
            for(Amenity a : HotelDatabase.amenities){
                if(amenity.getAmenityid()==a.getAmenityid()){
                    System.out.println(a);
                    return;
                }
            }
            throw new IllegalArgumentException("Amenity Not Found");
        }

        public void updateAmenityName(Amenity amenity, String newName){
            for(Amenity a : HotelDatabase.amenities){
                if(a.getAmenityid()==amenity.getAmenityid()){
                    a.setName(newName);
                    return;
                }

            }
            throw new IllegalArgumentException("Amenity Not Found");
        }

        public void updateAmenityCost(Amenity amenity, double newCost){
            for (Amenity a : HotelDatabase.amenities){
                if (a.getAmenityid()==amenity.getAmenityid()){
                    a.setAdditionalcost(newCost);
                    return;
                }
            }
            throw new IllegalArgumentException("Amenity Not Found");
        }

        public void deleteAmenity(Amenity amenityToDelete){
            for(int i=0 ; i<HotelDatabase.amenities.size(); i++){
                if (HotelDatabase.amenities.get(i).getAmenityid()==amenityToDelete.getAmenityid()){
                    HotelDatabase.amenities.remove(i);
                    return;
                }
            }
            throw new IllegalArgumentException("Amenity Not Found");
        }

        public void createRoomType(RoomType roomType){
            for(RoomType rt : HotelDatabase.roomTypes){
                if(rt.getRoomtypeid()==roomType.getRoomtypeid()){
                    throw new IllegalArgumentException("Room Type Already Exists");
                }
            }
            HotelDatabase.roomTypes.add(roomType);
        }

        public void readAllRoomTypes(){
            for (RoomType rt : HotelDatabase.roomTypes){
                System.out.println(rt);
            }
        }

        public void readRoomType(RoomType roomType){
            for(RoomType rt : HotelDatabase.roomTypes){
                if(rt.getRoomtypeid()==roomType.getRoomtypeid()){
                    System.out.println(rt);
                    return;
                }
            }
            throw new IllegalArgumentException("There is no such room type");
        }

        public void updateRoomTypeName(RoomType roomType, String newName){
            for (RoomType rt: HotelDatabase.roomTypes){
                if (rt.getRoomtypeid()== roomType.getRoomtypeid()){
                    rt.setName(newName);
                    return;
                }
            }
            throw new IllegalArgumentException("There is no such room type");
        }

        public void updateRoomTypePrice(RoomType roomType,double newPrice){
            for (RoomType rt: HotelDatabase.roomTypes){
                if (rt.getRoomtypeid()== roomType.getRoomtypeid()){
                    rt.setBaseprice(newPrice);
                    return;
                }
            }
        throw new IllegalArgumentException("There is no such room type");
        }

    public void deleteRoomTypes(RoomType roomType) {
        for (int i = 0; i < HotelDatabase.roomTypes.size(); i++) {
            if (HotelDatabase.roomTypes.get(i).getRoomtypeid() == roomType.getRoomtypeid()) {
                HotelDatabase.roomTypes.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("There is no such room type");
    }

}