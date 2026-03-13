import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

class Restaurant {
    private static final Logger logger = Logger.getLogger(Restaurant.class.getName());
    private final Deque<Order> orderQueue = new ArrayDeque<>(); // Sequential order flow
    private static final int CAPACITY = 16;
    private final Thread rt;

    public Restaurant() {
        rt = new Thread();
        rt.setName("Restaurant Thread");
        logger.log(Level.INFO,
                String.format("🚀 Restaurant is ready to take orders + Restaurant thread!", orderQueue.size()));
    }

    public Deque<Order> getOrderQueue() {
        return orderQueue;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public boolean isEmpty() {
        return orderQueue.isEmpty();
    }

    public boolean isAtCapacity() {
        return orderQueue.size() >= CAPACITY;
    }

    public void addOrder(Order order) {
        orderQueue.add(order);
    }

    public void dispatch(Order order) {
        orderQueue.add(order);
    }

    public void receiveOrders(int numberOfOrders) {
        // The main function that takes in number of order stream
        int ordersLeft = numberOfOrders;
        while (ordersLeft > 0 || !isEmpty()) {

        }
        logger.log(Level.INFO, String.format("🚀 Restaurant fulfilled %d orders!", numberOfOrders));
    }
}
