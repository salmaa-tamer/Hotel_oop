package RoomSystem;

public class RoomType {
private String name ; //Single or Double or Suite
private int capacity;
private double baseprice;
private int roomtypeid;

    public void setBaseprice(double baseprice) {
        this.baseprice = baseprice;
    }

    public double getBaseprice() {
        return baseprice;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRoomtypeid() {
        return roomtypeid;
    }

    public RoomType(String name, int capacity, double baseprice, int roomtypeid) {
        this.name = name;
        this.capacity = capacity;
        this.baseprice = baseprice;
        this.roomtypeid = roomtypeid;
    }

    @Override
    public String toString() {
        return "Name :"+name+"(Capacity :"+capacity+",Baseprice :"+baseprice +")";
    }
}
