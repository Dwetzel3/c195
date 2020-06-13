package Controller;

import DBConnection.DBQuery;
import Model.Customer;
import com.sun.javaws.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Customers implements Initializable {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<Customer, Integer> customerID;

    @FXML
    private TableColumn<Customer, String> customerName;

    @FXML
    private TableColumn<Customer, Integer> addressID;

    @FXML
    private TableColumn<Customer, Boolean> active;

    @FXML
    private TableColumn<Customer, Date> createdDateCol;

    @FXML
    private TableColumn<Customer, String> createdByCol;

    @FXML
    private TableColumn<Customer, Timestamp> lastUpdate;

    @FXML
    private TableColumn<Customer, String> lastUpdatedBy;

    static String countryName = "Canada";
    static String createDate = "2020-06-06 00:00:00";
    static String createdBy = "admin";
    static String lastUpdateBy = "admin";

    static String insertStatement = "INSERT INTO customers(customerName, addressID, active, createdBy, lastUpdateBy)" +
            "VALUES(" +
            "'" + countryName + "'," +
            "'" + createDate + "'," +
            "'" + createdBy + "'," +
            "'" + lastUpdateBy + "'" +
            ");";

    int countryId = 0;

    static Statement statement = DBQuery.getStatement();

    String deleteAll = "DELETE FROM customers WHERE customerId >= " + 0 + ";";

    //String insertStatement = "INSERT INTO country(country, createDate, createdBy, lastUpdateBy) VALUES('US', '2020-06-06 00:00:00', 'admin', 'admin')";


//    public void addCustomer(ActionEvent event) {
//        try {
//            statement.execute(insertStatement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
public void addCustomer(ActionEvent event) throws IOException {
    Parent projectParent = FXMLLoader.load(getClass().getResource("../View/AddCustomers.fxml"));
    Scene projectScene = new Scene(projectParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(projectScene);
    window.setTitle("Add Customer");
    window.show();
}

    public static void addNewCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

    public void deleteCustomer(ActionEvent event) {
        try {
            statement.execute(deleteAll);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goToUpdateCustomer(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/UpdateCustomer.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Update Customer");
        window.show();
    }
    public void goToMain(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Calendar.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Calendar");
        window.show();
    }

    public void saveCustomer(ActionEvent event) {
    }

    public void goToCustomer(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Customers.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Customers");
        window.show();
    }

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//sort TableViews
        customersTable.getSortOrder().setAll();

        //set up initial values in table
        customersTable.setItems(getAllCustomers());

        //Setup PartTable
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressID.setCellValueFactory(new PropertyValueFactory<>("addressId"));
        active.setCellValueFactory(new PropertyValueFactory<>("active"));
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
    }
}
