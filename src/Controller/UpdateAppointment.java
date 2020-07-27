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
import javafx.scene.control.cell.PropertyValueFactory;
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

import static Controller.Appointments.getSelectedAppointment;
import static Controller.Customers.*;

public class UpdateAppointment implements Initializable {

    @FXML
    private TableView<Appointment> AppointmentsTable;


    @FXML
    private ChoiceBox<String> startTimeCB;

    @FXML
    private ChoiceBox<String> endTimeCB;

    @FXML
    private TableColumn<Appointment, Integer> CustomerCol;

    @FXML
    private TableColumn<Appointment, Integer> UserCol;

    @FXML
    private TableColumn<Appointment, String> TitleCol;

    @FXML
    private TableColumn<Appointment, String> DescriptionCol;

    @FXML
    private TableColumn<Appointment, String> StartCol;

    @FXML
    private TableColumn<Appointment, String> EndCol;

    @FXML
    private TableColumn<Appointment, String> LastUpdatedCol;

    @FXML
    private TableColumn<Appointment, String> LastUpdatedByCol;

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
    private Button exitBtn;

    @FXML
    private TextField AppointmentField;

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
        Boolean valid = true;
        java.util.Date date = Date.valueOf(assignedDate.getValue());
        if (!TimeZone.getDefault().inDaylightTime(date)) {
            AddAppointment.offset = Integer.valueOf(ZonedDateTime.now().toString().substring(23, 26)) - 1;
        } else if (TimeZone.getDefault().inDaylightTime(date)) {
            AddAppointment.offset = Integer.valueOf(ZonedDateTime.now().toString().substring(23, 26));
        }
        int startOffset = (Integer.valueOf(startTimeCB.getValue().substring(0, 2)) - AddAppointment.offset);
        int endOffset = (Integer.valueOf(endTimeCB.getValue().substring(0, 2)) - AddAppointment.offset);
        int startTime = Integer.parseInt(startTimeCB.getValue().substring(0, 2));
        int endTime = Integer.parseInt(endTimeCB.getValue().substring(0, 2));
        String appointmentId = String.valueOf(AppointmentField.getText());
        String customerId = String.valueOf(CustomerIdField.getText());
        String userId = String.valueOf(UserIdField.getText());
        String title = TitleField.getText();
        String description = DescriptionField.getText();
        String location = LocationField.getText();
        String contact = ContactField.getText();
        String type = TypeField.getText();
        String url = URLField.getText();
        if (startOffset >= 24) {
            startOffset = ((Integer.valueOf(startTimeCB.getValue().substring(0, 2)) - 24));
        }
        if (endOffset >= 24) {
            endOffset = ((Integer.valueOf(endTimeCB.getValue().substring(0, 2)) - 24));
        }
        String start = assignedDate.getValue() + " " + startTimeCB.getValue().substring(0, 2) + ":00:00";
        String end = assignedDate.getValue() + " " + endTimeCB.getValue().substring(0, 2) + ":00:00";
        String starts = assignedDate.getValue() + " " + startOffset + ":00:00";
        String ends = assignedDate.getValue() + " " + endOffset + ":00:00";
        String createDate = String.valueOf(getSelectedAppointment().getCreatedDate());
        String createdBy = getSelectedAppointment().getCreatedBy();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String lastUpdateBy = LogIn.getUsername();

        String alterStatement = "UPDATE appointments " +
                "SET userId = '" + userId + "'," +
                "customerId = '" + customerId + "'," +
                "title = '" + title + "'," +
                "appointmentId = '" + appointmentId + "'," +
                "description = '" + description + "'," +
                "location = '" + location + "'," +
                "contact = '" + contact + "'," +
                "type = '" + type + "'," +
                "start = '" + starts + "'," +
                "end = '" + ends + "'," +
                "url = '" + url +
                "' WHERE appointmentId = '" + getSelectedAppointment().getAppointmentId() + "';";
//

        /**
         * Checks to see if hours selected are between 9 A.M. and 5 P.M.
         */

        if (startTime < 9 || startTime > 17
                || endTime < 9 || endTime > 17) {
            valid = false;
            Appointments.alertHours();
        }

        /***
         Checks to see if time selected is unique
         */
        for (int i = 0; i < Appointments.getAllAppointments().size(); i++) {
            if (Appointments.getAllAppointments().get(i).getStart().substring(0, 10).equals(assignedDate.getValue().toString())
                    && Appointments.getAllAppointments().get(i).getAppointmentId() != getSelectedAppointment().getAppointmentId()) {
                if (startTime < Integer.parseInt(Appointments.getAllAppointments().get(i).getEnd().substring(11, 13))
                        && endTime > Integer.parseInt(Appointments.getAllAppointments().get(i).getStart().substring(11, 13))) {
                    System.out.println(Integer.parseInt(Appointments.getAllAppointments().get(i).getStart().substring(11, 13)));
                    System.out.println(Integer.parseInt(Appointments.getAllAppointments().get(i).getEnd().substring(11, 13)));
                    valid = false;
                    Appointments.alertOverlap();
                }
            }
        }


        /**
         * Displays error if non-existent data entered
         */


        if (CustomerIdField.getText().isEmpty() ||
                UserIdField.getText().isEmpty() ||
                TitleField.getText().isEmpty() ||
                DescriptionField.getText().isEmpty() ||
                LocationField.getText().isEmpty() ||
                ContactField.getText().isEmpty() ||
                TypeField.getText().isEmpty() ||
                URLField.getText().isEmpty() ||
                assignedDate.getValue() == null ||
                startTimeCB.getValue().isEmpty() ||
                endTimeCB.getValue().isEmpty()) {
            valid = false;
            Appointments.alertEmpty();
        }

        /**
         * Checks if input is of appropriate type
         */

        try {
           Integer.parseInt(CustomerIdField.getText());
           Integer.parseInt(UserIdField.getText());
        } catch (NumberFormatException e) {
            valid = false;
            Appointments.alertType();
        }


        /**
         * If tests are passed, executes the update and alter statement
         */

        if (valid) {
            System.out.println("valid");
            Appointment appointment = new Appointment(Integer.valueOf(appointmentId), Integer.parseInt(customerId), Integer.valueOf(userId), title, description, location, contact, type, url, start, end, Date.valueOf(createDate), createdBy, Timestamp.valueOf(lastUpdate), lastUpdateBy);
            int thisIndex = Appointments.getAllAppointments().indexOf(getSelectedAppointment());
            Appointments.updateAppointment(thisIndex, appointment);
            statement.execute(alterStatement);
        }


        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Appointments.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Appointments");
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /**
         * Fills in the choice boxes
         */

        ObservableList startChoiceBox = FXCollections.observableArrayList();
        startChoiceBox.addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00");
        startTimeCB.setItems(startChoiceBox);
        assignedDate.setValue(LocalDate.parse(getSelectedAppointment().getStart().substring(0,10)));
        ObservableList endChoiceBox = FXCollections.observableArrayList();
        endChoiceBox.addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00");
        endTimeCB.setItems(endChoiceBox);

        /**
         * Fills in the textfields
         */

        AppointmentField.setText(String.valueOf(getSelectedAppointment().getAppointmentId()));
        CustomerIdField.setText(String.valueOf(getSelectedAppointment().getCustomerID()));
        UserIdField.setText(String.valueOf(getSelectedAppointment().getUserID()));
        TitleField.setText(String.valueOf(getSelectedAppointment().getTitle()));
        DescriptionField.setText(String.valueOf(getSelectedAppointment().getDescription()));
        LocationField.setText(String.valueOf(getSelectedAppointment().getLocation()));
        ContactField.setText(String.valueOf(getSelectedAppointment().getContact()));
        TypeField.setText(String.valueOf(getSelectedAppointment().getType()));
        URLField.setText(String.valueOf(getSelectedAppointment().getUrl()));
        startTimeCB.setValue(getSelectedAppointment().getStart().substring(11,16));
        endTimeCB.setValue(getSelectedAppointment().getEnd().substring(11,16));

        /**
         * Sets up the appointments table
         */

        AppointmentsTable.setItems(Appointments.getAllAppointments());
        CustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        UserCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        StartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        LastUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        LastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
    }
}
