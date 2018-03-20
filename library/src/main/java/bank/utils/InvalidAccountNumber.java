package bank.utils;

public class InvalidAccountNumber extends Throwable {
    public InvalidAccountNumber() {
        super("Invalid Account Number,expected format 1234-5678");
    }

}
