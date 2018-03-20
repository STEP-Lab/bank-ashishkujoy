package bank.utils;

public class MinimumBalanceException extends Throwable {
    public MinimumBalanceException() {
        super("Insufficient balance to open account.");
    }
}
