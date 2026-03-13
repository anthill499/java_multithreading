import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverQueue {
    private static final Logger logger = Logger.getLogger(DriverQueue.class.getName());
    private final Deque<Driver> dq = new ArrayDeque<>(); // Actual queue data structure
    private final Set<String> rdn = new HashSet<>(); // Hash set with ready driver names
    private final Thread dt;

    public DriverQueue() {
        for (String name : DriverData.getDriverNames()) {
            Driver driver = new Driver(name);
            dq.add(driver);
            rdn.add(driver.getName());
        }
        dt = new Thread();
        dt.setName("Drivers Thread");

        logger.log(Level.INFO, String.format("🚀 Driver Queue ready with %d drivers + Thread created with name: %s!",
                dq.size(), dt.getName()));
    }

    // getters and setters
    public Deque<Driver> getQueue() {
        return dq;
    }

    public Set<String> getReadyDriverNames() {
        return rdn;
    }

    public boolean isEmpty() {
        return rdn.isEmpty() && dq.isEmpty();
    }

    public int numDrivers() {
        return dq.size();
    }

    public void addDriver(Driver driver) {
        if (!rdn.contains(driver.getName())) {
            dq.addLast(driver);
            rdn.add(driver.getName());
        }
    }

    public Driver getNextDriver() {
        Driver nextDriver = dq.removeFirst();
        rdn.remove(nextDriver.getName());
        return nextDriver;
    }
}