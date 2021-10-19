package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser{
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public Object parseFrom(final Object line){
        String strLine = (String) line;
        final String[] columns = strLine.split(",");
        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];
        return new BankTransaction(date, amount, description);
    }

    public Object parseLinesFrom(final Object lines){
        List<String> strLines = (List<String>) lines;
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (final String line: strLines){
            bankTransactions.add((BankTransaction) parseFrom(line));
        }
        return bankTransactions;
    }
}
