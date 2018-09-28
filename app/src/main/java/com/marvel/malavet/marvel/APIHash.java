package com.marvel.malavet.marvel;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class APIHash {
    public static HashMap<String, String> data = new HashMap<>();
    String publicKey = "f93dccde9140de1f8d695830a1d7d260";
    String privateKey = "cbe47ebc281ccecf3d5946d4a5ba5e6867705fce";


    public HashMap makeApiParam() {
        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        data.put("ts", timeStamp);
        data.put("apikey", publicKey);
        data.put("hash", md5(timeStamp + privateKey + publicKey));
        return data;

    }

    public static boolean checkParam() {
        if (data.size() > 0) {
            return false;
        } else return true;

    }

    //from StackOVerflow  - MD5
    public String md5(String timestamp) {
        Log.d("stamp", timestamp);
        final String MD5 = "MD5";
        String s = timestamp;
        try {

            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            Log.d("hex", hexString.toString());
            return hexString.toString();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
