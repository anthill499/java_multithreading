package livelock;
import java.util.logging.Level;
import java.util.logging.Logger;
import resource.Resource;
/*
    check condition
    if conflict → back off
    retry
 */
class Worker {
    private String name;
    private boolean hasResourceA;
    private boolean hasResourceB;
    private boolean executionFinished;
    private static final Logger LOGGER = Logger.getLogger(Worker.class.getName());
    public Worker(String name) {
        this.name = name;
        executionFinished = false;
        hasResourceA = false;
        hasResourceB = false;
    }


    /*
     * Without continue, the code would immediately move on to the synchronized(resourceA) block even 
     * though the other worker is holding it, which breaks the logic of the livelock simulation
     * MAIN LIVELOCK CONCEPT: keep checking conditions, backing off, and retrying
     */
    public void work(Resource resourceA, Resource resourceB, Worker other) {
        while (!isFinished()) {
            // If other worker holds resource A, back off
            if (other.hasResourceA) {
                LOGGER.log(Level.INFO, String.format("%s holds resource A, yielding...", other.name));
                releaseResources();
                Thread.yield();
                // continue ensures the thread doesn’t try to acquire resources in the same iteration 
                // after backing off—it restarts the check from the top of the loop.
                continue; 
            }

            // If other worker holds resource B, back off
            if (other.hasResourceB) {
                LOGGER.log(Level.INFO, String.format("%s holds resource A, yielding...", other.name));
                releaseResources();
                Thread.yield();
                // continue ensures the thread doesn’t try to acquire resources in the same iteration 
                // after backing off—it restarts the check from the top of the loop.
                continue;
            }

            // Try to acquire resource A
            synchronized(resourceA) {
                LOGGER.log(Level.INFO, name + " acquired ResourceA");
                hasResourceA = true;
                resourceA.setValue(resourceA.getValue()-10);
            }

            // Try to acquire resource B
            synchronized(resourceB) {
                LOGGER.log(Level.INFO,name + " acquired ResourceB");
                hasResourceB = true;
                resourceA.setValue(resourceB.getValue()-10);
            }

            // Both resources acquired — Complete job
            LOGGER.log(Level.INFO, name + " completed work");
            setFinished(resourceA.getValue() <= 0 && resourceB.getValue() <= 0);
        }
    }

    public boolean isFinished() {
        return executionFinished;
    }

    public void setFinished(boolean finished) {
        executionFinished = finished;
    }

    private void releaseResources() {
        if (hasResourceA || hasResourceB) {
            LOGGER.log(Level.INFO, name + " releasing resources");
        }
        hasResourceA = false;
        hasResourceB = false;
    }
}