package resource;

public class Resource {
    private int value;
    private boolean inUse;
    public static void main(String[] args) {
        // not needed
    }
    public Resource(int value) {
        this.value = value;
        inUse = false;
    }

    public boolean isBusy() {
        return inUse;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }
}