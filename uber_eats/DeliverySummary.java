
class DeliverySummary {
    private final String time;
    private final int driverId;
    private final int orderId;

    public DeliverySummary(int driverId, int orderId, String time) {
        this.time = time;
        this.driverId = driverId;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return String.format("Order %d fulfilled by %d at time %s", orderId, driverId, time);
    } 
}