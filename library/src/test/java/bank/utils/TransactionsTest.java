package bank.utils;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
    @Test
    public void mustRecordADebitTransaction() {
        Transactions transactions = new Transactions();
        transactions.debit(1000,"ashish");
        assertThat(transactions.transactions,hasItem(new Debit(new Date(),1000,"ashish")));
    }

    @Test
    public void mustRecordACreditTransaction() {
        Transactions transactions = new Transactions();
        transactions.credit(1000,"ashish");
        assertThat(transactions.transactions,hasItem(new Credit(new Date(),1000,"ashish")));
    }
}
