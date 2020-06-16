package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Calendar implements Initializable {
    @FXML
    private Label month;

    private static LocalDate date = LocalDate.now();

    @FXML
    private ListView<String> day0List;
    ObservableList list= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        month.setText(date.getMonth().toString());
        populateCal();
    }


    @FXML
    private DatePicker datePicker;


    private void populateCal() {
        list.removeAll();
        String a = "Hello";
        String b = "GoodBYe";
        String c = "Still Here?";
        String d = "Okay then.";
        list.addAll(a,b,c,d);
        day0List.getItems().addAll(list);
    }

    @FXML
    void refreshDate(ActionEvent event) {
        month.setText(String.valueOf(datePicker.getValue().getMonth()));

    }
    public void goToCustomers(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Customers.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Customers");
        window.show();
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Appointments.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Controller.Appointments");
        window.show();
    }
}
