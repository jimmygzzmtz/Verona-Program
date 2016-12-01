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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductsController implements Initializable {
	public Stage mainStage;
	//This is what allows the Stage to load
	public Stage stageAux = new Stage();
	//This helps in loading the Stage to load
	public static Scene mainScene;
	//This is what allows the scene to load
	public String newproduct = null;
	//This is used to save the name of the
	//new product entered
	public static int selectedline;
	//This is used to save the index of the line selected by the user

	private ObservableList<ReaderProducts> data = FXCollections.observableArrayList();
	//This is used to save the values of the table

	@FXML
	private Button productsHomeButton;
	//This is used to define the homeButton

	@FXML
	private Button productsOrdersButton;
	//This is used to define the ordersButton

	@FXML
	private Button productsNewSaleButton;
	//This is used to define the newSaleButton

	@FXML
	private Button productsProductsButton;
	//This is used to define the productsButton

	@FXML
	private Button saveButton;
	//This is used to define the saveButton

	@FXML
	private Button deleteButton;
	//This is used to define the deleteButton

	@FXML
	private Button editLineButton;
	//This is used to define the editLineButton

	@FXML
	private Button deleteLineButton;
	//This is used to define the deleteLineButton

	@FXML
	private TableView<ReaderProducts> productsTable;
	//This is used to define the table containing the orders

	@FXML
	private TableColumn<ReaderProducts, String> productsColumn;
	//This is used to define the product column

	@FXML
	private TableColumn<ReaderProducts, String> priceColumn;
	//This is used to define the price column

	@FXML
	private TableColumn<ReaderProducts, String> costColumn;
	//This is used to define the cost column

	@FXML
	private Text warningText;
	//This is used to define the warningText text

	@FXML
	private TextField newProductEntry;
	//This is used to newProductEntry text field

	@FXML
	private TextField newProductPrice;
	//This is used to newProductPrice text field

	@FXML
	private TextField newProductCost;
	//This is used to newProductCost text field

	@FXML
	private TextField deleteProductEntry;
	//This is used to deleteProductEntry text field

	public void initialize(URL arg0, ResourceBundle arg1) {
		productsColumn.setCellValueFactory(new PropertyValueFactory<ReaderProducts, String>("products"));
		//This sets the Cell Value Factory of productsColumn to "products"
		priceColumn.setCellValueFactory(new PropertyValueFactory<ReaderProducts, String>("price"));
		//This sets the Cell Value Factory of priceColumn to "price"
		costColumn.setCellValueFactory(new PropertyValueFactory<ReaderProducts, String>("cost"));
		//This sets the Cell Value Factory of costColumn to "cost"

		productsTable.setEditable(true);
		//This allows productsTable to be editable

		for (int i = 0; i < Main.products.size(); i++){
			if (i != 0){
				data.add(new ReaderProducts(Main.products.get(i)[0], Main.products.get(i)[1], Main.products.get(i)[2]));

			}
		}
		//This is used so that that data in the products list is loaded onto data

		productsTable.setItems(data);
		//This is used so that the table loads the values in data

	}



	@FXML
	public void productsHomeClick(ActionEvent event) throws IOException{
		Parent homeRootGUI = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		Scene homeSceneGUI = new Scene(homeRootGUI);
		mainStage = (Stage) productsHomeButton.getScene().getWindow();
		mainStage.setScene(homeSceneGUI);

	}
	//This is used to show the MainGUI when the Home button is clicked

	@FXML
	public void productsNewSaleClick(ActionEvent event) throws IOException{
		Parent newSaleRootGUI = FXMLLoader.load(getClass().getResource("/application/NewSaleGUI.fxml"));
		Scene newSaleSceneGUI = new Scene(newSaleRootGUI);
		mainStage = (Stage) productsHomeButton.getScene().getWindow();
		mainStage.setScene(newSaleSceneGUI);

	}
	//This is used to show the NewSaleGUI when the New Sale button is clicked

	@FXML
	public void  productsOrdersClick(ActionEvent event) throws IOException{
		Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
		Scene ordersSceneGUI = new Scene(ordersRootGUI);
		mainStage = (Stage) productsHomeButton.getScene().getWindow();
		mainStage.setScene(ordersSceneGUI);

	}
	//This is used to show the OrdersGUI when the Orders button is clicked

	@FXML
	public void  productsProductsClick(ActionEvent event) throws IOException{
		Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
		Scene productsSceneGUI = new Scene(productsRootGUI);
		mainStage = (Stage) productsHomeButton.getScene().getWindow();
		mainStage.setScene(productsSceneGUI);

	}
	//This is used to show the ProductsGUI when the Products button is clicked

	@FXML
	public void  saveButtonClick(ActionEvent event) throws IOException{
		String[] newproduct = new String[3];
		//This is used to save the values of
		//the new product entered
		String Price;
		//This is used to save the price entered
		String Cost;
		//This is used to save the cost entered
		boolean decimalcheckerprice = true;
		//This is used to check for decimals in price
		boolean periodcheckerprice = true;
		//This is used to check for a period in price
		boolean pricesigncheckerprice = true;
		//This is used to check for a "$" sign in price
		boolean decimalcheckercost = true;
		//This is used to check for decimals in cost
		boolean periodcheckercost = true;
		//This is used to check for a period in cost
		boolean pricesigncheckercost = true;
		//This is used to check for a "$" sign in cost

		boolean nullchecker = false;
		//This is used to check if a value is null
		boolean numberchecker = false;
		//This is used to check if a value is a number or not

		if (newProductEntry.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the product name entered is null
		if (newProductPrice.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the product price entered is null
		if (newProductCost.getText().trim().isEmpty() == true){
			nullchecker = true;
		}
		//This is used to check if the product cost entered is null
		if (nullchecker == true){
			warningText.setText("Some items were left blank. Fill them to proceed.");
		}
		//If one of the values is null, the user is informed
		//that the values have to be filled to proceed

		if (nullchecker == false){
			try{
				double test = Double.parseDouble(newProductPrice.getText().replaceAll("[$]", ""));
			}catch (NumberFormatException e) {
				numberchecker = true;
				warningText.setText("Price and Cost should be numbers");
			}

			try{
				double test = Double.parseDouble(newProductCost.getText().replaceAll("[$]", ""));
			}catch (NumberFormatException e) {
				numberchecker = true;
				warningText.setText("Price and Cost should be numbers");
			}
		}
		//If price or cost are not numbers, the user are informed
		//that both should be numbers

		if (nullchecker == false && numberchecker == false){

			newproduct[0] = newProductEntry.getText();

			Price = newProductPrice.getText();

			decimalcheckerprice = (Price.contains(".00"));
			periodcheckerprice = (Price.contains("."));
			pricesigncheckerprice = (Price.contains("$"));

			if ((decimalcheckerprice == false) && (periodcheckerprice == false)){
				Price = (Price + ".00");
			}

			if (pricesigncheckerprice == false){
				Price = ("$" + Price);
			}

			newproduct [1] = Price;

			Cost = newProductCost.getText();

			decimalcheckercost = (Cost.contains(".00"));
			periodcheckercost= (Cost.contains("."));
			pricesigncheckercost = (Cost.contains("$"));

			if ((decimalcheckercost == false) && (periodcheckercost == false)){
				Cost = (Cost + ".00");
			}

			if (pricesigncheckercost == false){
				Cost = ("$" + Cost);
			}

			newproduct [2] = Cost;

			Main.products.add(newproduct);

			warningText.setText("");


			Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
			Scene productsSceneGUI = new Scene(productsRootGUI);
			mainStage = (Stage) productsHomeButton.getScene().getWindow();
			mainStage.setScene(productsSceneGUI);
		}
		//The new product entry entered by the user is added
		//to the products list, and the table is refreshed

	}

	@FXML
	public void  deleteButtonClick(ActionEvent event) throws IOException{
		String Productname = deleteProductEntry.getText();
		//This is used to save the name of the product
		//the user wants to be deleted

		String Productnamelower = Productname.toLowerCase();
		//This is used to change the name to lower case
		//so matches can be found no matter the way
		//they were entered

		Main.deleteproduct(Productnamelower);
		//deleteproduct is invoked while sending the name
		//of the product that the user wants to be delivered

		if (Main.productchecker == true){

			Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
			Scene productsSceneGUI = new Scene(productsRootGUI);
			mainStage = (Stage) productsHomeButton.getScene().getWindow();
			mainStage.setScene(productsSceneGUI);

			warningText.setText("");
		}
		//If a product was deleted, then the table is
		//refrehed to show the changes

		if (Main.productchecker == false){
			warningText.setText("Name not found");
		}
		//If no matches were found, the user is informed

	}

	@FXML
	public void  editLineButtonClick(ActionEvent event) throws IOException{
		selectedline = productsTable.getSelectionModel().getSelectedIndex();
		//The index of the selected line is extracted

		if (selectedline != -1){
			Parent ordersEditRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsEditGUI.fxml"));
			Scene ordersEditSceneGUI = new Scene(ordersEditRootGUI);
			mainStage = (Stage) productsHomeButton.getScene().getWindow();
			mainStage.setScene(ordersEditSceneGUI);
			//The table is refreshed

			warningText.setText("");
		}
		//If a line was selected, the OrdersEditGUI
		//is loaded

		if (selectedline == -1){
			warningText.setText("Please select a line to edit");
		}
		//If a line was not selected, the user is informed that
		//a line needs to be selected so that it can be edited

	}

	@FXML
	public void  deleteLineButtonClick(ActionEvent event) throws IOException{
		selectedline = productsTable.getSelectionModel().getSelectedIndex();
		//The index of the selected line is extracted

		if (selectedline != -1){
			Main.deleteproductsline(selectedline);
			//The deletproductsline method is invoked, using
			//the selectedline value that is sent to it

			Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
			Scene productsSceneGUI = new Scene(productsRootGUI);
			mainStage = (Stage) productsHomeButton.getScene().getWindow();
			mainStage.setScene(productsSceneGUI);
			//The table is refreshed

			warningText.setText("");
		}

		if (selectedline == -1){
			warningText.setText("Please select a line to delete");
		}
		//If a line was not selected, the user is informed that
		//a line needs to be selected so that it can be edited

	}



}