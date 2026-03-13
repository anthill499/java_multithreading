public class VolatileExample {
    public static void main(String[] args) {
        VolatileCounter counter = new VolatileCounter();

        Runnable r = () -> {
            for (int i = 1; i <= 10; i++) {
                counter.increment();
            }
        };
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);

        threadA.setName("Thread A");
        threadB.setName("Thread B");
        threadA.start();
        threadB.start();
    }
}