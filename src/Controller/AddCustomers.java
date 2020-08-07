package Controller;

import DBConnection.DBQuery;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static Controller.Customers.*;

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
    private ChoiceBox<Boolean> activeCB;

    @FXML
    private TextField customerNameField;

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
        boolean valid = true;
        Boolean newCountry = true;
        Boolean newCity = true;
        Boolean newAddress = true;
        Boolean newCustomer = true;
        Boolean newUser = true;


        ResultSet DBCustomer = statement.executeQuery("SELECT * FROM customer;");
        while (DBCustomer.next()) {
            String DBCustomerId = DBCustomer.getString("customerId");
            String DBCustomerName = DBCustomer.getString("customerName");
            System.out.println(DBCustomerName + " is under ID: " + DBCustomerId);
        }

        /**
         * Gets countryId Count
         */
        int countryCounter = 0;
        ResultSet countryCount = statement.executeQuery("SELECT *" +
                " FROM country;");
        while (countryCount.next()) {
            countryCounter = countryCount.getRow();
        }
        System.out.println("Country has " + countryCounter + " row(s).");
        countryCount.close();



        /**
         * Gets cityId Count
         */
        int cityCounter = 0;
        ResultSet cityCount = statement.executeQuery("SELECT *" +
                " FROM city;");
        while (cityCount.next()) {
            cityCounter = cityCount.getRow();
        }
        System.out.println("City has " + cityCounter + " row(s).");
        cityCount.close();



        /**
         * Gets userId Count
         */
        int userCounter = 0;
        ResultSet userCount = statement.executeQuery("SELECT *" +
                " FROM user;");
        while (userCount.next()) {
            userCounter = userCount.getRow();
        }
        System.out.println("User has " + userCounter + " row(s).");
        userCount.close();



        /**
         * Gets addressId Count
         */
        int addressCounter = 0;
        ResultSet addressCount = statement.executeQuery("SELECT *" +
                " FROM address;");
        while (addressCount.next()) {
            addressCounter = addressCount.getRow();
        }
        System.out.println("Address has " + addressCounter + " row(s).");
        addressCount.close();



        /**
         * Gets customerId Count
         */
        int customerCounter = 0;
        ResultSet customerCount = statement.executeQuery("SELECT *" +
                " FROM customer;");
        while (customerCount.next()) {
            customerCounter = customerCount.getRow();
        }
        System.out.println("Customer has " + customerCounter + " row(s).");
        customerCount.close();

        Integer newActive = null;
        if (activeCB.getValue() == false) {
            newActive = 0;
        } else if (activeCB.getValue() == true) {
            newActive = 1;
        }
        String active = newActive.toString();

        String customerId = String.valueOf(getAllCustomers().get(customerCounter - 1).getCustomerID() + 1);
        String customerName = customerNameField.getText();
        String createDate = String.valueOf(new Date(System.currentTimeMillis()));
        String createdBy = LogIn.getUsername();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String lastUpdateBy = LogIn.getUsername();
        String address1 = address.getText();
        String addressTwo = address2.getText();
        String cityName = city.getText();

        String addressId = String.valueOf(addressCounter + 1);
        ResultSet DBaddress = statement.executeQuery("SELECT * FROM address;");
        while (DBaddress.next()) {
            String DBaddressId = DBaddress.getString("addressId");
            String DBaddressName = DBaddress.getString("address");
            if (address.getText().compareTo(DBaddress.getString("address")) == 0) {
                System.out.println(DBaddressName + " already exists under ID: " + DBaddressId + "!");
                newAddress = false;
                addressId = String.valueOf(DBaddress.getString("addressId"));
            }
        }

        String countryName = country.getText();
        String countryId = String.valueOf(countryCounter + 1);
        ResultSet DBCountry = statement.executeQuery("SELECT * FROM country;");
        while (DBCountry.next()) {
            String DBCountryId = DBCountry.getString("countryId");
            String DBCountryName = DBCountry.getString("country");
            if (country.getText().compareTo(DBCountry.getString("country")) == 0) {
                System.out.println(DBCountryName + " already exists under ID: " + DBCountryId + "!");
                newCountry = false;
                countryId = String.valueOf(DBCountry.getString("countryId"));
            }
        }

        String cityId = String.valueOf(cityCounter + 1);
        ResultSet DBCity = statement.executeQuery("SELECT * FROM city;");
        while (DBCity.next()) {
            String DBCityId = DBCity.getString("cityId");
            String DBCityName = DBCity.getString("city");
            if ((city.getText().compareTo(DBCity.getString("city")) == 0) && !newCountry) {
                System.out.println(DBCityName + " already exists under ID: " + DBCityId + "!");
                newCity = false;
                cityId = String.valueOf(DBCity.getString("cityId"));
            }
        }


        String postalAdd = postal.getText();
        String phoneNumberAdd = phoneNumber.getText();

        String userId = String.valueOf(userCounter + 1);
        ResultSet DBUser = statement.executeQuery("SELECT * FROM user;");
        while (DBUser.next()) {
            String DBUserId = DBUser.getString("userId");
            String DBUserName = DBUser.getString("userName");
            if ((LogIn.username.compareTo(DBUser.getString("userName")) == 0)) {
                System.out.println(DBUserName + " already exists under ID: " + DBUserId + "!");
                newUser = false;
                userId = String.valueOf(DBUser.getString("userId"));
            }
        }

        String userName = User.getUsername();
        String password = User.getPassword();

        /**
         * Insert into table
         */

        String insertUser = "INSERT INTO user(userId, userName, password, active, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + userId + "'," +
                "'" + userName + "'," +
                "'" + password + "'," +
                "'" + active + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "');";

        String insertCustomer = "INSERT INTO customer(customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + customerId + "'," +
                "'" + customerName + "'," +
                "'" + addressId + "'," +
                "'" + active + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "');";

        String insertAddress = "INSERT INTO address(addressId, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + addressId + "'," +
                "'" + address1 + "'," +
                "'" + addressTwo + "'," +
                "'" + cityId + "'," +
                "'" + postalAdd + "'," +
                "'" + phoneNumberAdd + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "');";

        String insertCity = "INSERT INTO city(cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + cityId + "'," +
                "'" + cityName + "'," +
                "'" + countryId + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "');";

        String insertCountry = "INSERT INTO country(countryId, country, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + countryId + "'," +
                "'" + countryName + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "'" +
                ");";

        /**
         * Checks if forms are completed
         */

        if (customerNameField == null ||
        address.getText().isEmpty() ||
        address2.getText().isEmpty() ||
        city.getText().isEmpty() ||
        country.getText().isEmpty() ||
        postal.getText().isEmpty() ||
        phoneNumber.getText().isEmpty() ||
        activeCB.getValue() == null) {
            Alert nullFields = new Alert(Alert.AlertType.WARNING);
            nullFields.setTitle("Incomplete Data");
            nullFields.setContentText("Please check all forms for missing data.");
            nullFields.showAndWait();
            valid = false;
        }

        /**
         * Checks for non-conforming data
         */

        try {
            Integer.parseInt(phoneNumber.getText());
            Integer.parseInt(postal.getText());
        } catch (NumberFormatException e) {
            valid = false;
            Appointments.alertType();
        }




        if (valid) {
            Customer customer = new Customer(Integer.parseInt(customerId), customerName, Integer.valueOf(addressId), newActive, Date.valueOf(createDate), createdBy, Timestamp.valueOf(lastUpdate), lastUpdateBy);
            addNewCustomer(customer);

            if (newUser) {
                statement.execute(insertUser);
            }
            if (newCountry) {
                statement.execute(insertCountry);
            }
            if (newCity) {
                statement.execute(insertCity);
            }
            if (newAddress) {
                statement.execute(insertAddress);
            }
            if (newCustomer) {
                statement.execute(insertCustomer);
            }
        }

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
        choiceBox.addAll(false, true);
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
