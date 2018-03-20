package bank.utils;
public class Account {
    private final String accountNumber;
    private final String name;
    private final double minimumBalance=500;
    private double balance;

    public Account(String accountNumber, String name, double balance) throws MinimumBalanceException, InvalidAccountNumber {
        if(!Validator.isValidAccountNumber(accountNumber)){
            throw new InvalidAccountNumber();
        }
        this.accountNumber = accountNumber;
        this.name = name;
        if(balance<this.minimumBalance){
            throw new MinimumBalanceException();
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

    public double debit(double amount) {
        this.balance -= amount;
        return this.balance;
    }
}
