//COPYRIGHT (C) 2015 Jaime Gonzalez. All Rights Reserved.

package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import application.Main;
import application.ReaderMain;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController implements Initializable {
	public Stage mainStage;
	//This is what allows the Stage to load
	public Stage stageAux = new Stage();
	//This helps in loading the Stage to load
	public static Scene mainScene;
	//This is what allows the scene to load
	double revenuedouble = 0;
	//This is used to save the revenue value
	double cost = 0;
	//This is used to save the cost value
	double profit = 0;
	//This is used to save the profit value

	private ObservableList<ReaderMain> data = FXCollections.observableArrayList();
	//This is used to save the values of the table

	@FXML
	private Button homeButton;
	//This is used to define the homeButton
	@FXML
	private Button ordersButton;
	//This is used to define the ordersButton
	@FXML
	private Button newSaleButton;
	//This is used to define the newSaleButton
	@FXML
	private Button productsButton;
	//This is used to define the productsButton
	@FXML
	private Button saveValuesButton;
	//This is used to define the saveValuesButton
	@FXML
	private Button resetTableButton;
	//This is used to define the resetTableButton
	@FXML
	private Text revenueText;
	//This is used to define the revenueText
	@FXML
	private Text profitText;
	//This is used to define the profitText
	@FXML
	private Text totalRevenueText;
	//This is used to define the totalRevenueText
	@FXML
	private Text totalProfitText;
	//This is used to define the totalProfitText
	@FXML
	private TableView<ReaderMain> revenueprofitTable;
	//This is used to define the revenueprofitTable
	@FXML
	private TableColumn<ReaderMain, String> revenuecolumn;
	//This is used to define the revenuecolumn
	@FXML
	private TableColumn<ReaderMain, String> profitcolumn;
	//This is used to define the profitcolumn
	@FXML
	private TableColumn<ReaderMain, String> datecolumn;
	//This is used to define the datecolumn

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		revenuecolumn.setCellValueFactory(new PropertyValueFactory<ReaderMain, String>("revenue"));
		//This sets the Cell Value Factory of revenuecolumn to "revenue"
		profitcolumn.setCellValueFactory(new PropertyValueFactory<ReaderMain, String>("profit"));
		//This sets the Cell Value Factory of profitcolumn to "profit"
		datecolumn.setCellValueFactory(new PropertyValueFactory<ReaderMain, String>("date"));
		//This sets the Cell Value Factory of date to "date"

		revenueprofitTable.setEditable(true);
		//This allows revenueprofitTable to be editable

		for (int i = 0; i < Main.revenueprofit.size(); i++){
			if (i != 0){
				data.add(new ReaderMain(Main.revenueprofit.get(i)[0], Main.revenueprofit.get(i)[1], Main.revenueprofit.get(i)[2]));

			}
		}
		//This is used so that that data in the revenue
		//and profit list is loaded onto data

		revenueprofitTable.setItems(data);
		//This is used so that the table loads the values in data

		revenuedouble = Main.addrevenue();
		//This is used to extract the total revenue of the orders
		//from the method in Main
		revenueText.setText("$" + revenuedouble);
		//This is used to display the total revenue of the orders
		//in the Main GUI using revenueText and a $ sign

		cost = Main.addcost();
		//This is used to extract the total cost of the orders
		//from the method in Main

		profit = revenuedouble - cost;
		//This is used to calculate the total profit by subtracting
		//the total revenue minus the total cost

		profitText.setText("$" + profit);
		//This is used to display the total profit of the orders
		//in the Main GUI using profitText and a $ sign

		double totalrevenuedouble;
		//This is used to save the total revenue from the Revenue
		//and profit list
		double totalprofitdouble;
		//This is used to save the total profit from the Revenue
		//and profit list

		totalrevenuedouble = Main.addtotalrevenue();
		//This extracts the total revenue from the Revenue and
		//Profit list and saves it totalrevenuedouble
		totalprofitdouble = Main.addtotalprofit();
		//This extracts the total profit from the Revenue and
		//Profit list and saves it totalprofitdouble

		totalRevenueText.setText("$" + totalrevenuedouble);
		//This is used to display the total revenue of the Revenue and
		//Profit list in the Main GUI using totalRevenueText and a $ sign
		totalProfitText.setText("$" + totalprofitdouble);
		//This is used to display the total profit of the Revenue and
		//Profit list in the Main GUI using totalProfitText and a $ sign

	}

	@FXML
	public void homeClick(ActionEvent event) throws IOException{
		Parent homeRootGUI = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		Scene homeSceneGUI = new Scene(homeRootGUI);
		mainStage = (Stage) homeButton.getScene().getWindow();
		mainStage.setScene(homeSceneGUI);

	}
	//This is used to show the MainGUI when the Home button is clicked

	@FXML
	public void newSaleClick(ActionEvent event) throws IOException{
		Parent newSaleRootGUI = FXMLLoader.load(getClass().getResource("/application/NewSaleGUI.fxml"));
		Scene newSaleSceneGUI = new Scene(newSaleRootGUI);
		mainStage = (Stage) homeButton.getScene().getWindow();
		mainStage.setScene(newSaleSceneGUI);

	}
	//This is used to show the NewSaleGUI when the New Sale button is clicked

	@FXML
	public void  ordersClick(ActionEvent event) throws IOException{
		Parent ordersRootGUI = FXMLLoader.load(getClass().getResource("/application/OrdersGUI.fxml"));
		Scene ordersSceneGUI = new Scene(ordersRootGUI);
		mainStage = (Stage) homeButton.getScene().getWindow();
		mainStage.setScene(ordersSceneGUI);

	}
	//This is used to show the OrdersGUI when the Orders button is clicked

	@FXML
	public void  productsClick(ActionEvent event) throws IOException{
		Parent productsRootGUI = FXMLLoader.load(getClass().getResource("/application/ProductsGUI.fxml"));
		Scene productsSceneGUI = new Scene(productsRootGUI);
		mainStage = (Stage) homeButton.getScene().getWindow();
		mainStage.setScene(productsSceneGUI);

	}
	//This is used to show the ProductsGUI when the Products button is clicked

	@FXML
	public void  saveValuesButtonClick(ActionEvent event) throws IOException{
		String[] bRevenueProfit = new String[3];
		//This is used to save the Revenue and Profit values
		///onto the revenueprofit list

		bRevenueProfit[0] = ("$" + revenuedouble);
		//This is used to save the revenuedouble value onto the
		//bRevenueProfit array in the 0 position
		bRevenueProfit[1] = ("$" + profit);
		//This is used to save the profit value onto the
		//bRevenueProfit array in the 1 position
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
		LocalDate date = LocalDate.now();
		bRevenueProfit[2] = ((date.format(formatter)) + "");
		//This is used to save the date value onto the
		//bRevenueProfit array in the 2 position

		Main.revenueprofit.add(bRevenueProfit);
		//This adds the bRevenueProfit array onto the revenueprofit list

		Parent homeRootGUI = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		Scene homeSceneGUI = new Scene(homeRootGUI);
		mainStage = (Stage) homeButton.getScene().getWindow();
		mainStage.setScene(homeSceneGUI);
		//This reloads the current GUI

	}

	@FXML
	public void  resetTableButtonClick(ActionEvent event) throws IOException{

		Main.resettable();
		//This invokes the resettable method on Main

		Parent homeRootGUI = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		Scene homeSceneGUI = new Scene(homeRootGUI);
		mainStage = (Stage) homeButton.getScene().getWindow();
		mainStage.setScene(homeSceneGUI);
		//This reloads the current GUI

	}

	@FXML
	public void exitButtonClick(ActionEvent event) throws IOException{
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

		System.exit(0);
		//This exits the program
	}



}