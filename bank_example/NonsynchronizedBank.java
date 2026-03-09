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
                getAccount().withdraw(personA, 25);
            }
        };
        Runnable runnableB = () -> {
            for (int i = 0; i < 5; i++) {
                getAccount().withdraw(personB, 25);
            }
        };

        Thread threadA = new Thread(runnableA);
        Thread threadB = new Thread(runnableB);
        threadA.start();
        threadB.start();
    }
}