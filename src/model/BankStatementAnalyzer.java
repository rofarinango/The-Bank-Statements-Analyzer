package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "resources/";

    public void analyzeCSV(final String fileName, final BankStatementParser bankStatementParser, final Exporter exporter) throws IOException{
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = (List<BankTransaction>) bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();

        System.out.println(exporter.export(summaryStatistics));
    }

    public void analyzeJSON(String filename, BankStatementJSONParser bankStatementParser, Exporter exporter) throws Exception {
        JSONArray jsonArray= (JSONArray) bankStatementParser.parseFrom(RESOURCES+filename);
        final List<BankTransaction> bankTransactions = (List<BankTransaction>) bankStatementParser.parseLinesFrom(jsonArray);

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();

        System.out.println(exporter.export(summaryStatistics));
    }
}
