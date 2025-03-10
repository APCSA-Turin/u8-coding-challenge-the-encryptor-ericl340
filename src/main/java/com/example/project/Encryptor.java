package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int res = (int) Math.ceil((double) messageLen / rows);
        if (messageLen == 0) {
            res++;
        }
        return res;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        int l = message.length();
        int c = determineColumns(l, rows);
        String[][] res = new String[rows][c];
        for (int i = 0; i < res.length; i++) {
            for (int k = 0; k < res[i].length; k++) {
                int p = c * i + k;
                if (p < l) {
                    res[i][k] = message.substring(p, p + 1);
                }else {
                    res[i][k] = "=";
                }
            }
        }
        return res;
    }

    public static String encryptMessage(String message, int rows){
        String[][] arr = generateEncryptArray(message, rows);
        String res = "";
        for (int i = arr[0].length - 1; i >= 0; i--) {
            for (int k = 0; k < arr.length; k++) {
                res += arr[k][i];
            }
        }
        return res;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String[][] arr = generateEncryptArray(encryptedMessage, determineColumns(encryptedMessage.length(), rows));
        String res = "";
        for (int i = 0; i < arr[0].length; i++) {
            for (int k = arr.length - 1; k >= 0 ; k--) {
                res += arr[k][i];
            }
        }
        int c = 0;
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == '=') {
                break;
            }
            c++;
        }
        return res.substring(0, c);
    }
}