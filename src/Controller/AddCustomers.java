package Controller;

import DBConnection.DBQuery;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Controller.Customers.getAllCustomers;
import static javafx.collections.FXCollections.*;

public class AddCustomers implements Initializable {

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<Customer, Integer> customerIDCol;

    @FXML
    private TableColumn<Customer, String> customerNameCol;

    @FXML
    private TableColumn<Customer, Integer> addressIDCol;

    @FXML
    private TableColumn<Customer, Boolean> activeCol;

    @FXML
    private TableColumn<Customer, Date> createdDateCol;

    @FXML
    private TableColumn<Customer, String> createdByCol;

    @FXML
    private TableColumn<Customer, Timestamp> lastUpdateCol;

    @FXML
    private TableColumn<Customer, String> lastUpdatedByCol;


    @FXML
    private TextField customerIdField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField addressIdField;

    @FXML
    private ChoiceBox<String> activeCB;

    @FXML
    private HBox createdDateField;

    @FXML
    private TextField createdByField;

    @FXML
    private TextField lastUpdateField;

    @FXML
    private TextField lastUpdatedByField;

    Statement statement = DBQuery.getStatement();

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

    public void saveCustomer(ActionEvent event) throws IOException, SQLException {
        String customerName = customerNameField.getText();
        String addressID = String.valueOf(Integer.parseInt(addressIdField.getText()));
        String active = "1";
        String createDate = String.valueOf(new Date(System.currentTimeMillis()));;
        String createdBy = LogIn.getUsername();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));;
        String lastUpdateBy = lastUpdatedByField.getText();

        String insertStatement = "INSERT INTO customers(customerName, addressID, active, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + customerName + "'," +
                "'" + addressID + "'," +
                "'" + active + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "'" +
                ");";

        statement.execute(insertStatement);
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Customers.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Customers");
        window.show();
    }

    public void goToCustomer(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Customers.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Customers");
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList choiceBox = FXCollections.observableArrayList();
        choiceBox.addAll("True", "False");
        activeCB.setItems(choiceBox);

        customersTable.getSortOrder().setAll();

        //set up initial values in table
        customersTable.setItems(getAllCustomers());

        //Setup PartTable
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressIDCol.setCellValueFactory(new PropertyValueFactory<>("addressId"));
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
    }
}
