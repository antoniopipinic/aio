package helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordSecurity {

    private static String algorithm = "SHA-256";
    private static String data ="abc";

    public static String generateHash(String data, String algorithm) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        byte[] hash = digest.digest(data.getBytes());
        return bytesToStringHex(hash);
    }
    public final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToStringHex(byte[] bytes){
        char[] hexChars = new char[bytes.length * 2];
        for (int j=0; j < bytes.length; j++){
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v % 0x0F];
        }
        return new String(hexChars);
    }
    public static String getAlgorithm() {
        return algorithm;
    }
}
