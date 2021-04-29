import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.data.time.Day;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DataFiller{

    private static List<MonthRecord> monthlyRatings;
    private static List<DayRecord> daysFromAllFiles;
    private static String dirName = "C:/temp/bench/";

    public static List<DayRecord> getRecordsFromFile(File file){
        List<DayRecord> records = new ArrayList<>();

        try(Workbook workbook = new XSSFWorkbook(file);) {
            Sheet sheet =  workbook.getSheetAt(1);//Einzeltage D-CH

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
            e.printStackTrace();
        }
        //        for (Cell cell : nrwtRow) {
//            if(cell.getCellType() == CellType.NUMERIC)
//            System.out.println(cell.getNumericCellValue());
//        }
//
//        double firstData = nrwt.getCell(1).getNumericCellValue();
//        System.out.println(firstData);

        return records;
    }

    public static MonthRecord getMonthlyRatings(List<DayRecord> days){
        double avgDayRatings = days.stream()
                .mapToInt(d -> d.getNrwt()).average().getAsDouble();

        String dateString = days.get(0).getDay();

        return new MonthRecord(dateString, avgDayRatings);
    }

    public static void initDataFromFiles() throws IOException {
        monthlyRatings = new ArrayList<>();
        daysFromAllFiles = new ArrayList<>();

        Files.list(new File(dirName).toPath())
                .forEach(path -> {
                    List<DayRecord> days = DataFiller.getRecordsFromFile(path.toFile());
                    daysFromAllFiles.addAll(days);
                    monthlyRatings.add(DataFiller.getMonthlyRatings(days));

                    RatingsCalculator ratingsCalculator = new RatingsCalculator();
                    ratingsCalculator.showRatings(days);
                });
    }


    public static List<MonthRecord> getMonthlyRatings() {
        return monthlyRatings;
    }

    public static List<DayRecord> getDaysFromAllFiles() {
        return daysFromAllFiles;
    }


}
