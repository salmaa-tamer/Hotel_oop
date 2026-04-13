package GuestandRoomSystem;

public class RoomType {
private String name ; //Single or Double or Suite
private int capacity;
private double baseprice;
private int roomtypeid;

    public RoomType(String name, int capacity, double baseprice, int roomtypeid) {
        setName(name);
        setCapacity(capacity);
        setBaseprice(baseprice);
        this.roomtypeid = roomtypeid;
    }

    public void setBaseprice(double baseprice) {

        if(baseprice<=0){
            throw new IllegalArgumentException ("Invalid Price");
        }
        this.baseprice = baseprice;
    }

    public void setCapacity(int capacity) {
        if(capacity<1){
            throw new IllegalArgumentException("Invalid capacity");
        }
        this.capacity = capacity;
    }

    public void setName(String name) {
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name can't be emty");
        }
        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Type name must contain letters only");
        }
        this.name = name;
    }

    public void setRoomtypeid(int roomtypeid) {
        this.roomtypeid = roomtypeid;
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

    @Override
    public String toString() {
        return "Name :"+name+"(Capacity :"+capacity+",Baseprice :"+baseprice +")";
    }
}
