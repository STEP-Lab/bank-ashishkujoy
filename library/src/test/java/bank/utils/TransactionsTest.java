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
        transactions.debit(1000,"ashish");
        assertThat(transactions.transactions,hasItem(new Debit(new Date(),1000,"ashish")));
    }

    @Test
    public void mustRecordACreditTransaction() {
        transactions.credit(1000,"ashish");
        assertThat(transactions.transactions,hasItem(new Credit(new Date(),1000,"ashish")));
    }

    @Test
    public void mustRecordCreditAndDebitTransaction() {
        transactions.credit(1000,"Someone");
        transactions.debit(1000,"Someone");
        Credit credit = new Credit(new Date(),1000,"Someone");
        Debit debit = new Debit(new Date(),1000,"Someone");
        assertThat(transactions.transactions,hasItems(credit,debit));
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
        Credit credit = new Credit(1000,"Someone");
        Debit debit = new Debit(1000,"Someone");
        transactions.print(printWriter);
        assertThat(result,hasItems(credit.toString(),debit.toString()));
    }

    @Test
    public void shouldGiveTransactionAboveGivenAmount() {
        double[] creditAmount = {1100.0};
        double[] debitAmount = {1200,500};
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        Credit credit = new Credit(1100,"Someone");
        Debit debit = new Debit(1200,"Someone");
        Debit debit2 = new Debit(500,"Someone");
        assertThat(transactions.getTransactionsAbove(1000).transactions,hasItems(credit,debit));
        assertThat(transactions.getTransactionsAbove(1000).transactions,not(hasItems(debit2)));
    }

    @Test
    public void shouldGiveTransactionBelowGivenAmount() {
        double[] creditAmount = {1100.0};
        double[] debitAmount = {1200,500};
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        Credit credit = new Credit(1100,"Someone");
        Debit debit = new Debit(1200,"Someone");
        Debit debit2 = new Debit(500,"Someone");
        assertThat(transactions.getTransactionsBelow(1200).transactions,hasItems(credit,debit2));
        assertThat(transactions.getTransactionsAbove(1200).transactions,not(hasItems(debit)));
    }

    @Test
    public void shouldGiveAllCreditTransaction() {
        double[] creditAmount = {1100,1200};
        double[] debitAmount = {500};
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        Credit credit = new Credit(1100,"Someone");
        Credit credit1 = new Credit(1200,"Someone");
        Debit debit = new Debit(500,"Someone");
        assertThat(transactions.getCreditTransactions().transactions,hasItems(credit,credit1));
        assertThat(transactions.getCreditTransactions().transactions,not(hasItems(debit)));
    }

    @Test
    public void should_write_to_csv_file() throws IOException {
        double[] creditAmount = {1100,1200};
        double[] debitAmount = {500};
        ArrayList<String> result = new ArrayList<>();
        String headers = "date,amount,source";
        transactions = initTransactions(transactions, creditAmount,debitAmount);
        Credit credit = new Credit(1100,"Someone");
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
        assertThat(result,hasItems(headers,credit.getSource(),String.valueOf(credit.getAmount())));
    }
}
