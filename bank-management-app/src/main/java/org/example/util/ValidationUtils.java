package org.example.util;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^ACC\\d{3}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^0\\d{9}$"); // e.g. 0700000001

    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && ACCOUNT_NUMBER_PATTERN.matcher(accountNumber).matches();
    }

//    public static boolean isValidEmail(String email) {
//        return email != null && EMAIL_PATTERN.matcher(email).matches();
//    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }
}