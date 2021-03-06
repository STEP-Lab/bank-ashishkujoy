package bank.utils;

import java.io.FileWriter;
import java.io.IOException;

public class CsvPrinter implements Iterable{
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private final FileWriter writer;
    private final String headers;

    public CsvPrinter(FileWriter writer,String headers) {
        this.writer = writer;
        this.headers = headers;
    }

    public void writeHeaders() throws IOException {
        writer.append(headers);
        writer.append(NEW_LINE_SEPARATOR);
    }

    @Override
    public void iterateOverTransactions(Transaction transaction) throws IOException {
        String transactionType = "CREDIT";
        if(transaction.isDebitTransaction()){
            transactionType = "DEBIT";
        }
        writer.append(transaction.getDate().toString());
        writer.append(COMMA_DELIMITER);
        writer.append(String.valueOf(transaction.getAmount()));
        writer.append(COMMA_DELIMITER);
        writer.append(transaction.getSource());
        writer.append(COMMA_DELIMITER);
        writer.append(transactionType);
        writer.append(NEW_LINE_SEPARATOR);
    }

    public void close() throws IOException {
        try{
            writer.flush();
            writer.close();
        }catch (Exception e){
            return;
        }
    }
}
