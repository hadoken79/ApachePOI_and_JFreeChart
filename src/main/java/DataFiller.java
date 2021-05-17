import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DataFiller{

    private static List<MonthRecord> monthlyRatings;
    private static List<DayRecord> daysFromAllFiles;

    public static void initDataFromFiles(String dirName) throws IOException, InvalidFormatException {
        monthlyRatings = new ArrayList<>();
        daysFromAllFiles = new ArrayList<>();

            Files.list(new File(dirName).toPath())
                    .forEach(path -> {

                        List<DayRecord> days = null;

                        try {
                            days = DataFiller.getRecordsFromFile(path.toFile());
                            daysFromAllFiles.addAll(days);
                            monthlyRatings.add(DataFiller.getMonthlyRatings(days));
                        } catch (IOException | InvalidFormatException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    });
    }

    public static List<DayRecord> getRecordsFromFile(File file) throws IOException, InvalidFormatException {
        List<DayRecord> records = new ArrayList<>();

        try(Workbook workbook = new XSSFWorkbook(file);) {
            Sheet sheet =  workbook.getSheetAt(1);//Einzeltage D-CH

            String errorStat = validateMACell(workbook);
                if(errorStat != "")
                    throw new InvalidFormatException(errorStat);

            Row factsRow = sheet.getRow(2);
            Row nrwtRow = sheet.getRow(3);
            Row maRow = sheet.getRow(4);
            Row vdRow = sheet.getRow(5);
            Row ageRow = sheet.getRow(6);

            for(int i = 1; i < (int)sheet.getRow(1).getLastCellNum(); i++){

                String date = factsRow.getCell(i).getStringCellValue();
                double rt = nrwtRow.getCell(i).getNumericCellValue();
                double ma = maRow.getCell(i).getNumericCellValue();
                double vd = vdRow.getCell(i).getNumericCellValue();
                double age = ageRow.getCell(i).getNumericCellValue();

                records.add(new DayRecord(date, rt, ma, vd, age));
            }

        } catch (IOException | InvalidFormatException e) {
            throw e;
        }
        return records;
    }

    public static MonthRecord getMonthlyRatings(List<DayRecord> days){
        //average ratings for month
        double avgDayRatings = days.stream()
                .mapToInt(d -> d.getNrwt()).average().getAsDouble();
        //average marketshare for month
        double avgDayMa = days.stream()
                .mapToDouble(d -> d.getMa()).average().getAsDouble();
        //average viewtime for month
        double avgDayVd = days.stream()
                .mapToDouble(d -> d.getVd()).average().getAsDouble();

        String dateString = days.get(0).getDay();
        //ToDo add params for avgMA and avgVD

        return new MonthRecord(dateString, avgDayRatings, avgDayMa, avgDayVd);
    }


    public static List<MonthRecord> getMonthlyRatings() {
        return monthlyRatings;
    }

    public static List<DayRecord> getDaysFromAllFiles() {
        return daysFromAllFiles;
    }

    private static String validateMACell(Workbook workbook){
        //sometimes sheets are not correctly formatted and MA and VD are interchanged
        Sheet sheet =  workbook.getSheetAt(1);

        Row maRow = sheet.getRow(4);//this should be MA Cell. Sometimes they are mixed up

        if(!maRow.getCell(0).getStringCellValue().contains("MA"))
            return "Zelle A5 sollte Marktanteil sein, 'MA' kommt aber nicht im Namen der Spalte vor. \nIst Doc: " + sheet.getSheetName() + " falsch Formattiert?";

        return "";
    }

}
