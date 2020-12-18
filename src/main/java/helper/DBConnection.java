package helper;

import java.sql.*;

public class DBConnection {
    public Connection connect;

    public Connection getConnection() {
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://e63979-mysql.services.easyname.eu/u92519db3?"
                            + "user=u92519db3&password=AllesInOrdnung2020");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return connect;
    }
}
