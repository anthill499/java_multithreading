import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Thread A. 
 * continuously poll order queue for any orders and sending them to driver queue 
 */
class Restaurant {
    private static final Logger logger = Logger.getLogger(Restaurant.class.getName());
    private final OrderQueue orderQueue;
    private final CompletedOrderQueue corq;
    
    public Restaurant() {
        orderQueue = new OrderQueue(16);
        corq = new CompletedOrderQueue();
    }

    public void start() {
        Thread rt = new Thread(() -> {
            while (true) {
                synchronized(orderQueue) {
                    try {
                        // Empty order queue or at capacity
                        while (orderQueue.isEmpty()) {
                            orderQueue.wait();
                        }
                    } catch (Exception e) {}
                }
                Order order = orderQueue.poll();
                // Cook the food
                try {
                    cook(order.getCookTime());
                } catch (Exception e) {}
                
                // add order to completed order queue
                dispatchToCorq(order);
            }
        });
        rt.start();
        logger.log(Level.INFO,
                String.format("🚀 Restaurant is ready to take orders + Restaurant thread!", orderQueue.size()));
    }
    
    public OrderQueue getOrderQueue() {
        return orderQueue;
    }

    public CompletedOrderQueue getCorq() {
        return corq;
    }

    private void dispatchToCorq(Order order) {
        synchronized(corq) {
            corq.addOrder(order);
        }
    }

    private void cook(int cookingTime) {
        try {
            Thread.sleep(cookingTime*1000);
        } catch (Exception e) {

        }
    }
}
