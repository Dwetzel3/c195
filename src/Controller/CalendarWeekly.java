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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class CalendarWeekly implements Initializable {

    ObservableList<Appointment> list = FXCollections.observableArrayList();

    @FXML
    private Label month;

    @FXML
    private Button previousWeek;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button nextWeek;

    @FXML
    private Label col1;

    @FXML
    private Label col2;

    @FXML
    private Label col3;

    @FXML
    private Label col4;

    @FXML
    private Label col5;

    @FXML
    private Label col6;

    @FXML
    private Label col7;

    @FXML
    private ListView<String> sunday;

    @FXML
    private Label x2y1;

    @FXML
    private ListView<String> monday;

    @FXML
    private Label x3y1;

    @FXML
    private ListView<String> tuesday;

    @FXML
    private Label x4y1;

    @FXML
    private ListView<String> wednesday;

    @FXML
    private Label x5y1;

    @FXML
    private ListView<String> thursday;

    @FXML
    private Label x6y1;

    @FXML
    private ListView<String> friday;

    @FXML
    private Label x7y1;

    @FXML
    private ListView<String> saturday;

    @FXML
    void goToAppointments(ActionEvent event) {

    }

    @FXML
    void goToCustomers(ActionEvent event) {

    }

    @FXML
    void nextWeek(ActionEvent event) {
        datePicker.setValue(datePicker.getValue().plusWeeks(1));
    }

    @FXML
    void previousWeek(ActionEvent event) {
        datePicker.setValue(datePicker.getValue().minusWeeks(1));
    }

    public void clearDates() {
        sunday.getItems().clear();
        monday.getItems().clear();
        tuesday.getItems().clear();
        wednesday.getItems().clear();
        thursday.getItems().clear();
        friday.getItems().clear();
        saturday.getItems().clear();
    }

    public void populate() {
        if (datePicker.getValue() != null) {
            if (datePicker.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                col1.setText(String.valueOf(datePicker.getValue()));
                col2.setText(String.valueOf(datePicker.getValue().plusDays(1)));
                col3.setText(String.valueOf(datePicker.getValue().plusDays(2)));
                col4.setText(String.valueOf(datePicker.getValue().plusDays(3)));
                col5.setText(String.valueOf(datePicker.getValue().plusDays(4)));
                col6.setText(String.valueOf(datePicker.getValue().plusDays(5)));
                col7.setText(String.valueOf(datePicker.getValue().plusDays(6)));
            }
            if (datePicker.getValue().getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                col1.setText(String.valueOf(datePicker.getValue().minusDays(1)));
                col2.setText(String.valueOf(datePicker.getValue()));
                col3.setText(String.valueOf(datePicker.getValue().plusDays(1)));
                col4.setText(String.valueOf(datePicker.getValue().plusDays(2)));
                col5.setText(String.valueOf(datePicker.getValue().plusDays(3)));
                col6.setText(String.valueOf(datePicker.getValue().plusDays(4)));
                col7.setText(String.valueOf(datePicker.getValue().plusDays(5)));
            }
            if (datePicker.getValue().getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                col1.setText(String.valueOf(datePicker.getValue().minusDays(2)));
                col2.setText(String.valueOf(datePicker.getValue().minusDays(1)));
                col3.setText(String.valueOf(datePicker.getValue()));
                col4.setText(String.valueOf(datePicker.getValue().plusDays(1)));
                col5.setText(String.valueOf(datePicker.getValue().plusDays(2)));
                col6.setText(String.valueOf(datePicker.getValue().plusDays(3)));
                col7.setText(String.valueOf(datePicker.getValue().plusDays(4)));
            }
            if (datePicker.getValue().getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
                col1.setText(String.valueOf(datePicker.getValue().minusDays(3)));
                col2.setText(String.valueOf(datePicker.getValue().minusDays(2)));
                col3.setText(String.valueOf(datePicker.getValue().minusDays(1)));
                col4.setText(String.valueOf(datePicker.getValue()));
                col5.setText(String.valueOf(datePicker.getValue().plusDays(1)));
                col6.setText(String.valueOf(datePicker.getValue().plusDays(2)));
                col7.setText(String.valueOf(datePicker.getValue().plusDays(3)));
            }
            if (datePicker.getValue().getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
                col1.setText(String.valueOf(datePicker.getValue().minusDays(4)));
                col2.setText(String.valueOf(datePicker.getValue().minusDays(3)));
                col3.setText(String.valueOf(datePicker.getValue().minusDays(2)));
                col4.setText(String.valueOf(datePicker.getValue().minusDays(1)));
                col5.setText(String.valueOf(datePicker.getValue()));
                col6.setText(String.valueOf(datePicker.getValue().plusDays(1)));
                col7.setText(String.valueOf(datePicker.getValue().plusDays(2)));
            }
            if (datePicker.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                col1.setText(String.valueOf(datePicker.getValue().minusDays(5)));
                col2.setText(String.valueOf(datePicker.getValue().minusDays(4)));
                col3.setText(String.valueOf(datePicker.getValue().minusDays(3)));
                col4.setText(String.valueOf(datePicker.getValue().minusDays(2)));
                col5.setText(String.valueOf(datePicker.getValue().minusDays(1)));
                col6.setText(String.valueOf(datePicker.getValue()));
                col7.setText(String.valueOf(datePicker.getValue().plusDays(1)));
            }
            if (datePicker.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                col1.setText(String.valueOf(datePicker.getValue().minusDays(6)));
                col2.setText(String.valueOf(datePicker.getValue().minusDays(5)));
                col3.setText(String.valueOf(datePicker.getValue().minusDays(4)));
                col4.setText(String.valueOf(datePicker.getValue().minusDays(3)));
                col5.setText(String.valueOf(datePicker.getValue().minusDays(2)));
                col6.setText(String.valueOf(datePicker.getValue().minusDays(1)));
                col7.setText(String.valueOf(datePicker.getValue()));
            }
        }

        for (int i = 0; i < Appointments.getAllAppointments().size(); i++) {

            Appointment a = Appointments.getAllAppointments().get(i);
            list.addAll(a);

            if (col1.getText().equals(Appointments.getAllAppointments().get(i).getStart().substring(0, 10))) {
                sunday.getItems().add(list.get(i).getTitle());
            }
            if (col2.getText().equals(Appointments.getAllAppointments().get(i).getStart().substring(0, 10))) {
                monday.getItems().add(list.get(i).getTitle());
            }
            if (col3.getText().equals(Appointments.getAllAppointments().get(i).getStart().substring(0, 10))) {
                tuesday.getItems().add(list.get(i).getTitle());
            }
            if (col4.getText().equals(Appointments.getAllAppointments().get(i).getStart().substring(0, 10))) {
                wednesday.getItems().add(list.get(i).getTitle());
            }
            if (col5.getText().equals(Appointments.getAllAppointments().get(i).getStart().substring(0, 10))) {
                thursday.getItems().add(list.get(i).getTitle());
            }
            if (col6.getText().equals(Appointments.getAllAppointments().get(i).getStart().substring(0, 10))) {
                friday.getItems().add(list.get(i).getTitle());
            }
            if (col7.getText().equals(Appointments.getAllAppointments().get(i).getStart().substring(0, 10))) {
                saturday.getItems().add(list.get(i).getTitle());
            }
        }
    }

    @FXML
    void refreshDate(ActionEvent event) {
        clearDates();
        populate();
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
        datePicker.setValue(LocalDate.now());
        populate();
    }

    public void setSelectedView(MouseEvent mouseEvent) {
    }
}
