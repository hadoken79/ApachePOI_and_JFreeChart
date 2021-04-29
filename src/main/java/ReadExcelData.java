import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReadExcelData {



    public static void main(String[] args) {

        try {
            DataFiller.initDataFromFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new MainWindow();

    }


}
