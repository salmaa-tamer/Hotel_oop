package RoomSystem;

import java.util.List;

public class Room {
    private int roomid;
    private int roomnumber;
    private boolean isavailable;
    private int floor;
    private RoomType roomtype;
    private List<Amenity> amenities;
    private double pricepernight;

    public double getPricepernight() {
        return pricepernight;
    }

    public int getRoomid() {
        return roomid;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setAvailablity(boolean status) {
        isavailable=status;
    }

    public void setPricepernight(double pricepernight) {
        this.pricepernight = pricepernight;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public RoomType getRoomtype() {
        return roomtype;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public void setRoomtype(RoomType roomtype) {
        this.roomtype = roomtype;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public boolean isIsavailable() {
        return isavailable;
    }

    public Room(int roomid, boolean status, int floor, RoomType roomtype, List<Amenity> amenities, double pricepernight) {
        this.roomid = roomid;
        isavailable = status;
        this.floor = floor;
        this.roomtype = roomtype;
        this.amenities = amenities;
        this.pricepernight = pricepernight;
    }
    public void addamenity(Amenity a){
        if(a!=null) {
            amenities.add(a);
        }
    }
    public void removeamenity(Amenity a){
      amenities.remove(a);
    }
    public double CalculateTotalPrice(int nights){
        if (nights < 1) {
            throw new IllegalArgumentException("Nights must be at least 1");
        }
        double additionalprice=0;
        for(int i=0; i<=amenities.size();i++){
            additionalprice = additionalprice +amenities.get(i).getAdditionalcost();
        }
        double Total= (roomtype.getBaseprice()+additionalprice)*nights;
        return Total;

    }

    public String toString(){
     return "Room Number :"+roomnumber+",Availablity :"+(isavailable?"YES":"NO")+",Floor :"+floor+",PricePernight :"+
             pricepernight +roomtype.toString();
    }

}
