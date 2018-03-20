import bank.utils.Account;
import bank.utils.InvalidAccountNumber;
import bank.utils.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;
    @Before
    public void setUp() throws MinimumBalanceException, InvalidAccountNumber {
        account = new Account("1234-5678", "ashish", 1000);
    }

    @Test
    public void getAccountNumber() {
        assertThat(account.getAccountNumber(),is("1234-5678"));
    }

    @Test
    public void getName() {
        assertThat(account.getName(),is("ashish"));
    }

    @Test
    public void getBalance() {
        assertEquals(1000,account.getBalance(), 0);
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumber {
        new Account("1234-5678", "ashish",100);
    }

    @Test (expected = InvalidAccountNumber.class)
    public void validateAccountNumber() throws MinimumBalanceException, InvalidAccountNumber {
        new Account("1234-23","ashish",2000);
    }

    @Test
    public void debit() throws MinimumBalanceException {
        double remainingBalance = account.debit(100);
        assertEquals(900,remainingBalance,0);
    }

    @Test (expected = MinimumBalanceException.class)
    public void validMininumBalanceForDebit() throws MinimumBalanceException {
        try{
            account.debit(2000);
        }catch(MinimumBalanceException e){
            assertEquals(account.getBalance(),1000,0);
        }
        account.debit(2000);
    }

    @Test
    public void credit() {
    double newBalance = account.credit(100);
        assertEquals(1100,newBalance,0);
    }
}
