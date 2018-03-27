package bank.utils;

import java.io.IOException;
import java.util.function.Consumer;

public interface Iterable {
    void iterateOverTransactions(Transaction transaction) throws IOException;
}
