package bank.utils;

import bank.utils.AccountNumber;
import bank.utils.InvalidAccountNumber;
import org.junit.Test;

public class AccountNumberTest {
    @Test (expected = InvalidAccountNumber.class)
    public void account_number_of_alphates_is_invalid() throws InvalidAccountNumber {
        AccountNumber accountNumber = new AccountNumber("abcd-efgh");
    }

    @Test (expected = InvalidAccountNumber.class)
    public void alphates_numeric_account_number_is_invalid() throws InvalidAccountNumber {
        AccountNumber accountNumber = new AccountNumber("a1b2-c3d4");
    }

    @Test (expected = InvalidAccountNumber.class)
    public void account_number_without_hyphen_is_invalid() throws InvalidAccountNumber {
        AccountNumber accountNumber = new AccountNumber("a1b2c3d4");
    }

    @Test (expected = InvalidAccountNumber.class)
    public void account_number_of_length_more_than_nine_is_invalid() throws InvalidAccountNumber {
        AccountNumber accountNumber = new AccountNumber("1234-12345");
    }

    @Test (expected = InvalidAccountNumber.class)
    public void account_number_of_length_less_than_nine_is_invalid() throws InvalidAccountNumber {
        AccountNumber accountNumber = new AccountNumber("1234-123");
    }
}
