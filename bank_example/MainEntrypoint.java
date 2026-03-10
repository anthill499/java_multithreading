import java.util.logging.Level;
import java.util.logging.Logger;
public class MainEntrypoint {
    private static final Logger LOGGER = Logger.getLogger(MainEntrypoint.class.getName());
    public static void main(String[] args) {
        MainEntrypoint.startNonsynchronizedScenario();
    }

    public static void startNonsynchronizedScenario() {
        Thread.currentThread().setName("main-nonsynchronized");
        LOGGER.log(Level.INFO, Thread.currentThread().getName());
        NonsynchronizedBank bank = new NonsynchronizedBank();
        bank.demo();
    }
    public static void startSynchronizedScenario() {
        Thread.currentThread().setName("main-synchronized");
        LOGGER.log(Level.INFO, Thread.currentThread().getName());
        SynchronizedBank syncBank = new SynchronizedBank();
        syncBank.demo();
    }
}