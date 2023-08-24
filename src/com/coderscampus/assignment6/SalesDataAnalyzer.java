package com.coderscampus.assignment6;

import java.text.ParseException;
import java.util.List;

public class SalesDataAnalyzer {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws ParseException {
		CarSales carSales = new CarSales();
		
		System.out.println("Model 3 Yearly Sales Report");
		System.out.println("---------------------------");
		List<CarSales> model3Sales = carSales.readFile("model3.csv");
		System.out.println("2017 -> " + carSales.totalSold(model3Sales, "17"));
		System.out.println("2018 -> " + carSales.totalSold(model3Sales, "18"));
		System.out.println("2019 -> " + carSales.totalSold(model3Sales, "19"));
		carSales.maxMin(model3Sales, "Model 3");
		
		System.out.println("Model S Yearly Sales Report");
		System.out.println("---------------------------");
		List<CarSales> modelSSales = carSales.readFile("modelS.csv");
		System.out.println("2016 -> " + carSales.totalSold(modelSSales, "16"));
		System.out.println("2017 -> " + carSales.totalSold(modelSSales, "17"));
		System.out.println("2018 -> " + carSales.totalSold(modelSSales, "18"));
		System.out.println("2019 -> " + carSales.totalSold(modelSSales, "19"));
		carSales.maxMin(modelSSales, "Model S");
		
		System.out.println("Model X Yearly Sales Report");
		System.out.println("---------------------------");
		List<CarSales> modelXSales = carSales.readFile("modelX.csv");
		System.out.println("2016 -> " + carSales.totalSold(modelXSales, "16"));
		System.out.println("2017 -> " + carSales.totalSold(modelXSales, "17"));
		System.out.println("2018 -> " + carSales.totalSold(modelXSales, "18"));
		System.out.println("2019 -> " + carSales.totalSold(modelXSales, "19"));
		carSales.maxMin(modelXSales, "Model X");
	}

}
