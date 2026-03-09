class BankAccount {
    private int balance;
    
    public BankAccount() {
        this.balance = 100;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int newAmount) {
        this.balance = newAmount;
    }

    public void withdraw(Person person, int amount) {
        int currentBalance = this.getBalance();
        if (amount > this.balance) {
            System.out.println(String.format("%s tried to withdraw an amount larger than the current balance. Attempted amount %d, Current balance: %d", person.getName(), amount, currentBalance));
            return;
        }
        int newBalance = currentBalance-amount;
        System.out.println(String.format("%s successfully withdrew %d dollars. Previous balance %d, New balance: %d", person.getName(), amount, currentBalance, newBalance));
        this.setBalance(newBalance);
    }
}