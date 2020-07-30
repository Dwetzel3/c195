package Controller;

import Model.User;
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
import sun.security.mscapi.CPublicKey;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Locale;
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

    static final String jdbcURL = protocol + vendorName + ipAddress;

    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";

    static final String username = "U062Ax";
    static final String password = "53688672953";
    public static Connection conn;

    static Locale locale = Locale.getDefault();
    static String lang = locale.getDisplayLanguage();

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
            if (lang.equals("español")) {
                System.out.println("\n" + "Conectado a la base de datos.");
                try(FileWriter fw = new FileWriter("Log.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                    out.println(username + " logged in at " + Timestamp.valueOf(LocalDateTime.now()));
                } catch (IOException e) {
                }
            } else if (lang.equals("English")) {
                System.out.println("Connected to the database.");

                try(FileWriter fw = new FileWriter("Log.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
                    out.println(username + " logged in at " + Timestamp.valueOf(LocalDateTime.now()));
                } catch (IOException e) {
                }
            }
            User.setUsername(username);
            User.setPassword(password);
            Parent projectParent = FXMLLoader.load(getClass().getResource("../View/Calendar.fxml"));
            Scene projectScene = new Scene(projectParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(projectScene);
            window.setTitle("Calendar");
            window.show();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return conn;
    }

    public static void closedConnection() {
        try {
            System.getProperty("user.country");
            conn.close();

            if (lang.equals("español")) {
                System.out.println("Desconectado de la base de datos.");
            } else if (lang.equals("English")) {
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
