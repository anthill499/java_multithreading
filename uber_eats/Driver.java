public class Driver {
    private final String name;
    private final int driverId;
    public Driver(String name, int driverID) {
        this.name = name;
        driverId = driverID;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return driverId;
    }
}
