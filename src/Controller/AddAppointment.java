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
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static Controller.Customers.statement;

public class AddAppointment implements Initializable {

    @FXML
    private TableView<Appointment> AppointmentsTable;


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
    private TextField CustomerIdField;

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
//        System.out.println("Date and time in Madrid: " + df.format(date));
        if (true) {
//            System.out.println("Local: " + offset);

//            System.out.println("Date and time in Seattle: " + df.format(date));
        }
        else {
//            System.out.println("NOT!");
        }
    }

    public void saveAppointment(ActionEvent event) throws IOException, SQLException {

        /**
         * Gets appointmentId Count
         */

        int appointmentCounter = 0;
        ResultSet appointmentCount = statement.executeQuery("SELECT *" +
                " FROM appointment;");
        while (appointmentCount.next()) {
            appointmentCounter = appointmentCount.getRow();
        }
        System.out.println("Appointment has " + appointmentCounter + " row(s).");
        appointmentCount.close();


        Boolean valid = true;
        java.util.Date date = Date.valueOf(assignedDate.getValue());
        if (!TimeZone.getDefault().inDaylightTime(date)) {
            AddAppointment.offset = Integer.valueOf(ZonedDateTime.now().toString().substring(23,26)) - 1;
        } else if (TimeZone.getDefault().inDaylightTime(date)){
            AddAppointment.offset = Integer.valueOf(ZonedDateTime.now().toString().substring(23,26));
        }
        int startOffset = (Integer.valueOf(startTime.getValue().substring(0,2)) - AddAppointment.offset);
        int endOffset = (Integer.valueOf(endTime.getValue().substring(0,2)) - AddAppointment.offset);
        String appointmentId = String.valueOf(Appointments.getAllAppointments().get(appointmentCounter - 1).getAppointmentId() + 1);
        String customerId = String.valueOf(CustomerIdField.getText());

        String userId = null;
        ResultSet DBUser = statement.executeQuery("SELECT * FROM user;");
        while (DBUser.next()) {
            String DBUserId = DBUser.getString("userId");
            String DBUserName = DBUser.getString("userName");
            if ((LogIn.username.compareTo(DBUser.getString("userName")) == 0)) {
                System.out.println(DBUserName + " already exists under ID: " + DBUserId + "!");
                userId = String.valueOf(DBUser.getString("userId"));
            }
        }
        String title = String.valueOf(TitleField.getText());
        String description = DescriptionField.getText();
        String location = LocationField.getText();
        String contact = ContactField.getText();
        String type = TypeField.getText();
        String url = URLField.getText();
        if (Integer.valueOf(startOffset) >= 24) {
            startOffset = ((Integer.valueOf(startTime.getValue().substring(0,2)) - 24));
        }
        if (Integer.valueOf(endOffset) >= 24) {
            endOffset = ((Integer.valueOf(endTime.getValue().substring(0,2)) - 24));
        }
        String start = assignedDate.getValue().toString() + " " + Integer.valueOf(startTime.getValue().substring(0,2)) + ":00:00";
        String end = assignedDate.getValue().toString() + " " + Integer.valueOf(endTime.getValue().substring(0,2)) + ":00:00";
        String starts = assignedDate.getValue().toString() + " " + startOffset + ":00:00";
        String ends = assignedDate.getValue().toString() + " " + endOffset + ":00:00";
        int selectedStartTime = Integer.parseInt(startTime.getValue().substring(0,2));
        int selectedEndTime = Integer.parseInt(endTime.getValue().substring(0,2));
        String createDate = String.valueOf(new Date(System.currentTimeMillis()));
        String createdBy = LogIn.getUsername();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String lastUpdateBy = LogIn.getUsername();

        String insertStatement = "INSERT INTO appointment(appointmentId, customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(" +
                "'" + appointmentId + "'," +
                "'" + customerId + "'," +
                "'" + userId + "'," +
                "'" + title + "'," +
                "'" + description + "'," +
                "'" + location + "'," +
                "'" + contact + "'," +
                "'" + type + "'," +
                "'" + url + "'," +
                "'" + starts + "'," +
                "'" + ends + "'," +
                "'" + createDate + "'," +
                "'" + createdBy + "'," +
                "'" + lastUpdate + "'," +
                "'" + lastUpdateBy + "'" +
                ");";
        Appointment appointment = new Appointment(Integer.valueOf(appointmentId), Integer.valueOf(customerId), Integer.valueOf(userId), title, description,location, contact, type, url, start, end, Date.valueOf(createDate), createdBy, Timestamp.valueOf(lastUpdate), lastUpdateBy);


        if (selectedStartTime < 9 || selectedStartTime > 17
                || selectedEndTime < 9 || selectedEndTime > 17) {
            valid = false;
            Appointments.alertHours();
        }

        /**
         * Checks if the appointment would cause an overlapping conflict
         */

        for (int i = 0; i < Appointments.getAllAppointments().size(); i++) {
            if (Appointments.getAllAppointments().get(i).getStart().substring(0, 10).equals(assignedDate.getValue().toString())
                    && (Timestamp.valueOf(Appointments.getAllAppointments().get(i).getStart()).equals(Timestamp.valueOf(start))
                || Timestamp.valueOf(Appointments.getAllAppointments().get(i).getStart()).after(Timestamp.valueOf(start)) && Timestamp.valueOf(Appointments.getAllAppointments().get(i).getEnd()).before(Timestamp.valueOf(end)))
                || Timestamp.valueOf(Appointments.getAllAppointments().get(i).getStart()).before(Timestamp.valueOf(start)) && Timestamp.valueOf(Appointments.getAllAppointments().get(i).getStart()).after(Timestamp.valueOf(end))) {
                valid = false;
                Appointments.alertOverlap();
            }
        }

        if (valid) {
            Appointments.addNewAppointment(appointment);
            statement.execute(insertStatement);
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
        ObservableList startChoiceBox = FXCollections.observableArrayList();
        startChoiceBox.addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00");
        startTime.setItems(startChoiceBox);

        ObservableList endChoiceBox = FXCollections.observableArrayList();
        endChoiceBox.addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00");
        endTime.setItems(endChoiceBox);

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
