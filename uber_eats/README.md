# Uber Eats practice example (high level design with multithreading)

## Threads
1. Entrypoint thread - 


## Check Pt. 1

### Classes Implemented
| Class | Role |
|---|---|
| `Entrypoint` | Main thread / producer — reads user input, creates `Order` objects, enqueues them |
| `Restaurant` | Consumer/producer thread — dequeues raw orders, simulates cooking, dispatches to completed queue |
| `DriverQueue` | Consumer thread — picks completed orders, assigns a driver, simulates delivery, returns driver |
| `Order` | Data class — holds `orderId`, `cookTime`, `deliveryTime` |
| `Driver` | Data class — holds `name`, `driverId` |
| `DriverData` | Static utility — 16 hardcoded driver names |
| `OrderQueue` | Bounded queue (capacity 16) — holds orders awaiting cooking |
| `CompletedOrderQueue` | Unbounded queue — holds cooked orders awaiting delivery |
| `DeliveryHistory` | Accumulates `DeliverySummary` records |
| `DeliverySummary` | Data class — records `orderId`, `driverId`, timestamp |

### Threading & Synchronization
- 3 threads: Main (Entrypoint), Restaurant Thread, Drivers Thread
- `synchronized` + `wait()`/`notifyAll()` on OrderQueue (Entrypoint ↔ Restaurant)
- `synchronized` + `wait()` on CompletedOrderQueue (Restaurant ↔ DriverQueue)
- `synchronized` + `wait()` on driver Deque when no drivers available
- `Thread.sleep()` simulates cook time and delivery time

### Data Flow
```
Entrypoint → OrderQueue (bounded, 16) → Restaurant Thread → CompletedOrderQueue → DriverQueue Thread → DeliveryHistory
```

### Gaps / Disagreements
1. **`OrderQueue.addOrder()` capacity-block is incomplete** — `while (isAtCapacity())` body is empty; actual blocking is done externally in Entrypoint, making this method misleading.
2. **No `notifyAll()` when a driver is returned to `dq`** — driver returned via `addLast()` but nothing wakes a thread waiting on an empty driver queue.
3. **No shutdown mechanism** — Restaurant and DriverQueue threads loop infinitely with no interrupt, poison pill, or graceful stop.
4. **`DeliveryHistory.summarize()` is never called** — history accumulates but is never surfaced to the user at exit.
5. **`CompletedOrderQueue.addOrder()` has stale comment** — says "at capacity" but queue is unbounded; no capacity check exists.
6. **Redundant queue methods** — `popLeftOrder()` and `addOrder()` overlap with `poll()` and `dispatch()`.