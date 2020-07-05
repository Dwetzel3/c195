package Controller;

import Model.Appointment;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import static Controller.Customers.statement;


public class Appointments implements Initializable {

    @FXML
    private TableView<Appointment> AppointmentsTable;

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
    public static void updateAppointment(int userId, Appointment appointment) {
        allAppointments.remove(selectedAppointment);
        allAppointments.set(userId, appointment);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppointmentsTable.getSortOrder().setAll();
        //set up initial values in table
        AppointmentsTable.setItems(getAllAppointments());
        //Setup customer table
        CustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        UserCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        StartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        LastUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        UpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));

    }

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

}
