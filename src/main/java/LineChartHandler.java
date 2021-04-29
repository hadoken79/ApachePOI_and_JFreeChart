import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Comparator;
import java.util.List;

public class LineChartHandler {

    public static ChartPanel getRatingsChart(List<MonthRecord> monthlyRatings){
        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();//JFreeChartElement

        //populate data
        monthlyRatings.stream()
                .sorted(Comparator.comparing(MonthRecord::getMonthNumber))
                .forEach(monthlyRating -> {
                    objDataset.setValue(monthlyRating.getAvgNrwt(), String.valueOf(monthlyRating.getYear()), monthlyRating.getMonth());
                });


        //draw linechart
        // TODO: 4/22/2021 validate, if record empty, and then initialize default params
        JFreeChart objChart = ChartFactory.createBarChart(
                "Ratings",     //Chart title
                "Tagesdurchschnitt",     //Domain axis label
                "Ratings CH-D",         //Range axis label
                objDataset,         //Chart Data
                PlotOrientation.VERTICAL, // orientation
                true,             // include legend?
                true,             // include tooltips?
                false // include URLs?
        );

        ChartFrame frame = new ChartFrame("Telebasel Quoten", objChart);


        return frame.getChartPanel();
    }

    public static ChartPanel getStrongestDaysRT(List<DayRecord> allRatings, boolean desc){

        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();//JFreeChartElement
        ChartFrame frame;
        //populate data top and worst, as switchable
        if(desc){
            allRatings.stream()
                    .sorted(Comparator.comparing(DayRecord::getNrwt).reversed())
                    .limit(10)
                    .forEach(dayRating -> {
                        objDataset.setValue(dayRating.getNrwt(), String.valueOf(dayRating.getDayOfWeek()),String.valueOf(dayRating.getDay()));
                    });


            //draw linechart
            // TODO: 4/22/2021 validate, if record empty, and then initialize default params
            JFreeChart objChart = ChartFactory.createBarChart(
                    "Top Ratings",     //Chart title
                    "Tag",     //Domain axis label
                    "Ratings CH-D",         //Range axis label
                    objDataset,         //Chart Data
                    PlotOrientation.HORIZONTAL, // orientation
                    true,             // include legend?
                    true,             // include tooltips?
                    false // include URLs?
            );
            frame = new ChartFrame("Telebasel Quoten", objChart);
        }else {
            allRatings.stream()
                    .sorted(Comparator.comparing(DayRecord::getNrwt))
                    .limit(10)
                    .forEach(dayRating -> {
                        objDataset.setValue(dayRating.getNrwt(), String.valueOf(dayRating.getDayOfWeek()), String.valueOf(dayRating.getDay()));
                    });


            //draw linechart
            // TODO: 4/22/2021 validate, if record empty, and then initialize default params
            JFreeChart objChart = ChartFactory.createBarChart(
                    "Flop Ratings",     //Chart title
                    "Tag",     //Domain axis label
                    "Ratings CH-D",         //Range axis label
                    objDataset,         //Chart Data
                    PlotOrientation.HORIZONTAL, // orientation
                    true,             // include legend?
                    true,             // include tooltips?
                    false // include URLs?
            );
            frame = new ChartFrame("Telebasel Quoten", objChart);
        }



        return frame.getChartPanel();
    }

    public static ChartPanel getStrongestDaysMA(List<DayRecord> allRatings, boolean desc){
        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();//JFreeChartElement
        ChartFrame frame;

        //populate data
        if(desc) {
            allRatings.stream()
                    .sorted(Comparator.comparing(DayRecord::getMa).reversed())
                    .limit(10)
                    .forEach(dayRating -> {
                        objDataset.setValue(dayRating.getMa(), String.valueOf(dayRating.getDayOfWeek()), String.valueOf(dayRating.getDay()));
                    });


            //draw linechart
            // TODO: 4/22/2021 validate, if record empty, and then initialize default params
            JFreeChart objChart = ChartFactory.createBarChart(
                    "Top-Marktanteil",     //Chart title
                    "Tag",     //Domain axis label
                    "MA CH-D",         //Range axis label
                    objDataset,         //Chart Data
                    PlotOrientation.HORIZONTAL, // orientation
                    true,             // include legend?
                    true,             // include tooltips?
                    false // include URLs?
            );

            frame = new ChartFrame("Telebasel Quoten", objChart);
        }else{
            allRatings.stream()
                    .sorted(Comparator.comparing(DayRecord::getMa))
                    .limit(10)
                    .forEach(dayRating -> {
                        objDataset.setValue(dayRating.getMa(), String.valueOf(dayRating.getDayOfWeek()),String.valueOf(dayRating.getDay()));
                    });


            //draw linechart
            // TODO: 4/22/2021 validate, if record empty, and then initialize default params
            JFreeChart objChart = ChartFactory.createBarChart(
                    "Flop-Marktanteil",     //Chart title
                    "Tag",     //Domain axis label
                    "MA CH-D",         //Range axis label
                    objDataset,         //Chart Data
                    PlotOrientation.HORIZONTAL, // orientation
                    true,             // include legend?
                    true,             // include tooltips?
                    false // include URLs?
            );

            frame = new ChartFrame("Telebasel Quoten", objChart);
        }

        return frame.getChartPanel();
    }

    public static ChartPanel getStrongestDaysVD(List<DayRecord> allRatings, boolean desc){
        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();//JFreeChartElement
        ChartFrame frame;

        //populate data
        if(desc) {
            allRatings.stream()
                    .sorted(Comparator.comparing(DayRecord::getVd).reversed())
                    .limit(10)
                    .forEach(dayRating -> {
                        objDataset.setValue(dayRating.getVd(), String.valueOf(dayRating.getDayOfWeek()), String.valueOf(dayRating.getDay()));
                    });


            //draw linechart
            // TODO: 4/22/2021 validate, if record empty, and then initialize default params
            JFreeChart objChart = ChartFactory.createBarChart(
                    "Top-Verweildauer",     //Chart title
                    "Tag",     //Domain axis label
                    "VD CH-D",         //Range axis label
                    objDataset,         //Chart Data
                    PlotOrientation.HORIZONTAL, // orientation
                    true,             // include legend?
                    true,             // include tooltips?
                    false // include URLs?
            );

            frame = new ChartFrame("Telebasel Quoten", objChart);
        }else{
            allRatings.stream()
                    .sorted(Comparator.comparing(DayRecord::getVd))
                    .limit(10)
                    .forEach(dayRating -> {
                        objDataset.setValue(dayRating.getVd(), String.valueOf(dayRating.getDayOfWeek()),String.valueOf(dayRating.getDay()));
                    });


            //draw linechart
            // TODO: 4/22/2021 validate, if record empty, and then initialize default params
            JFreeChart objChart = ChartFactory.createBarChart(
                    "Flop-Verweildauer",     //Chart title
                    "Tag",     //Domain axis label
                    "VD CH-D",         //Range axis label
                    objDataset,         //Chart Data
                    PlotOrientation.HORIZONTAL, // orientation
                    true,             // include legend?
                    true,             // include tooltips?
                    false // include URLs?
            );

            frame = new ChartFrame("Telebasel Quoten", objChart);
        }

        return frame.getChartPanel();
    }
}
