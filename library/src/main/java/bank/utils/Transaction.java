package bank.utils;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    protected final Date date;
    protected final double amount;
    protected final String source;
    protected boolean isCredit = false;
    protected boolean isDebit = false;
    public Transaction(Date date, double amount, String source) {
        this.source = source;
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, source);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                '}';
    }

    public double getAmount() {
        return amount;
    }


    public boolean isCreditTransaction() {
        return isCredit;
    }

    public String getSource() {
        return source;
    }

    public boolean isDebitTransaction() {
        return isDebit;
    }
}
