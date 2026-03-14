import java.util.Collections;
import java.util.List;

class DriverData {
    // READ-ONLY LIST; modifying it throws UnsupportedOperationException
    private static final List<String> names = Collections.unmodifiableList(List.of(
            "James",
            "Michael",
            "Robert",
            "John",
            "David",
            "William",
            "Richard",
            "Joseph",
            "Thomas",
            "Charles",
            "Christopher",
            "Daniel",
            "Matthew",
            "Anthony",
            "Mark",
            "Steven"));

    private DriverData() {}
    
    public static List<String> getDriverNames() {
        return names;
    }

    

}
