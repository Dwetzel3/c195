import Controller.AddAppointment;
import Controller.Appointments;
import Controller.Customers;
import Controller.LogIn;
import DBConnection.DBQuery;
import Model.Appointment;
import Model.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.time.*;
import java.util.TimeZone;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {

        Connection conn = LogIn.conn;
        try {
            DBQuery.setStatement(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = DBQuery.getStatement();

        int thisInt = 5;
        String search = "SELECT FROM country WHERE customerId = " + thisInt + "";
        ResultSet results = null;
        try {
            results = statement.executeQuery("SELECT * FROM customer");
        } catch (SQLException e) {
            e.printStackTrace();
        }


// For each row of the result set ...

        while (true) {
            try {
                if (!results.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int customerId = 0;
            try {
                customerId = Integer.parseInt(results.getString("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String customerName = null;
            try {
                customerName = results.getString("customerName");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int addressID = 0;
            try {
                addressID = Integer.parseInt(results.getString("addressID"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Boolean active = null;
            try {
                active = Boolean.valueOf(results.getString("active"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Date createDate = null;
            createDate = new Date(System.currentTimeMillis());
            String createdBy = null;
            try {
                createdBy = results.getString("createdBy");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Timestamp lastUpdate = null;
            try {
                lastUpdate = Timestamp.valueOf(results.getString("lastUpdate"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String lastUpdateBy = null;
            try {
                lastUpdateBy = results.getString("lastUpdateBy");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Customer customer = new Customer(customerId, customerName, addressID, active, createDate, createdBy, lastUpdate, lastUpdateBy);
            Customers.addNewCustomer(customer);
        }


        ResultSet appointmentResults = null;
        try {
            appointmentResults = statement.executeQuery("SELECT * FROM appointment");
        } catch (SQLException e) {
            e.printStackTrace();
        }


// For each row of the result set ...

        while (true) {
            try {
                if (!appointmentResults.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int appointmentId = 0;
            try {
                appointmentId = Integer.parseInt(appointmentResults.getString("appointmentId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int customerID = 0;
            try {
                customerID = Integer.parseInt(appointmentResults.getString("customerId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int userId = 0;
            try {
                userId = Integer.parseInt(appointmentResults.getString("userId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String title = null;
            try {
                title = appointmentResults.getString("title");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String description = null;
            try {
                description = appointmentResults.getString("description");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String location = null;
            try {
                location = appointmentResults.getString("location");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String contact = null;
            try {
                contact = appointmentResults.getString("contact");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String type = null;
            try {
                type = appointmentResults.getString("type");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String url = null;
            try {
                url = appointmentResults.getString("url");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String start = null;
            try {
                start = appointmentResults.getString("start");
                start = start.substring(0,10) + " " + (Integer.valueOf(start.substring(11,13)) + (Integer.valueOf(AddAppointment.offset))) + ":00:00";
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String end = null;
            try {
                end = appointmentResults.getString("end");
                end = end.substring(0,10) + " " + (Integer.valueOf(end.substring(11,13)) + (Integer.valueOf(AddAppointment.offset))) + ":00:00";
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Date appCreateDate = null;
            appCreateDate = new Date(System.currentTimeMillis());

            String appCreatedBy = null;
            try {
                appCreatedBy = appointmentResults.getString("createdBy");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Timestamp appLastUpdate = null;
            try {
                appLastUpdate = Timestamp.valueOf(appointmentResults.getString("lastUpdate"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String appLastUpdateBy = null;
            try {
                appLastUpdateBy = appointmentResults.getString("lastUpdateBy");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Appointment appointment = new Appointment(appointmentId, customerID, userId, title, description, location, contact, type, url, start, end, appCreateDate, appCreatedBy, appLastUpdate, appLastUpdateBy);
            Appointments.addNewAppointment(appointment);
            // Get the countryId from the current row using the column name - column countryId are in the VARCHAR format


        }
        AddAppointment.convertTime();
            launch(args);
            LogIn.closedConnection();
//        ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);

//        ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("Europe")).forEach(System.out::println);

        LocalDate parisDate = LocalDate.of(2019, 10, 26);
        LocalTime timeParis = LocalTime.of(01, 00);
//        System.out.println(Appointments.getAllAppointments().get(1).getStart());
        ZoneId parisZoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime parisZDT = ZonedDateTime.of(parisDate, timeParis, parisZoneId);
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        Instant parisToGMTInstant = parisZDT.toInstant();
        ZonedDateTime parisToLocalZDT = parisZDT.withZoneSameInstant(localZoneId);
        ZonedDateTime gmtToLocalZDT = parisToGMTInstant.atZone(localZoneId);

//        System.out.println("Local: " + ZonedDateTime.now());
//        System.out.println("Paris: " + parisZDT);
//        System.out.println("Paris->GMT: " + parisToGMTInstant);
//        System.out.println("GMT->Local: " + gmtToLocalZDT);
//        System.out.println("GMT->LocalDate: " + gmtToLocalZDT.toLocalDate());
//        System.out.println("GMT->LocalTime: " + gmtToLocalZDT.toLocalTime());

        String date = String.valueOf(gmtToLocalZDT.toLocalDate());
        String time = String.valueOf(gmtToLocalZDT.toLocalTime());
        String dateTime = date + " " + time;
//        System.out.println(dateTime);
    }
}
