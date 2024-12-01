package com.a101.mobile.core.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DateUtils {

    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    private static final String DEFAULT_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private DateUtils() {
        // Private constructor to prevent instantiation
    }

    public static String getCurrentDate() {
        return formatDate(new Date(), DEFAULT_DATE_FORMAT);
    }

    public static String getCurrentTime() {
        return formatDate(new Date(), DEFAULT_TIME_FORMAT);
    }

    public static String getCurrentDateTime() {
        return formatDate(new Date(), DEFAULT_DATETIME_FORMAT);
    }

    public static String formatDate(Date date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String formattedDate = sdf.format(date);
            log.debug("Formatted date {} with pattern {}: {}", date, pattern, formattedDate);
            return formattedDate;
        } catch (Exception e) {
            log.error("Error formatting date: {}", e.getMessage());
            return "";
        }
    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public static String getFutureDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return formatDate(calendar.getTime(), DEFAULT_DATE_FORMAT);
    }

    public static String getPastDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        return formatDate(calendar.getTime(), DEFAULT_DATE_FORMAT);
    }

    public static LocalDateTime parseDateTime(String dateTimeStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            log.error("Error parsing date time string: {}", e.getMessage());
            return null;
        }
    }

    public static long getDateDiff(Date date1, Date date2) {
        try {
            long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
            long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
            log.debug("Date difference between {} and {} is {} days",
                    date1, date2, diffInDays);
            return diffInDays;
        } catch (Exception e) {
            log.error("Error calculating date difference: {}", e.getMessage());
            return 0;
        }
    }

    public static boolean isValidFutureDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            Date date = sdf.parse(dateStr);
            return date.after(new Date());
        } catch (Exception e) {
            log.error("Error validating future date: {}", e.getMessage());
            return false;
        }
    }

    public static String getTurkishMonthName(int month) {
        String[] months = {
                "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
                "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"
        };
        if (month >= 1 && month <= 12) {
            return months[month - 1];
        }
        return "";
    }
}
