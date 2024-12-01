package com.a101.mobile.core.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.text.Normalizer;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StringUtils {

    private StringUtils() {
        // Private constructor to prevent instantiation
    }

    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            log.warn("Empty phone number provided");
            return "";
        }

        // Remove all non-numeric characters
        String cleanNumber = phoneNumber.replaceAll("[^0-9]", "");

        // Format as Turkish phone number
        if (cleanNumber.length() == 10) {
            log.debug("Formatted 10-digit phone number: {}", cleanNumber);
            return cleanNumber;
        } else if (cleanNumber.length() == 11 && cleanNumber.startsWith("0")) {
            String formattedNumber = cleanNumber.substring(1);
            log.debug("Formatted 11-digit phone number: {}", formattedNumber);
            return formattedNumber;
        }

        log.warn("Invalid phone number format: {}", phoneNumber);
        return cleanNumber;
    }

    public static String formatPrice(String price) {
        if (price == null || price.isEmpty()) {
            log.warn("Empty price provided");
            return "0.00 TL";
        }

        try {
            // Remove all non-numeric characters except decimal point
            String cleanPrice = price.replaceAll("[^0-9.]", "");
            double amount = Double.parseDouble(cleanPrice);
            String formattedPrice = String.format("%.2f TL", amount);
            log.debug("Formatted price: {} -> {}", price, formattedPrice);
            return formattedPrice;
        } catch (NumberFormatException e) {
            log.error("Error formatting price: {} - {}", price, e.getMessage());
            return "0.00 TL";
        }
    }

    public static String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            log.warn("Empty phone number provided for masking");
            return "";
        }

        String formattedNumber = formatPhoneNumber(phoneNumber);
        if (formattedNumber.length() != 10) {
            log.warn("Invalid phone number length for masking: {}", phoneNumber);
            return formattedNumber;
        }

        // Mask middle digits (e.g., 555****789)
        String maskedNumber = formattedNumber.substring(0, 3) +
                "****" +
                formattedNumber.substring(7);
        log.debug("Masked phone number: {} -> {}", phoneNumber, maskedNumber);
        return maskedNumber;
    }

    public static String generateRandomString(int length) {
        String randomString = RandomStringUtils.randomAlphanumeric(length);
        log.debug("Generated random string of length {}: {}", length, randomString);
        return randomString;
    }

    public static String removeSpecialCharacters(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(normalized).replaceAll("");
        log.debug("Removed special characters: {} -> {}", text, result);
        return result;
    }

    public static String truncateString(String text, int maxLength) {
        if (text == null || text.isEmpty() || text.length() <= maxLength) {
            return text;
        }

        String truncated = text.substring(0, maxLength) + "...";
        log.debug("Truncated string: {} -> {}", text, truncated);
        return truncated;
    }

    public static boolean isValidEmailFormat(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        boolean isValid = email.matches(emailRegex);
        log.debug("Email validation: {} is {}", email, isValid ? "valid" : "invalid");
        return isValid;
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        boolean isNumeric = str.matches("\\d+");
        log.debug("String numeric check: {} is {}", str, isNumeric ? "numeric" : "not numeric");
        return isNumeric;
    }

    public static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        String capitalized = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
        log.debug("Capitalized first letter: {} -> {}", text, capitalized);
        return capitalized;
    }
}
