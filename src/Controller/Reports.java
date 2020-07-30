package Controller;

import Model.Appointment;
import com.sun.javaws.jnl.InformationDesc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.time.Month.*;

public class Reports  implements Initializable {


    @FXML
    private ChoiceBox<Month> appsByMonth;

    private int january = 5;
    private int february = 0;
    private int march = 0;
    private int april = 0;
    private int may = 0;
    private int june = 0;
    private int july = 0;
    private int august = 0;
    private int september = 0;
    private int october = 0;
    private int november = 0;
    private int december = 0;


    ObservableList<Appointment> appointmentTypes = FXCollections.observableArrayList();
    ObservableList<Month> busiestMonths = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        GeneralInterface message = (s, r) -> "Hello " + s + r;
//        System.out.println(message.getMessage("Daniel", "!"));
        // void lambda expression with one parameter
//        GeneralInterface sum = (n1, n2) -> n1 + n2;
//        System.out.println(sum.calculateSum(2,4));

//        GeneralInterface square = n -> {
//            int result = n * n;
//            return result;
//        };
//
        /**

//        System.out.println(square.calculateSquare(6));
//        final int num = 50;
//
//        GeneralInterface square = n -> n * n;
//
//        System.out.println(square.calculateSquare(num));

         */
//
//        final int num = 50;
//
//        GeneralInterface square = n -> n * n;
//
//        System.out.println(square.calculateSquare(num));

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
        Alert schedule = new Alert(Alert.AlertType.INFORMATION);
        schedule.setContentText("Schedule of each appointment: \n");
        for (Appointment appointment: Appointments.getAllAppointments()
        ) {
            /**
             * lambda # 1 improves readability and ease to alter
             */
            ScheduleInterface message = (s, r) -> "Start: " + s + "\n" +
                    "End: " + r;
            schedule.setContentText(message.getMessage(appointment.getStart(), appointment.getEnd()));
            schedule.showAndWait();
        }
    }

    public void average(ActionEvent actionEvent) {
        for (Appointment appointment : Appointments.getAllAppointments()
        ) {
            if (Integer.parseInt(appointment.getStart().substring(0, 4)) == LocalDate.now().getYear()) {
                busiestMonths.add(Month.of(Integer.parseInt(appointment.getStart().substring(5, 7))));
            }
        }

        /**
         * Lambda # 2 used to simplify code and improve readability
         */

        double total = Appointments.getAllAppointments().size();

        AverageInterface getAve = n -> n / 12;

        Alert greatestMonth = new Alert(Alert.AlertType.INFORMATION);
        greatestMonth.setTitle("Average Appointments");
        greatestMonth.setContentText("Average Appointments Per Month: " + getAve.calculateAverage(total));
        greatestMonth.showAndWait();
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
