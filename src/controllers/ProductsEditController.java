//COPYRIGHT (C) 2015 Jaime Gonzalez. All Rights Reserved.

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import application.ReaderProducts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductsEditController implements Initializable {
	public Stage mainStage;
	//This is what allows the Stage to load
	public Stage stageAux = new Stage();
	//This helps in loading the Stage to load
	public static Scene mainScene;
	//This is what allows the scene to load

	private ObservableList<ReaderProducts> data = FXCollections.observableArrayList();
	//This is used to save the values of the table

	@FXML
	private Button productsEditHomeButton;
	//This is used to define the homeButton

	@FXML
	private Button productsEditOrdersButton;
	//This is used to define the ordersButton

	@FXML
	private Button productsEditNewSaleButton;
	//This is used to define the newSaleButton

	@FXML
	private Button productsEditProductsButton;
	//This is used to define the productsButton

	@FXML
	private Button saveChangesProductsButton;
	//This is used to define the save changes button

	@FXML
	private TextField productEntry;
	//This is used to save the productEntry text field

	@FXML
	private TextField productPrice;
	//This is used to save the productPrice text field

	@FXML
	private TextField productCost;
	//This is used to save the productCost text field

	@FXML
	private Text warningText;
	//This is used to save the warningText Text



	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] LineValues = new String[3];
		//This is used to save the previously entered values
		//from the selected line

		for (int i = 0; i < 3; i++){
			LineValues[i] = Main.products.get(OrdersController.selectedline + 1)[i];
		}
		//The values of the selected line is saved to
		//LineValues

		productEntry.setText(LineValues[0]);
		productPrice.setText(LineValues[1]);
		productCost.setText(LineValues[2]);
		//The values of LineValues are presented to the
		//user for reference


	}



	@FXML
	public void productsHomeClick(ActionEvent event) throws IOException{
		Parent homeRootGUI = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		Scene homeSceneGUI = new Scene(homeRootGUI);
		mainStage = (Stage) productsEditHomeButton.getScene().getWindow();
		mainStage.setScene(homeSceneGUI);

	}
	//This is used to show the MainGUI when the Home button is clicked

	@FXML
	public void productsNewSaleClick(ActionEvent event) throws IOException{
		Parent newSaleRootGUI = FXMLLoader.load(getClass().getResource("/application/NewSaleGUI.fxml"));
		Scene newSaleSceneGUI = new Scene(newSaleRootGUI);
		mainStage = (Stage) productsEditHomeButton.getScene().getWindow();
		mainStage.setScene(newSaleSceneGUI);

	}
	//This is used to show the NewSaleGUI when the New Sale button is clicked

	@FXML
	public void  productsOrdersClick(ActionEvent event) throws IOException{
		Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
		Scene ordersSceneGUI = new Scene(ordersRootGUI);
		mainStage = (Stage) productsEditHomeButton.getScene().getWindow();
		mainStage.setScene(ordersSceneGUI);

	}
	//This is used to show the OrdersGUI when the Orders button is clicked

	@FXML
	public void  productsProductsClick(ActionEvent event) throws IOException{
		Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
		Scene productsSceneGUI = new Scene(productsRootGUI);
		mainStage = (Stage) productsEditHomeButton.getScene().getWindow();
		mainStage.setScene(productsSceneGUI);

	}
	//This is used to show the ProductsGUI when the Products button is clicked

	@FXML
	public void  saveChangesProductsClick(ActionEvent event) throws IOException{
		String[] LineValues = new String[3];
		//This is used to define the LineValues Array, that
		//is used to get the values edited by the user
		String Price;
		//This is used to save the new value
		//for Price, entered by the user
		String Cost;
		//This is used to save the new value
		//for Cost, entered by the user
		boolean decimalcheckerprice = true;
		//This is used to check if price has
		//decimal values
		boolean periodcheckerprice = true;
		//This is used to check if price has
		//a period
		boolean pricesigncheckerprice = true;
		//This is used to check if price has
		//a "$" sign
		boolean decimalcheckercost = true;
		//This is used to check if cost has
		//decimal values
		boolean periodcheckercost = true;
		//This is used to check if cost has
		//a period
		boolean pricesigncheckercost = true;
		//This is used to check if cost has
		//a "$" sign

		boolean nullchecker = false;
		//This is used to check if a value
		//is null
		boolean numberchecker = false;
		//This is used to check if a value
		//entered is a number

		if (productEntry.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the product
		//name is entered as null
		if (productPrice.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the product
		//price is entered as null
		if (productCost.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the product
		//cost is entered as null
		if (nullchecker == true){
			warningText.setText("Some items were left blank. Fill them to proceed.");
		}
		//If a value is null, the user is informed
		//that a value was left blank, and that
		//it has to be filled out to proceed

		if (nullchecker == false){
			try{
				double test = Double.parseDouble(productPrice.getText().replaceAll("[$]", ""));
			}catch (NumberFormatException e) {
				numberchecker = true;
				warningText.setText("Price and Cost should be numbers");
			}

			try{
				double test = Double.parseDouble(productCost.getText().replaceAll("[$]", ""));
			}catch (NumberFormatException e) {
				numberchecker = true;
				warningText.setText("Price and Cost should be numbers");
			}
		}
		//Quantity is checked to see if it is a number, and if it isn't
		//then the user is informed that it should be a number

		if (nullchecker == false && numberchecker == false){

			LineValues[0] = productEntry.getText();

			Price = productPrice.getText();

			decimalcheckerprice = (Price.contains(".00"));
			periodcheckerprice = (Price.contains("."));
			pricesigncheckerprice = (Price.contains("$"));

			if ((decimalcheckerprice == false) && (periodcheckerprice == false)){
				Price = (Price + ".00");
			}

			if (pricesigncheckerprice == false){
				Price = ("$" + Price);
			}

			LineValues [1] = Price;

			Cost = productCost.getText();

			decimalcheckercost = (Cost.contains(".00"));
			periodcheckercost= (Cost.contains("."));
			pricesigncheckercost = (Cost.contains("$"));

			if ((decimalcheckercost == false) && (periodcheckercost == false)){
				Cost = (Cost + ".00");
			}

			if (pricesigncheckercost == false){
				Cost = ("$" + Cost);
			}

			LineValues [2] = Cost;

			Main.editproducts(LineValues);
			//editproducts is invoked while sending the new values

			warningText.setText("");

			Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
			Scene productsSceneGUI = new Scene(productsRootGUI);
			mainStage = (Stage) productsEditHomeButton.getScene().getWindow();
			mainStage.setScene(productsSceneGUI);
			//The table is refreshed

		}
		//If there are no null values and all the proper values are numbers,
		//then the new values are saved to LineValues, which are then sent
		//to editproducts to replace the selected line. Finally, the table
		//is refreshed to reflect the changes

	}




}