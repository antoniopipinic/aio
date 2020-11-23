package org.openjfx;

import java.sql.*;

public class DBConnection {
    Connection connect = null;

    public DBConnection(){
        // This will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            this.connect = DriverManager
                    .getConnection("jdbc:mysql://e63979-mysql.services.easyname.eu/u92519db3?"
                            + "user=u92519db3&password=AllesInOrdnung2020");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() throws SQLException {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from users");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            System.out.println("");

            int numberOfColumns = rsmd.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                if (i > 1) System.out.print(",  ");
                String columnName = rsmd.getColumnName(i);
                System.out.print(columnName);
            }
            System.out.println("");

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
            }

        } catch (Exception e) {
            throw e;
        }


    }

}
