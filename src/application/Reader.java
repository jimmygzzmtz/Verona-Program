//COPYRIGHT (C) 2015 Jaime Gonzalez. All Rights Reserved.

package application;

import javafx.beans.property.SimpleStringProperty;

public class Reader {
	private final SimpleStringProperty client;
	//This creates a SimpleString for client so that the String can be wrapped
	private final SimpleStringProperty size;
	//This creates a SimpleString for size so that the String can be wrapped
	private final SimpleStringProperty product;
	//This creates a SimpleString for product so that the String can be wrapped
	private final SimpleStringProperty date;
	//This creates a SimpleString for date so that the String can be wrapped
	private final SimpleStringProperty quantity;
	//This creates a SimpleString for quantity so that the String can be wrapped
	private final SimpleStringProperty paid;
	//This creates a SimpleString for paid so that the String can be wrapped
	private final SimpleStringProperty delivered;
	//This creates a SimpleString for delivered so that the String can be wrapped


	public Reader (String sProduct, String sSize, String sClient, String sDate, String sQuantity, String sPaid, String sDelivered) {
		this.product = new SimpleStringProperty(sProduct);
		//This equals this.product to the SimpleStringProperty defined by sProduct
		this.size = new SimpleStringProperty(sSize);
		//This equals this.size to the SimpleStringProperty defined by sSize
		this.client = new SimpleStringProperty(sClient);
		//This equals this.client to the SimpleStringProperty defined by sClient
		this.date = new SimpleStringProperty(sDate);
		//This equals this.date to the SimpleStringProperty defined by sDate
		this.quantity = new SimpleStringProperty(sQuantity);
		//This equals this.quantity to the SimpleStringProperty defined by sQuantity
		this.paid = new SimpleStringProperty(sPaid);
		//This equals this.paid to the SimpleStringProperty defined by sPaid
		this.delivered = new SimpleStringProperty(sDelivered);
		//This equals this.delivered to the SimpleStringProperty defined by sDelivered

	}


	public String getProduct(){
		return product.get();
	}
	//This returns product

	public String getSize(){
		return size.get();
	}
	//This returns size

	public String getClient(){
		return client.get();
	}
	//This returns client

	public String getDate(){
		return date.get();
	}
	//This returns date

	public String getQuantity(){
		return quantity.get();
	}
	//This returns quantity

	public String getPaid(){
		return paid.get();
	}
	//This returns paid

	public String getDelivered(){
		return delivered.get();
	}
	//This returns delivered



	public void setClient(String nClient){
		client.set(nClient);
	}
	//This sets client to nClient

	public void setSize(String nSize){
		size.set(nSize);
	}
	//This sets size to nSize

	public void setProduct(String nProduct){
		product.set(nProduct);
	}
	//This sets product to nProduct

	public void setDate(String nDate){
		date.set(nDate);
	}
	//This sets date to nDate

	public void setQuantity(String nQuantity){
		quantity.set(nQuantity);
	}
	//This sets quantity to nQuantity

	public void setPaid(String nPaid){
		paid.set(nPaid);
	}
	//This sets paid to nPaid

	public void setDelivered(String nDelivered){
		delivered.set(nDelivered);
	}
	//This sets delivered to nDelivered


}
