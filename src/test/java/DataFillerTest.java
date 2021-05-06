import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataFillerTest {

    @Test
    void dataFillergetRecords_shouldGenerateAsManyDayrecordsAsDataCollumnsInFile() throws FileNotFoundException {
        //given
        int dataCollumnsInTestFile = 31;

        //when
        List<DayRecord> days = DataFiller.getRecordsFromFile(new File("src/test/resources/TestExcelRatings.xlsx"));

        //then
        assertEquals(dataCollumnsInTestFile, days.stream().count());
    }

    @Test
    void dataFillergetRecords_DayRatingElementsShould_beCorrectDataTypes() throws FileNotFoundException {
        //given
        String firstDateElement = "01.03.2021";
        int firstRTElement = 74;
        double firstMAElement = 0.16;
        double firstVDElement = 12.3;
        int firstAgeElement = 65;


        //when
        List<DayRecord> days = DataFiller.getRecordsFromFile(new File("src/test/resources/TestExcelRatings.xlsx"));

        //then
        //assertEquals(dataCollumnsInTestFile, days.stream().count());
        assertAll(
                () -> assertEquals(days.get(0).getDay().getClass(), String.class),
                () -> assertEquals(days.get(0).getDay(), firstDateElement),
                () -> assertEquals(days.get(0).getNrwt(), firstRTElement),
                () -> assertEquals(days.get(0).getMa(), firstMAElement),
                () -> assertEquals(days.get(0).getVd(), firstVDElement),
                () -> assertEquals(days.get(0).getAge(), firstAgeElement)
        );
    }

    @Test
    void dataFillergetRecords_shouldThrowException_whenFileisProthected() {
        //given
        File testFile = new File("src/test/resources/TestExcelRatings.xlsx");
        testFile.setWritable(false);


        //when
        Executable executable = () -> DataFiller.getRecordsFromFile(testFile);

        //then
        assertThrows(FileNotFoundException.class, executable);

    }

    @Test
    void getRecordsFromFile() {
    }

    @Test
    void getMonthlyRatings() {
    }

    @Test
    void testGetMonthlyRatings() {
    }

    @Test
    void getDaysFromAllFiles() {
    }
}