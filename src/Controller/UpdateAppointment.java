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
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static Controller.Customers.getSelectedCustomer;
import static Controller.Customers.statement;

public class UpdateAppointment implements Initializable {


    @FXML
    private ChoiceBox<String> startTimeCB;

    @FXML
    private ChoiceBox<String> endTimeCB;


    @FXML
    private TextField CreatedField;

    @FXML
    private TextField CreatedByField;

    @FXML
    private TextField LastUpdateField;

    @FXML
    private TextField LastUpdatedByField;

    @FXML
    private Button SaveBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField CustomerIdField;

    @FXML
    private TextField UserIdField;

    @FXML
    private TextField TitleField;

    @FXML
    private TextField DescriptionField;

    @FXML
    private HBox createdDateField;

    @FXML
    private TextField LocationField;

    @FXML
    private TextField ContactField;

    @FXML
    private TextField TypeField;

    @FXML
    private TextField URLField;

    @FXML
    private DatePicker assignedDate;

    public void goToAppointments(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Appointments.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Appointments");
        window.show();
    }

    public void saveAppointment(ActionEvent event) throws IOException, SQLException {
        java.util.Date date = Date.valueOf(assignedDate.getValue());
        if (!TimeZone.getDefault().inDaylightTime(date)) {
            AddAppointment.offset = Integer.valueOf(ZonedDateTime.now().toString().substring(23,26)) - 1;
        } else if (TimeZone.getDefault().inDaylightTime(date)){
            AddAppointment.offset = Integer.valueOf(ZonedDateTime.now().toString().substring(23,26));
        }
        System.out.println(assignedDate.getValue().toString() + " " + (Integer.valueOf(startTimeCB.getValue().substring(0,2)) + AddAppointment.offset) + ":00:00");

        String appointmentId = String.valueOf(UserIdField.getText());
        String customerId = String.valueOf(CustomerIdField.getText());
        String userId = String.valueOf(UserIdField.getText());
        String title = TitleField.getText();
        String description = DescriptionField.getText();
        String location = LocationField.getText();
        String contact = ContactField.getText();
        String type = TypeField.getText();
        String url = URLField.getText();
        String starts = assignedDate.getValue().toString() + " " + Integer.valueOf(startTimeCB.getValue().substring(0,2)) + ":00:00";
        String ends = assignedDate.getValue().toString() + " " + Integer.valueOf(endTimeCB.getValue().substring(0,2)) + ":00:00";
        String start = assignedDate.getValue().toString() + " " + (Integer.valueOf(startTimeCB.getValue().substring(0,2)) - AddAppointment.offset) + ":00:00";
        String end = assignedDate.getValue().toString() + " " + (Integer.valueOf(endTimeCB.getValue().substring(0,2)) - AddAppointment.offset) + ":00:00";
        String createDate = String.valueOf(new Date(System.currentTimeMillis()));
        String createdBy = LogIn.getUsername();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String lastUpdateBy = LogIn.getUsername();


        String alterStatement = "UPDATE appointments " +
                "SET userId = '" + userId + "'," +
                "customerId = '" + customerId + "'," +
                "description = '" + description + "'," +
                "location = '" + location + "'," +
                "contact = '" + contact + "'," +
                "type = '" + type + "'," +
                "start = '" + start + "'," +
                "end = '" + end + "'," +
                "url = '" + url +
                "' WHERE appointmentId = '" + Appointments.getSelectedAppointment().getAppointmentId() + "';";
        System.out.println(start);
        System.out.println(end);
//

        Appointment appointment = new Appointment(Integer.valueOf(appointmentId), Integer.valueOf(customerId), Integer.valueOf(userId), title, description, location, contact, type, url, starts, ends, Date.valueOf(createDate), createdBy, Timestamp.valueOf(lastUpdate), lastUpdateBy);

        Appointments.updateAppointment(Integer.valueOf(UserIdField.getText()), appointment);
        statement.execute(alterStatement);


        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Appointments.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Appointments");
        window.show();
    }

    public void deleteAppointment(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList startChoiceBox = FXCollections.observableArrayList();
        startChoiceBox.addAll("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");
        startTimeCB.setItems(startChoiceBox);

        ObservableList endChoiceBox = FXCollections.observableArrayList();
        endChoiceBox.addAll("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");
        endTimeCB.setItems(endChoiceBox);
        assignedDate.setValue(LocalDate.parse(Appointments.getSelectedAppointment().getStart().substring(0,10)));
        CustomerIdField.setText(String.valueOf(Appointments.getSelectedAppointment().getAppointmentId()));
        UserIdField.setText(String.valueOf(Appointments.getSelectedAppointment().getUserID()));
        TitleField.setText(String.valueOf(Appointments.getSelectedAppointment().getTitle()));
        DescriptionField.setText(String.valueOf(Appointments.getSelectedAppointment().getDescription()));
        LocationField.setText(String.valueOf(Appointments.getSelectedAppointment().getLocation()));
        ContactField.setText(String.valueOf(Appointments.getSelectedAppointment().getContact()));
        TypeField.setText(String.valueOf(Appointments.getSelectedAppointment().getType()));
        URLField.setText(String.valueOf(Appointments.getSelectedAppointment().getUrl()));
        startTimeCB.setValue(String.valueOf(Appointments.getSelectedAppointment().getStart()));
        endTimeCB.setValue(String.valueOf(Appointments.getSelectedAppointment().getEnd()));
        CreatedField.setText(String.valueOf(Appointments.getSelectedAppointment().getCreatedDate()));
        CreatedByField.setText(String.valueOf(Appointments.getSelectedAppointment().getCreatedBy()));
        LastUpdateField.setText(String.valueOf(Appointments.getSelectedAppointment().getLastUpdate()));
        LastUpdatedByField.setText(String.valueOf(Appointments.getSelectedAppointment().getLastUpdatedBy()));
    }
}
