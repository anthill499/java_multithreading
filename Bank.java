public class Bank {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Person personA = new Person("Anthony");
        Person personB = new Person("Eric");
        
        // Create runnables
        Runnable runnableA = () -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(personA, 25);
            }
        };
        Runnable runnableB = () -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(personB, 25);
            }

        };
        Thread threadA = new Thread(runnableA);
        Thread threadB = new Thread(runnableB);
        threadA.start();
        threadB.start();
    }
}