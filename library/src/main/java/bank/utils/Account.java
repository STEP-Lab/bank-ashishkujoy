package bank.utils;
public class Account {
    private final String accountNumber;
    private final String name;
    private final double minimumBalance=500;
    private double balance;

    private static boolean isInvalidAccountNumber(String accountNumber) {
        return !accountNumber.matches("^\\d{4}-\\d{4}$");
    }

    public Account(String accountNumber, String name, double balance) throws MinimumBalanceException, InvalidAccountNumber {
        if(isInvalidAccountNumber(accountNumber)){
            throw new InvalidAccountNumber();
        }
        this.accountNumber = accountNumber;
        this.name = name;
        if(balance<this.minimumBalance){
            throw new MinimumBalanceException("Insufficient balance to open account.");
        }
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
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
