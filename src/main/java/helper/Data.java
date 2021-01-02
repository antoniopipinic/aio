package helper;

import java.sql.ResultSet;

public final class Data {
    private static String dataString = "";
    private static int dataInt = 0;
    private static String username;
    private static String id;

    public static void setDataString(String string){
        if (string != null && !string.isBlank()) {
            dataString = string;
        }
    }

    public static void setDataInt(int dataInt) {
        Data.dataInt = dataInt;
    }

    public static String getDataString() {
        if (!dataString.isBlank()) {
            return dataString;//
        }else{
            return "No Data";
        }
    }

    public static int getDataInt() {
        return dataInt;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        if(!username.isBlank()){
            Data.username = username;
        }
    }

    public static String getId() {
        String userNameId = "SELECT id FROM users WHERE email = '" + getUsername() + "'";

        try {
            ResultSet queryResult = DatenbankMG.performQuery(userNameId);
            while (queryResult.next()) {
                id = queryResult.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return id;
    }

    public static void setId(String id) {
        Data.id = id;
    }
}
