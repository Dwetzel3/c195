package DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    private static Statement statement; // statement reference

    // Create statement object
    public  static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    // return statement object
    public static Statement getStatement() {
        return statement;
    }

}
