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

public class OrdersEditController implements Initializable {
	public Stage mainStage;
	//This is what allows the Stage to load
	public Stage stageAux = new Stage();
	//This helps in loading the Stage to load
	public static Scene mainScene;
	//This is what allows the scene to load

	private ObservableList productsData = FXCollections.observableArrayList();
	//This is used to save the values of the combo box

	@FXML
	private Button ordersEditHomeButton;
	//This is used to define the homeButton

	@FXML
	private Button ordersEditOrdersButton;
	//This is used to define the ordersButton

	@FXML
	private Button ordersEditNewSaleButton;
	//This is used to define the newSaleButton

	@FXML
	private Button ordersEditProductsButton;
	//This is used to define the productsButton

	@FXML
	private Button saveChangesOrdersButton;
	//This is used to define the save changes button

	@FXML
	private TextField productTextField;
	//This is used to define the product text field

	@FXML
	private TextField clientTextField;
	//This is used to define the client text field

	@FXML
	private TextField sizeTextField;
	//This is used to define the size text field

	@FXML
	private TextField dateTextField;
	//This is used to define the date text field

	@FXML
	private TextField deliveredTextField;
	//This is used to define the delivered text field

	@FXML
	private TextField quantityTextField;
	//This is used to define the quantity text field

	@FXML
	private Text warningText;
	//This is used to define the warningText text

	@FXML
	private DatePicker dateEntry;
	//This used to define the dateEntry DatePicker

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] LineValues = new String[7];
		//This is used to define the LineValues Array,
		//that is used to present to the user
		//the previous values of that line
		for (int i = 0; i < 7; i++){
			LineValues[i] = Main.orders.get(OrdersController.selectedline + 1)[i];
		}
		//This is used to save the values of the selected
		//line to the LineValues array

		productTextField.setText(LineValues[0]);
		sizeTextField.setText(LineValues[1]);
		clientTextField.setText(LineValues[2]);
		dateTextField.setText(LineValues[3]);
		quantityTextField.setText(LineValues[4]);
		deliveredTextField.setText(LineValues[6]);
		//This is used to set the previous values
		//of the line onto text fields so that the
		//user can see it

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
	public void ordersEditHomeClick(ActionEvent event) throws IOException{
		Parent homeRootGUI = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		Scene homeSceneGUI = new Scene(homeRootGUI);
		mainStage = (Stage) ordersEditHomeButton.getScene().getWindow();
		mainStage.setScene(homeSceneGUI);

	}
	//This is used to show the MainGUI when the Home button is clicked

	@FXML
	public void ordersEditNewSaleClick(ActionEvent event) throws IOException{
		Parent newSaleRootGUI = FXMLLoader.load(getClass().getResource("/application/NewSaleGUI.fxml"));
		Scene newSaleSceneGUI = new Scene(newSaleRootGUI);
		mainStage = (Stage) ordersEditHomeButton.getScene().getWindow();
		mainStage.setScene(newSaleSceneGUI);

	}
	//This is used to show the NewSaleGUI when the New Sale button is clicked

	public void  ordersEditOrdersClick(ActionEvent event) throws IOException{
		Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
		Scene ordersSceneGUI = new Scene(ordersRootGUI);
		mainStage = (Stage) ordersEditHomeButton.getScene().getWindow();
		mainStage.setScene(ordersSceneGUI);

	}
	//This is used to show the OrdersGUI when the Orders button is clicked

	@FXML
	public void  ordersEditProductsClick(ActionEvent event) throws IOException{
		Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
		Scene productsSceneGUI = new Scene(productsRootGUI);
		mainStage = (Stage) ordersEditHomeButton.getScene().getWindow();
		mainStage.setScene(productsSceneGUI);

	}
	//This is used to show the ProductsGUI when the Products button is clicked

	@FXML
	public void  saveChangesOrdersClick(ActionEvent event) throws IOException{
		String[] LineValues = new String[7];
		//This is used to define the LineValues Array, that
		//is used to get the values edited by the user
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
		if (clientTextField.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the client name
		//is entered as null
		if (quantityTextField.getText().trim().isEmpty() == true){
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
				double test = Double.parseDouble(quantityTextField.getText().replaceAll("[$]", ""));
			}catch (NumberFormatException e) {
				numberchecker = true;
				warningText.setText("Quantity should be a number");
			}
		}
		//Quantity is checked to see if it is a number, and if it isn't
		//then the user is informed that it should be a number

		if (nullchecker == false && numberchecker == false){


			LineValues[0] = ("" + productCombo.getValue());
			LineValues[1] = ("" + sizeCombo.getValue());

			LineValues[2] = clientTextField.getText();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
			//This is used to define the format the date
			//will be in when converted to a string

			LocalDate date = dateEntry.getValue();

			LineValues[3] = date.format(formatter);

			LineValues[4] = quantityTextField.getText();


			Price = Main.checkprice(LineValues[0]);
			//Price is checked using the checkprice method,
			//while sending the product name entered to
			//check for matches
			priceaux = Double.parseDouble(Price.replaceAll("[$]", ""));
			quantityaux = Double.parseDouble(LineValues[4]);
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

			LineValues[5] = (Price);

			LineValues[6] = ("" + yesNoCombo.getValue());

			Main.editorders(LineValues);
			//editorders is invoked while sending the new values

			warningText.setText("");

			Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
			Scene ordersSceneGUI = new Scene(ordersRootGUI);
			mainStage = (Stage) ordersEditHomeButton.getScene().getWindow();
			mainStage.setScene(ordersSceneGUI);
			//The table is refreshed

		}
		//If there are no null values and all the proper values are numbers,
		//then the new values are saved to LineValues, which are then sent
		//to editorders to replace the selected line. Finally, the table
		//is refreshed to reflect the changes

	}






}