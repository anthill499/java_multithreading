import java.util.ArrayDeque;
import java.util.Deque;

class OrderQueue {
    private final int capacity;
    private final Deque<Order> oq = new ArrayDeque<>(); // Sequential order flow
    
    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public Deque<Order> getOrderQueue() {
        return oq;
    }

    public int size() {
        return oq.size();
    }

    public boolean isEmpty() {
        return oq.isEmpty();
    }

    public boolean isAtCapacity() {
        return oq.size() >= getCapacity();
    }

    public Order poll() {
        return oq.poll();
    }

    public void addOrder(Order order) {
        // At capacity, wait until order clears up
        while (isAtCapacity()) {
            // We can defer this operation until next Thread comes up
            
        }
        oq.add(order);
    }

    public Order popLeftOrder() {
        return oq.removeFirst();
    }

    public void dispatch(Order order) {
        oq.add(order);
    }
}