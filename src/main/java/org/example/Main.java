package org.example;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws IOException {
        XYSeriesCollection dataset = first_step();
        second_step(dataset);
    }

    public static double[][] loadData(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        int m = lines.size();

        double[][] data = new double[m][2];
        for (int i = 0; i < m; i++) {
            String[] values = lines.get(i).split(",");
            data[i][0] = Double.parseDouble(values[0]);
            data[i][1] = Double.parseDouble(values[1]);
        }

        return data;
    }
    private static XYSeriesCollection first_step() {
        System.out.println("Plotting Data ...");
        Path filePath = Paths.get("src/main/resources/ex1data1.txt");
        List<String> data = new ArrayList<>();
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                String[] values = line.split(",");
                String correctedLine = values[0] + "," + values[1].replace(",", ".");
                data.add(correctedLine);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYSeries series = new XYSeries("Data");
        for (String line : data) {
            String[] values = line.split(",");
            series.add(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Данные", "Прибыль", "Кол-во машин", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);
        System.out.println("\n\n Данные отображены. \n Первый этап пройден.");
        return dataset;
    }
    private static void second_step(XYSeriesCollection dataset) throws IOException {
        System.out.println("Running Gradient Descent ...");

        Path filePath = Paths.get("src/main/resources/ex1data1.txt");
        double[][] data = loadData(filePath);

        SimpleRegression regression = new SimpleRegression();

        for (double[] datum : data) {
            regression.addData(datum[0], datum[1]);
        }

        double[] theta = {regression.getIntercept(), regression.getSlope()};

        System.out.println("Найденные веса с помощью градиентного спуска: ");
        System.out.println(theta[0] + " " + theta[1]);

        double predict1 = regression.predict(3.5);
        double predict2 = regression.predict(7.0);
        double xMin = 0; // Минимальное значение по оси X
        double xMax = 25; // Максимальное значение по оси X

        double yMin = theta[0]; // Минимальное значение по оси Y
        double yMax = theta[0] + theta[1] * xMax; // Максимальное значение по оси Y

        JFreeChart chart = ChartFactory.createScatterPlot(
                "Данные", "Прибыль", "Кол-во машин", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAnnotation line = new XYLineAnnotation(xMin, yMin, xMax, yMax, new BasicStroke(6.0f), Color.blue);
        plot.addAnnotation(line);

        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);

        System.out.println("For cars = 35,000, we predict a profit of " + (predict1*10000));
        System.out.println("For cars = 70,000, we predict a profit of " + (predict2*10000));
    }
}