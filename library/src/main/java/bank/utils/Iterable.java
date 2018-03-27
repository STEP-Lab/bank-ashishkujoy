package bank.utils;

import java.io.IOException;

public interface Iterable {
    void iterateOverTransactions(Transaction transaction) throws IOException;
}
