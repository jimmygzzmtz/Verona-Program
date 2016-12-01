//COPYRIGHT (C) 2015 Jaime Gonzalez. All Rights Reserved.


package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import controllers.OrdersController;
import controllers.ProductsController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This is used to control almost all of the main functions in the program, while also controlling the databases used by the tables.
 * @author Jaime Gonzalez
 * @version 1.0
 * @since November 10 2015
 */

public class Main extends Application {
	/**
	 *This is used to define the location of the .jar file, so that the files
	 *can be written to in the same folder.
	 */
	public static String userDir = System.getProperty("user.dir");
	/**
	 *This is used to define the name of the csv file containing
	 *the orders.
	 */
	public static String csvFile = userDir + "/VeronaData.csv";

	public static String productsFile = userDir + "/VeronaProducts.csv";
	//This is used to define the name of the csv file containing
	//the products
	public static String revenueprofitFile = userDir + "/VeronaRevenueProfit.csv";
	//This is used to define the name of the csv file containing
	//the revenue and profit
	public String line = "";
	//This is used to save the values of each line of each file
	public String cvsSplitBy = ",";
	//This is used so that the program knows what part of the file
	//is a different data point
	public BufferedReader br = null;
	//This is used to read files
	public static FileWriter writer;
	//This is used to write onto files
	public static List<String[]> orders = new ArrayList<String[]>();
	//This is used to save a list of orders that is read from the file
	//and represented on a table
	public static ArrayList<String[]> products = new ArrayList<String[]>();
	//This is used to save a list of products that is read from the file
	//and represented on a table
	public static ArrayList<String[]> revenueprofit = new ArrayList<String[]>();
	//This is used to save a list of Revenue and Profit that is read from the file
	//and represented on a table
	public static String[] aOrder = new String[7];
	//This is used to assist in the transferring or replacing of the list of Orders
	public static String[] aProducts = new String[3];
	//This is used to assist in the transferring or replacing of the list of Products
	public static String[] aRevenueProfit = new String[3];
	//This is used to assist in the transferring or replacing of the
	//list of Revenue and Profit
	public static boolean namechecker = false;
	//This is used to check if a client name matches an entered name
	public static boolean productchecker = false;
	//This is used to check if a product name matches an entered name

	@Override
	public void start(Stage primaryStage) {
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				aOrder = line.split(cvsSplitBy);
				orders.add(aOrder);
			}
			//This adds all the data of the csv file containing the orders
			//into the orders list
		} catch (FileNotFoundException e){
			try {
				aOrder[0] = "Product";
				aOrder[1] = "Size";
				aOrder[2] = "Name";
				aOrder[3] = "Delivery Date";
				aOrder[4] = "Quantity";
				aOrder[5] = "Paid";
				aOrder[6] = "Delivered";
				orders.add(aOrder);
			} catch (Exception e2){
				e.printStackTrace();
			}
			//This is used in case no file is found, it writes the heading values
			// so that the csv file can have it upon writing to it
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			br = new BufferedReader(new FileReader(productsFile));
			while ((line = br.readLine()) != null) {
				aProducts = line.split(cvsSplitBy);
				products.add(aProducts);

			}
			//This adds all the data of the csv file containing the products
			//into the products list
		} catch (FileNotFoundException e){
			try {
				aProducts[0] = "Products";
				aProducts[1] = "Price";
				aProducts[2] = "Cost";
				products.add(aProducts);
			} catch (Exception e2){
				e.printStackTrace();
			}
			//This is used in case no file is found, it writes the heading values
			// so that the csv file can have it upon writing to it
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			br = new BufferedReader(new FileReader(revenueprofitFile));
			while ((line = br.readLine()) != null) {
				aRevenueProfit = line.split(cvsSplitBy);
				revenueprofit.add(aRevenueProfit);
			}
			//This adds all the data of the csv file containing the revenue
			//and profit values into the revenue and profit list
		} catch (FileNotFoundException e){
			try {
				aRevenueProfit[0] = "Revenue";
				aRevenueProfit[1] = "Profit";
				aRevenueProfit[2] = "Date";
				revenueprofit.add(aRevenueProfit);
			} catch (Exception e2){
				e.printStackTrace();
			}
			//This is used in case no file is found, it writes the heading values
			// so that the csv file can have it upon writing to it
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//This shows the Main GUI, so it is shown by default

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			//In the case that the user doesn't close the program using the Save
			//and Exit button, the same actions are replicating using this on
			//the window close button

			public void handle(WindowEvent we) {


				try{
					Main.writer = new FileWriter(Main.csvFile);
					for (int i = 0; i < Main.orders.size(); i++){
						Main.writer.append(Main.orders.get(i)[0]);
						Main.writer.append(',');
						Main.writer.append(Main.orders.get(i)[1]);
						Main.writer.append(',');
						Main.writer.append(Main.orders.get(i)[2]);
						Main.writer.append(',');
						Main.writer.append(Main.orders.get(i)[3]);
						Main.writer.append(',');
						Main.writer.append(Main.orders.get(i)[4]);
						Main.writer.append(',');
						Main.writer.append(Main.orders.get(i)[5]);
						Main.writer.append(',');
						Main.writer.append(Main.orders.get(i)[6]);
						Main.writer.append('\n');
					}
					//This writes all the orders onto the orders csv file

					Main.writer.flush();
					//This flushes the writer so it can be used again

					Main.writer = new FileWriter(Main.productsFile);
					for (int i = 0; i < Main.products.size(); i++){
						Main.writer.append(Main.products.get(i)[0]);
						Main.writer.append(',');
						Main.writer.append(Main.products.get(i)[1]);
						Main.writer.append(',');
						Main.writer.append(Main.products.get(i)[2]);
						Main.writer.append('\n');
					}
					//This writes all the products onto the products csv file

					Main.writer.flush();
					//This flushes the writer so it can be used again

					Main.writer = new FileWriter(Main.revenueprofitFile);
					for (int i = 0; i < Main.revenueprofit.size(); i++){
						Main.writer.append(Main.revenueprofit.get(i)[0]);
						Main.writer.append(',');
						Main.writer.append(Main.revenueprofit.get(i)[1]);
						Main.writer.append(',');
						Main.writer.append(Main.revenueprofit.get(i)[2]);
						Main.writer.append('\n');
					}
					//This writes all the revenue and profit values
					//onto the revenue and profit csv file

					Main.writer.flush();
					//This flushes the writer so it can be used again

					Main.writer.close();
					//This closes the writer since it will no longer be used

				} catch(Exception e) {
					e.printStackTrace();
				}

				System.exit(0);
				//This exits the program


			}
		});
	}

	/**
	 * This is used to delete the orders that have been set to delivered by
	 * creating a copy of the list and only copying those who aren't delivered,
	 * and then equaling the original list to the copy.
	 */
	public static void deletedelivered(){
		List<String[]> ordersaux = new ArrayList<String[]>();
		//This is used to assist in the replacement of the orders list

		boolean[] deliveredchecker = new boolean[orders.size()];
		//This is used to check for orders that have been delivered

		for (int i = 0; i < orders.size(); i++){
			if (orders.get(i)[6].equals("Yes")){
				deliveredchecker[i] = true;
			}
		}
		//If an order has been delivered, deliveredchecker is
		//set to true in that spot




		for (int i = 0; i < orders.size(); i++){
			if (deliveredchecker[i] == false){
				String[] aOrderaux = new String[7];
				for (int z = 0; z < 7; z++){
					aOrderaux[z] = orders.get(i)[z];
				}
				//This uses the deliveredchecker to add all the entries
				//that are not delivered onto the aOrderaux array
				ordersaux.add(aOrderaux);
				//This adds the aOrderaux array onto the ordersaux list

			}

		}

		orders = ordersaux;
		//This equals the orders list to the new ordersaux list

	}

	/**
	 * This is used to set an order(s) made by a certain client to delivered
	 * by looking for his or her name in the order's and setting delivered to
	 * yes in those orders.
	 */
	public static void changedelivered(String Clientname){
		List<String[]> ordersaux = new ArrayList<String[]>();
		//This is used to assist in the replacement of the orders list
		namechecker = false;
		//This is used to check for orders that have a client name
		//that matches the name entered

		for (int i = 0; i < orders.size(); i++){
			String Namecheckaux = (orders.get(i)[2]).toLowerCase();
			if (Namecheckaux.equals(Clientname)){
				namechecker = true;
			}
		}
		//If an client name matches the name entered, namechecker is
		//set to true in that spot


		if (namechecker == true){
			for (int i = 0; i < orders.size(); i++){
				String[] aOrderaux = new String[7];
				for (int z = 0; z < 7; z++){
					String Namecheckaux = (orders.get(i)[2]).toLowerCase();
					if (Namecheckaux.equals(Clientname)){
						if (z != 6){
							aOrderaux[z] = orders.get(i)[z];
						}
						if (z == 6){
							aOrderaux[z] = "Yes";
						}
						//This uses the namechecker to add all the entries
						//that match the name onto the aOrderaux array, but
						//with the delivered entry being "Yes"
					}
					if (!(Namecheckaux.equals(Clientname))){
						aOrderaux[z] = orders.get(i)[z];
						//This uses the namechecker to add all the entries
						//that do not match the name onto the aOrderaux array
					}

				}

				ordersaux.add(aOrderaux);
				//This adds the aOrderaux array onto the ordersaux list
			}

			orders = ordersaux;
			//This equals the orders list to the new ordersaux list
		}


	}

	/**
	 * This is used to delete a certain product by looking for its name and
	 * adding all products that do not match its name to a copy of the list,
	 * and then equaling the original list to the copy.
	 */
	public static void deleteproduct(String Productname){

		ArrayList<String[]> productsaux = new ArrayList<String[]>();
		//This is used to assist in the replacement of the products list

		productchecker = false;
		//This is used to check for orders that have a product
		//that matches the name entered


		for (int i = 0; i < products.size(); i++){
			String Productcheckeraux = (products.get(i)[0]).toLowerCase();
			if (Productcheckeraux.equals(Productname)){
				productchecker = true;
			}
		}
		//This is used to check if a product matches the name entered,
		//to see if the operation should be done or not


		if (productchecker == true){
			//If productchecker is true, meaning that a product
			//matches the name entered, this operation is done
			String[] aProductaux = new String[3];
			for (int i = 0; i < products.size(); i++){
				String Productcheckeraux = (products.get(i)[0]).toLowerCase();
				if (!(Productcheckeraux.equals(Productname))){
					aProductaux = products.get(i);
					productsaux.add(aProductaux);
				}
			}
			//This adds all products that do not match the entered name
			//and adds them to the productsaux list

			products = productsaux;
			//This equals the products list to the new productsaux list
		}


	}

	/**
	 * This is used to check the price of a product by using its name
	 * and checking its matching price in the Products list, to return it
	 * on to the requesting method.
	 */
	public static String checkprice(String Productname){
		String ProductPrice = null;
		//This is used to save the value of the price of the product

		for (int i = 0; i < products.size(); i++){

			if (products.get(i)[0].equals(Productname)){
				ProductPrice = products.get(i)[1];

			}

		}
		//This searches for the product that matches what was asked for
		//and sets ProductPrice to the price in the database

		return ProductPrice;
		//This returns ProductPrice to the line that invoked the method
	}

	/**
	 * This is used to add costs to get a total cost by checking it each product to
	 * its matching price, and then adding all of them, while returning the total.
	 */
	public static double addcost(){
		double productcost = 0;
		//This is the value that calculates the total cost
		double productcostdoubleaux = 0;
		//This is used to save the cost of a product onto a double,
		//so that it can have decimals
		String ProductCostAux = null;
		//This is used to transfer the value from the products list onto the double value
		String Productname = null;
		//This is used to check for the product data that matches the product
		//that is put on the order
		String QuantityAux = null;
		//This is used to transfer the value from the orders list onto the double value
		double quantity = 1;
		//This is used to save the cost of the quantity onto a double,
		//so that it can be multiplied by the price


		for (int e = 0; e< orders.size(); e++){
			Productname = orders.get(e)[0];
			//This equals Productname to the name of the product
			//that the orders array has in the e position
			for (int i = 0; i < products.size(); i++){
				if (products.get(i)[0].equals(Productname)){
					//This only runs if the products data in that position
					//has the same name as Productname
					ProductCostAux = products.get(i)[2];
					//This equals ProductCostAux to the value provided
					//by the products list in the i position
					productcostdoubleaux = Double.parseDouble(ProductCostAux.replaceAll("[$]", ""));
					//This is used to extract the price gotten using ProductCostAux,
					//take out the $, and convert it onto a double
					QuantityAux = orders.get(i)[4];
					//This equals QuantityAux to the value provided
					//by the orders list in the i position
					quantity = Double.parseDouble(QuantityAux);
					//This is used to extract the quantity gotten using QuantityAux,
					//and convert it onto a double
					productcost = productcost + (productcostdoubleaux * quantity);
					//This multiplies the product cost by the quantity and
					//adds it to the total cost
				}
			}
		}
		//This calculates the total cost by running several loops to check
		//for matching entries, extract values, and multiply them

		return productcost;
		//This returns the total cost to the line invoking the method
		//so that it can use it
	}


	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This is used to add revenues to get a total revenue by comparing each product to
	 * its matching price paid, and then adding all of them, while returning the total.
	 */
	public static double addrevenue(){
		double revenuedouble = 0.00;
		//This is used to calculate the total revenue from the orders list
		String Revenueaux;
		//This is used to save the revenue gotten from orders
		double revenueintaux;
		//This is used to assist in the addition of the revenue

		for (int i = 0; i < orders.size(); i++){
			if (i != 0){
				Revenueaux = orders.get(i)[5];
				//This is used to save the value of the total money
				//paid in the order as said on the i position of the
				//orders list
				revenueintaux = Double.parseDouble(Revenueaux.replaceAll("[$]", ""));
				//This is used to convert Revenueaux to a double
				//so that it can be added, while ignoring "$"
				revenuedouble = revenuedouble + revenueintaux;
				//This adds the revenue calculated from that order
				//to the total revenue
			}
		}
		//This adds the total revenue by running a loop that goes
		//through each order and adding their price paid

		return revenuedouble;
		//This returns the total revenue from the orders list to
		//the line invoking the method so that it can use it
	}

	/**
	 * This is used to add total revenues to get a final total revenue by going through each line
	 * in the RevenueProfit list and adding all of them, and returning the final value to the
	 * method that requested it.
	 */
	public static double addtotalrevenue(){
		double revenuedouble = 0.00;
		//This is used to calculate the total revenue from
		//the Revenue and Profit list
		String Revenueaux;
		//This is used to save the revenue gotten from Revenue and Profit
		double revenueintaux;
		//This is used to assist in the addition of the revenue

		for (int i = 0; i < revenueprofit.size(); i++){
			if (i != 0){
				Revenueaux = revenueprofit.get(i)[0];
				//This is used to save the value of the total money
				//paid in the the i position of the Revenue and Profit list
				revenueintaux = Double.parseDouble(Revenueaux.replaceAll("[$]", ""));
				//This is used to convert Revenueaux to a double
				//so that it can be added, while ignoring "$"
				revenuedouble = revenuedouble + revenueintaux;
				//This adds the revenue calculated from that order
				//to the total revenue
			}
		}
		//This adds the total revenue by running a loop that goes
		//through each line in the Revenue and Profit list
		//and adding their revenue value

		return revenuedouble;
		//This returns the total revenue from the Revenue and Profit list to
		//the line invoking the method so that it can use it
	}

	/**
	 * This is used to add total profits to get a final total profit by going through each line
	 * in the RevenueProfit list and adding all of them, and returning the final value to the
	 * method that requested it.
	 */
	public static double addtotalprofit(){
		double profitdouble = 0.00;
		//This is used to calculate the total profit from
		//the Revenue and Profit list
		String Profitaux;
		//This is used to save the profit gotten from Revenue and Profit
		double profitintaux;
		//This is used to assist in the addition of the profit
		for (int i = 0; i < revenueprofit.size(); i++){
			if (i != 0){
				Profitaux = revenueprofit.get(i)[1];
				//This is used to save the value of the total money
				//earned in the the i position of the Revenue and Profit list
				profitintaux = Double.parseDouble(Profitaux.replaceAll("[$]", ""));
				//This is used to convert Profitaux to a double
				//so that it can be added, while ignoring "$"
				profitdouble = profitdouble + profitintaux;
				//This adds the profit calculated from that order
				//to the total profit
			}
		}
		return profitdouble;
		//This returns the total profit from the Revenue and Profit list to
		//the line invoking the method so that it can use it
	}

	/**
	 * This is used to reset the RevenueProfit table by creating a new list that only includes
	 * the headers, and then equaling the original list to the new one.
	 */
	public static void resettable(){
		ArrayList<String[]> revenueprofitaux = new ArrayList<String[]>();
		//This is used to save blank values for the Revenue and Profit List
		aRevenueProfit[0] = "Revenue";
		//This is is used to create the header for the Revenue column
		aRevenueProfit[1] = "Profit";
		//This is is used to create the header for the Profit column
		aRevenueProfit[2] = "Date";
		//This is is used to create the header for the Date column
		revenueprofitaux.add(aRevenueProfit);
		//This is used to equal the new Revenue and Profit list
		//to the blank values created above
		revenueprofit = revenueprofitaux;
		//This equals the Revenue and Profit list that
		//the table reads to the blank one
	}

	/**
	 * This is used to add edit an order by creating a copy of the database, copying
	 * all values that do not match the order, and then replacing the order in the
	 * list by using the edited values.
	 */
	public static void editorders(String[] LineValues){
		ArrayList<String[]> ordersaux = new ArrayList<String[]>();
		//This is used to assist in the replacement of the orders list
		String[] aOrderaux2 = new String[7];
		//This is used to add the save the headings onto the orders list
		aOrderaux2[0] = "Product";
		aOrderaux2[1] = "Size";
		aOrderaux2[2] = "Name";
		aOrderaux2[3] = "Delivery Date";
		aOrderaux2[4] = "Quantity";
		aOrderaux2[5] = "Paid";
		aOrderaux2[6] = "Delivered";
		ordersaux.add(aOrderaux2);
		//This is used to add the headings onto ordersaux

		for (int i = 0; i < orders.size(); i++){
			if (i != 0){
				String[] aOrderaux = new String[7];
				//This is used to save the values of the given line
				//of the orders list
				if (i == (OrdersController.selectedline + 1)){
					for (int z = 0; z < 7; z++){
						aOrderaux[z] = LineValues[z];
					}
				}
				//If the orders line matches the selected line,
				//the lines saved to aOrderaux in the z position
				//are those in LineValues in the z position
				if (!(i == OrdersController.selectedline + 1)){
					for (int z = 0; z < 7; z++){
						aOrderaux[z] = orders.get(i)[z];
					}
				}
				//If the orders line does not match the selected line,
				//the lines saved to aOrderaux in the z position
				//are the originals in that position

				ordersaux.add(aOrderaux);
				//This is used to add the order line onto the
				//ordersaux list
			}


		}
		orders = ordersaux;
		//This equals the old orders list to the new ordersaux line
	}

	/**
	 * This is used to delete a clicked line in orders by creating a copy of the
	 * list and copying all values except the clicked one.
	 */
	public static void deleteordersline(int selectedline){
		ArrayList<String[]> ordersaux = new ArrayList<String[]>();
		//This is used to assist in the replacement of the orders list
		String[] aOrderaux2 = new String[7];
		//This is used to add the save the headings onto the orders list
		aOrderaux2[0] = "Product";
		aOrderaux2[1] = "Size";
		aOrderaux2[2] = "Name";
		aOrderaux2[3] = "Delivery Date";
		aOrderaux2[4] = "Quantity";
		aOrderaux2[5] = "Paid";
		aOrderaux2[6] = "Delivered";
		ordersaux.add(aOrderaux2);
		//This is used to add the headings onto ordersaux

		for (int i = 0; i < orders.size(); i++){
			if (i != 0){
				String[] aOrderaux = new String[7];
				//This is used to save the values of the given line
				//of the orders list
				if (!(i == OrdersController.selectedline + 1)){
					for (int z = 0; z < 7; z++){
						aOrderaux[z] = orders.get(i)[z];
					}
					//This is used to save the values of orders in that line
					//only if it doesn't match the selected line

					ordersaux.add(aOrderaux);
					//This is used to add the order line onto the
					//ordersaux list
				}
			}
		}
		orders = ordersaux;
		//This equals the old orders list to the new ordersaux line
	}

	/**
	 * This is used to add edit a product by creating a copy of the database, copying
	 * all values that do not match the product, and then replacing the product in the
	 * list by using the edited values.
	 */
	public static void editproducts(String[] LineValues){
		ArrayList<String[]> productsaux = new ArrayList<String[]>();
		//This is used to assist in the replacement of the products list
		String[] aProductsaux2 = new String[3];
		//This is used to add the save the headings onto the products list
		aProductsaux2[0] = "Products";
		aProductsaux2[1] = "Price";
		aProductsaux2[2] = "Cost";
		productsaux.add(aProductsaux2);
		//This is used to add the headings onto productsaux

		for (int i = 0; i < products.size(); i++){
			if (i != 0){
				String[] aProductsaux = new String[3];
				//This is used to save the values of the given line
				//of the products list
				if (i == (ProductsController.selectedline + 1)){
					for (int z = 0; z < 3; z++){
						aProductsaux[z] = LineValues[z];
					}
				}
				//If the orders line matches the selected line,
				//the lines saved to aProductsaux in the z position
				//are those in LineValues in the z position
				if (!(i == ProductsController.selectedline + 1)){
					for (int z = 0; z < 3; z++){
						aProductsaux[z] = products.get(i)[z];

					}
				}
				//If the orders line does not match the selected line,
				//the lines saved to aProductsaux in the z position
				//are the originals in that position

				productsaux.add(aProductsaux);
				//This is used to add the product line onto the
				//products list
			}


		}
		products = productsaux;
		//This equals the old products list to the new productsaux line
	}

	/**
	 * This is used to delete a clicked line in products by creating a copy of the
	 * list and copying all values except the clicked one.
	 */
	public static void deleteproductsline(int selectedline){
		ArrayList<String[]> productsaux = new ArrayList<String[]>();
		//This is used to assist in the replacement of the products list
		String[] aProductsaux2 = new String[3];
		//This is used to add the save the headings onto the products list
		aProductsaux2[0] = "Products";
		aProductsaux2[1] = "Price";
		aProductsaux2[2] = "Cost";
		productsaux.add(aProductsaux2);
		//This is used to add the headings onto productsaux

		for (int i = 0; i < products.size(); i++){
			if (i != 0){
				String[] aProductsaux = new String[3];
				//This is used to save the values of the given line
				//of the products list
				if (!(i == ProductsController.selectedline + 1)){
					for (int z = 0; z < 3; z++){
						aProductsaux[z] = products.get(i)[z];

					}
					//This is used to save the values of products in that line
					//only if it doesn't match the selected line

					productsaux.add(aProductsaux);
					//This is used to add the product line onto the
					//productsaux list
				}


			}


		}
		products = productsaux;
		//This equals the old products list to the new productsaux line
	}


}
