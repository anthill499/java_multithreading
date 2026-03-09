class SynchronizedBank {
    private BankAccount account;

    public SynchronizedBank() {
        setAccount(new BankAccount());
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount newAccount) {
        account = newAccount;
    }

    public void demo() {
        // Create person objects
        Person personA = new Person("Anthony");
        Person personB = new Person("Eric");
        
        // Create runnables
        Runnable runnableA = () -> {
            for (int i = 0; i < 5; i++) {
                // System.out.println(Thread.currentThread().getName());
                getAccount().withdrawSynchronized(personA, 25);
            }
        };

        Runnable runnableB = () -> {
            for (int i = 0; i < 5; i++) {
                // System.out.println(Thread.currentThread().getName());
                getAccount().withdrawSynchronized(personB, 25);
            }
        };

        Thread threadA = new Thread(runnableA);
        threadA.setName("Thread-Person-A");
        Thread threadB = new Thread(runnableB);
        threadB.setName("Thread-Person-B");
        threadA.start();
        threadB.start();
    }

}