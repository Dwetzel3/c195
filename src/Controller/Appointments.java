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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static Controller.Customers.*;

public class Appointments implements Initializable {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public static void setSelectedAppointment(Appointment selectedAppointment) {
        Appointments.selectedAppointment = selectedAppointment;
    }

    private static Appointment selectedAppointment;

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


    @FXML
    private Label AppointmentsLbl;

    @FXML
    private Button goToCustomer;

    @FXML
    private Button AddAppointment;

    @FXML
    private Button UpdateAppointment;

    @FXML
    private Button DeleteAppointment;

    @FXML
    private Button exitBtn1;



    public static void updateAppointment(int index, Appointment appointment) {
        allAppointments.set(index, appointment);
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
        for (int i = 0; i < Customers.getAllCustomers().size(); i++) {
            if (getAllCustomers().get(i).getCustomerID() == AppointmentsTable.getSelectionModel().getSelectedItem().getCustomerID()) {
                    setSelectedCustomer(Customers.getAllCustomers().get(i));
                break;
            }
        }

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
        String deleteSelected = "DELETE FROM appointment WHERE appointmentID = " + selectedAppointment.getAppointmentId() + ";";
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
        Alert alertEmpty = new Alert(Alert.AlertType.WARNING);
        alertEmpty.setTitle("Incomplete Data");
        alertEmpty.setContentText("Please check all forms for empty data.");
        alertEmpty.showAndWait();
    }

    public static void alertType() {
        Alert alertType = new Alert(Alert.AlertType.WARNING);
        alertType.setTitle("Non-Conforming Data");
        alertType.setContentText("Please check all forms for incompatible data types.");
        alertType.showAndWait();
    }

    public static void alertUpcoming() {
        Alert upcoming = new Alert(Alert.AlertType.WARNING);
        upcoming.setTitle("Appointment Soon!");
        upcoming.setContentText("There is an appointment soon.");
        upcoming.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResourceBundle rb = ResourceBundle.getBundle("languages/Nat", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("en")) {
            AppointmentsLbl.setText(rb.getString("appointment"));
            AddAppointment.setText(rb.getString("add"));
            UpdateAppointment.setText(rb.getString("update"));
            DeleteAppointment.setText(rb.getString("delete"));
            exitBtn1.setText(rb.getString("exitBtn"));
            goToCustomer.setText(rb.getString("goToCustomer"));
        }

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
