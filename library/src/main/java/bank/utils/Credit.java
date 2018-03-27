package bank.utils;

import java.util.Date;

public class Credit extends Transaction {

    public Credit(Date date, double amount, String from) {
        super(date, amount, from);
        super.isCredit = true;
    }

    protected Credit(double amount,String from) {
        this(new Date(), amount, from);
    }

    public boolean isCredit() {
        return true;
    }

    public boolean isDebit() {
        return true;
    }
}
