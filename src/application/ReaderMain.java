//COPYRIGHT (C) 2015 Jaime Gonzalez. All Rights Reserved.

package application;

import javafx.beans.property.SimpleStringProperty;

public class ReaderMain {
	private final SimpleStringProperty revenue;
	//This creates a SimpleString for revenue so that the String can be wrapped
	private final SimpleStringProperty profit;
	//This creates a SimpleString for profit so that the String can be wrapped
	private final SimpleStringProperty date;
	//This creates a SimpleString for date so that the String can be wrapped


	public ReaderMain (String sRevenue, String sProfit, String sDate){
		this.revenue = new SimpleStringProperty(sRevenue);
		//This equals this.revenue to the SimpleStringProperty defined by sRevenue
		this.profit = new SimpleStringProperty(sProfit);
		//This equals this.profit to the SimpleStringProperty defined by sProfit
		this.date = new SimpleStringProperty(sDate);
		//This equals this.date to the SimpleStringProperty defined by sDate

	}

	public String getRevenue(){
		return revenue.get();
	}
	//This returns revenue


	public String getProfit(){
		return profit.get();
	}
	//This returns profit

	public String getDate(){
		return date.get();
	}
	//This returns date


	public void setRevenue(String nRevenue){
		revenue.set(nRevenue);
	}
	//This sets revenue to nRevenue

	public void setProfit(String nProfit){
		profit.set(nProfit);
	}
	//This sets profit to nProfit

	public void setDate(String nDate){
		date.set(nDate);
	}
	//This sets date to nDate

}
