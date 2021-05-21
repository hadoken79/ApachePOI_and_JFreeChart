import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class BarChartHandler {

    //-------------------Month-Charts
    public static ChartPanel getAvgRatingsChart(List<MonthRecord> monthlyRatings){

        if(monthlyRatings == null){
            return errorChart();
        }

        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();//JFreeChartElement

        //populate data
        monthlyRatings.stream()
                .sorted(Comparator.comparing(MonthRecord::getMonthNumber))
                .sorted(Comparator.comparing(MonthRecord::getYear))
                .forEach(monthlyRating -> {
                    objDataset.setValue(monthlyRating.getAvgNrwt(), String.valueOf(monthlyRating.getYear()), monthlyRating.getMonth());
                });

        //draw barchart
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
        //prevent bar from becoming to wide, when only one bar is used
        JFreeChart fancyChart = limitBarSize(objChart);

        ChartFrame frame = new ChartFrame("Telebasel Quoten", fancyChart);


        return frame.getChartPanel();
    }

    public static ChartPanel getAvgMaChart(List<MonthRecord> monthlyRatings){

        if(monthlyRatings == null){
            return errorChart();
        }

        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();//JFreeChartElement

        //populate data
        monthlyRatings.stream()
                .sorted(Comparator.comparing(MonthRecord::getMonthNumber))
                .sorted(Comparator.comparing(MonthRecord::getYear))
                .forEach(monthlyRating -> {
                    objDataset.setValue(monthlyRating.getAvgMa(), String.valueOf(monthlyRating.getYear()), monthlyRating.getMonth());
                });

        //draw linechart
        // TODO: 4/22/2021 validate, if record empty, and then initialize default params
        JFreeChart objChart = ChartFactory.createBarChart(
                "Marktanteil",     //Chart title
                "Tagesdurchschnitt",     //Domain axis label
                "Marktanteil CH-D",         //Range axis label
                objDataset,         //Chart Data
                PlotOrientation.VERTICAL, // orientation
                true,             // include legend?
                true,             // include tooltips?
                false // include URLs?
        );

        JFreeChart fancyChart = limitBarSize(objChart);

        ChartFrame frame = new ChartFrame("Telebasel Quoten", fancyChart);


        return frame.getChartPanel();
    }

    public static ChartPanel getAvgVdChart(List<MonthRecord> monthlyRatings){

        if(monthlyRatings == null){
            return errorChart();
        }

        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();//JFreeChartElement

        //populate data
        monthlyRatings.stream()
                .sorted(Comparator.comparing(MonthRecord::getMonthNumber))
                .sorted(Comparator.comparing(MonthRecord::getYear))
                .forEach(monthlyRating -> {
                    objDataset.setValue(monthlyRating.getAvgVd(), String.valueOf(monthlyRating.getYear()), monthlyRating.getMonth());
                });

        //draw linechart
        // TODO: 4/22/2021 validate, if record empty, and then initialize default params
        JFreeChart objChart = ChartFactory.createBarChart(
                "Verweildauer",     //Chart title
                "Tagesdurchschnitt",     //Domain axis label
                "Verweildauer CH-D",         //Range axis label
                objDataset,         //Chart Data
                PlotOrientation.VERTICAL, // orientation
                true,             // include legend?
                true,             // include tooltips?
                false // include URLs?
        );

        JFreeChart fancyChart = limitBarSize(objChart);

        ChartFrame frame = new ChartFrame("Telebasel Quoten", fancyChart);



        return frame.getChartPanel();
    }





    //-------------------Day-Charts
    public static ChartPanel getStrongestDaysRT(List<DayRecord> allRatings, boolean desc){

        if(allRatings == null){
            return errorChart();
        }

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

        if(allRatings == null){
            return errorChart();
        }

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

        if(allRatings == null){
            return errorChart();
        }

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

    private static ChartPanel errorChart(){
        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();//JFreeChartElement
        ChartFrame frame;

        objDataset.setValue(0, "Keine Daten", "Keine Daten");

        JFreeChart objChart = ChartFactory.createBarChart(
                "Keine Daten!!!!",     //Chart title
                "Tag",     //Domain axis label
                "VD CH-D",         //Range axis label
                objDataset,         //Chart Data
                PlotOrientation.HORIZONTAL, // orientation
                true,             // include legend?
                true,             // include tooltips?
                false // include URLs?
        );

        frame = new ChartFrame("Telebasel Quoten", objChart);
        return frame.getChartPanel();
    }

    private static JFreeChart limitBarSize(JFreeChart chart){
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        BarRenderer br = (BarRenderer) categoryPlot.getRenderer();
        br.setMaximumBarWidth(.1); // set maximum width to 10% of chart

        return chart;
    }
}
