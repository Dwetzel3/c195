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
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

import static java.time.Month.*;

public class Reports  implements Initializable {


    @FXML
    private ChoiceBox<Month> appsByMonth;

    ObservableList<Appointment> appointmentTypes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList appointmentsByMonth = FXCollections.observableArrayList();
        appointmentsByMonth.addAll(JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER);
        appsByMonth.setItems(appointmentsByMonth);


    }
    int index = 0;

    int differentTypes = 1;

    public void appointmentTypes(ActionEvent actionEvent) {

        index = 0;
        differentTypes = 0;
        appointmentTypes.clear();

        for (Appointment appointment : Appointments.getAllAppointments()
        ) {
            if (Integer.parseInt(appointment.getStart().substring(5, 7)) == appsByMonth.getValue().getValue()
                    && (Integer.parseInt(appointment.getStart().substring(0, 4)) == LocalDate.now().getYear())) {
                appointmentTypes.add(appointment);
            }
        }

        LinkedHashSet<String> al=new LinkedHashSet<String>();
        for (int i = 0; i < appointmentTypes.size(); i++) {
            al.add(appointmentTypes.get(i).getType());
        }

        Iterator<String> uniqueTypes=al.iterator();
        while(uniqueTypes.hasNext()){
            System.out.println(uniqueTypes.next());
        }
    }



    public void schedule(ActionEvent actionEvent) {
        for (Appointment appointment: Appointments.getAllAppointments()
        ) {
            System.out.println(appointment.getStart());
            System.out.println(appointment.getEnd());
        }
    }

    public void additional(ActionEvent actionEvent) {
    }

    public void goToMain(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Calendar.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Calendar");
        window.show();
    }
}
