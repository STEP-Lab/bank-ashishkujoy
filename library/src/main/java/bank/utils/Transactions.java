package bank.utils;

import java.io.PrintWriter;
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

    public void credit(double amount, String source) {
        Transaction credit = new Credit(amount,source);
        this.transactions.add(credit);
    }

    public void print(PrintWriter printWriter) {
        for(Transaction transaction : transactions){
            printWriter.write(transaction.toString());
        }
    }

    public Transactions getTransactionsAbove(double amount) {
        Transactions requiredTransaction = new Transactions();
        for(Transaction transaction : transactions){
            if(transaction.getAmount()>amount){
                requiredTransaction.transactions.add(transaction);
            }
        }
        return requiredTransaction;
    }

    public Transactions getTransactionsBelow(double amount) {
        Transactions requiredTransaction = new Transactions();
        for(Transaction transaction : transactions){
            if(transaction.getAmount()<amount){
                requiredTransaction.transactions.add(transaction);
            }
        }
        return requiredTransaction;
    }

    public Transactions getCreditTransactions() {
        Transactions requiredTransaction = new Transactions();
        for(Transaction transaction : transactions){

            if(transaction.isCreditTransaction()){
                requiredTransaction.transactions.add(transaction);
            }
        }
        return requiredTransaction;
    }
}
