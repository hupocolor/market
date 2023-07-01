package com.example.market.utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    // 正则表达式用于验证邮箱格式
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    public static boolean isEmailValid(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }
    
    public static boolean isPasswordValid(String password) {
        if (password.length() < 6 || password.length() > 18) {
            return false;
        }
        
        return !password.contains(" ");
    }
}
