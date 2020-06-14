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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import static Controller.Customers.getAllCustomers;
import static Controller.Customers.statement;


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
    private ChoiceBox<Boolean> activeCB;

    @FXML
    private HBox createdDateField;

    @FXML
    private TextField createdByField;

    @FXML
    private TextField lastUpdateField;

    @FXML
    private TextField lastUpdatedByField;
    public void goToCustomer(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Customers.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Customers");
        window.show();
    }
    public void saveCustomer(ActionEvent event) throws IOException, SQLException {
        String customerID = String.valueOf(selectedCustomer.getCustomerID());
        String customerName = customerNameField.getText();
        String addressID = String.valueOf(Integer.parseInt(addressIdField.getText()));
        String active = String.valueOf(activeCB.getValue());
        String createDate = String.valueOf(new Date(System.currentTimeMillis()));;
        String createdBy = LogIn.getUsername();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));;
        String lastUpdateBy = LogIn.getUsername();

        String UpdateCustomer = "UPDATE customers SET customerName = '" + customerName + "'," +
                "addressID = '" + addressID + "'," +
                "active = '" + active + "'," +
                "createDate = '" + createDate + "'," +
                "createdBy = '" + createdBy + "'," +
                "lastUpdate = '" + lastUpdate + "'," +
                "lastUpdateBy = '" + lastUpdateBy + "'" +
                " WHERE customerId = '" + customerID +
                "';";

        statement.execute(UpdateCustomer);
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
        choiceBox.addAll(0, 1);
        activeCB.setItems(choiceBox);

        selectedCustomer = Customers.getSelectedCustomer();
        customerIdField.setText(String.valueOf((selectedCustomer.getCustomerID())));

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
