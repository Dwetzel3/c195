package Controller;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import static Controller.Customers.*;


public class UpdateCustomer implements Initializable {
    public static Customer selectedCustomer;
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
    private TextField customerIdField ;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField addressIdField;

    @FXML
    private TextField getCustomerIdField;


    @FXML
    private ChoiceBox<Boolean> activeCB;


    @FXML
    private TextField address;

    @FXML
    private TextField address2;

    @FXML
    private TextField city;

    @FXML
    private TextField country;

    @FXML
    private Label postalCode;

    @FXML
    private TextField postal;

    @FXML
    private TextField phoneNumber;

    int cityId = 0;
    int countryId = 0;

    public void goToCustomer(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Customers.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Customers");
        window.show();
    }
    public void saveCustomer(ActionEvent event) throws IOException, SQLException {


        Boolean valid = true;
        String customerID = String.valueOf(Customers.getSelectedCustomer().getCustomerID());
        String customerName = customerNameField.getText();
        //        ResultSet DBaddress = statement.executeQuery("SELECT * FROM customer;");
//        while (DBaddress.next()) {
//            String DBaddressId = DBaddress.getString("addressId");
//            if (customerID.compareTo(DBaddress.getString("customerId")) == 0) {
//                address.setText(String.valueOf(Customers.getSelectedCustomer().getAddressID()));
//            }
//        }
        String addressId = String.valueOf(getSelectedCustomer().getAddressID());
        String active = String.valueOf(0);
        if (activeCB.getValue()) {
            active = String.valueOf(1);
        }
        String address1 = address.getText();
        String addressTwo = address2.getText();
        String cityName = city.getText();
        String postalAdd = postal.getText();
        String phoneNumberAdd = phoneNumber.getText();
//        String addressId = String.valueOf(statement.executeQuery("SELECT addressId FROM customer WHERE customerId = '" + customerID + "';"));

        String createDate = getSelectedCustomer().getCreatedDate().toString();
        String createdBy = getSelectedCustomer().getCreatedBy();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String lastUpdateBy = LogIn.getUsername();

        String UpdateCustomer = "UPDATE customer SET customerName = '" + customerName + "'," +
                "active = '" + active + "'," +
                "lastUpdate = '" + lastUpdate + "'," +
                "lastUpdateBy = '" + lastUpdateBy + "'" +
                " WHERE customerId = '" + customerID +
                "';";

        String UpdateAddress = "UPDATE address SET address = '" + address1 + "'," +
                "address2 = '" + addressTwo + "'," +
                "postalCode = '" + postalAdd + "'," +
                "phone = '" + phoneNumberAdd + "'," +
                "lastUpdate = '" + lastUpdate + "'," +
                "lastUpdateBy = '" + lastUpdateBy + "'" +
                " WHERE addressId = '" + addressId +
                "';";

        String UpdateCity = "UPDATE city SET city = '" + cityName + "'," +
                "lastUpdate = '" + lastUpdate + "'," +
                "lastUpdateBy = '" + lastUpdateBy + "'" +
                " WHERE cityId = '" + cityId +
                "';";

        String UpdateCountry = "UPDATE country SET country = '" + country.getText() + "'," +
                "lastUpdate = '" + lastUpdate + "'," +
                "lastUpdateBy = '" + lastUpdateBy + "'" +
                " WHERE countryId = '" + countryId +
                "';";

        /**
         * Checks to see that data is completely entered
         */

        if (customerName.isEmpty() ||
        active.isEmpty() ||
        createDate.isEmpty() ||
        createdBy.isEmpty() ||
        lastUpdate.isEmpty() ||
        lastUpdateBy.isEmpty()) {
            valid = false;
            Appointments.alertEmpty();
        }


        /**
         * Checks to see that data is of correct type
         */

        try {
            Integer.parseInt(postal.getText());
            Integer.parseInt(phoneNumber.getText());
        } catch (NumberFormatException e) {
            valid = false;
            Alert alertType = new Alert(Alert.AlertType.WARNING);
            alertType.setTitle("Non-Conforming Data");
            alertType.setContentText("Please check all forms for incompatible data types.");
            alertType.showAndWait();
        }

        /**
         * If valid, updates customer to database
         */

        if (valid) {
            Customer customer = new Customer(Integer.valueOf(customerID), customerName, Integer.valueOf(addressId), Integer.valueOf(active), Date.valueOf(createDate), createdBy, Timestamp.valueOf(lastUpdate), lastUpdateBy);
            int thisIndex = Customers.getAllCustomers().indexOf(getSelectedCustomer());
            Customers.updateCustomer(thisIndex, customer);

            statement.execute(UpdateCountry);

            statement.execute(UpdateCity);

            statement.execute(UpdateAddress);

            statement.execute(UpdateCustomer);

            Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Customers.fxml"));
            Scene projectScene = new Scene(projectParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(projectScene);
            window.setTitle("Customers");
            window.show();
        }
    }

    /** lambda example
 *      step 1
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList choiceBox = FXCollections.observableArrayList();
        choiceBox.addAll(false, true);
        activeCB.setItems(choiceBox);
        customerIdField.setText(String.valueOf(Customers.getSelectedCustomer().getCustomerID()));
        customerNameField.setText(String.valueOf(Customers.getSelectedCustomer().getCustomerName()));
//        try {
//            address.setText(String.valueOf(statement.executeQuery("SELECT * FROM address WHERE addressId = '" + Customers.getSelectedCustomer().getAddressID().toString() + "');").getString("address")));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        ResultSet DBaddress = null;
        try {
            DBaddress = statement.executeQuery("SELECT * FROM address;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!DBaddress.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                String DBaddressId = DBaddress.getString("addressId");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (getSelectedCustomer().getAddressID().compareTo(Integer.valueOf(DBaddress.getString("addressId"))) == 0) {
                    address.setText(String.valueOf(DBaddress.getString("address")));
                    address2.setText(String.valueOf(DBaddress.getString("address2")));
                    cityId = Integer.parseInt(DBaddress.getString("cityId"));
                    postal.setText(String.valueOf(DBaddress.getString("postalCode")));
                    phoneNumber.setText(String.valueOf(DBaddress.getString("phone")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ResultSet DBCity = null;
        try {
            DBCity = statement.executeQuery("SELECT * FROM city;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!DBCity.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (String.valueOf(cityId).compareTo(DBCity.getString("cityId")) == 0) {
                    city.setText(String.valueOf(DBCity.getString("city")));
                    countryId = Integer.parseInt(DBCity.getString("countryId"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ResultSet DBCountry = null;
        try {
            DBCountry = statement.executeQuery("SELECT * FROM country;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!DBCountry.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (String.valueOf(countryId).compareTo(DBCountry.getString("countryId")) == 0) {
                    country.setText(String.valueOf(DBCountry.getString("country")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        customerNameField.setText(String.valueOf(Customers.getSelectedCustomer().getCustomerName()));

        selectedCustomer = Customers.getSelectedCustomer();
//        customerIdField.setText(String.valueOf((selectedCustomer.getCustomerID())));

        //set up initial values in table
        customersTable.setItems(getAllCustomers());
//        customerIdField.setText(String.valueOf(Customers.getSelectedCustomer().getCustomerID()));
//        customerNameField.setText(getSelectedCustomer().getCustomerName());
//        addressIdField.setText(String.valueOf(getSelectedCustomer().getAddressID()));
        if (getSelectedCustomer().getActive() == 0) {
            activeCB.setValue(false);
        } else {
            activeCB.setValue(true);
        }
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
