package main;

import model.*;

import java.io.IOException;

public class MainApplication {
    public static void main(final String[] args) throws Exception {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParserCSV = new BankStatementCSVParser();
        final BankStatementJSONParser bankStatementParserJSON = new BankStatementJSONParser();

        final Exporter exporter = new HtmlExporter();
        bankStatementAnalyzer.analyzeCSV("bank-data-simple.csv", bankStatementParserCSV, exporter);
        bankStatementAnalyzer.analyzeJSON("bank-data-simple.json", bankStatementParserJSON, exporter);
    }
}
