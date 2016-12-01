//COPYRIGHT (C) 2015 Jaime Gonzalez. All Rights Reserved.

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Reader;
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

public class OrdersController implements Initializable {
	public Stage mainStage;
	//This is what allows the Stage to load
	public Stage stageAux = new Stage();
	//This helps in loading the Stage to load
	public static Scene mainScene;
	//This is what allows the scene to load
	public static int selectedline;
	//This is used to save the index of the line selected by the user

	private ObservableList<Reader> data = FXCollections.observableArrayList();
	//This is used to save the values of the table

	@FXML
	private Button ordersHomeButton;
	//This is used to define the homeButton

	@FXML
	private Button ordersOrdersButton;
	//This is used to define the ordersButton

	@FXML
	private Button ordersNewSaleButton;
	//This is used to define the newSaleButton

	@FXML
	private Button deliveryChangeButton;
	//This used to define the change to delivered button

	@FXML
	private Button ordersProductsButton;
	//This is used to define the productsButton

	@FXML
	private Button editLineButton;
	//This is used to define the edit line button

	@FXML
	private Button deleteLineButton;
	//This is used to define the delete line button

	@FXML
	private TableView<Reader> dataTable;
	//This is used to define the table containing the orders

	@FXML
	private TableColumn<Reader, String> product;
	//This is used to define the product column

	@FXML
	private TableColumn<Reader, String> size;
	//This is used to define the size column

	@FXML
	private TableColumn<Reader, String> client;
	//This is used to define the client column

	@FXML
	private TableColumn<Reader, String> date;
	//This is used to define the date column

	@FXML
	private TableColumn<Reader, String> quantity;
	//This is used to define the quantity column

	@FXML
	private TableColumn<Reader, String> paid;
	//This is used to define the paid column

	@FXML
	private TableColumn<Reader, String> delivered;
	//This is used to define the delivered column

	@FXML
	private TextField clientNameDelivered;
	//This is used to define the Client Name text field

	@FXML
	private Text warningText;
	//This is used to define the warningText text

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		product.setCellValueFactory(new PropertyValueFactory<Reader, String>("product"));
		//This sets the Cell Value Factory of product to "product"
		size.setCellValueFactory(new PropertyValueFactory<Reader, String>("size"));
		//This sets the Cell Value Factory of size to "size"
		client.setCellValueFactory(new PropertyValueFactory<Reader,String>("client"));
		//This sets the Cell Value Factory of client to "client"
		date.setCellValueFactory(new PropertyValueFactory<Reader, String>("date"));
		//This sets the Cell Value Factory of date to "date"
		quantity.setCellValueFactory(new PropertyValueFactory<Reader, String>("quantity"));
		//This sets the Cell Value Factory of quantity to "quantity"
		paid.setCellValueFactory(new PropertyValueFactory<Reader,String>("paid"));
		//This sets the Cell Value Factory of paid to "paid"
		delivered.setCellValueFactory(new PropertyValueFactory<Reader, String>("delivered"));
		//This sets the Cell Value Factory of delivered to "delivered"

		dataTable.setEditable(true);
		//This allows dataTable to be editable

		for (int i = 0; i < Main.orders.size(); i++){
			if (i != 0){
				data.add(new Reader(Main.orders.get(i)[0], Main.orders.get(i)[1], Main.orders.get(i)[2], Main.orders.get(i)[3], Main.orders.get(i)[4], Main.orders.get(i)[5], Main.orders.get(i)[6]));
			}
		}
		//This is used so that that data in the orders list is loaded onto data

		dataTable.setItems(data);
		//This is used so that the table loads the values in data
	}


	@FXML
	public void ordersHomeClick(ActionEvent event) throws IOException{
		Parent homeRootGUI = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		Scene homeSceneGUI = new Scene(homeRootGUI);
		mainStage = (Stage) ordersHomeButton.getScene().getWindow();
		mainStage.setScene(homeSceneGUI);

	}
	//This is used to show the MainGUI when the Home button is clicked

	@FXML
	public void ordersNewSaleClick(ActionEvent event) throws IOException{
		Parent newSaleRootGUI = FXMLLoader.load(getClass().getResource("/application/NewSaleGUI.fxml"));
		Scene newSaleSceneGUI = new Scene(newSaleRootGUI);
		mainStage = (Stage) ordersHomeButton.getScene().getWindow();
		mainStage.setScene(newSaleSceneGUI);

	}
	//This is used to show the NewSaleGUI when the New Sale button is clicked

	public void  ordersOrdersClick(ActionEvent event) throws IOException{
		Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
		Scene ordersSceneGUI = new Scene(ordersRootGUI);
		mainStage = (Stage) ordersHomeButton.getScene().getWindow();
		mainStage.setScene(ordersSceneGUI);

	}
	//This is used to show the OrdersGUI when the Orders button is clicked

	@FXML
	public void  ordersProductsClick(ActionEvent event) throws IOException{
		Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
		Scene productsSceneGUI = new Scene(productsRootGUI);
		mainStage = (Stage) ordersHomeButton.getScene().getWindow();
		mainStage.setScene(productsSceneGUI);

	}
	//This is used to show the ProductsGUI when the Products button is clicked

	public void  deliveryChangeButtonClick(ActionEvent event) throws IOException{
		String Clientname = clientNameDelivered.getText();
		//This is used to extract and save the entered client name

		String Clientnamelower = Clientname.toLowerCase();
		//This is used to convert the name to lower case so that
		//the names can match no matter what way they were entered

		Main.changedelivered(Clientnamelower);
		//This invokes the changedelivered method, using
		//the Clientnamelower that is sent to it

		if (Main.namechecker == true){
			Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
			Scene ordersSceneGUI = new Scene(ordersRootGUI);
			mainStage = (Stage) ordersHomeButton.getScene().getWindow();
			mainStage.setScene(ordersSceneGUI);
			warningText.setText("");
		}
		//If a match for the name was found, the table
		//is refreshed

		if (Main.namechecker == false){
			warningText.setText("Name not found");
		}
		//If a match for the name was not found, the user
		//is informed that no match was found

	}

	public void  deleteDeliveredButtonClick(ActionEvent event) throws IOException{
		Main.deletedelivered();
		//The delete delivered method is invoked

		Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
		Scene ordersSceneGUI = new Scene(ordersRootGUI);
		mainStage = (Stage) ordersHomeButton.getScene().getWindow();
		mainStage.setScene(ordersSceneGUI);
		//The table is refreshed

	}

	public void  editLineButtonClick(ActionEvent event) throws IOException{
		selectedline = dataTable.getSelectionModel().getSelectedIndex();
		//The index of the selected line is extracted

		if (selectedline != -1){
			Parent ordersEditRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersEditGUI.fxml"));
			Scene ordersEditSceneGUI = new Scene(ordersEditRootGUI);
			mainStage = (Stage) ordersHomeButton.getScene().getWindow();
			mainStage.setScene(ordersEditSceneGUI);

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

	public void  deleteLineButtonClick(ActionEvent event) throws IOException{
		selectedline = dataTable.getSelectionModel().getSelectedIndex();
		//The index of the selected line is extracted

		if (selectedline != -1){
			Main.deleteordersline(selectedline);
			//The deleteordersline method is invoked, using
			//the selectedline value that is sent to it

			Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
			Scene ordersSceneGUI = new Scene(ordersRootGUI);
			mainStage = (Stage) ordersHomeButton.getScene().getWindow();
			mainStage.setScene(ordersSceneGUI);
			//The table is refreshed

			warningText.setText("");
		}
		//If a line was selected, the selected line is deleted and
		//the table is refreshed to show the changes


		if (selectedline == -1){
			warningText.setText("Please select a line to delete");
		}
		//If a line was not selected, the user is informed that
		//a line needs to be selected so that it can be edited
	}






}