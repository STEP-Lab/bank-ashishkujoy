package bank.utils;

import java.util.Date;

public class Debit extends Transaction {

    public Debit(Date date, double amount, String to) {
        super(date, amount, to);
        super.isDebit = true;
    }

    protected Debit(double amount,String to) {
        this(new Date(), amount, to);
    }
}
