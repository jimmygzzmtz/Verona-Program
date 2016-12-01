//COPYRIGHT (C) 2015 Jaime Gonzalez. All Rights Reserved.

package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class NewSaleController implements Initializable {
	public Stage mainStage;
	//This is what allows the Stage to load
	public Stage stageAux = new Stage();
	//This helps in loading the Stage to load
	public static Scene mainScene;
	//This is what allows the scene to load

	private ObservableList productsData = FXCollections.observableArrayList();
	//This is used to save the values of the combo box

	@FXML
	private Button newSaleHomeButton;
	//This is used to define the homeButton

	@FXML
	private Button newSaleOrdersButton;
	//This is used to define the ordersButton

	@FXML
	private Button newSaleNewSaleButton;
	//This is used to define the newSaleButton

	@FXML
	private Button newSaleProductsButton;
	//This is used to define the productsButton

	@FXML
	private TextField clientEntry;
	//This is used to define the clientEntry text field

	@FXML
	private DatePicker dateEntry;
	//This used to define the dateEntry DatePicker

	@FXML
	private TextField quantityEntry;
	//This is used to define the quantityEntry text field

	@FXML
	private Text warningText;
	//This is used to define the warningText text

	@FXML
	private ComboBox productCombo;
	//This is used to define the productCombo ComboBox

	private ObservableList yesNoOptions = FXCollections.observableArrayList();
	//This is used to define the yesNoOptions ObservableList

	private ObservableList sizeOptions = FXCollections.observableArrayList();
	//This is used to define the sizeOptions ObservableList

	@FXML
	private ComboBox yesNoCombo;
	//This is used to define the yesNoCombo ComboBox

	@FXML
	private ComboBox sizeCombo;
	//This is used to define the sizeCombo ComboBox


	public void initialize(URL arg0, ResourceBundle arg1) {

		for (int i = 0; i < Main.products.size(); i++){
			if (i != 0){
				String Combodata = (Main.products.get(i)[0]);
				productsData.add(Combodata);
			}
		}
		//This is used to define the products that
		//will be available for the user to choose
		//from in the combo box

		productCombo.setItems(productsData);
		//This is used to add those options
		//to the combo box

		yesNoOptions.addAll("Yes", "No");
		yesNoCombo.setItems(yesNoOptions);
		//This is used to define the two options,
		//"Yes" and "No", that the user
		//will be able to choose from in the
		//combo box

		sizeOptions.addAll("S", "M");
		sizeCombo.setItems(sizeOptions);
		//This is used to define the two options,
		//"S" and "M", that the user will be
		//able to choose from in the combo box

	}

	@FXML
	public void newSaleHomeClick(ActionEvent event) throws IOException{
		Parent homeRootGUI = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		Scene homeSceneGUI = new Scene(homeRootGUI);
		mainStage = (Stage) newSaleHomeButton.getScene().getWindow();
		mainStage.setScene(homeSceneGUI);

	}
	//This is used to show the MainGUI when the Home button is clicked

	@FXML
	public void newSaleNewSaleClick(ActionEvent event) throws IOException{
		Parent newSaleRootGUI = FXMLLoader.load(getClass().getResource("/application/NewSaleGUI.fxml"));
		Scene newSaleSceneGUI = new Scene(newSaleRootGUI);
		mainStage = (Stage) newSaleHomeButton.getScene().getWindow();
		mainStage.setScene(newSaleSceneGUI);

	}
	//This is used to show the NewSaleGUI when the New Sale button is clicked

	@FXML
	public void  newSaleOrdersClick(ActionEvent event) throws IOException{
		Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
		Scene ordersSceneGUI = new Scene(ordersRootGUI);
		mainStage = (Stage) newSaleHomeButton.getScene().getWindow();
		mainStage.setScene(ordersSceneGUI);

	}
	//This is used to show the OrdersGUI when the Orders button is clicked

	@FXML
	public void  newSaleProductsClick(ActionEvent event) throws IOException{
		Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
		Scene productsSceneGUI = new Scene(productsRootGUI);
		mainStage = (Stage) newSaleHomeButton.getScene().getWindow();
		mainStage.setScene(productsSceneGUI);

	}
	//This is used to show the ProductsGUI when the Products button is clicked

	@FXML
	public void  saveButtonClick(ActionEvent event) throws IOException{
		String[] bOrders = new String[7];
		//This is used to define the bOrders Array, that
		//is used to get the values entered by the user
		String Price;
		//This is used to save the Price value
		//gotten by the checkprice method
		double priceaux;
		//This is used to assist in the transition
		//of the price to a double value
		double quantityaux;
		//This is used to assist in the transition
		//of the quantity to a double value
		boolean pricesignchecker = true;
		//This is used to check if a value has
		//a "$" sign
		boolean decimalchecker = true;
		//This is used to check if a value has
		//decimal values
		boolean periodchecker = true;
		//This is used to check if a value has
		//a period
		boolean nullchecker = false;
		//This is used to check if a value
		//is null
		boolean numberchecker = false;
		//This is used to check if a value
		//entered is a number

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
		//This is used to define the format the date
		//will be in when converted to a string

		if( productCombo.getValue() == null ) {
			nullchecker = true;
		}
		//This is used to check if the product
		//is entered as null
		if( sizeCombo.getValue() == null ) {
			nullchecker = true;
		}
		//This is used to check if the size
		//is entered as null
		if( yesNoCombo.getValue() == null ) {
			nullchecker = true;
		}
		//This is used to check if the delivered
		//entry is entered as null
		if( dateEntry.getValue() == null ) {
			nullchecker = true;
		}
		//This is used to check if the date entry
		//is entered as null
		if (clientEntry.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the client name
		//is entered as null
		if (quantityEntry.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the quantity
		//is entered as null
		if (nullchecker == true){
			warningText.setText("Some items were left blank. Fill them to proceed.");
		}
		//If a value is null, the user is informed
		//that a value was left blank, and that
		//it has to be filled out to proceed

		if (nullchecker == false){
			try{
				double test = Double.parseDouble(quantityEntry.getText().replaceAll("[$]", ""));
			}catch (NumberFormatException e) {
				numberchecker = true;
				warningText.setText("Quantity should be a number");
			}
		}
		//Quantity is checked to see if it is a number, and if it isn't
		//then the user is informed that it should be a number


		if (nullchecker == false && numberchecker == false){


			bOrders[0] = ("" + productCombo.getValue());

			bOrders[1] = ("" + sizeCombo.getValue());

			bOrders[2] = clientEntry.getText();

			LocalDate date = dateEntry.getValue();

			bOrders[3] = date.format(formatter);

			bOrders[4] = quantityEntry.getText();

			Price = Main.checkprice(bOrders[0]);
			//Price is checked using the checkprice method,
			//while sending the product name entered to
			//check for matches

			priceaux = Double.parseDouble(Price.replaceAll("[$]", ""));
			quantityaux = Double.parseDouble(bOrders[4]);

			priceaux = (priceaux * quantityaux);

			Price = (priceaux + "");

			decimalchecker = (Price.contains(".00"));
			pricesignchecker = (Price.contains("$"));
			periodchecker = (Price.contains("."));


			if (pricesignchecker == false){
				Price = ("$" + Price);
			}

			if ((decimalchecker == false) && (periodchecker == false)){
				Price = (Price + ".00");
			}


			bOrders[5] = (Price);


			bOrders[6] = ("" + yesNoCombo.getValue());

			Main.orders.add(bOrders);
			//The new order is added to the orders list


			warningText.setText("");

			Parent newSaleRootGUI = FXMLLoader.load(getClass().getResource("/application/NewSaleGUI.fxml"));
			Scene newSaleSceneGUI = new Scene(newSaleRootGUI);
			mainStage = (Stage) newSaleHomeButton.getScene().getWindow();
			mainStage.setScene(newSaleSceneGUI);
			//The table is refreshed
		}
		//If there are no null values and all the proper values are numbers,
		//then the new values are saved to the orders list. Finally, the
		// tableis refreshed to reflect the changes

	}

}