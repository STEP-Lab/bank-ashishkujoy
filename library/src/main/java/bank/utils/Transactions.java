package bank.utils;

import java.util.ArrayList;

public class Transactions {
    protected final ArrayList<Transaction> transactions;

    public Transactions() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void debit(double amount, String source){
        Transaction debit = new Debit(amount,source);
        this.transactions.add(debit);
    }
}
