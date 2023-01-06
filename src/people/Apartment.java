package people;

public class Apartment {
    private String flatNumber;
    private int floorNumber;
    private String blockName;
    private int bhkSize;
    private  int totalSquareFeet;
    private Tenant tenant;

    public Apartment(String flatNumber, int floorNumber, String blockName, int bhkSize, int totalSquareFeet) {
        this.flatNumber = flatNumber;
        this.floorNumber = floorNumber;
        this.blockName = blockName;
        this.bhkSize = bhkSize;
        this.totalSquareFeet = totalSquareFeet;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getBlockName() {
        return blockName;
    }

    public int getBhkSize() {
        return bhkSize;
    }

    public int getTotalSquareFeet() {
        return totalSquareFeet;
    }

    public Tenant getTenant() {
        return tenant;
    }
}
