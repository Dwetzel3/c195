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
        int selectedCustomer = customersTable.getSelectionModel().getSelectedItems().get(0).getCustomerID();

        String deleteSelected = "DELETE FROM customers WHERE customerId = " + selectedCustomer + ";";
        //String insertStatement = "INSERT INTO country(country, createDate, createdBy, lastUpdateBy) VALUES('US', '2020-06-06 00:00:00', 'admin', 'admin')";

        try {
            statement.execute(deleteSelected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customersTable.refresh();
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
        Connection conn = LogIn.conn;
        try {
            DBQuery.setStatement(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = DBQuery.getStatement();

        // Update statement
        String updateStatement = "UPDATE country SET country = 'Japan' WHERE country = 'Canada'";

        try {
            statement.execute(updateStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // confirm rows affected
        try {
            if (statement.getUpdateCount() > 0) {
                System.out.println(statement.getUpdateCount() + " rows affected.");
            } else {
                System.out.println("No change.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int thisInt = 5;
        String search = "SELECT FROM country WHERE customerId = " + thisInt + "";
        ResultSet results = null;
        try {
            results = statement.executeQuery("SELECT * FROM customers");
        } catch (SQLException e) {
            e.printStackTrace();
        }


// For each row of the result set ...

        while (true) {
            try {
                if (!results.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }


            int customerId = 0;
            try {
                customerId = Integer.parseInt(results.getString("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String customerName = null;
            try {
                customerName = results.getString("customerName");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int addressID = 0;
            try {
                addressID = Integer.parseInt(results.getString("addressID"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Boolean active = null;
            try {
                active = Boolean.valueOf(results.getString("active"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Date createDate = null;
            try {
                createDate = Date.valueOf(results.getString("createDate"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String createdBy = null;
            try {
                createdBy = results.getString("createdBy");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Timestamp lastUpdate = null;
            try {
                lastUpdate = Timestamp.valueOf(results.getString("lastUpdate"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String lastUpdateBy = null;
            try {
                lastUpdateBy = results.getString("lastUpdateBy");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Customer customer = new Customer(customerId, customerName, addressID, active, createDate, createdBy, lastUpdate, lastUpdateBy);
            Customers.addNewCustomer(customer);
            // Get the countryId from the current row using the column name - column countryId are in the VARCHAR format

//            customerName = results.getString("customerId");



        }
        customersTable.getSortOrder().setAll();
        //set up initial values in table
        customersTable.setItems(getAllCustomers());

        //Setup PartTable
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressID.setCellValueFactory(new PropertyValueFactory<>("addressID"));
        active.setCellValueFactory(new PropertyValueFactory<>("active"));
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
    }
}
