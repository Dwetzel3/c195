package Controller;

import Model.Appointment;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static Controller.Customers.*;

public class Appointments implements Initializable {
    @FXML
    private TableView<Appointment> AppointmentsTable;

    @FXML
    private TableColumn<Appointment, Integer> appointmentId;

    @FXML
    private TableColumn<Appointment, Integer> CustomerIdCol;

    @FXML
    private TableColumn<Appointment, Integer> UserCol;

    @FXML
    private TableColumn<Appointment, String> TitleCol;

    @FXML
    private TableColumn<Appointment, String> DescriptionCol;

    @FXML
    private TableColumn<Appointment, String> StartCol;

    @FXML
    private TableColumn<Appointment, String > EndCol;

    @FXML
    private TableColumn<Appointment, Timestamp> LastUpdatedCol;

    @FXML
    private TableColumn<Appointment, String> UpdatedByCol;

    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }
    public static void updateAppointment(int index, Appointment appointment) {
        allAppointments.set(index, appointment);
    }
    private static Appointment selectedAppointment;

    public static void setSelectedAppointment(Appointment selectedAppointment) {
        Appointments.selectedAppointment = selectedAppointment;
    }

    public void goToUpdateAppointment(ActionEvent event) throws IOException {
        setSelectedAppointment(AppointmentsTable.getSelectionModel().getSelectedItem());
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/UpdateAppointment.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Update Appointment");
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
        setSelectedCustomer(Customers.getAllCustomers().get(AppointmentsTable.getSelectionModel().getSelectedItem().getCustomerID() - 1));

        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Customers.fxml"));
        Scene projectScene = new Scene(projectParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Customers");
        window.show();
    }

    public void deleteAppointment(ActionEvent event) {
        setSelectedAppointment(AppointmentsTable.getSelectionModel().getSelectedItem());
        allAppointments = AppointmentsTable.getItems();
        String deleteSelected = "DELETE FROM appointments WHERE appointmentID = " + selectedAppointment.getAppointmentId() + ";";
        allAppointments.removeAll(selectedAppointment);
        try {
            statement.execute(deleteSelected);
            AppointmentsTable.setItems(getAllAppointments());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GoToAddAppointments(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/AddAppointment.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Appointments");
        window.show();
    }
    public static void addNewAppointment(Appointment appointment) {
        allAppointments.add(appointment);
    }

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public static void alertHours() {
        Alert hours = new Alert(Alert.AlertType.ERROR);
        hours.setTitle("Error");
        hours.setContentText("Business hours are between 09:00 and 17:00.");
        hours.showAndWait();
    }

    public static void alertOverlap() {
        Alert overlap = new Alert(Alert.AlertType.WARNING);
        overlap.setTitle("Conflict detected");
        overlap.setContentText("The times selected are overlapping a current appointment. Please choose a new timespan.");
        overlap.showAndWait();
    }

    public static void alertEmpty() {
        Alert overlap = new Alert(Alert.AlertType.WARNING);
        overlap.setTitle("Incomplete Data");
        overlap.setContentText("Please check all forms for empty data.");
        overlap.showAndWait();
    }

    public static void alertType() {
        Alert overlap = new Alert(Alert.AlertType.WARNING);
        overlap.setTitle("Non-Conforming Data");
        overlap.setContentText("Please check all forms for incompatible data types.");
        overlap.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AppointmentsTable.getSortOrder().setAll();
        //set up initial values in table
        AppointmentsTable.setItems(getAllAppointments());
        //Setup customer table
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        CustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        UserCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        StartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        LastUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        UpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));

    }


}
