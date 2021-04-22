import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ReadExcelData {
    public static void main(String[] args) throws IOException {


        String dirName = "C:/temp/";
        List<MonthRecord>monthlyRatings = new ArrayList<>();

        Files.list(new File(dirName).toPath())
                .forEach(path -> {
                    List<DayRecord> days = DataFiller.getRecordsFromFile(path.toFile());
                    monthlyRatings.add(DataFiller.getMonthlyRatings(days));

                    RatingsCalculator ratingsCalculator = new RatingsCalculator();
                    ratingsCalculator.showRatings(days);
                });


        ChartHandler.showRatingsChart(monthlyRatings);

    }
}
