package bank.utils;
public class Account {
    private final AccountNumber accountNumber;
    private final String name;
    private static final double minimumBalance=500;
    private double balance;

    private Account(AccountNumber accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public static Account createAccount(String number,String name, double balance) throws InvalidAccountNumber, MinimumBalanceException {
        AccountNumber accountNumber = new AccountNumber(number);
        if(balance<minimumBalance){
            throw new MinimumBalanceException("Insufficient balance to open account.");
        }
        return new Account(accountNumber,name,balance);
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
