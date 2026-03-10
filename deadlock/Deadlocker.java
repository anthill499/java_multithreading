class Deadlocker {
    private Resource resourceA = new Resource(10);
    private Resource resourceB = new Resource(5);
    public int read() {
        synchronized(resourceA) {
            System.out.println(Thread.currentThread().getName() + " locked resourceA");
            try { Thread.sleep(100); } catch(Exception e) {}
            synchronized(resourceB) {
                System.out.println(Thread.currentThread().getName() + " locked resourceB");
                return resourceA.value + resourceB.value;
            }
        }
    }

    public void write(int a, int b) {
        synchronized(resourceB) {
            System.out.println(Thread.currentThread().getName() + " locked resourceB");
            try { Thread.sleep(100); } catch(Exception e) {}
            synchronized(resourceA) {
                System.out.println(Thread.currentThread().getName() + " locked resourceA");
                resourceA.value = a;
                resourceB.value = b;
            }
        }
    }
}
