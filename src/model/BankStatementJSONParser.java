package model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BankStatementJSONParser implements BankStatementParser{
    @Override
    public Object parseFrom(Object line) throws IOException, ParseException {
        FileReader reader = new FileReader((String) line);
        JSONParser parser = new JSONParser();
        return parser.parse(reader);
    }

    @Override
    public Object parseLinesFrom(Object lines) {
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<BankTransaction> bankTransactions = new ArrayList<>();
        JSONArray jsonArray = (JSONArray) lines;
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            JSONObject jsonObject = iterator.next();
            String dateStr = (String) jsonObject.get("Date");
            LocalDate date = LocalDate.parse(dateStr, DATE_PATTERN);
            Long amount = (Long) jsonObject.get("Amount");
            String description = (String) jsonObject.get("Description");
            BankTransaction bankTransaction = new BankTransaction(date,(double) amount, description);
            bankTransactions.add(bankTransaction);
        }
        return bankTransactions;
    }



}
