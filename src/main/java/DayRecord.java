import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.LocalDate;
import java.util.Locale;

public class DayRecord {
    private String day;

    private String dayOfWeek;
    private int nrwt;
    private double ma;
    private double vd;
    private int age;

    public DayRecord(String day, double nrwt, double ma, double vd, double age) {
        this.day = day;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(day, formatter);
        this.dayOfWeek = getDayFromDate(localDate);
        this.nrwt = (int)nrwt;
        this.ma = ma;
        this.vd = vd;
        this.age = (int)age;
    }

    public String getDay() {
        return day;
    }

    public int getNrwt() {
        return nrwt;
    }

    public double getMa() {
        return ma;
    }

    public double getVd() {
        return vd;
    }

    public int getAge() {
        return age;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    private String getDayFromDate(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getDisplayName(TextStyle.SHORT, Locale.GERMAN);
    }


    public void show(){
        System.out.print("Tag: ");
        System.out.print(this.getDay() + " | ");
        System.out.print(this.getDayOfWeek() + " | ");

        System.out.print("Ratings: ");
        System.out.print(this.getNrwt() + " | ");

        System.out.print("Marktanteil: ");
        System.out.print(this.getMa() + " | ");

        System.out.print("Verweildauer: ");
        System.out.print(this.getVd() + " | ");

        System.out.print("Alter: ");
        System.out.println(this.getAge());
    }

}
