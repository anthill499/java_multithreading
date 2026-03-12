import java.util.HashSet;
public class Concert {
    private static Concert instance;
    private HashSet<String> availableSeats;

    // Singleton initialization
    public static Concert getInstance() {
        if (instance == null) {
            instance = new Concert();
        }
        return instance;
    }

    // Constructor
    private Concert() {
        availableSeats = new HashSet<>();
        availableSeats.add("1A");
        availableSeats.add("1B");
    }

    public boolean bookSeat(String seat) {
        return availableSeats.remove(seat);
    }
}