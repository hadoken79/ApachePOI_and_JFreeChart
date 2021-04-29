import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainWindow extends JFrame {

    public MainWindow(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TV-Ratings");
        this.setSize(1200, 900);
        ImageIcon tb = new ImageIcon("tb_logo.png");
        this.setIconImage(tb.getImage());
        //frame.setResizable(false);
        //frame.getContentPane().setBackground(new Color(94, 97, 95));
        this.setLayout(new BorderLayout());//default
        ImageIcon reload = new ImageIcon("reload_25x25.png");


        JPanel buttonPanel = new JPanel();
        JPanel chartArea = new JPanel();
//        buttonPanel.setBounds(0, 0, 800, 30);//needed when setting layoutmanager to null
//        chartArea.setBounds(0, 30, 800, 400);
        buttonPanel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 30 ));
        chartArea.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 600 ));

        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));


        JButton btn1 = new JButton("RT");
        JButton btn2 = new JButton("MA");
        JButton btn3 = new JButton("VD");
        JButton btn4 = new JButton("AVG");
        JButton btn5 = new JButton();

        btn5.setIcon(reload);

        btn1.setPreferredSize(new Dimension(100, 30));
        btn2.setPreferredSize(new Dimension(100, 30));
        btn3.setPreferredSize(new Dimension(100, 30));
        btn4.setPreferredSize(new Dimension(100, 30));
        btn5.setPreferredSize(new Dimension(50, 30));

        btn1.setFocusable(false);
        btn2.setFocusable(false);
        btn3.setFocusable(false);
        btn4.setFocusable(false);
        btn5.setFocusable(false);

        AtomicBoolean toggle1 = new AtomicBoolean(true);
        AtomicBoolean toggle2 = new AtomicBoolean(true);
        AtomicBoolean toggle3 = new AtomicBoolean(true);

        btn1.addActionListener(e -> {
            //JOptionPane.showConfirmDialog(frame,"Fuck you", "Message from your mother", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            ChartPanel topRatings = LineChartHandler.getStrongestDaysRT(DataFiller.getDaysFromAllFiles(), toggle1.get());
            toggle1.set(!toggle1.get());
            chartArea.removeAll();
            chartArea.revalidate();
            chartArea.add(topRatings);
            chartArea.revalidate();
            this.repaint();
        });
        btn2.addActionListener(e -> {
            ChartPanel topMa = LineChartHandler.getStrongestDaysMA(DataFiller.getDaysFromAllFiles(), toggle2.get());
            toggle2.set(!toggle2.get());
            chartArea.removeAll();
            chartArea.revalidate();
            chartArea.add(topMa);
            chartArea.revalidate();
            this.repaint();
        });

        btn3.addActionListener(e -> {
            ChartPanel topVd = LineChartHandler.getStrongestDaysVD(DataFiller.getDaysFromAllFiles(), toggle3.get());
            toggle3.set(!toggle3.get());
            chartArea.removeAll();
            chartArea.revalidate();
            chartArea.add(topVd);
            chartArea.revalidate();
            this.repaint();
        });

        btn4.addActionListener(e -> {
            ChartPanel monthRatings = LineChartHandler.getRatingsChart(DataFiller.getMonthlyRatings());
            chartArea.removeAll();
            chartArea.revalidate();
            chartArea.add(monthRatings);
            chartArea.revalidate();
            this.repaint();
        });
        btn5.addActionListener(e -> {
            try {
                DataFiller.initDataFromFiles();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btn4);
        buttonPanel.add(btn5);

        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(chartArea, BorderLayout.CENTER);
        //frame.pack();
        this.setVisible(true);

    }




}