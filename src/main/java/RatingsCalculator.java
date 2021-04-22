import java.util.Comparator;
import java.util.List;



public class RatingsCalculator {


    public void showRatings(List<DayRecord> days){

            days.stream()
                .forEach(DayRecord::show);

        System.out.println("----------------------------------------");
        double avg = days.stream()
                .mapToDouble(d -> d.getNrwt()).average().getAsDouble();
        System.out.println("Durchschnitt: " + Math.round(avg));
        System.out.println();

        System.out.println("stärkste Tage:");
            days.stream()
                .sorted(Comparator.comparing(DayRecord::getNrwt).reversed())
                .limit(4)
                .forEach(d -> {
                    System.out.println("Tag: " + d.getDay() + " | " + d.getDayOfWeek() + " | " + d.getNrwt());
                });
        System.out.println();

        System.out.println("schwächste Tage:");
            days.stream()
                .sorted(Comparator.comparing(DayRecord::getNrwt))
                .limit(4)
                .forEach(d -> {
                    System.out.println("Tag: " + d.getDay() + " | " + d.getDayOfWeek() + " | " + d.getNrwt());
                });
        System.out.println();

        System.out.println("stärkster Marktanteil:");
        days.stream()
                .sorted(Comparator.comparing(DayRecord::getMa).reversed())
                .limit(4)
                .forEach(d -> {
                    System.out.println("Tag: " + d.getDay() + " | " + d.getDayOfWeek() + " | " + d.getMa());
                });

        System.out.println("----------------------------------------");
    }

    public void compareRatings(){

    }

}
