package bank.utils;

public class AccountNumber {
    public AccountNumber(String number) throws InvalidAccountNumber {
       validateAccountNumber(number);
    }

    private void validateAccountNumber(String number) throws InvalidAccountNumber {
        if(!number.matches("^\\d{4}-\\d{4}$")){
                throw new InvalidAccountNumber();
        }
    }
}
