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
                getAccount().withdrawSynchronized(personA, 50);
            }
        };

        Runnable runnableB = () -> {
            for (int i = 0; i < 5; i++) {
                getAccount().withdrawSynchronized(personB, 50);
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