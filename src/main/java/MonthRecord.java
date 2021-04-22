import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthRecord {

    private String month;



    private int monthNumber;
    private int year;
    private int avgNrwt;

    public MonthRecord(String dateString, double avgNrwt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        this.month = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.GERMAN);
        this.monthNumber = localDate.getMonthValue();
        this.year = localDate.getYear();
        this.avgNrwt = (int)Math.round(avgNrwt);
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getAvgNrwt() {
        return avgNrwt;
    }

    public int getMonthNumber() {
        return monthNumber;
    }



}
