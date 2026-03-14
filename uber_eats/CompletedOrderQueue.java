import java.util.ArrayDeque;
import java.util.Deque;

class CompletedOrderQueue {
    private final Deque<Order> oq = new ArrayDeque<>(); // Sequential order flow
    
    public Deque<Order> getOrderQueue() {
        return oq;
    }

    public int size() {
        return oq.size();
    }

    public boolean isEmpty() {
        return oq.isEmpty();
    }

    public void addOrder(Order order) {
        // At capacity, wait until order clears up
        oq.add(order);
    }

    public Order poll() {
        return oq.poll();
    }
}