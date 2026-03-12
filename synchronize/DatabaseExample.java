import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
class DatabaseExample {
    private Logger logger = Logger.getLogger(DatabaseExample.class.getName());
    private Set<String> records = new HashSet<>();

    public Set<String> getRecords() {
        return records;
    }

    public String getRecordByKey(String key) {
        logInfo(String.format(" in %s with key %s", Thread.currentThread().getStackTrace()[1].getMethodName(), key));
        if (!getRecords().contains(key)) {
            return null;
        }
        return key;
    }
    
    public synchronized void addRecord(String key) {
        logInfo(String.format(" in %s with key %s", Thread.currentThread().getStackTrace()[1].getMethodName(), key));

        if (getRecords().contains(key)) {
            logInfo(String.format(" key %s already exists in db", key));
            return;
        }
        getRecords().add(key);
    }

    public synchronized void removeRecord(String key) {
        logInfo(String.format(" in %s with key %s", Thread.currentThread().getStackTrace()[1].getMethodName(), key));
        if (!getRecords().contains(key)) {
            logInfo(String.format(" key %s does not exist in db", key));
            return;
        }
        getRecords().remove(key);
    }

    public void logInfo(String info) {
        String threadName = Thread.currentThread().getName();
        logger.log(Level.INFO, String.format("%s executing: %s",threadName,  info));
    }
}