import java.util.logging.Level;
import java.util.logging.Logger;
class VolatileCounter {
    private volatile int count = 100;
    private Logger logger = Logger.getLogger(VolatileCounter.class.getName());
    public synchronized void increment() {
        logger.log(Level.INFO, String.format("%s increment: Count before increment %d", Thread.currentThread().getName(), count));
        count++;
        logger.log(Level.INFO, String.format("%s increment: Count after increment %d", Thread.currentThread().getName(), count));
    };

    public synchronized void decrement() {
         logger.log(Level.INFO, String.format("%s decrement: Count before decrement %d", Thread.currentThread().getName(), count));
        count--;
        logger.log(Level.INFO, String.format("%s decrement: Count after decrement %d", Thread.currentThread().getName(), count));
    };

}