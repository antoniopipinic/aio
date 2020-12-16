package org.openjfx;

public class Data {
    private static String dataString = "";
    private static int dataInt = 0;

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
            return dataString;
        }else{
            return "No Data";
        }
    }

    public static int getDataInt() {
        return dataInt;
    }
}
