# java_multithreading


## Results

### Bank Example
Nonsychronized
```bash
main-nonsynchronized
Thread-Person-B: Eric successfully withdrew 50 dollars. Previous balance 250, New balance: 200
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 250, New balance: 200
Thread-Person-B: Eric successfully withdrew 50 dollars. Previous balance 200, New balance: 150
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 200, New balance: 150
Thread-Person-B: Eric successfully withdrew 50 dollars. Previous balance 150, New balance: 100
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 150, New balance: 100
Thread-Person-B: Eric successfully withdrew 50 dollars. Previous balance 100, New balance: 50
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 100, New balance: 50
Thread-Person-B: Eric successfully withdrew 50 dollars. Previous balance 50, New balance: 0
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 50, New balance: 0
```
Synchronized
```bash
main-synchronized
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 250, New balance: 200
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 200, New balance: 150
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 150, New balance: 100
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 100, New balance: 50
Thread-Person-A: Anthony successfully withdrew 50 dollars. Previous balance 50, New balance: 0
Thread-Person-B: Eric tried to withdraw an amount larger than the current balance. Attempted amount 50, Current balance: 0
Thread-Person-B: Eric tried to withdraw an amount larger than the current balance. Attempted amount 50, Current balance: 0
Thread-Person-B: Eric tried to withdraw an amount larger than the current balance. Attempted amount 50, Current balance: 0
Thread-Person-B: Eric tried to withdraw an amount larger than the current balance. Attempted amount 50, Current balance: 0
Thread-Person-B: Eric tried to withdraw an amount larger than the current balance. Attempted amount 50, Current balance: 0
```