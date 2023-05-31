package Business;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashClass {

    private static HashClass instance;     //SINGLETON PATTERN

    public static synchronized HashClass getInstance() {
        if (instance == null) instance = new HashClass();
        return instance;
    }

    private HashClass() {}

    public String encrypt(String password){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    public String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
