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
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static Controller.Customers.statement;

public class AddAppointment implements Initializable {
    @FXML
    private TextField StartField;

    @FXML
    private TextField EndField;

    @FXML
    private TextField CreatedField;

    @FXML
    private TextField CreatedByField;

    @FXML
    private TextField LastUpdateField;

    @FXML
    private TextField LastUpdatedByField;

    @FXML
    private TableView<?> AppointmentsTable;

    @FXML
    private TableColumn<?, ?> CustomerCol;

    @FXML
    private TableColumn<?, ?> UserCol;

    @FXML
    private TableColumn<?, ?> TitleIdCol;

    @FXML
    private TableColumn<?, ?> DescriptionCol;

    @FXML
    private TableColumn<?, ?> StartCol;

    @FXML
    private TableColumn<?, ?> EndCol;

    @FXML
    private TableColumn<?, ?> LastUpdatedCol;

    @FXML
    private TableColumn<?, ?> LastUpdatedByCol;

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
    private TextField LocationField;

    @FXML
    private TextField ContactField;

    @FXML
    private TextField TypeField;

    @FXML
    private TextField URLField;

    @FXML
    private ChoiceBox<String> startTime;

    @FXML
    private ChoiceBox<String> endTime;

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

    public static int offset = Integer.valueOf(ZonedDateTime.now().toString().substring(23,26));

    public static void convertTime() {
        java.util.Date date = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        df.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        System.out.println("Date and time in Madrid: " + df.format(date));
        if (true) {
            System.out.println("Local: " + offset);

            System.out.println("Date and time in Seattle: " + df.format(date));
        }
        else {
            System.out.println("NOT!");
        }
    }

    public void saveAppointment(ActionEvent event) throws IOException, SQLException {
        System.out.println(assignedDate.getValue().toString() + " " + startTime.getValue() + ":00:00");
        String appointmentId = String.valueOf(0);
        String customerId = String.valueOf(CustomerIdField.getText());
        String userId = String.valueOf(UserIdField.getText());
        String title = String.valueOf(TitleField.getText());
        String description = DescriptionField.getText();
        String location = LocationField.getText();
        String contact = ContactField.getText();
        String type = TypeField.getText();
        String url = URLField.getText();
        String starts = assignedDate.getValue().toString() + " " + Integer.valueOf(startTime.getValue().substring(0,2)) + ":00:00";
        String ends = assignedDate.getValue().toString() + " " + Integer.valueOf(endTime.getValue().substring(0,2)) + ":00:00";
        String start = assignedDate.getValue().toString() + " " + (Integer.valueOf(startTime.getValue()) - offset) + ":00:00";
        String end = assignedDate.getValue().toString() + " " + (Integer.valueOf(endTime.getValue()) - offset) + ":00:00";
        String createDate = String.valueOf(new Date(System.currentTimeMillis()));
        String createdBy = LogIn.getUsername();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String lastUpdateBy = LogIn.getUsername();

        String insertStatement = "INSERT INTO appointments(customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + customerId + "'," +
                "'" + userId + "'," +
                "'" + title + "'," +
                "'" + description + "'," +
                "'" + location + "'," +
                "'" + contact + "'," +
                "'" + type + "'," +
                "'" + url + "'," +
                "'" + start + "'," +
                "'" + end + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "'" +
                ");";
        Appointment appointment = new Appointment(Integer.valueOf(appointmentId), Integer.valueOf(customerId), Integer.valueOf(userId), title, description,location, contact, type, url, starts, ends, Date.valueOf(createDate), createdBy, Timestamp.valueOf(lastUpdate), lastUpdateBy);

        Appointments.addNewAppointment(appointment);
        statement.execute(insertStatement);

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
        startTime.setItems(startChoiceBox);

        ObservableList endChoiceBox = FXCollections.observableArrayList();
        endChoiceBox.addAll("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");
        endTime.setItems(endChoiceBox);
    }
}
