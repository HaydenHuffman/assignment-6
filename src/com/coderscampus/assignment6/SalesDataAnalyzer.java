package com.coderscampus.assignment6;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SalesDataAnalyzer {

    public static void main(String[] args) throws ParseException {
        CarSales carSales = new CarSales();

        System.out.println("Model 3 Yearly Sales Report");
        System.out.println("---------------------------");
        List<CarSales> model3Sales = carSales.readFile("model3.csv");
        CarSales.printYearlySalesReport("Model 3", model3Sales);
        CarSales.maxMin(model3Sales, "Model 3");

        System.out.println("Model S Yearly Sales Report");
        System.out.println("---------------------------");
        List<CarSales> modelSSales = carSales.readFile("modelS.csv");
        CarSales.printYearlySalesReport("Model S", modelSSales);
        CarSales.maxMin(modelSSales, "Model S");

        System.out.println("Model X Yearly Sales Report");
        System.out.println("---------------------------");
        List<CarSales> modelXSales = carSales.readFile("modelX.csv");
        CarSales.printYearlySalesReport("Model X", modelXSales);
        CarSales.maxMin(modelXSales, "Model X");
    }

   
}
