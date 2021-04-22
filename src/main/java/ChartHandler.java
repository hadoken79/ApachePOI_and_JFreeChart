import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Comparator;
import java.util.List;

public class ChartHandler {

    public static void showRatingsChart(List<MonthRecord> monthlyRatings){
        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();

        monthlyRatings.stream()
                .sorted(Comparator.comparing(MonthRecord::getMonthNumber))
                .forEach(monthlyRating -> {
                    objDataset.setValue(monthlyRating.getAvgNrwt(), String.valueOf(monthlyRating.getYear()), monthlyRating.getMonth());
                });

//        objDataset.setValue(86,"2021","Januar");
//        objDataset.setValue(78,"2021","Februar");
//
//
//        objDataset.setValue(84,"2020","Januar");
//        objDataset.setValue(74,"2020","Februar");
//
//
//        objDataset.setValue(77,"2019","Januar");
//        objDataset.setValue(69,"2019","Februar");

        // TODO: 4/22/2021 validate, if record emtpty, and then initialize default params
        JFreeChart objChart = ChartFactory.createBarChart(
                "Ratings",     //Chart title
                "Monatsdurchschnitt",     //Domain axis label
                "Ratings CH-D",         //Range axis label
                objDataset,         //Chart Data
                PlotOrientation.VERTICAL, // orientation
                true,             // include legend?
                true,             // include tooltips?
                false             // include URLs?
        );

        ChartFrame frame = new ChartFrame("Telebasel Quoten", objChart);
        frame.pack();
        frame.setVisible(true);
    }
}
