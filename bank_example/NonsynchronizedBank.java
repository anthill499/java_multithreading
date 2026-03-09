class NonsynchronizedBank {
    private BankAccount account;
    public NonsynchronizedBank() {
        setAccount(new BankAccount());
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount newAccount) {
        account = newAccount;
    }

    public void demo() {
        Person personA = new Person("Anthony");
        Person personB = new Person("Eric");
        
        // Create runnables
        Runnable runnableA = () -> {
            for (int i = 0; i < 5; i++) {
                getAccount().withdraw(personA, 50);
            }
        };
        Runnable runnableB = () -> {
            for (int i = 0; i < 5; i++) {
                getAccount().withdraw(personB, 50);
            }
        };

        // Create and name threads
        Thread threadA = new Thread(runnableA);
        threadA.setName("Thread-Person-A");
        Thread threadB = new Thread(runnableB);
        threadB.setName("Thread-Person-B");
        threadA.start();
        threadB.start();
    }
}