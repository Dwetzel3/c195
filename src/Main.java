import Controller.Customers;
import Controller.LogIn;
import DBConnection.DBQuery;
import Model.Customer;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {

        //String insertStatement = "INSERT INTO country(country, createDate, createdBy, lastUpdateBy) VALUES('US', '2020-06-06 00:00:00', 'admin', 'admin')";

        // var insert
//        String XsearchName = "SELECT customerName FROM customers WHERE customerId = 17;";
//        String XcustomerName = "Daniel";
//        String XaddressID = "10";
//        String Xactive = "1";
//        String XcreateDate = "2020-06-06";
//        String XcreatedBy = "admin";
//        String XlastUpdate = "2020-06-06 00:00:00";
//        String XlastUpdateBy = "admin";
//
//        String insertStatement = "INSERT INTO customers(customerName, addressID, active, createDate, createdBy, lastUpdate, lastUpdateBy)" +
//                "VALUES(" +
//                "'" + XcustomerName + "'," +
//                "'" + XaddressID + "'," +
//                "'" + Xactive + "'," +
//                "'" + XcreateDate + "'," +
//                "'" + XcreatedBy + "'," +
//                "'" + XlastUpdate + "'," +
//                "'" + XlastUpdateBy + "'" +
//                ");";
//
//                // execute sql statement
////        statement.execute(deleteAll);
//        statement.execute(insertStatement);

        // Update statement

        Connection conn = LogIn.conn;
        try {
            DBQuery.setStatement(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = DBQuery.getStatement();

        // confirm rows affected
        try {
            if (statement.getUpdateCount() > 0) {
                System.out.println(statement.getUpdateCount() + " rows affected.");
            } else {
                System.out.println("No change.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int thisInt = 5;
        String search = "SELECT FROM country WHERE customerId = " + thisInt + "";
        ResultSet results = null;
        try {
            results = statement.executeQuery("SELECT * FROM customers");
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
            try {
                createDate = Date.valueOf(results.getString("createDate"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            // Get the countryId from the current row using the column name - column countryId are in the VARCHAR format

//            customerName = results.getString("customerId");



        }
            launch(args);
            LogIn.closedConnection();
        }
    }

