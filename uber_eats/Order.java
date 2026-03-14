class Order {
    private final int orderId;
    private final int cookTime;
    private final int deliveryTime;

    public Order(int id, int cookTime, int deliveryTime) {
        orderId = id;
        this.cookTime = cookTime;
        this.deliveryTime = deliveryTime;
    }

    public int getID() {
        return orderId;
    }

    public int getCookTime() {
        return cookTime;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

  
}
