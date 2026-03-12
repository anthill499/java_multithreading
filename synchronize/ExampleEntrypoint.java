public class ExampleEntrypoint {
    public static void main(String[] args) {
        setUpSynchronizedMethods();
    }

    public static void setUpSynchronizedMethods() {
        DatabaseExample db = new DatabaseExample();
        String[] names = {"Anthony", "Antoinette", "Andy", "Andrew", "Antonius", "Anthropic", "Annie"};

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < names.length; i++) {
                db.addRecord(names[i]);
                try {
                    Thread.sleep(5000);
                    System.out.println("...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < names.length; i++) {
                db.addRecord(names[i]);
                try {
                    Thread.sleep(5000);
                    System.out.println("...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.setName("Thread 1");
        t2.setName("Thread 2");
        t1.start();
        t2.start();
    }
}