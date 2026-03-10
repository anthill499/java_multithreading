package deadlock;

public class DeadlockExample {
    public static void main(String[] args) {
        Deadlocker deadlocker = new Deadlocker();

        Thread threadA = new Thread(() -> {
            while(true) {
                deadlocker.read();
            }
        }, "Thread-A");

        Thread threadB = new Thread(() -> {
            while(true) {
                deadlocker.write(1,2);
            }
        }, "Thread-B");

        threadA.start();
        threadB.start();
    }
}