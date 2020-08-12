package Controller;

import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import sun.applet.Main;
import sun.rmi.runtime.Log;
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

    Locale userLocale;
    ResourceBundle rb;

    @FXML
    private Label invalidCreds;

    @FXML
    private TextField userField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button logInButton;

    @FXML
    private Label userNameLbl;

    @FXML
    private Label passwordLbl;

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

    public static Locale locale = Locale.getDefault();

    String connected = "";

    public static Locale getLocale() {
        return Locale.getDefault();
    }

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

    void alertConnected() {
        Alert alertType = new Alert(Alert.AlertType.INFORMATION);
        alertType.setTitle("Status");
        alertType.setContentText("Connected to the database.");
        alertType.showAndWait();
    }

    void alertConnectedEs() {
        Alert alertType = new Alert(Alert.AlertType.INFORMATION);
        alertType.setTitle("Estado");
        alertType.setContentText("Conexión establecida");
        alertType.showAndWait();
    }

    @FXML
    public Connection goToCalendar(ActionEvent event) throws IOException {
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, userField.getText(), passwordField.getText());
                try(FileWriter fw = new FileWriter("Log.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                    out.println(username + " logged in at " + Timestamp.valueOf(LocalDateTime.now()));
                } catch (IOException e) {
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
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setContentText("Error: " + e.getLocalizedMessage());
            alertType.showAndWait();
        } catch (SQLException e) {
            ResourceBundle rb = ResourceBundle.getBundle("languages/Nat", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("en")) {
                invalidCreds.setText(rb.getString("invalid"));
            }
//            Alert alertType = new Alert(Alert.AlertType.ERROR);
//            alertType.setTitle("Error");
//            if (getLocale().toString().equals("es_MX")) {
//                alertType.setContentText("Error: el nombre de usuario y la contraseña no coinciden.");
//            } else if (getLocale().toString().equals("en_US")) {
//                alertType.setContentText("Error: The username and password did not match.");
//            }
//            alertType.showAndWait();
        }
        return conn;
    }

    public static void closedConnection() {
        try {
            System.getProperty("user.country");
            if (locale.getLanguage().equals(new Locale("es").getLanguage())) {
                System.out.println("Desconectado");
            } else if (locale.getLanguage().equals(new Locale("en").getLanguage())) {
                System.out.println("Disconnected.");
            conn.close();

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResourceBundle rb = ResourceBundle.getBundle("languages/Nat", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("en")) {
            userNameLbl.setText(rb.getString("username"));
            passwordLbl.setText(rb.getString("password"));
            logInButton.setText(rb.getString("login"));

        }



//        if (locale.getLanguage().equals(new Locale("es").getLanguage())) {
//            logInButton.setText("Iniciar sesión");
//            userNameLbl.setText("Nombre de usuario");
//            passwordLbl.setText("Contraseña");
//        }
    }
}
