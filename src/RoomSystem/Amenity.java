package RoomSystem;

public class Amenity {
    private int amenityid;
    private String name;
    private double additionalcost;

    public void setAdditionalcost(double additionalcost) {
        this.additionalcost = additionalcost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmenityid() {
        return amenityid;
    }

    public String getName() {
        return name;
    }

    public double getAdditionalcost() {
        return additionalcost;
    }

    public Amenity(int amenityid, String name, double additionalcost) {
        this.amenityid = amenityid;
        this.name = name;
        this.additionalcost = additionalcost;
    }

    @Override
    public String toString() {
        return "Name:"+name + ",Additionalcost :"+ additionalcost;
    }


}
