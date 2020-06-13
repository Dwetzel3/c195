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
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = LogIn.conn;
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();

        String deleteAll = "DELETE FROM customers WHERE customerId > 0;";
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
        String updateStatement = "UPDATE country SET country = 'Japan' WHERE country = 'Canada'";

        statement.execute(updateStatement);

        // confirm rows affected
        if (statement.getUpdateCount() > 0) {
            System.out.println(statement.getUpdateCount() + " rows affected.");
        } else {
            System.out.println("No change.");
        }
//        Customer customer1 = new Customer(1, "Danwell", 10, true, null, "admin",null,"admin");
//        Customers.addNewCustomer(customer1);
        int thisInt = 5;
        String search = "SELECT FROM country WHERE customerId = " + thisInt + "";
//        statement.execute(search);
//        Customer danyo = new Customer(1, "Danwell", 10, true, null, "admin", null, "admin");
//        Customer Charlie = new Customer(2, "Charlie", 10, true, null, "admin", null, "admin");
//        Customer juju = new Customer(3, "Juju", 10, true, null, "admin", null, "admin");

        ResultSet results = statement.executeQuery("SELECT * FROM customers");


// For each row of the result set ...

        while (results.next()) {


            int customerId = Integer.parseInt(results.getString("customerId"));
            String customerName = results.getString("customerName");
            int addressID = Integer.parseInt(results.getString("addressID"));
            Boolean active = Boolean.valueOf(results.getString("active"));
            Date createDate = Date.valueOf(results.getString("createDate"));
            String createdBy = results.getString("createdBy");
            Timestamp lastUpdate = Timestamp.valueOf(results.getString("lastUpdate"));
            String lastUpdateBy = results.getString("lastUpdateBy");

            Customer customer = new Customer(customerId, customerName, addressID, active, createDate, createdBy, lastUpdate, lastUpdateBy);
            Customers.addNewCustomer(customer);
            // Get the countryId from the current row using the column name - column countryId are in the VARCHAR format

//            customerName = results.getString("customerId");



        }

        for (int i = 0; i < 2; i++) {
            System.out.println();
        }

        launch(args);
        LogIn.closedConnection();
    }
}
