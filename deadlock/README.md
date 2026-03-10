# Explaining the deadlock
```java
/*
* The Deadlocker instance has two shared resources (resourceA and resourceB)
* These objects are shared among all threads that have a reference to Deadlocker instance
*/
class Deadlocker {
    // Simple Integer wrappers;
    // Each have an intrinsic lock
    private Resource resourceA = new Resource(10); // contains lock
    private Resource resourceB = new Resource(5); // contains lock

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
```

## ```Read()``` method

```java
public int read() {
    synchronized(resourceA) {
        // printing does not release the lock
        System.out.println(Thread.currentThread().getName() + " locked resourceA");
        // Does not release the lock even while asleep
        try { Thread.sleep(100); } catch(Exception e) {}
        synchronized(resourceB) {
            System.out.println(Thread.currentThread().getName() + " locked resourceB");
            return resourceA.value + resourceB.value;
        }
    }
}
```

1. ```synchronized(resourceA)```
    - currently executing thread (```threadA```) attempts to acquire ```resourceA```'s lock
    - if available, ```threadA``` acquires ```resourceA```
2. ```synchronized(resourceB)```
    - ```ThreadA``` now attempts to acquire the lock on ```resourceB```
    - ```ThreadA``` if available, acquires lock on ```resourceB```
3. ```return```
    - Exits ```synchronized``` blocks, releasing ```resourceB``` and ```resourceA``` in that order

## ```write()``` method
```java
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
```
1. ```synchronized(resourceB)```
    - Thread-B attempts to acquire the lock on resourceB.
        - If Thread-A currently holds resourceB → Thread-B blocks.
        - If lock is free → Thread-B acquires it and proceeds.
2. ```Thread.sleep(100)``` Simulates delay while holding resourceB lock.
    - Thread-B is still holding the resourceB lock during sleep.
3. ```synchronized(resourceA)```
    - Thread-B now attempts to acquire the lock on resourceA.
        - If Thread-A holds resourceA → Thread-B blocks, waiting for resourceA.
4. Resource update
    - After acquiring both locks, Thread-B updates the resource values.
5. Lock release
    - Locks are released automatically upon exiting the synchronized blocks in reverse order: 
        1. resourceA 
        2. then resourceB.


## Timeline
__Simultanously__, 
Thread-A → calls ```read()```
Thread-B → calls ```write()```

1. ```ThreadA``` attempts ```synchronized(resourceA)``` in ```read()``` 
    - ```ThreadA``` acquires lock for ```resourceA```
    - then sleeps while holding ```resourceA```
2. ```ThreadB``` attempts ```synchronized(resourceB)``` in ```write()```
    - ```ThreadB``` acquires lock for ```resourceB```
    - then sleeps while holding ```resourceB```
3. ```ThreadA``` wakes and attempts ```synchronized(resourceB)``` in ```read()``` 
    - gets blocked waiting for resourceB
4. ```ThreadB``` wakes and attempts ```synchronized(resourceA)``` in ```write()``` 
    - gets blocked waiting for resourceA
5. DEADLOCK occurs