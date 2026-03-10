import resource.Resource;
/**
 * "When 2 polite people try their best to walk past each other politely but keep swaying"
 */
class Livelocker {
    private Resource resourceA;
    private Resource resourceB;

    public Livelocker() {
        resourceA = new Resource(100);
        resourceB = new Resource(100);
    }

    public Resource getResourceA() {
        return resourceA;
    }

    public Resource getResourceB() {
        return resourceB;
    }
}