package test;

import model.BankStatementCSVParser;
import model.BankStatementParser;
import model.BankTransaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void testParseFromShouldParseOneCorrectLine() throws Exception{
        final String line = "30-01-2017,-50,Tesco";
        final BankTransaction result = (BankTransaction) statementParser.parseFrom(line);
        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50,"Tesco");
        final double tolerance = 0.0d;
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void testParseLinesFromShouldParseMultipleCorrectLine() throws Exception{
        final String lineOne = "30-01-2017,-100,Deliveroo";
        final String lineTwo = "30-01-2017,-50,Tesco";
        final String lineThree = "01-02-2017,6000,Salary";
        final String lineFour = "02-02-2017,2000,Royalties";
        final List<String> lines = new ArrayList<>();
        lines.add(lineOne);
        lines.add(lineTwo);
        lines.add(lineThree);
        lines.add(lineFour);

        final List<BankTransaction> result = (List<BankTransaction>) statementParser.parseLinesFrom(lines);
        final List<BankTransaction> expected = new ArrayList<>();
        final double tolerance = 0.0d;
        expected.add(new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -100, "Deliveroo"));
        expected.add(new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco"));
        expected.add(new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 01), 6000, "Salary"));
        expected.add(new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 02), 2000, "Royalties"));

        Assert.assertEquals(expected.get(0).getDate(), result.get(0).getDate());
        Assert.assertEquals(expected.get(0).getAmount(), result.get(0).getAmount(), tolerance);
        Assert.assertEquals(expected.get(0).getDescription(), result.get(0).getDescription());

        Assert.assertEquals(expected.get(1).getDate(), result.get(1).getDate());
        Assert.assertEquals(expected.get(1).getAmount(), result.get(1).getAmount(), tolerance);
        Assert.assertEquals(expected.get(1).getDescription(), result.get(1).getDescription());

        Assert.assertEquals(expected.get(2).getDate(), result.get(2).getDate());
        Assert.assertEquals(expected.get(2).getAmount(), result.get(2).getAmount(), tolerance);
        Assert.assertEquals(expected.get(2).getDescription(), result.get(2).getDescription());

        Assert.assertEquals(expected.get(3).getDate(), result.get(3).getDate());
        Assert.assertEquals(expected.get(3).getAmount(), result.get(3).getAmount(), tolerance);
        Assert.assertEquals(expected.get(3).getDescription(), result.get(3).getDescription());

    }
}
