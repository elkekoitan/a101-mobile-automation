package com.a101.mobile.core.utils;

import lombok.extern.log4j.Log4j2;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Log4j2
public class TestDataGenerator {
    private static final Random random = new Random();
    private static final String DEFAULT_OTP = "141414";  // Sabit OTP kodu
    private static final String PHONE_PREFIX = "554";    // Sabit telefon prefix'i

    public static String generatePhoneNumber() {
        return "5545417561"; // Şu an için sabit numara
    }

    public static String getDefaultOTP() {
        return DEFAULT_OTP;
    }

    public static String generateTestEmail() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "test.user" + timestamp + "@a101test.com";
        log.debug("Generated test email: {}", email);
        return email;
    }

    public static String generateTestName() {
        String[] firstNames = {"Test", "Demo", "Sample", "QA"};
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(8);
        String name = firstNames[random.nextInt(firstNames.length)] + "_User_" + timestamp;
        log.debug("Generated test name: {}", name);
        return name;
    }

    public static String generateTestSurname() {
        return "Test_" + System.currentTimeMillis() % 1000;
    }

    // Daha sonra gerçek format eklenecek
    public static String generateTestCardNumber() {
        return "4111111111111111"; // Test kart numarası
    }

    // Daha sonra gerçek format eklenecek
    public static String generateTestCardExpiry() {
        return "12/25";
    }

    // Daha sonra gerçek format eklenecek
    public static String generateTestCardCVV() {
        return "123";
    }

    public static String generateTestAddress() {
        String[] districts = {"Kadıköy", "Beşiktaş", "Şişli", "Üsküdar"};
        String[] streets = {"Test Sokak", "Demo Caddesi", "QA Bulvarı"};

        String address = districts[random.nextInt(districts.length)] + " " +
                streets[random.nextInt(streets.length)] + " No:" +
                (random.nextInt(100) + 1) + " D:" +
                (random.nextInt(20) + 1);

        log.debug("Generated test address: {}", address);
        return address;
    }

    public static String generateTestPostalCode() {
        return "34" + String.format("%03d", random.nextInt(1000));
    }

    // Test için referans numarası oluşturur
    public static String generateReferenceNumber() {
        return "A101_" + System.currentTimeMillis();
    }

    // İleride eklenecek diğer metodlar için yer tutucu
    public static class Builder {
        // Gelecekte eklenecek özelleştirilebilir veri oluşturma metodları için
        // Builder pattern kullanılacak
    }

    private TestDataGenerator() {
        // Private constructor to prevent instantiation
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }
}
