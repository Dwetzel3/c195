package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LogIn implements Initializable {
    @FXML
    private TextField userField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button logInButton;

    public LogIn() throws SQLException {
    }

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U062Ax";

    private static final String jdbcURL = protocol + vendorName + ipAddress;

    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";

    private static final String username = "U062Ax";
    private static final String password = "53688672953";
    public static Connection conn;

    public static String getUsername() {
        return username;
    }

    static {
        try {
            conn = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public Connection goToCalendar(ActionEvent event) throws IOException {
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, userField.getText(), passwordField.getText());
            System.out.println("Connected to database");
            Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Calendar.fxml"));
            Scene projectScene = new Scene(projectParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(projectScene);
            window.setTitle("Calendar");
            window.show();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conn;
    }

    public static void closedConnection() {
        try {
            conn.close();
            System.out.println("Disconnected from database.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
