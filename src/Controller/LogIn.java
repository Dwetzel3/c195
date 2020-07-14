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
import sun.security.mscapi.CPublicKey;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    private static final String jdbcURL = protocol + vendorName + ipAddress;

    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";

    private static final String username = "U062Ax";
    private static final String password = "53688672953";
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
            } else if (lang.equals("English")) {
                System.out.println("Connected to the database.");
            }
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

    public void callMe(String call1) {
        call1 = "DUDE";
        System.out.println(call1);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Comparator<String> stringComparator = new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        };
//
//        int comparison = stringComparator.compare("Hello", "World");
//        System.out.println(comparison);


        /**
         * step 2 - lambda implementation
         */

        Comparator<String> stringComparatorLambda =
                (String o1, String o2) -> { return  o1.compareTo(o2); };

        int lambdaComparison = stringComparatorLambda.compare("hello", "world");
        System.out.println(lambdaComparison);

        /**
         * step 3 - lambda simplification
         */

//        Comparator<String> stringComparatorLambda =
//                (o1, o2) -> o1.compareTo(o2);
//
//        int lambdaComparison = stringComparatorLambda.compare("hello", "world");
//        System.out.println(lambdaComparison);

        /**
         * further simplification
         */

        myFunction myFunction = (text1, text2) -> {
            System.out.println(text1 + " " + text2);
            return text1 + " " + text2;
        };


        String returnValue  = myFunction.apply("hello function body", "Test");
        System.out.println(returnValue);

        myFunction myFunction2 = myFunction;
        String returnValue2 = myFunction2.apply("Text 1", "Text 2");
        System.out.println(returnValue2);

        /**
         *
         */

    }
}
