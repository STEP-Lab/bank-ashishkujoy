package bank.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validator {
    public static boolean isValidAccountNumber(String accountNumber) {
        Pattern regex = Pattern.compile("^(\\d+)(-)(\\d+)$");
        Matcher matcher = regex.matcher(accountNumber);
        return matcher.find() && accountNumber.length()==9;
    }
}

