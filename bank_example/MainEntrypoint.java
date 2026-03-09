public class MainEntrypoint {
    public static void main(String[] args) {
        // NonsynchronizedBank bank = new NonsynchronizedBank();
        // bank.demo();

        SynchronizedBank syncBank = new SynchronizedBank();
        syncBank.demo();
    }
}