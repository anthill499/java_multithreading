import java.util.ArrayDeque;
import java.util.Deque;

public final class DriverQueue {
    private static DriverQueue instance = null;
    private static Deque<String> queue;
    private DriverQueue() {}
    
    public static void initialize() {
        if (instance == null) {
            instance = new DriverQueue();
            queue = new ArrayDeque<>();
        }
    }

    public static boolean isInitialized() {
        return instance != null;
    }
    
    // getters and setters
    public static DriverQueue getInstance() {
        return instance;
    }

    public static Deque<String> getQueue() {
        return queue;
    }

}