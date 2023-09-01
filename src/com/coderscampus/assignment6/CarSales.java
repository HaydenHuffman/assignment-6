package com.coderscampus.assignment6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarSales {
    private YearMonth month;
    private Integer numberSold;

    public CarSales(YearMonth month, Integer numberSold) {
        this.month = month;
        this.numberSold = numberSold;
    }

    public CarSales() {
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public Integer getNumberSold() {
        return numberSold;
    }

    public void setNumberSold(Integer numberSold) {
        this.numberSold = numberSold;
    }

    public List<CarSales> readFile(String file) {
        List<CarSales> sales = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                YearMonth carMonth = YearMonth.parse(values[0], formatter);
                CarSales carSales = new CarSales(carMonth, Integer.parseInt(values[1]));
                sales.add(carSales);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public static Integer totalSold(List<CarSales> sales, YearMonth yearMonth) {
        Integer total = sales.stream()
                .filter(sale -> sale.getMonth().getYear() == yearMonth.getYear() &&
                                sale.getMonth().getMonthValue() == yearMonth.getMonthValue())
                .map(CarSales::getNumberSold)
                .mapToInt(Integer::intValue)
                .sum();
        return total;
    }

    public static void maxMin(List<CarSales> sales, String car) {
        CarSales maxMonth = sales.stream()
                .max(Comparator.comparing(CarSales::getNumberSold))
                .orElse(null);

        YearMonth maxDate = maxMonth.getMonth();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedMaxDate = outputFormatter.format(maxDate);
        System.out.println();
        System.out.println("The best month for " + car + " was: " + formattedMaxDate);

        CarSales minMonth = sales.stream()
                .min(Comparator.comparing(CarSales::getNumberSold))
                .orElse(null);

        YearMonth minDate = minMonth.getMonth();
        String formattedMinDate = outputFormatter.format(minDate);
        System.out.println("The worst month for " + car + " was: " + formattedMinDate);
        System.out.println();
    }

    public static void printYearlySalesReport(String modelName, List<CarSales> sales) {
        Map<Integer, Integer> yearlySales = sales.stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.getMonth().getYear(),
                        Collectors.summingInt(CarSales::getNumberSold)
                ));

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy");

        yearlySales.forEach((year, totalSales) -> {
            System.out.println(year + " -> " + totalSales);
        });
    }
}


