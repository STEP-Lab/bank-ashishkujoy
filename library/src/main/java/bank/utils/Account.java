package bank.utils;
public class Account {
    private final AccountNumber accountNumber;
    private final String name;
    private final double minimumBalance=500;
    private double balance;

    public Account(AccountNumber accountNumber, String name, double balance) throws MinimumBalanceException {
        this.accountNumber = accountNumber;
        this.name = name;
        if(balance<this.minimumBalance){
            throw new MinimumBalanceException("Insufficient balance to open account.");
        }
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double debit(double amount) throws MinimumBalanceException {
        if(this.balance-amount<this.minimumBalance){
            throw new MinimumBalanceException("Insufficient balance to withdraw");
        }
        this.balance -= amount;
        return this.balance;
    }

    public double credit(double amount) {
        this.balance += amount;
        return this.balance;
    }

}
