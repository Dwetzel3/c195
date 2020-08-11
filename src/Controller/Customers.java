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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Customers implements Initializable {
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public static void setSelectedCustomer(Customer selectedCustomer) {
        Customers.selectedCustomer = selectedCustomer;
    }

    private static Customer selectedCustomer;

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

    static Statement statement = DBQuery.getStatement();

    @FXML
    private Label customerHeader;

    @FXML
    private Button goToAppointments;

    @FXML
    private Button addCustomerBtn;

    @FXML
    private Button updateCustomerBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button exitBtn;


    public static void updateCustomer(int thisIndex, Customer customer) {
        allCustomers.set(thisIndex, customer);
    }

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
        setSelectedCustomer(customersTable.getSelectionModel().getSelectedItem());
        String deleteSelected = "DELETE FROM customer WHERE customerId = " + selectedCustomer.getCustomerID() + ";";
        allCustomers.removeAll(selectedCustomer);
        try {
            statement.execute(deleteSelected);
            customersTable.setItems(getAllCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        for (int i = 0; i < Appointments.getAllAppointments().size(); i++) {
            if (customersTable.getSelectionModel().getSelectedItem().getCustomerID() != null &&
                    customersTable.getSelectionModel().getSelectedItem().getCustomerID() == Appointments.getAllAppointments().get(i).getCustomerID()) {
                    Appointments.setSelectedAppointment(Appointments.getAllAppointments().get(i));
                break;
            }
        }

        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Appointments.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Appointments");
        window.show();
    }

    public void goToUpdateCustomer(ActionEvent event) throws IOException {
        setSelectedCustomer(customersTable.getSelectionModel().getSelectedItem());
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/UpdateCustomer.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Update Customer");
        window.show();
    }

    private void setCustomerToUpdate(Customer selectedItem) {
        Customers.selectedCustomer = selectedCustomer;
    }

    public void goToMain(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Calendar.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Calendar");
        window.show();
    }

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResourceBundle rb = ResourceBundle.getBundle("languages/Nat", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("en")) {
            customerHeader.setText(rb.getString("customers"));
            addCustomerBtn.setText(rb.getString("add"));
            updateCustomerBtn.setText(rb.getString("update"));
            deleteBtn.setText(rb.getString("delete"));
            exitBtn.setText(rb.getString("exitBtn"));
            goToAppointments.setText(rb.getString("goToAppointments"));
        }

        /**
         * sort TableViews
         */

        customersTable.getSortOrder().setAll();

        /**
         * set up initial values in table
         */

        customersTable.setItems(getAllCustomers());

        /**
         *  Setup Table
         */

        customersTable.getSelectionModel().select(getSelectedCustomer());

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
