package Controller;

import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.TimeZone;

import static Controller.Customers.statement;

public class AddAppointment {
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
    private HBox createdDateField;

    @FXML
    private TextField LocationField;

    @FXML
    private TextField ContactField;

    @FXML
    private TextField TypeField;

    @FXML
    private TextField URLField;

    public void goToAppointments(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Appointments.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Appointments");
        window.show();
    }

    public void saveAppointment(ActionEvent event) throws IOException, SQLException {
        String customerId = String.valueOf(CustomerIdField.getText());
        String userId = String.valueOf(Integer.parseInt(UserIdField.getText()));
        String title = String.valueOf(TitleField.getText());
        String description = DescriptionField.getText();
        String location = LocationField.getText();
        String contact = ContactField.getText();
        String type = TypeField.getText();
        String url = URLField.getText();
        String start = StartField.getText();
        String end = EndField.getText();
        String createDate = String.valueOf(new Date(System.currentTimeMillis()));;
        String createdBy = LogIn.getUsername();
        String lastUpdate = String.valueOf(new Timestamp(System.currentTimeMillis()));;
        String lastUpdateBy = LastUpdatedByField.getText();

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
        Appointment appointment = new Appointment(Integer.valueOf(customerId), Integer.valueOf(userId), title, description, TimeZone.getTimeZone(location), contact, type, url, Timestamp.valueOf(start), Timestamp.valueOf(end), Date.valueOf(createDate), createdBy, Timestamp.valueOf(lastUpdate), lastUpdateBy);
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
}
