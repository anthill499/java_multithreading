class Order {
    private final int orderId;
    private final int timeToCompletion;
    private final int numItems;

    public Order(int id, int ttc, int itemCount) {
        orderId = id;
        timeToCompletion = ttc;
        numItems = itemCount;
    }

    public int getID() {
        return orderId;
    }

    public int getTTC() {
        return timeToCompletion;
    }

    public int getItemCount() {
        return numItems;
    }
}
