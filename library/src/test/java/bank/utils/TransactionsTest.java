package bank.utils;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;
    private Debit debitOf1000;
    private Debit debitOf100;
    private Debit debitOf1500;
    private Debit debitOf1200;
    private Credit creditOf1500;
    private Credit creditOf1000;
    private Credit creditOf100;
    private Credit creditOf1200;

    @Before
    public void transactionsSetup() {
        debitOf1000 = new Debit(1000, "Someone");
        debitOf100 = new Debit(100, "Someone");
        debitOf1500 = new Debit(1500, "Someone");
        debitOf1200 = new Debit(1200, "Someone");
        creditOf1500 = new Credit(1500,"Someone");
        creditOf1000 = new Credit(1000,"Someone");
        creditOf100 = new Credit(100,"Someone");
        creditOf1200 = new Credit(1200,"Someone");
    }

    @Before
    public void setUp() {
        transactions = new Transactions();
    }

    private Transactions initTransactions(Transactions transactions,double[] creditAmounts,double[] debitAmounts) {
        for(double creditAmount : creditAmounts){
            transactions.credit(creditAmount,"Someone");
        }
        for(double debitAmount : debitAmounts){
            transactions.debit(debitAmount,"Someone");
        }
        return transactions;
    }

    @Test
    public void mustRecordADebitTransaction() {
        transactions.debit(1000,"Someone");
        assertThat(transactions.transactions,hasItem(debitOf1000));
    }

    @Test
    public void mustRecordACreditTransaction() {
        transactions.credit(1000,"Someone");
        assertThat(transactions.transactions,hasItem(creditOf1000));
    }

    @Test
    public void mustRecordCreditAndDebitTransaction() {
        transactions.credit(1000,"Someone");
        transactions.debit(1000,"Someone");
        assertThat(transactions.transactions,hasItems(creditOf1000,debitOf1000));
    }

    @Test
    public void mustWriteTheTransactionToGivenStream() throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> result = new ArrayList<>();
        PrintWriter printWriter = new PrintWriter("Somefile", "utf-8") {
            @Override
            public void write(String x) {
                result.add(x);
            }
        };
        transactions.credit(1000,"Someone");
        transactions.debit(1000,"Someone");
        transactions.print(printWriter);
        assertThat(result,hasItems(creditOf1000.toString(),debitOf1000.toString()));
    }

    @Test
    public void shouldGiveTransactionAboveGivenAmount() {
        double[] creditAmount = {1000,100};
        double[] debitAmount = {1500};
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        assertThat(transactions.getTransactionsAbove(100).transactions,hasItems(creditOf1000,debitOf1500));
        assertThat(transactions.getTransactionsAbove(100).transactions,not(hasItems(debitOf100)));
    }

    @Test
    public void shouldGiveTransactionBelowGivenAmount() {
        double[] creditAmount = {1000,100};
        double[] debitAmount = {1200,100};
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        assertThat(transactions.getTransactionsBelow(1200).transactions,hasItems(creditOf1000,creditOf100,debitOf100));
        assertThat(transactions.getTransactionsBelow(1200).transactions,not(hasItems(debitOf1200)));
    }

    @Test
    public void shouldGiveAllCreditTransaction() {
        double[] creditAmount = {1000,1500};
        double[] debitAmount = {100};
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        assertThat(transactions.getCreditTransactions().transactions,hasItems(creditOf1000,creditOf1500));
        assertThat(transactions.getCreditTransactions().transactions,not(hasItems(debitOf100)));
    }

    @Test
    public void shouldGiveAllDebitTransactions() {
        double[] creditAmount = {1000,1500};
        double[] debitAmount = {100};
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        assertThat(transactions.getDebitTransactions().transactions,hasItems(debitOf100));
        assertThat(transactions.getDebitTransactions().transactions,not(hasItems(creditOf1000,creditOf1500)));
    }

    @Test
    public void should_write_to_csv_file() throws IOException {
        double[] creditAmount = {1000};
        double[] debitAmount = {};
        ArrayList<String> result = new ArrayList<>();
        String headers = "date,amount,source,type";
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        CsvPrinter csvPrinter;
        try (FileWriter fileWriter = new FileWriter("foo.csv") {
            @Override
            public Writer append(CharSequence csq) {
                result.add((String) csq);
                return this;
            }
        }) {
            csvPrinter = new CsvPrinter(fileWriter, headers);
        }
        csvPrinter.writeHeaders();
        transactions.iterateOnTransactions(csvPrinter);
        assertThat(result,hasItems(headers,creditOf1000.getSource(),String.valueOf(creditOf1000.getAmount()),"CREDIT"));
        assertThat(result,not(hasItems("DEBIT")));
        transactions.debit(100,"AnotherAccount");
        transactions.iterateOnTransactions(csvPrinter);
        assertThat(result,hasItems("DEBIT"));
        csvPrinter.close();
    }

}
