import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverQueue {
    private static final Logger logger = Logger.getLogger(DriverQueue.class.getName());
    private final Deque<Driver> dq = new ArrayDeque<>(); // Actual queue data structure
    private final Set<String> rdn = new HashSet<>(); // Hash set with ready driver names
    private final DeliveryHistory history = new DeliveryHistory();
    private final Restaurant restaurant;
    public DriverQueue(Restaurant restaurant) {
        List<String> driverData = DriverData.getDriverNames();
        for (int i = 0; i < driverData.size(); i++) {
            Driver driver = new Driver(driverData.get(i), i+1);
            dq.add(driver);
            rdn.add(driver.getName());
        }
        logger.log(Level.INFO, String.format("🚀 Driver Queue ready with %d drivers!",
                dq.size()));
        this.restaurant = restaurant;
    }

    public void start() {
        Thread dt = new Thread(() -> {
            // Driver queue listens for new orders in corq
            CompletedOrderQueue corq = restaurant.getCorq();
            while (true) {
                synchronized(corq) {
                    try {
                        while (corq.isEmpty()) {
                            corq.wait();
                        }
                    } catch (InterruptedException e) {}
                }
                
                // Poll earliest completed order when queue is ready
                Order completedOrder = corq.poll();

                // Find next driver
                synchronized(dq) {
                    try {
                        while (dq.isEmpty()) {
                            dq.wait();
                        }
                    } catch (InterruptedException e) {

                    }
                }
                Driver driver = dq.poll();
                
                // Complete delivery 
                try {
                    Thread.sleep(completedOrder.getDeliveryTime()*1000);
                } catch (Exception e) {}
                // Add driver back in to the queue after delivery
                dq.addLast(driver);
                
                history.add(
                    new DeliverySummary(
                        driver.getID(),
                        completedOrder.getID(),
                        getDeliveredTime()
                    )
                );
                
            }
        });
        dt.setName("Drivers Thread");
        dt.start();
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

    public String getDeliveredTime() {
        LocalDateTime now = LocalDateTime.now();
        
        // Define a custom pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the time to a string
        return now.format(formatter);
    }
}