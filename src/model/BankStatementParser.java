package model;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BankStatementParser {
    Object parseFrom(Object line) throws IOException, ParseException;
    Object parseLinesFrom(Object lines);
}
