//COPYRIGHT (C) 2015 Jaime Gonzalez. All Rights Reserved.

package application;

import javafx.beans.property.SimpleStringProperty;

public class ReaderProducts {
	private final SimpleStringProperty products;
	//This creates a SimpleString for products so that the String can be wrapped
	private final SimpleStringProperty price;
	//This creates a SimpleString for price so that the String can be wrapped
	private final SimpleStringProperty cost;
	//This creates a SimpleString for cost so that the String can be wrapped


	public ReaderProducts (String sProducts, String sPrice, String sCost){
		this.products = new SimpleStringProperty(sProducts);
		//This equals this.products to the SimpleStringProperty defined by sProducts
		this.price = new SimpleStringProperty(sPrice);
		//This equals this.price to the SimpleStringProperty defined by sPrice
		this.cost = new SimpleStringProperty(sCost);
		//This equals this.cost to the SimpleStringProperty defined by sCost
	}

	public String getProducts(){
		return products.get();
	}
	//This returns products

	public String getPrice(){
		return price.get();
	}
	//This returns price

	public String getCost(){
		return cost.get();
	}
	//This returns cost

	public void setProducts(String nProducts){
		products.set(nProducts);
	}
	//This sets products to nProducts

	public void setPrice(String nPrice){
		products.set(nPrice);
	}
	//This sets price to nPrice

	public void setCost(String nPrice){
		cost.set(nPrice);
	}
	//This sets cost to nCost


}
