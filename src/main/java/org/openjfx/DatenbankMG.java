package org.openjfx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatenbankMG {
    private static DBConnection connectNow;
    private static Connection connectDB;

    private static Statement statement;


    public static void connectToDB() {
        connectNow = new DBConnection();
        connectDB = connectNow.getConnection();

        try {
            statement = connectDB.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ResultSet performQuery(String queryString) {
        try {
            return statement.executeQuery(queryString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int performUpdate(String SQLfetch) {
        try {
            return statement.executeUpdate(SQLfetch);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
