package com.coderscampus.assignment6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarSales {
	private String month;
	private Integer numberSold;
	
	public CarSales(String month, Integer numberSold) {
		this.month = month;
		this.numberSold = numberSold;
	}

	public CarSales() {
		// TODO Auto-generated constructor stub
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
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
		try (FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				String line;
				bufferedReader.readLine();
				while ((line = bufferedReader.readLine()) != null) {
					String[] values = line.split(",");
					CarSales carSales = new CarSales(values[0], Integer.parseInt(values[1]));
					sales.add(carSales);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sales;
	}
	
	public static Integer totalSold(List<CarSales> sales, String year) {
		Integer total = sales.stream()
					 		 .filter(y -> y.getMonth().contains(year))
					 		 .map(CarSales::getNumberSold)
					 		 .mapToInt(Integer::intValue)
					 		 .sum();
		return total;
	}
	
	public static void maxMin(List<CarSales> sales, String car) throws ParseException {
		CarSales maxMonth = sales.stream()
								 .max(Comparator.comparing(CarSales::getNumberSold))
								 .orElse(null);		
		
		
		
		
		String maxMonthString = maxMonth.getMonth();
		SimpleDateFormat format = new SimpleDateFormat("MMM-yy");
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM");
		Date maxDate = format.parse(maxMonthString);
		String formattedMaxDate = newFormat.format(maxDate);
		System.out.println();
		System.out.println("The best month for " + car + " was: " + formattedMaxDate);
		
		CarSales minMonth = sales.stream()
								 .min(Comparator.comparing(CarSales::getNumberSold))
								 .orElse(null);
		
		String minMonthString = minMonth.getMonth();
		Date minDate = format.parse(minMonthString);
		String formattedMinDate = newFormat.format(minDate);
		System.out.println("The worst month for " + car + " was: " + formattedMinDate);
		System.out.println();
			 
		
	}
	
	 public static void printYearlySalesReport(String modelName, List<CarSales> sales) {
	        Set<String> years = sales.stream()
	                				 .map(sale -> sale.getMonth().substring(4, 6))
	                				 .collect(Collectors.toSet());

	        years.forEach(year -> {
	            int totalSales = CarSales.totalSold(sales, year);
	            System.out.println("20" + year + " -> " + totalSales);
	        });
	    }
	
	
}

