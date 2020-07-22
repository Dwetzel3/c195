package Controller;

import DBConnection.DBQuery;
import Model.Country;
import Model.Customer;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Controller.Customers.addNewCustomer;
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
    private ChoiceBox<Integer> activeCB;


    @FXML
    private TextField address;

    @FXML
    private TextField address2;

    @FXML
    private TextField city;

    @FXML
    private TextField country;

    @FXML
    private TextField postal;

    @FXML
    private TextField phoneNumber;

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
        String active = activeCB.getValue().toString();
        String addressId = addressIdField.getText();
        String customerId = customerIdField.getText();
        String customerName = customerNameField.getText();
//        if (activeCB.getValue() == 0) {
//            active = "false";
//        }
        String createDate = String.valueOf(new Date(System.currentTimeMillis()));
        String createdBy = LogIn.getUsername();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String lastUpdateBy = LogIn.getUsername();
        String address1 = address.getText();
        String addressTwo = address2.getText();
        String cityAdd = city.getText();
        String countryAdd = country.getText();
        String countryId = String.valueOf(country.getText());
        String postalAdd = postal.getText();
        String phoneNumberAdd = phoneNumber.getText();

        String insertCustomer = "INSERT INTO customer(addressId, customerName, active, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + addressId + "'," +
                "'" + customerName + "'," +
                "'" + active + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "');";

        String insertAddress = "INSERT INTO address(address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + address1 + "'," +
                "'" + addressTwo + "'," +
                "'" + cityAdd + "'," +
                "'" + postalAdd + "'," +
                "'" + phoneNumberAdd + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "');";

        String insertCity = "INSERT INTO city(city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + cityAdd + "'," +
                "'" + countryId + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "');";

        String insertCountry = "INSERT INTO country(countryId, country, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + countryId + "'," +
                "'" + countryAdd + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "'" +
                ");";
        Customer customer = new Customer(Integer.parseInt(customerId), customerName, Integer.valueOf(addressId), Boolean.valueOf(active), Date.valueOf(createDate), createdBy, Timestamp.valueOf(lastUpdate), lastUpdateBy);
        Customers.addNewCustomer(customer);

        statement.execute(insertCountry);
        statement.execute(insertCity);
        statement.execute(insertAddress);
        statement.execute(insertCustomer);
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

    public static Country getCountry(
            int countryId
    ) {
        try (Connection conn = DriverManager.getConnection(LogIn.jdbcURL, LogIn.username, LogIn.password);
             Statement stmt = conn.createStatement()) {

            String query = String.format(""
                            + "SELECT * "
                            + "FROM country c "
                            + "WHERE c.countryId "
                            + "= %d "
                            + "LIMIT 1",
                    countryId
            );

            ResultSet country
                    = stmt.executeQuery(query);

            if (country.next()) {
                Country countryFromDatabase;

                int id = country.getInt("countryId");
                String name = country.getString("country");
                Timestamp createDate = country.getTimestamp("createDate");
                String createdBy = country.getString("createdBy");
                String lastUpdateBy = country.getString("lastUpdateBy");

                countryFromDatabase = new Country(id, name, createDate,
                        createdBy, lastUpdateBy);

                return countryFromDatabase;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList choiceBox = FXCollections.observableArrayList();
        choiceBox.addAll(0, 1);
        activeCB.setItems(choiceBox);

        customersTable.getSortOrder().setAll();

        //set up initial values in table
        customersTable.setItems(getAllCustomers());

        //Setup customer table
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
