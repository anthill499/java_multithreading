public class MainEntrypoint {
    public static void main(String[] args) {
        Thread.currentThread().setName("main-nonsynchronized");
        System.out.println(Thread.currentThread().getName());
        NonsynchronizedBank bank = new NonsynchronizedBank();
        bank.demo();
        
        // Thread.currentThread().setName("main-synchronized");
        // System.out.println(Thread.currentThread().getName());
        // SynchronizedBank syncBank = new SynchronizedBank();
        // syncBank.demo();
    }
}