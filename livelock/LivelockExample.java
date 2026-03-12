package livelock;
import resource.Resource;

/**
 * LivelockExample — entry point that sets up and demonstrates a livelock.
 *
 * WHAT IS A LIVELOCK?
 * A livelock occurs when two or more threads are NOT blocked (unlike a deadlock),
 * but they continuously react to each other in a way that prevents any of them
 * from making forward progress. Both threads are "alive" and doing work, but
 * the overall system is stuck.
 *
 * THE SCENARIO:
 * Two workers (worker1 and worker2) each need BOTH resourceA and resourceB
 * to complete their task. They follow a "polite" strategy:
 *   - If the resource I want is taken, I'll release what I'm holding and retry.
 * Because both workers apply this strategy at the same time and in sync,
 * they end up in a cycle of acquiring, yielding, and re-acquiring forever.
 *
 * ANALOGY (from Livelocker.java):
 * Two people in a narrow hallway both step to the same side to let the other
 * pass — then both step the other way — then back again. Both are moving
 * (active), but neither gets through (no progress).
 *
 * LIVELOCK vs DEADLOCK:
 *   Deadlock  — threads are BLOCKED, waiting on each other. CPU usage is low.
 *   Livelock  — threads are RUNNING, reacting to each other. CPU usage is high.
 *               The system looks busy but nothing useful is accomplished.
 */
public class LivelockExample {
    public static void main(String[] args) {
        Resource resourceA = new Resource(10);
        Resource resourceB = new Resource(20);
        Worker worker1 = new Worker("Worker-1");
        Worker worker2 = new Worker("Worker-2");
        Thread t1 = new Thread(() -> worker1.work(resourceA, resourceB, worker2));
        Thread t2 = new Thread(() -> worker2.work(resourceA, resourceB, worker1));
        t1.start();
        t2.start();
    }
}