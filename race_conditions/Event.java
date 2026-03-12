public class Event {
    public static void main(String[] args) {
        Event testThreads = new Event();
        testThreads.go();
    }

    public void go() {
        // create Thread 1, which will try to book seats 1A and 1B
        Thread getSeats1 = new Thread(() -> {
            ticketAgentBooks("1A");
            ticketAgentBooks("1B");
        });
        // create Thread 2, which will try to book seats 1A and 1B
        Thread getSeats2 = new Thread(() -> {
            ticketAgentBooks("1A");
            ticketAgentBooks("1B");
        });

        getSeats1.setName("THREAD GET SEATS 1");
        getSeats2.setName("THREAD GET SEATS 2");

        // start both threads
        getSeats1.start();
        getSeats2.start();
    }
    public void ticketAgentBooks(String seat) {
        // get the one instance of the Show Singleton
        Concert concert = Concert.getInstance();
        // book a seat and print
        System.out.println(Thread.currentThread().getName() + ": "
            + concert.bookSeat(seat));
    }
}