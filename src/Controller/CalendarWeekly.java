package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalendarWeekly implements Initializable {

    @FXML
    private Label month;

    @FXML
    private Button previousMonth;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button nextMonth;

    @FXML
    private Label x1y1;

    @FXML
    private ListView<?> listx1y1;

    @FXML
    private Label x2y1;

    @FXML
    private ListView<?> listx2y1;

    @FXML
    private Label x3y1;

    @FXML
    private ListView<?> listx3y1;

    @FXML
    private Label x4y1;

    @FXML
    private ListView<?> listx4y1;

    @FXML
    private Label x5y1;

    @FXML
    private ListView<?> listx5y1;

    @FXML
    private Label x6y1;

    @FXML
    private ListView<?> listx6y1;

    @FXML
    private Label x7y1;

    @FXML
    private ListView<?> listx7y1;

    @FXML
    private Button customersBtn;

    @FXML
    private Button appointmentsBtn;

    @FXML
    void goToAppointments(ActionEvent event) {

    }

    @FXML
    void goToCustomers(ActionEvent event) {

    }

    @FXML
    void nextMonth(ActionEvent event) {

    }

    @FXML
    void previousMonth(ActionEvent event) {

    }

    @FXML
    void refreshDate(ActionEvent event) {

    }


    @FXML
    private Button Weekly;

    @FXML
    public void goToMonthly(ActionEvent event) throws IOException {
            Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Calendar.fxml"));
            Scene projectScene = new Scene(projectParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(projectScene);
            window.setTitle("Calendar");
            window.show();
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
