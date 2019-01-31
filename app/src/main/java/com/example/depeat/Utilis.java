package com.example.depeat;

public class Utilis {


    private static final int LEN_PASSWORD = 6;
    private static final int PHONE_LENGTH = 10;

    public static boolean verifyEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > LEN_PASSWORD;
    }

    public static boolean isPhoneValid(String phone) {
        return phone.length() > PHONE_LENGTH;
    }


}