package org.openjfx;

public class Data {
    private static String dataString = "";
    private static int dataInt = 0;

    public static void setDataString(String string){
        dataString = string;
    }

    public static void setDataInt(int dataInt) {
        Data.dataInt = dataInt;
    }

    public static String getDataString() {
        return dataString;
    }

    public static int getDataInt() {
        return dataInt;
    }
}
