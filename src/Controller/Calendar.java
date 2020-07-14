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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.ResourceBundle;

public class Calendar implements Initializable {
    LocalDate today = LocalDate.now();

    @FXML
    private Button previousMonth;

    @FXML
    private Button nextMonth;
    @FXML
    private Label x1y1;
    @FXML
    private Label x2y1;
    @FXML
    private Label x3y1;
    @FXML
    private Label x4y1;
    @FXML
    private Label x5y1;
    @FXML
    private Label x6y1;
    @FXML
    private Label x7y1;
    @FXML
    private Label x1y2;
    @FXML
    private Label x2y2;
    @FXML
    private Label x3y2;
    @FXML
    private Label x4y2;
    @FXML
    private Label x5y2;
    @FXML
    private Label x6y2;
    @FXML
    private Label x7y2;
    @FXML
    private Label x1y3;
    @FXML
    private Label x2y3;
    @FXML
    private Label x1y4;
    @FXML
    private Label x2y4;
    @FXML
    private Label x1y5;
    @FXML
    private Label x1y6;
    @FXML
    private Label x1y7;
    @FXML
    private Label x2y5;
    @FXML
    private Label x3y3;
    @FXML
    private Label x4y3;
    @FXML
    private Label x5y3;
    @FXML
    private Label x6y3;
    @FXML
    private Label x7y3;
    @FXML
    private Label x3y4;
    @FXML
    private Label x4y4;
    @FXML
    private Label x3y5;
    @FXML
    private Label x4y5;
    @FXML
    private Label x5y5;
    @FXML
    private Label x5y4;
    @FXML
    private Label x6y4;
    @FXML
    private Label x7y4;
    @FXML
    private Label x7y5;
    @FXML
    private Label x6y5;
    @FXML
    private Label month;

    private static final LocalDate date = LocalDate.now();

    private ListView selectedView;
    @FXML
    private ListView<String> listx1y1;
    @FXML
    private ListView<String> listx1y2;
    @FXML
    private ListView<String> listx1y3;
    @FXML
    private ListView<String> listx1y4;
    @FXML
    private ListView<String> listx1y5;
    @FXML
    private ListView<String> listx1y6;
    @FXML
    private ListView<String> listx1y7;
    @FXML
    private ListView<String> listx2y1;
    @FXML
    private ListView<String> listx2y2;
    @FXML
    private ListView<String> listx2y3;
    @FXML
    private ListView<String> listx2y4;
    @FXML
    private ListView<String> listx2y5;
    @FXML
    private ListView<String> listx2y6;
    @FXML
    private ListView<String> listx2y7;
    @FXML
    private ListView<String> listx3y1;
    @FXML
    private ListView<String> listx3y2;
    @FXML
    private ListView<String> listx3y3;
    @FXML
    private ListView<String> listx3y4;
    @FXML
    private ListView<String> listx3y5;
    @FXML
    private ListView<String> listx3y6;
    @FXML
    private ListView<String> listx3y7;
    @FXML
    private ListView<String> listx4y1;
    @FXML
    private ListView<String> listx4y2;
    @FXML
    private ListView<String> listx4y3;
    @FXML
    private ListView<String> listx4y4;
    @FXML
    private ListView<String> listx4y5;
    @FXML
    private ListView<String> listx4y6;
    @FXML
    private ListView<String> listx4y7;
    @FXML
    private ListView<String> listx5y1;
    @FXML
    private ListView<String> listx5y2;
    @FXML
    private ListView<String> listx5y3;
    @FXML
    private ListView<String> listx5y4;
    @FXML
    private ListView<String> listx5y5;
    @FXML
    private ListView<String> listx5y6;
    @FXML
    private ListView<String> listx5y7;
    @FXML
    private ListView<String> listx6y1;
    @FXML
    private ListView<String> listx6y2;
    @FXML
    private ListView<String> listx6y3;
    @FXML
    private ListView<String> listx6y4;
    @FXML
    private ListView<String> listx6y5;
    @FXML
    private ListView<String> listx7y1;
    @FXML
    private ListView<String> listx7y2;
    @FXML
    private ListView<String> listx7y3;
    @FXML
    private ListView<String> listx7y4;
    @FXML
    private ListView<String> listx7y5;

    ObservableList<Appointment> list = FXCollections.observableArrayList();

    @FXML
    private DatePicker datePicker;

    public void clearDates() {
        listx1y1.getItems().clear();
        listx2y1.getItems().clear();
        listx3y1.getItems().clear();
        listx4y1.getItems().clear();
        listx5y1.getItems().clear();
        listx6y1.getItems().clear();
        listx7y1.getItems().clear();
        listx1y2.getItems().clear();
        listx2y2.getItems().clear();
        listx3y2.getItems().clear();
        listx4y2.getItems().clear();
        listx5y2.getItems().clear();
        listx6y2.getItems().clear();
        listx7y2.getItems().clear();
        listx1y3.getItems().clear();
        listx2y3.getItems().clear();
        listx3y3.getItems().clear();
        listx4y3.getItems().clear();
        listx5y3.getItems().clear();
        listx6y3.getItems().clear();
        listx7y3.getItems().clear();
        listx1y4.getItems().clear();
        listx2y4.getItems().clear();
        listx3y4.getItems().clear();
        listx4y4.getItems().clear();
        listx5y4.getItems().clear();
        listx6y4.getItems().clear();
        listx7y4.getItems().clear();
        listx1y5.getItems().clear();
        listx2y5.getItems().clear();
        listx3y5.getItems().clear();
        listx4y5.getItems().clear();
        listx5y5.getItems().clear();
        listx6y5.getItems().clear();
        listx7y5.getItems().clear();
    }

    private void populateAppointments() {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();
        if (datePicker.getValue() != null) {
            currentMonth = datePicker.getValue().getMonthValue();
        }
        if (datePicker.getValue() != null) {
            currentYear = datePicker.getValue().getYear();
        }
        for (int i = 0; i < Appointments.getAllAppointments().size(); i++) {
            Boolean sameYear = true;
            Boolean sameMonth = true;

            if (datePicker.getValue() != null && !(String.valueOf(Appointments.getAllAppointments().get(i).getStart()).substring(0, 4).equals(String.valueOf(currentYear)))) {
                sameYear = false;
            }

            Appointment a = Appointments.getAllAppointments().get(i);
            list.addAll(a);
            if (sameYear && sameMonth) {
                if (list.get(i).getStart() != null && Integer.parseInt(Appointments.getAllAppointments().get(i).getStart().substring(5, 7)) == (currentMonth)) {
                    for (int j = 1; j <= 22; j++) {
                        if (Integer.valueOf(list.get(i).getStart().substring(8, 10)) == j) {
                            if (Integer.valueOf(x1y1.getText()) == j) {
                                listx1y1.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y1.getText()) == j) {
                                listx2y1.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y1.getText()) == j) {
                                listx3y1.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y1.getText()) == j) {
                                listx4y1.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y1.getText()) == j) {
                                listx5y1.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y1.getText()) == j) {
                                listx6y1.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y1.getText()) == j) {
                                listx7y1.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x1y2.getText()) == j) {
                                listx1y2.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y2.getText()) == j) {
                                listx2y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y2.getText()) == j) {
                                listx3y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y2.getText()) == j) {
                                listx4y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y2.getText()) == j) {
                                listx5y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y2.getText()) == j) {
                                listx6y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y2.getText()) == j) {
                                listx7y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x1y3.getText()) == j) {
                                listx1y3.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y3.getText()) == j) {
                                listx2y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y3.getText()) == j) {
                                listx3y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y3.getText()) == j) {
                                listx4y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y3.getText()) == j) {
                                listx5y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y3.getText()) == j) {
                                listx6y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y3.getText()) == j) {
                                listx7y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x1y4.getText()) == j) {
                                listx1y4.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y4.getText()) == j) {
                                listx2y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y4.getText()) == j) {
                                listx3y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y4.getText()) == j) {
                                listx4y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y4.getText()) == j) {
                                listx5y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y4.getText()) == j) {
                                listx6y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y4.getText()) == j) {
                                listx7y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x1y5.getText()) == j) {
                                listx1y5.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y5.getText()) == j) {
                                listx2y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y5.getText()) == j) {
                                listx3y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y5.getText()) == j) {
                                listx4y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y5.getText()) == j) {
                                listx5y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y5.getText()) == j) {
                                listx6y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y5.getText()) == j) {
                                listx7y5.getItems().addAll(list.get(i).getTitle());
                            }
                        }
                    }
                    for (int j = 23; j <= 31; j++) {
                        if (Integer.valueOf(list.get(i).getStart().substring(8, 10)) == j) {
                            if (Integer.valueOf(x1y2.getText()) == j) {
                                listx1y2.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y2.getText()) == j) {
                                listx2y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y2.getText()) == j) {
                                listx3y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y2.getText()) == j) {
                                listx4y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y2.getText()) == j) {
                                listx5y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y2.getText()) == j) {
                                listx6y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y2.getText()) == j) {
                                listx7y2.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x1y3.getText()) == j) {
                                listx1y3.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y3.getText()) == j) {
                                listx2y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y3.getText()) == j) {
                                listx3y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y3.getText()) == j) {
                                listx4y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y3.getText()) == j) {
                                listx5y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y3.getText()) == j) {
                                listx6y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y3.getText()) == j) {
                                listx7y3.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x1y4.getText()) == j) {
                                listx1y4.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y4.getText()) == j) {
                                listx2y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y4.getText()) == j) {
                                listx3y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y4.getText()) == j) {
                                listx4y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y4.getText()) == j) {
                                listx5y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y4.getText()) == j) {
                                listx6y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y4.getText()) == j) {
                                listx7y4.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x1y5.getText()) == j) {
                                listx1y5.getItems().add(list.get(i).getTitle());
                            } else if (Integer.valueOf(x2y5.getText()) == j) {
                                listx2y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x3y5.getText()) == j) {
                                listx3y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x4y5.getText()) == j) {
                                listx4y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x5y5.getText()) == j) {
                                listx5y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x6y5.getText()) == j) {
                                listx6y5.getItems().addAll(list.get(i).getTitle());
                            } else if (Integer.valueOf(x7y5.getText()) == j) {
                                listx7y5.getItems().addAll(list.get(i).getTitle());
                            }
                        }
                    }
                }
            }
        }
    }

    private void populateCal() {
//        int thisYear = LocalDate.now().getYear();
//        int thisMonth = LocalDate.now().getMonthValue();
        refreshDate();
//        for (int j = 0; j < Appointments.getAllAppointments().size(); j++) {
//            Boolean sameYear = true;
//            Boolean sameMonth = true;
//            System.out.println(Appointments.getAllAppointments().get(j).getStart());
//            if (String.valueOf(Appointments.getAllAppointments().get(j).getStart()).substring(0, 4).equals(String.valueOf(thisYear))) {
//                System.out.println("Same year");
//            } else {
//                System.out.println("Different Year");
//            }
//            if (Appointments.getAllAppointments().get(j).getStart() != null &&
//                    String.valueOf(Appointments.getAllAppointments().get(j).getStart()).substring(5, 7).equals(String.valueOf(thisMonth))) {
//                System.out.println("Same month");
//            } else {
//                System.out.println("Different Month");
//            }
//        }
    }

    @FXML
    public void refreshDate() {
        clearDates();
        int thisYear = LocalDate.now().getYear();
        Month formMonth = Month.of(datePicker.getValue().getMonthValue());
        Integer formDay = today.getDayOfMonth();
        if (datePicker.getValue() != null) {
            thisYear = datePicker.getValue().getYear();
            formMonth = Month.of((datePicker.getValue().getMonthValue()));
            formDay = datePicker.getValue().getDayOfMonth();
        }
        Integer formYear = Integer.valueOf((String.valueOf(thisYear)));
        int monthCode = 0;
        int centuryCode = 0;
        int leapYearCode = 0;
        int dateNumber = 10;
        if (datePicker.getValue() != null) {
            dateNumber = datePicker.getValue().getDayOfMonth();
        }
        int dayNumber = 0;
        int yearCode = 0;

        if (today.getMonth() == Month.JANUARY) {
            monthCode = 0;
        }
        if (today.getMonth() == Month.FEBRUARY) {
            monthCode = 3;
        }
        if (today.getMonth() == Month.MARCH) {
            monthCode = 3;
        }
        if (today.getMonth() == Month.APRIL) {
            monthCode = 6;
        }
        if (today.getMonth() == Month.MAY) {
            monthCode = 1;
        }
        if (today.getMonth() == Month.JUNE) {
            monthCode = 4;
        }
        if (today.getMonth() == Month.JULY) {
            monthCode = 6;
        }
        if (today.getMonth() == Month.AUGUST) {
            monthCode = 2;
        }
        if (today.getMonth() == Month.SEPTEMBER) {
            monthCode = 5;
        }
        if (today.getMonth() == Month.OCTOBER) {
            monthCode = 0;
        }
        if (today.getMonth() == Month.NOVEMBER) {
            monthCode = 3;
        }
        if (today.getMonth() == Month.DECEMBER) {
            monthCode = 5;
        }

        if (formYear >= 1700 && formYear < 1800) {
            centuryCode = 4;
        }
        if (formYear >= 1800 && formYear < 1900) {
            centuryCode = 2;
        }
        if (formYear >= 1900 && formYear < 2000) {
            centuryCode = 0;
        }
        if (formYear >= 2000 && formYear < 2100) {
            centuryCode = 6;
        }
        if (formYear >= 2100 && formYear < 2200) {
            centuryCode = 4;
        }
        if (formYear >= 2200 && formYear < 2300) {
            centuryCode = 2;
        }
        if (formYear > 2200 && formYear < 2400) {
            centuryCode = 0;
        }

        if (today.getYear() >= 1700 && formYear < 1800) {
            centuryCode = 4;
        }
        if (today.getYear() >= 1800 && formYear < 1900) {
            centuryCode = 2;
        }
        if (today.getYear() >= 1900 && formYear < 2000) {
            centuryCode = 0;
        }
        if (today.getYear() >= 2000 && formYear < 2100) {
            centuryCode = 6;
        }
        if (today.getYear() >= 2100 && formYear < 2200) {
            centuryCode = 4;
        }
        if (today.getYear() >= 2200 && formYear < 2300) {
            centuryCode = 2;
        }
        if (today.getYear() > 2200 && formYear < 2400) {
            centuryCode = 0;
        }
        /**
         *
         *
         */
        if (formMonth == Month.JANUARY) {
            monthCode = 0;
        }
        if (formMonth == Month.FEBRUARY) {
            monthCode = 3;
        }
        if (formMonth == Month.MARCH) {
            monthCode = 3;
        }
        if (formMonth == Month.APRIL) {
            monthCode = 6;
        }
        if (formMonth == Month.MAY) {
            monthCode = 1;
        }
        if (formMonth == Month.JUNE) {
            monthCode = 4;
        }
        if (formMonth == Month.JULY) {
            monthCode = 6;
        }
        if (formMonth == Month.AUGUST) {
            monthCode = 2;
        }
        if (formMonth == Month.SEPTEMBER) {
            monthCode = 5;
        }
        if (formMonth == Month.OCTOBER) {
            monthCode = 0;
        }
        if (formMonth == Month.NOVEMBER) {
            monthCode = 3;
        }
        if (formMonth == Month.DECEMBER) {
            monthCode = 5;
        }

        int yY = (Integer.parseInt(formYear.toString().substring(2)));
        yearCode = (yY + (yY / 4)) % 7;
        if ((Year.isLeap(thisYear) && formMonth == Month.FEBRUARY) || (Year.isLeap(thisYear) && formMonth == Month.JANUARY)) {
            leapYearCode = 1;
        }
//        System.out.println(yearCode + " " + monthCode + " " + centuryCode + " " + dateNumber + " " + leapYearCode);
        dayNumber = ((yearCode + monthCode + centuryCode + dateNumber - leapYearCode) % 7);
//        System.out.println(dayNumber);
        int testFirstDay = ((1 + yearCode + monthCode + centuryCode - leapYearCode) % 7);

        month.setText(String.valueOf(today.getMonth()));
        if (datePicker.getValue() != null) {
            month.setText(String.valueOf(datePicker.getValue().getMonth()));
        }

        if (formMonth == Month.SEPTEMBER || formMonth == Month.APRIL || formMonth == Month.JUNE || formMonth == Month.NOVEMBER) {
            int i = 0;
            if (testFirstDay == 0) {
                i = 0;
            }
            if (testFirstDay == 1) {
                i = -1;
            }
            if (testFirstDay == 2) {
                i = -2;
            }
            if (testFirstDay == 3) {
                i = -3;
            }
            if (testFirstDay == 4) {
                i = -4;
            }
            if (testFirstDay == 5) {
                i = -5;
            }
            if (testFirstDay == 6) {
                i = -6;
            }
            x1y1.setText(String.valueOf(1 + i));
            x2y1.setText(String.valueOf(2 + i));
            x3y1.setText(String.valueOf(3 + i));
            x4y1.setText(String.valueOf(4 + i));
            x5y1.setText(String.valueOf(5 + i));
            x6y1.setText(String.valueOf(6 + i));
            x7y1.setText(String.valueOf(7 + i));
            x1y2.setText(String.valueOf(8 + i));
            x2y2.setText(String.valueOf(9 + i));
            x3y2.setText(String.valueOf(10 + i));
            x4y2.setText(String.valueOf(11 + i));
            x5y2.setText(String.valueOf(12 + i));
            x6y2.setText(String.valueOf(13 + i));
            x7y2.setText(String.valueOf(14 + i));
            x1y3.setText(String.valueOf(15 + i));
            x2y3.setText(String.valueOf(16 + i));
            x3y3.setText(String.valueOf(17 + i));
            x4y3.setText(String.valueOf(18 + i));
            x5y3.setText(String.valueOf(19 + i));
            x6y3.setText(String.valueOf(20 + i));
            x7y3.setText(String.valueOf(21 + i));
            x1y4.setText(String.valueOf(22 + i));
            x2y4.setText(String.valueOf(23 + i));
            x3y4.setText(String.valueOf(24 + i));
            x4y4.setText(String.valueOf(25 + i));
            x5y4.setText(String.valueOf(26 + i));
            x6y4.setText(String.valueOf(27 + i));
            x7y4.setText(String.valueOf(28 + i));
            x1y5.setText(String.valueOf(29 + i));
            x2y5.setText(String.valueOf(30 + i));
            x3y5.setText(String.valueOf(1 + i));
            x4y5.setText(String.valueOf(2 + i));
            x5y5.setText(String.valueOf(3 + i));
            x6y5.setText(String.valueOf(4 + i));
            x7y5.setText(String.valueOf(5 + i));
            if (testFirstDay == 1) {
                x1y1.setText(String.valueOf(31));
                x3y5.setText(String.valueOf(30));
                x4y5.setText(String.valueOf(1));
                x5y5.setText(String.valueOf(2));
                x6y5.setText(String.valueOf(3));
                x7y5.setText(String.valueOf(4));
            }
            if (testFirstDay == 2) {
                x1y1.setText(String.valueOf(30));
                x2y1.setText(String.valueOf(31));
                x3y5.setText(String.valueOf(29));
                x4y5.setText(String.valueOf(30));
                x6y5.setText(String.valueOf(2));
                x7y5.setText(String.valueOf(3));
            }
            if (testFirstDay == 3) {
                x1y1.setText(String.valueOf(29));
                x2y1.setText(String.valueOf(30));
                x3y1.setText(String.valueOf(31));
                x3y5.setText(String.valueOf(28));
                x4y5.setText(String.valueOf(29));
                x5y5.setText(String.valueOf(30));
                x6y5.setText(String.valueOf(1));
                x7y5.setText(String.valueOf(2));
            }
            if (testFirstDay == 4) {
                x1y1.setText(String.valueOf(28));
                x2y1.setText(String.valueOf(29));
                x3y1.setText(String.valueOf(30));
                x4y1.setText(String.valueOf(31));
                x3y5.setText(String.valueOf(27));
                x4y5.setText(String.valueOf(28));
                x5y5.setText(String.valueOf(29));
                x6y5.setText(String.valueOf(30));
                x7y5.setText(String.valueOf(1));
            }
            if (testFirstDay == 5) {
                x1y1.setText(String.valueOf(27));
                x2y1.setText(String.valueOf(28));
                x3y1.setText(String.valueOf(29));
                x4y1.setText(String.valueOf(30));
                x5y1.setText(String.valueOf(31));
                x1y5.setText(String.valueOf(24));
                x2y5.setText(String.valueOf(25));
                x3y5.setText(String.valueOf(26));
                x4y5.setText(String.valueOf(27));
                x5y5.setText(String.valueOf(28));
                x6y5.setText(String.valueOf(29));
                x7y5.setText(String.valueOf(30));
            }
            if (testFirstDay == 6) {
                x1y1.setText(String.valueOf(26));
                x2y1.setText(String.valueOf(27));
                x3y1.setText(String.valueOf(28));
                x4y1.setText(String.valueOf(29));
                x5y1.setText(String.valueOf(30));
                x6y1.setText(String.valueOf(31));
                x7y1.setText(String.valueOf(1));
                x1y5.setText(String.valueOf(23));
                x2y5.setText(String.valueOf(24));
                x3y5.setText(String.valueOf(25));
                x4y5.setText(String.valueOf(26));
                x5y5.setText(String.valueOf(27));
                x6y5.setText(String.valueOf(28));
                x7y5.setText(String.valueOf(29));
            }
            populateAppointments();

        }

        if (formMonth == Month.JANUARY || formMonth == Month.MAY) {
            int i = 0;
            if (testFirstDay == 0) {
                i = 0;
            }
            if (testFirstDay == 1) {
                i = -1;
            }
            if (testFirstDay == 2) {
                i = -2;
            }
            if (testFirstDay == 3) {
                i = -3;
            }
            if (testFirstDay == 4) {
                i = -4;
            }
            if (testFirstDay == 5) {
                i = -5;
            }
            if (testFirstDay == 6) {
                i = -6;
            }
            x1y1.setText(String.valueOf(1 + i));
            x2y1.setText(String.valueOf(2 + i));
            x3y1.setText(String.valueOf(3 + i));
            x4y1.setText(String.valueOf(4 + i));
            x5y1.setText(String.valueOf(5 + i));
            x6y1.setText(String.valueOf(6 + i));
            x7y1.setText(String.valueOf(7 + i));
            x1y2.setText(String.valueOf(8 + i));
            x2y2.setText(String.valueOf(9 + i));
            x3y2.setText(String.valueOf(10 + i));
            x4y2.setText(String.valueOf(11 + i));
            x5y2.setText(String.valueOf(12 + i));
            x6y2.setText(String.valueOf(13 + i));
            x7y2.setText(String.valueOf(14 + i));
            x1y3.setText(String.valueOf(15 + i));
            x2y3.setText(String.valueOf(16 + i));
            x3y3.setText(String.valueOf(17 + i));
            x4y3.setText(String.valueOf(18 + i));
            x5y3.setText(String.valueOf(19 + i));
            x6y3.setText(String.valueOf(20 + i));
            x7y3.setText(String.valueOf(21 + i));
            x1y4.setText(String.valueOf(22 + i));
            x2y4.setText(String.valueOf(23 + i));
            x3y4.setText(String.valueOf(24 + i));
            x4y4.setText(String.valueOf(25 + i));
            x5y4.setText(String.valueOf(26 + i));
            x6y4.setText(String.valueOf(27 + i));
            x7y4.setText(String.valueOf(28 + i));
            x1y5.setText(String.valueOf(29 + i));
            x2y5.setText(String.valueOf(30 + i));
            x3y5.setText(String.valueOf(31 + i));
            x4y5.setText(String.valueOf(1 + i));
            x5y5.setText(String.valueOf(2 + i));
            x6y5.setText(String.valueOf(3 + i));
            x7y5.setText(String.valueOf(4 + i));
            if (testFirstDay == 1) {
                x1y1.setText(String.valueOf(31));
                x2y1.setText(String.valueOf(1));
                x3y1.setText(String.valueOf(2));
                x4y1.setText(String.valueOf(3));
                x5y1.setText(String.valueOf(4));
                x6y1.setText(String.valueOf(5));
                x7y1.setText(String.valueOf(6));
                x1y5.setText(String.valueOf(28));
                x2y5.setText(String.valueOf(29));
                x3y5.setText(String.valueOf(30));
                x4y5.setText(String.valueOf(31));
                x5y5.setText(String.valueOf(1));
                x6y5.setText(String.valueOf(2));
                x7y5.setText(String.valueOf(3));
            }
            if (testFirstDay == 2) {
                x1y1.setText(String.valueOf(30));
                x2y1.setText(String.valueOf(31));
                x3y1.setText(String.valueOf(1));
                x4y1.setText(String.valueOf(2));
                x5y1.setText(String.valueOf(3));
                x6y1.setText(String.valueOf(4));
                x7y1.setText(String.valueOf(5));
                x1y5.setText(String.valueOf(27));
                x2y5.setText(String.valueOf(28));
                x3y5.setText(String.valueOf(29));
                x4y5.setText(String.valueOf(30));
                x5y5.setText(String.valueOf(31));
                x6y5.setText(String.valueOf(1));
                x7y5.setText(String.valueOf(2));
            }
            if (testFirstDay == 3) {
                x1y1.setText(String.valueOf(29));
                x2y1.setText(String.valueOf(30));
                x3y1.setText(String.valueOf(31));
                x4y1.setText(String.valueOf(1));
                x5y1.setText(String.valueOf(2));
                x6y1.setText(String.valueOf(3));
                x7y1.setText(String.valueOf(4));
                x1y5.setText(String.valueOf(26));
                x2y5.setText(String.valueOf(27));
                x3y5.setText(String.valueOf(28));
                x4y5.setText(String.valueOf(29));
                x5y5.setText(String.valueOf(30));
                x6y5.setText(String.valueOf(31));
                x7y5.setText(String.valueOf(1));
            }
            if (testFirstDay == 4) {
                x1y1.setText(String.valueOf(28));
                x2y1.setText(String.valueOf(29));
                x3y1.setText(String.valueOf(30));
                x4y1.setText(String.valueOf(31));
                x5y1.setText(String.valueOf(1));
                x6y1.setText(String.valueOf(2));
                x7y1.setText(String.valueOf(3));
                x1y5.setText(String.valueOf(25));
                x2y5.setText(String.valueOf(26));
                x3y5.setText(String.valueOf(27));
                x4y5.setText(String.valueOf(28));
                x5y5.setText(String.valueOf(29));
                x6y5.setText(String.valueOf(30));
                x7y5.setText(String.valueOf(31));
            }
            if (testFirstDay == 5) {
                x1y1.setText(String.valueOf(27));
                x2y1.setText(String.valueOf(28));
                x3y1.setText(String.valueOf(29));
                x4y1.setText(String.valueOf(30));
                x5y1.setText(String.valueOf(31));
                x6y1.setText(String.valueOf(1));
                x7y1.setText(String.valueOf(2));
                x1y5.setText(String.valueOf(24));
                x2y5.setText(String.valueOf(25));
                x3y5.setText(String.valueOf(26));
                x4y5.setText(String.valueOf(27));
                x5y5.setText(String.valueOf(28));
                x6y5.setText(String.valueOf(29));
                x7y5.setText(String.valueOf(30));
            }
            if (testFirstDay == 6) {
                x1y1.setText(String.valueOf(26));
                x2y1.setText(String.valueOf(27));
                x3y1.setText(String.valueOf(28));
                x4y1.setText(String.valueOf(29));
                x5y1.setText(String.valueOf(30));
                x6y1.setText(String.valueOf(31));
                x7y1.setText(String.valueOf(1));
                x1y5.setText(String.valueOf(23));
                x2y5.setText(String.valueOf(24));
                x3y5.setText(String.valueOf(25));
                x4y5.setText(String.valueOf(26));
                x5y5.setText(String.valueOf(27));
                x6y5.setText(String.valueOf(28));
                x7y5.setText(String.valueOf(29));
            }
            populateAppointments();
        }
        if (formMonth == Month.JULY || formMonth == Month.OCTOBER || formMonth == Month.DECEMBER) {
            int i = 0;
            if (testFirstDay == 0) {
                i = 0;
            }
            if (testFirstDay == 1) {
                i = -1;
            }
            if (testFirstDay == 2) {
                i = -2;
            }
            if (testFirstDay == 3) {
                i = -3;
            }
            if (testFirstDay == 4) {
                i = -4;
            }
            if (testFirstDay == 5) {
                i = -5;
            }
            if (testFirstDay == 6) {
                i = -6;
            }
            x1y1.setText(String.valueOf(1 + i));
            x2y1.setText(String.valueOf(2 + i));
            x3y1.setText(String.valueOf(3 + i));
            x4y1.setText(String.valueOf(4 + i));
            x5y1.setText(String.valueOf(5 + i));
            x6y1.setText(String.valueOf(6 + i));
            x7y1.setText(String.valueOf(7 + i));
            x1y2.setText(String.valueOf(8 + i));
            x2y2.setText(String.valueOf(9 + i));
            x3y2.setText(String.valueOf(10 + i));
            x4y2.setText(String.valueOf(11 + i));
            x5y2.setText(String.valueOf(12 + i));
            x6y2.setText(String.valueOf(13 + i));
            x7y2.setText(String.valueOf(14 + i));
            x1y3.setText(String.valueOf(15 + i));
            x2y3.setText(String.valueOf(16 + i));
            x3y3.setText(String.valueOf(17 + i));
            x4y3.setText(String.valueOf(18 + i));
            x5y3.setText(String.valueOf(19 + i));
            x6y3.setText(String.valueOf(20 + i));
            x7y3.setText(String.valueOf(21 + i));
            x1y4.setText(String.valueOf(22 + i));
            x2y4.setText(String.valueOf(23 + i));
            x3y4.setText(String.valueOf(24 + i));
            x4y4.setText(String.valueOf(25 + i));
            x5y4.setText(String.valueOf(26 + i));
            x6y4.setText(String.valueOf(27 + i));
            x7y4.setText(String.valueOf(28 + i));
            x1y5.setText(String.valueOf(29 + i));
            x2y5.setText(String.valueOf(30 + i));
            x3y5.setText(String.valueOf(31 + i));
            x4y5.setText(String.valueOf(1 + i));
            x5y5.setText(String.valueOf(2 + i));
            x6y5.setText(String.valueOf(3 + i));
            x7y5.setText(String.valueOf(4 + i));
            if (testFirstDay == 1) {
                x1y1.setText(String.valueOf(30));
                x2y1.setText(String.valueOf(1));
                x3y1.setText(String.valueOf(2));
                x4y1.setText(String.valueOf(3));
                x5y1.setText(String.valueOf(4));
                x6y1.setText(String.valueOf(5));
                x7y1.setText(String.valueOf(6));
                x1y5.setText(String.valueOf(28));
                x2y5.setText(String.valueOf(29));
                x3y5.setText(String.valueOf(30));
                x4y5.setText(String.valueOf(31));
                x5y5.setText(String.valueOf(1));
                x6y5.setText(String.valueOf(2));
                x7y5.setText(String.valueOf(3));
            }
            if (testFirstDay == 2) {
                x1y1.setText(String.valueOf(29));
                x2y1.setText(String.valueOf(30));
                x3y1.setText(String.valueOf(1));
                x4y1.setText(String.valueOf(2));
                x5y1.setText(String.valueOf(3));
                x6y1.setText(String.valueOf(4));
                x7y1.setText(String.valueOf(5));
                x1y5.setText(String.valueOf(27));
                x2y5.setText(String.valueOf(28));
                x3y5.setText(String.valueOf(29));
                x4y5.setText(String.valueOf(30));
                x5y5.setText(String.valueOf(31));
                x6y5.setText(String.valueOf(1));
                x7y5.setText(String.valueOf(2));
            }
            if (testFirstDay == 3) {
                x1y1.setText(String.valueOf(28));
                x2y1.setText(String.valueOf(29));
                x3y1.setText(String.valueOf(30));
                x4y1.setText(String.valueOf(1));
                x5y1.setText(String.valueOf(2));
                x6y1.setText(String.valueOf(3));
                x7y1.setText(String.valueOf(4));
                x1y5.setText(String.valueOf(26));
                x2y5.setText(String.valueOf(27));
                x3y5.setText(String.valueOf(28));
                x4y5.setText(String.valueOf(29));
                x5y5.setText(String.valueOf(30));
                x6y5.setText(String.valueOf(31));
                x7y5.setText(String.valueOf(1));
            }
            if (testFirstDay == 4) {
                x1y1.setText(String.valueOf(27));
                x2y1.setText(String.valueOf(28));
                x3y1.setText(String.valueOf(29));
                x4y1.setText(String.valueOf(30));
                x5y1.setText(String.valueOf(1));
                x6y1.setText(String.valueOf(2));
                x7y1.setText(String.valueOf(3));
                x1y5.setText(String.valueOf(25));
                x2y5.setText(String.valueOf(26));
                x3y5.setText(String.valueOf(27));
                x4y5.setText(String.valueOf(28));
                x5y5.setText(String.valueOf(29));
                x6y5.setText(String.valueOf(30));
                x7y5.setText(String.valueOf(31));
            }
            if (testFirstDay == 5) {
                x1y1.setText(String.valueOf(26));
                x2y1.setText(String.valueOf(27));
                x3y1.setText(String.valueOf(28));
                x4y1.setText(String.valueOf(29));
                x5y1.setText(String.valueOf(30));
                x6y1.setText(String.valueOf(1));
                x7y1.setText(String.valueOf(2));
                x1y5.setText(String.valueOf(24));
                x2y5.setText(String.valueOf(25));
                x3y5.setText(String.valueOf(26));
                x4y5.setText(String.valueOf(27));
                x5y5.setText(String.valueOf(28));
                x6y5.setText(String.valueOf(29));
                x7y5.setText(String.valueOf(30));
            }
            if (testFirstDay == 6) {
                x1y1.setText(String.valueOf(25));
                x2y1.setText(String.valueOf(26));
                x3y1.setText(String.valueOf(27));
                x4y1.setText(String.valueOf(28));
                x5y1.setText(String.valueOf(29));
                x6y1.setText(String.valueOf(30));
                x7y1.setText(String.valueOf(1));
                x1y5.setText(String.valueOf(23));
                x2y5.setText(String.valueOf(24));
                x3y5.setText(String.valueOf(25));
                x4y5.setText(String.valueOf(26));
                x5y5.setText(String.valueOf(27));
                x6y5.setText(String.valueOf(28));
                x7y5.setText(String.valueOf(29));
            }
            populateAppointments();
        }
        if (formMonth == Month.MARCH) {
            int i = 0;
            if (testFirstDay == 0) {
                i = 0;
            }
            if (testFirstDay == 1) {
                i = -1;
            }
            if (testFirstDay == 2) {
                i = -2;
            }
            if (testFirstDay == 3) {
                i = -3;
            }
            if (testFirstDay == 4) {
                i = -4;
            }
            if (testFirstDay == 5) {
                i = -5;
            }
            if (testFirstDay == 6) {
                i = -6;
            }
            x1y1.setText(String.valueOf(1 + i));
            x2y1.setText(String.valueOf(2 + i));
            x3y1.setText(String.valueOf(3 + i));
            x4y1.setText(String.valueOf(4 + i));
            x5y1.setText(String.valueOf(5 + i));
            x6y1.setText(String.valueOf(6 + i));
            x7y1.setText(String.valueOf(7 + i));
            x1y2.setText(String.valueOf(8 + i));
            x2y2.setText(String.valueOf(9 + i));
            x3y2.setText(String.valueOf(10 + i));
            x4y2.setText(String.valueOf(11 + i));
            x5y2.setText(String.valueOf(12 + i));
            x6y2.setText(String.valueOf(13 + i));
            x7y2.setText(String.valueOf(14 + i));
            x1y3.setText(String.valueOf(15 + i));
            x2y3.setText(String.valueOf(16 + i));
            x3y3.setText(String.valueOf(17 + i));
            x4y3.setText(String.valueOf(18 + i));
            x5y3.setText(String.valueOf(19 + i));
            x6y3.setText(String.valueOf(20 + i));
            x7y3.setText(String.valueOf(21 + i));
            x1y4.setText(String.valueOf(22 + i));
            x2y4.setText(String.valueOf(23 + i));
            x3y4.setText(String.valueOf(24 + i));
            x4y4.setText(String.valueOf(25 + i));
            x5y4.setText(String.valueOf(26 + i));
            x6y4.setText(String.valueOf(27 + i));
            x7y4.setText(String.valueOf(28 + i));
            x1y5.setText(String.valueOf(29 + i));
            x2y5.setText(String.valueOf(30 + i));
            x3y5.setText(String.valueOf(31 + i));
            x4y5.setText(String.valueOf(1 + i));
            x5y5.setText(String.valueOf(2 + i));
            x6y5.setText(String.valueOf(3 + i));
            x7y5.setText(String.valueOf(4 + i));
            if (testFirstDay == 1) {
                x1y1.setText(String.valueOf(28));
                x1y5.setText(String.valueOf(28));
                x2y5.setText(String.valueOf(29));
                x3y5.setText(String.valueOf(30));
                x4y5.setText(String.valueOf(31));
                x5y5.setText(String.valueOf(1));
                x6y5.setText(String.valueOf(2));
                x7y5.setText(String.valueOf(3));
            }
            if (testFirstDay == 2) {
                x1y1.setText(String.valueOf(27));
                x2y1.setText(String.valueOf(28));
                x1y5.setText(String.valueOf(27));
                x2y5.setText(String.valueOf(28));
                x3y5.setText(String.valueOf(29));
                x4y5.setText(String.valueOf(30));
                x5y5.setText(String.valueOf(31));
                x6y5.setText(String.valueOf(1));
                x7y5.setText(String.valueOf(2));
            }
            if (testFirstDay == 3) {
                x1y1.setText(String.valueOf(26));
                x2y1.setText(String.valueOf(27));
                x3y1.setText(String.valueOf(28));
                x1y5.setText(String.valueOf(26));
                x2y5.setText(String.valueOf(27));
                x3y5.setText(String.valueOf(28));
                x4y5.setText(String.valueOf(29));
                x5y5.setText(String.valueOf(30));
                x6y5.setText(String.valueOf(31));
                x7y5.setText(String.valueOf(1));
            }
            if (testFirstDay == 4) {
                x1y1.setText(String.valueOf(25));
                x2y1.setText(String.valueOf(26));
                x3y1.setText(String.valueOf(27));
                x4y1.setText(String.valueOf(28));
                x1y5.setText(String.valueOf(25));
                x2y5.setText(String.valueOf(26));
                x3y5.setText(String.valueOf(27));
                x4y5.setText(String.valueOf(28));
                x5y5.setText(String.valueOf(29));
                x6y5.setText(String.valueOf(30));
                x7y5.setText(String.valueOf(31));
            }
            if (testFirstDay == 5) {
                x1y1.setText(String.valueOf(24));
                x2y1.setText(String.valueOf(25));
                x3y1.setText(String.valueOf(26));
                x4y1.setText(String.valueOf(27));
                x5y1.setText(String.valueOf(28));
                x1y5.setText(String.valueOf(24));
                x2y5.setText(String.valueOf(25));
                x3y5.setText(String.valueOf(26));
                x4y5.setText(String.valueOf(27));
                x5y5.setText(String.valueOf(28));
                x6y5.setText(String.valueOf(29));
                x7y5.setText(String.valueOf(30));
            }
            if (testFirstDay == 6) {
                x1y1.setText(String.valueOf(23));
                x2y1.setText(String.valueOf(24));
                x3y1.setText(String.valueOf(25));
                x4y1.setText(String.valueOf(26));
                x5y1.setText(String.valueOf(27));
                x6y1.setText(String.valueOf(28));
                x1y5.setText(String.valueOf(23));
                x2y5.setText(String.valueOf(24));
                x3y5.setText(String.valueOf(25));
                x4y5.setText(String.valueOf(26));
                x5y5.setText(String.valueOf(27));
                x6y5.setText(String.valueOf(28));
                x7y5.setText(String.valueOf(29));
            }
            populateAppointments();

        }
        if (formMonth == Month.MARCH && Year.isLeap(thisYear)) {
            int i = 0;
            if (testFirstDay == 0) {
                i = 0;
            }
            if (testFirstDay == 1) {
                i = -1;
            }
            if (testFirstDay == 2) {
                i = -2;
            }
            if (testFirstDay == 3) {
                i = -3;
            }
            if (testFirstDay == 4) {
                i = -4;
            }
            if (testFirstDay == 5) {
                i = -5;
            }
            if (testFirstDay == 6) {
                i = -6;
            }
            x1y1.setText(String.valueOf(1 + i));
            x2y1.setText(String.valueOf(2 + i));
            x3y1.setText(String.valueOf(3 + i));
            x4y1.setText(String.valueOf(4 + i));
            x5y1.setText(String.valueOf(5 + i));
            x6y1.setText(String.valueOf(6 + i));
            x7y1.setText(String.valueOf(7 + i));
            x1y2.setText(String.valueOf(8 + i));
            x2y2.setText(String.valueOf(9 + i));
            x3y2.setText(String.valueOf(10 + i));
            x4y2.setText(String.valueOf(11 + i));
            x5y2.setText(String.valueOf(12 + i));
            x6y2.setText(String.valueOf(13 + i));
            x7y2.setText(String.valueOf(14 + i));
            x1y3.setText(String.valueOf(15 + i));
            x2y3.setText(String.valueOf(16 + i));
            x3y3.setText(String.valueOf(17 + i));
            x4y3.setText(String.valueOf(18 + i));
            x5y3.setText(String.valueOf(19 + i));
            x6y3.setText(String.valueOf(20 + i));
            x7y3.setText(String.valueOf(21 + i));
            x1y4.setText(String.valueOf(22 + i));
            x2y4.setText(String.valueOf(23 + i));
            x3y4.setText(String.valueOf(24 + i));
            x4y4.setText(String.valueOf(25 + i));
            x5y4.setText(String.valueOf(26 + i));
            x6y4.setText(String.valueOf(27 + i));
            x7y4.setText(String.valueOf(28 + i));
            x1y5.setText(String.valueOf(29 + i));
            x2y5.setText(String.valueOf(30 + i));
            x3y5.setText(String.valueOf(31 + i));
            x4y5.setText(String.valueOf(1 + i));
            x5y5.setText(String.valueOf(2 + i));
            x6y5.setText(String.valueOf(3 + i));
            x7y5.setText(String.valueOf(4 + i));
            if (testFirstDay == 1) {
                x1y1.setText(String.valueOf(29));
                x1y5.setText(String.valueOf(28));
                x2y5.setText(String.valueOf(29));
                x3y5.setText(String.valueOf(30));
                x4y5.setText(String.valueOf(31));
                x5y5.setText(String.valueOf(1));
                x6y5.setText(String.valueOf(2));
                x7y5.setText(String.valueOf(3));
            }
            if (testFirstDay == 2) {
                x1y1.setText(String.valueOf(28));
                x2y1.setText(String.valueOf(29));
                x1y5.setText(String.valueOf(27));
                x2y5.setText(String.valueOf(28));
                x3y5.setText(String.valueOf(29));
                x4y5.setText(String.valueOf(30));
                x5y5.setText(String.valueOf(31));
                x6y5.setText(String.valueOf(1));
                x7y5.setText(String.valueOf(2));
            }
            if (testFirstDay == 3) {
                x1y1.setText(String.valueOf(27));
                x2y1.setText(String.valueOf(28));
                x3y1.setText(String.valueOf(29));
                x1y5.setText(String.valueOf(26));
                x2y5.setText(String.valueOf(27));
                x3y5.setText(String.valueOf(28));
                x4y5.setText(String.valueOf(29));
                x5y5.setText(String.valueOf(30));
                x6y5.setText(String.valueOf(31));
                x7y5.setText(String.valueOf(1));
            }
            if (testFirstDay == 4) {
                x1y1.setText(String.valueOf(26));
                x2y1.setText(String.valueOf(27));
                x3y1.setText(String.valueOf(28));
                x4y1.setText(String.valueOf(29));
                x1y5.setText(String.valueOf(25));
                x2y5.setText(String.valueOf(26));
                x3y5.setText(String.valueOf(27));
                x4y5.setText(String.valueOf(28));
                x5y5.setText(String.valueOf(29));
                x6y5.setText(String.valueOf(30));
                x7y5.setText(String.valueOf(31));
            }
            if (testFirstDay == 5) {
                x1y1.setText(String.valueOf(25));
                x2y1.setText(String.valueOf(26));
                x3y1.setText(String.valueOf(27));
                x4y1.setText(String.valueOf(28));
                x5y1.setText(String.valueOf(29));
                x1y5.setText(String.valueOf(24));
                x2y5.setText(String.valueOf(25));
                x3y5.setText(String.valueOf(26));
                x4y5.setText(String.valueOf(27));
                x5y5.setText(String.valueOf(28));
                x6y5.setText(String.valueOf(29));
                x7y5.setText(String.valueOf(30));
            }
            if (testFirstDay == 6) {
                x1y1.setText(String.valueOf(24));
                x2y1.setText(String.valueOf(25));
                x3y1.setText(String.valueOf(26));
                x4y1.setText(String.valueOf(27));
                x5y1.setText(String.valueOf(28));
                x6y1.setText(String.valueOf(29));
                x1y5.setText(String.valueOf(23));
                x2y5.setText(String.valueOf(24));
                x3y5.setText(String.valueOf(25));
                x4y5.setText(String.valueOf(26));
                x5y5.setText(String.valueOf(27));
                x6y5.setText(String.valueOf(28));
                x7y5.setText(String.valueOf(29));
            }
            populateAppointments();

        }
        if (formMonth == Month.AUGUST) {
            int i = 0;
            if (testFirstDay == 0) {
                i = 0;
            }
            if (testFirstDay == 1) {
                i = -1;
            }
            if (testFirstDay == 2) {
                i = -2;
            }
            if (testFirstDay == 3) {
                i = -3;
            }
            if (testFirstDay == 4) {
                i = -4;
            }
            if (testFirstDay == 5) {
                i = -5;
            }
            if (testFirstDay == 6) {
                i = -6;
            }
            x1y1.setText(String.valueOf(1 + i));
            x2y1.setText(String.valueOf(2 + i));
            x3y1.setText(String.valueOf(3 + i));
            x4y1.setText(String.valueOf(4 + i));
            x5y1.setText(String.valueOf(5 + i));
            x6y1.setText(String.valueOf(6 + i));
            x7y1.setText(String.valueOf(7 + i));
            x1y2.setText(String.valueOf(8 + i));
            x2y2.setText(String.valueOf(9 + i));
            x3y2.setText(String.valueOf(10 + i));
            x4y2.setText(String.valueOf(11 + i));
            x5y2.setText(String.valueOf(12 + i));
            x6y2.setText(String.valueOf(13 + i));
            x7y2.setText(String.valueOf(14 + i));
            x1y3.setText(String.valueOf(15 + i));
            x2y3.setText(String.valueOf(16 + i));
            x3y3.setText(String.valueOf(17 + i));
            x4y3.setText(String.valueOf(18 + i));
            x5y3.setText(String.valueOf(19 + i));
            x6y3.setText(String.valueOf(20 + i));
            x7y3.setText(String.valueOf(21 + i));
            x1y4.setText(String.valueOf(22 + i));
            x2y4.setText(String.valueOf(23 + i));
            x3y4.setText(String.valueOf(24 + i));
            x4y4.setText(String.valueOf(25 + i));
            x5y4.setText(String.valueOf(26 + i));
            x6y4.setText(String.valueOf(27 + i));
            x7y4.setText(String.valueOf(28 + i));
            x1y5.setText(String.valueOf(29 + i));
            x2y5.setText(String.valueOf(30 + i));
            x3y5.setText(String.valueOf(31 + i));
            x4y5.setText(String.valueOf(1 + i));
            x5y5.setText(String.valueOf(2 + i));
            x6y5.setText(String.valueOf(3 + i));
            x7y5.setText(String.valueOf(4 + i));
            if (testFirstDay == 1) {
                x1y1.setText(String.valueOf(31));
                x3y5.setText(String.valueOf(30));
                x4y5.setText(String.valueOf(31));
                x5y5.setText(String.valueOf(1));
                x6y5.setText(String.valueOf(2));
                x7y5.setText(String.valueOf(3));
            }
            if (testFirstDay == 2) {
                x1y1.setText(String.valueOf(30));
                x2y1.setText(String.valueOf(31));
                x3y5.setText(String.valueOf(29));
                x4y5.setText(String.valueOf(30));
                x5y5.setText(String.valueOf(31));
                x6y5.setText(String.valueOf(1));
                x7y5.setText(String.valueOf(2));
            }
            if (testFirstDay == 3) {
                x1y1.setText(String.valueOf(29));
                x2y1.setText(String.valueOf(30));
                x3y1.setText(String.valueOf(31));
                x3y5.setText(String.valueOf(28));
                x4y5.setText(String.valueOf(29));
                x5y5.setText(String.valueOf(30));
                x6y5.setText(String.valueOf(31));
                x7y5.setText(String.valueOf(1));
            }
            if (testFirstDay == 4) {
                x1y1.setText(String.valueOf(27));
                x2y1.setText(String.valueOf(28));
                x3y1.setText(String.valueOf(29));
                x4y1.setText(String.valueOf(30));
                x5y1.setText(String.valueOf(31));
                x1y5.setText(String.valueOf(25));
                x2y5.setText(String.valueOf(26));
                x3y5.setText(String.valueOf(27));
                x4y5.setText(String.valueOf(28));
                x5y5.setText(String.valueOf(29));
                x6y5.setText(String.valueOf(30));
                x7y5.setText(String.valueOf(31));
            }
            if (testFirstDay == 5) {
                x1y1.setText(String.valueOf(27));
                x2y1.setText(String.valueOf(28));
                x3y1.setText(String.valueOf(29));
                x4y1.setText(String.valueOf(30));
                x5y1.setText(String.valueOf(31));
                x4y5.setText(String.valueOf(27));
                x5y5.setText(String.valueOf(28));
                x6y5.setText(String.valueOf(29));
                x7y5.setText(String.valueOf(30));
            }
            if (testFirstDay == 6) {
                x1y1.setText(String.valueOf(26));
                x2y1.setText(String.valueOf(27));
                x3y1.setText(String.valueOf(28));
                x4y1.setText(String.valueOf(29));
                x5y1.setText(String.valueOf(30));
                x6y1.setText(String.valueOf(31));
                x3y5.setText(String.valueOf(25));
                x4y5.setText(String.valueOf(26));
                x5y5.setText(String.valueOf(27));
                x6y5.setText(String.valueOf(28));
                x7y5.setText(String.valueOf(29));
            }
            populateAppointments();

        }
        if (formMonth == Month.FEBRUARY) {
            if (leapYearCode == 0) {
                int i = 0;
                if (testFirstDay == 0) {
                    i = 0;
                }
                if (testFirstDay == 1) {
                    i = -1;
                }
                if (testFirstDay == 2) {
                    i = -2;
                }
                if (testFirstDay == 3) {
                    i = -3;
                }
                if (testFirstDay == 4) {
                    i = -4;
                }
                if (testFirstDay == 5) {
                    i = -5;
                }
                if (testFirstDay == 6) {
                    i = -6;
                }
                x1y1.setText(String.valueOf(1 + i));
                x2y1.setText(String.valueOf(2 + i));
                x3y1.setText(String.valueOf(3 + i));
                x4y1.setText(String.valueOf(4 + i));
                x5y1.setText(String.valueOf(5 + i));
                x6y1.setText(String.valueOf(6 + i));
                x7y1.setText(String.valueOf(7 + i));
                x1y2.setText(String.valueOf(8 + i));
                x2y2.setText(String.valueOf(9 + i));
                x3y2.setText(String.valueOf(10 + i));
                x4y2.setText(String.valueOf(11 + i));
                x5y2.setText(String.valueOf(12 + i));
                x6y2.setText(String.valueOf(13 + i));
                x7y2.setText(String.valueOf(14 + i));
                x1y3.setText(String.valueOf(15 + i));
                x2y3.setText(String.valueOf(16 + i));
                x3y3.setText(String.valueOf(17 + i));
                x4y3.setText(String.valueOf(18 + i));
                x5y3.setText(String.valueOf(19 + i));
                x6y3.setText(String.valueOf(20 + i));
                x7y3.setText(String.valueOf(21 + i));
                x1y4.setText(String.valueOf(22 + i));
                x2y4.setText(String.valueOf(23 + i));
                x3y4.setText(String.valueOf(24 + i));
                x4y4.setText(String.valueOf(25 + i));
                x5y4.setText(String.valueOf(26 + i));
                x6y4.setText(String.valueOf(27 + i));
                x7y4.setText(String.valueOf(28 + i));
                x1y5.setText(String.valueOf(1 + i));
                x2y5.setText(String.valueOf(2 + i));
                x3y5.setText(String.valueOf(3 + i));
                x4y5.setText(String.valueOf(4 + i));
                x5y5.setText(String.valueOf(5 + i));
                x6y5.setText(String.valueOf(6 + i));
                x7y5.setText(String.valueOf(7 + i));
                if (testFirstDay == 1) {
                    x1y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(28));
                }
                if (testFirstDay == 2) {
                    x1y1.setText(String.valueOf(30));
                    x2y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(27));
                    x2y5.setText(String.valueOf(28));
                }
                if (testFirstDay == 3) {
                    x1y1.setText(String.valueOf(29));
                    x2y1.setText(String.valueOf(30));
                    x3y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(26));
                    x2y5.setText(String.valueOf(27));
                    x3y5.setText(String.valueOf(28));
                }
                if (testFirstDay == 4) {
                    x1y1.setText(String.valueOf(28));
                    x2y1.setText(String.valueOf(29));
                    x3y1.setText(String.valueOf(30));
                    x4y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(25));
                    x2y5.setText(String.valueOf(26));
                    x3y5.setText(String.valueOf(27));
                    x4y5.setText(String.valueOf(28));
                }
                if (testFirstDay == 5) {
                    x1y1.setText(String.valueOf(27));
                    x2y1.setText(String.valueOf(28));
                    x3y1.setText(String.valueOf(29));
                    x4y1.setText(String.valueOf(30));
                    x5y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(24));
                    x2y5.setText(String.valueOf(25));
                    x3y5.setText(String.valueOf(26));
                    x4y5.setText(String.valueOf(27));
                    x5y5.setText(String.valueOf(28));

                }
                if (testFirstDay == 6) {
                    x1y1.setText(String.valueOf(26));
                    x2y1.setText(String.valueOf(27));
                    x3y1.setText(String.valueOf(28));
                    x4y1.setText(String.valueOf(29));
                    x5y1.setText(String.valueOf(30));
                    x6y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(23));
                    x2y5.setText(String.valueOf(24));
                    x3y5.setText(String.valueOf(25));
                    x4y5.setText(String.valueOf(26));
                    x5y5.setText(String.valueOf(27));
                    x6y5.setText(String.valueOf(28));
                }
                populateAppointments();

            }
            if (leapYearCode == 1) {
                int i = 0;
                if (testFirstDay == 0) {
                    i = 0;
                }
                if (testFirstDay == 1) {
                    i = -1;
                }
                if (testFirstDay == 2) {
                    i = -2;
                }
                if (testFirstDay == 3) {
                    i = -3;
                }
                if (testFirstDay == 4) {
                    i = -4;
                }
                if (testFirstDay == 5) {
                    i = -5;
                }
                if (testFirstDay == 6) {
                    i = -6;
                }
                x1y1.setText(String.valueOf(1 + i));
                x2y1.setText(String.valueOf(2 + i));
                x3y1.setText(String.valueOf(3 + i));
                x4y1.setText(String.valueOf(4 + i));
                x5y1.setText(String.valueOf(5 + i));
                x6y1.setText(String.valueOf(6 + i));
                x7y1.setText(String.valueOf(7 + i));
                x1y2.setText(String.valueOf(8 + i));
                x2y2.setText(String.valueOf(9 + i));
                x3y2.setText(String.valueOf(10 + i));
                x4y2.setText(String.valueOf(11 + i));
                x5y2.setText(String.valueOf(12 + i));
                x6y2.setText(String.valueOf(13 + i));
                x7y2.setText(String.valueOf(14 + i));
                x1y3.setText(String.valueOf(15 + i));
                x2y3.setText(String.valueOf(16 + i));
                x3y3.setText(String.valueOf(17 + i));
                x4y3.setText(String.valueOf(18 + i));
                x5y3.setText(String.valueOf(19 + i));
                x6y3.setText(String.valueOf(20 + i));
                x7y3.setText(String.valueOf(21 + i));
                x1y4.setText(String.valueOf(22 + i));
                x2y4.setText(String.valueOf(23 + i));
                x3y4.setText(String.valueOf(24 + i));
                x4y4.setText(String.valueOf(25 + i));
                x5y4.setText(String.valueOf(26 + i));
                x6y4.setText(String.valueOf(27 + i));
                x7y4.setText(String.valueOf(28 + i));
                x1y5.setText(String.valueOf(29 + i));
                x2y5.setText(String.valueOf(1 + i));
                x3y5.setText(String.valueOf(2 + i));
                x4y5.setText(String.valueOf(3 + i));
                x5y5.setText(String.valueOf(4 + i));
                x6y5.setText(String.valueOf(5 + i));
                x7y5.setText(String.valueOf(6 + i));
                if (testFirstDay == 1) {
                    x1y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(28));
                    x2y5.setText(String.valueOf(29));
                }
                if (testFirstDay == 2) {
                    x1y1.setText(String.valueOf(30));
                    x2y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(27));
                    x2y5.setText(String.valueOf(28));
                    x3y5.setText(String.valueOf(29));
                }
                if (testFirstDay == 3) {
                    x1y1.setText(String.valueOf(29));
                    x2y1.setText(String.valueOf(30));
                    x3y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(26));
                    x2y5.setText(String.valueOf(27));
                    x3y5.setText(String.valueOf(28));
                    x4y5.setText(String.valueOf(29));
                }
                if (testFirstDay == 4) {
                    x1y1.setText(String.valueOf(28));
                    x2y1.setText(String.valueOf(29));
                    x3y1.setText(String.valueOf(30));
                    x4y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(25));
                    x2y5.setText(String.valueOf(26));
                    x3y5.setText(String.valueOf(27));
                    x4y5.setText(String.valueOf(28));
                    x5y5.setText(String.valueOf(29));
                }
                if (testFirstDay == 5) {
                    x1y1.setText(String.valueOf(27));
                    x2y1.setText(String.valueOf(28));
                    x3y1.setText(String.valueOf(29));
                    x4y1.setText(String.valueOf(30));
                    x5y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(24));
                    x2y5.setText(String.valueOf(25));
                    x3y5.setText(String.valueOf(26));
                    x4y5.setText(String.valueOf(27));
                    x5y5.setText(String.valueOf(28));
                    x6y5.setText(String.valueOf(29));
                }
                if (testFirstDay == 6) {
                    x1y1.setText(String.valueOf(26));
                    x2y1.setText(String.valueOf(27));
                    x3y1.setText(String.valueOf(28));
                    x4y1.setText(String.valueOf(29));
                    x5y1.setText(String.valueOf(30));
                    x6y1.setText(String.valueOf(31));
                    x1y5.setText(String.valueOf(23));
                    x2y5.setText(String.valueOf(24));
                    x3y5.setText(String.valueOf(25));
                    x4y5.setText(String.valueOf(26));
                    x5y5.setText(String.valueOf(27));
                    x6y5.setText(String.valueOf(28));
                    x7y5.setText(String.valueOf(29));
                }
                populateAppointments();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setValue(LocalDate.now());
        month.setText(today.getMonth().toString());
        populateCal();
        populateAppointments();
        refreshDate();
        for (int i = 0; i < Appointments.getAllAppointments().size(); i++) {
            System.out.println(Appointments.getAllAppointments().get(i).getStart());
            if ((Appointments.getAllAppointments().get(i).getStart().substring(0,10)).equals(LocalDate.now().toString()));
//            && Integer.parseInt((Appointments.getAllAppointments().get(i).getStart().substring(11,19))) <= Integer.parseInt(String.valueOf(LocalTime.now().minusMinutes(60)).substring(0,2))
//            && Integer.parseInt((Appointments.getAllAppointments().get(i).getStart().substring(11,19))) >= Integer.parseInt(String.valueOf(LocalTime.now()).substring(0,2))){
                System.out.println("There's an appointment soon!");
//            }
        }
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
        window.setTitle("Appointments");
        window.show();
    }

    public void previousMonth(ActionEvent event) {
        datePicker.setValue(datePicker.getValue().minusMonths(1));
        refreshDate();
        month.setText(String.valueOf(Month.valueOf(month.getText())));
    }

    public void nextMonth(ActionEvent event) {
        datePicker.setValue(datePicker.getValue().plusMonths(1));
        refreshDate();
        month.setText(String.valueOf(Month.valueOf(month.getText())));
    }

    @FXML
    private Button Weekly;

    @FXML
    public void goToWeekly(ActionEvent event) throws IOException {
        Parent projectParent = FXMLLoader.load(getClass().getResource("../View/CalendarWeekly.fxml"));
        Scene projectScene = new Scene(projectParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(projectScene);
        window.setTitle("Weekly");
        window.show();
    }


    public ListView getSelectedView() {
        return selectedView;
    }

    public void setSelectedView(ListView selectedView) {

    }

    public void setSelectedView(MouseEvent mouseEvent) {
        this.selectedView = selectedView;
        System.out.println(selectedView);
    }
}
