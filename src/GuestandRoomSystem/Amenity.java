package GuestandRoomSystem;

public class Amenity {
    private int amenityid;
    private String name;
    private double additionalcost;

    public Amenity(int amenityid, String name, double additionalcost) {
        this.amenityid = amenityid;
        setName(name);
       setAdditionalcost(additionalcost);
    }

    public void setAdditionalcost(double additionalcost) {
        if(additionalcost<0){
            throw new IllegalArgumentException("Invalid Additionalcost");
        }
        this.additionalcost = additionalcost;
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

    public int getAmenityid() {
        return amenityid;
    }

    public String getName() {
        return name;
    }

    public double getAdditionalcost() {
        return additionalcost;
    }

    @Override
    public String toString() {
        return "Name:"+name + ",Additionalcost :"+ additionalcost;
    }

}
