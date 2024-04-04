package seedu.address.model.util;

import java.time.LocalDate;

public class DateValidatorUtil {
    public static final int MONTH_JANUARY = 1;
    public static final int MONTH_FEBRUARY = 2;
    public static final int MONTH_MARCH = 3;
    public static final int MONTH_APRIL = 4;
    public static final int MONTH_MAY = 5;
    public static final int MONTH_JUNE = 6;
    public static final int MONTH_JULY = 7;
    public static final int MONTH_AUGUST = 8;
    public static final int MONTH_SEPTEMBER = 9;
    public static final int MONTH_OCTOBER = 10;
    public static final int MONTH_NOVEMBER = 11;
    public static final int MONTH_DECEMBER = 12;

    public static boolean isToday(LocalDate dateEntered) {
        LocalDate todayDate = LocalDate.now();
        return dateEntered.isEqual(todayDate);
    }

    public static boolean isBeforeToday(LocalDate dateEntered) {
        LocalDate todayDate = LocalDate.now();
        return dateEntered.isBefore(todayDate);
    }

    public static boolean isValidDate(String dateEntered) {
        String[] splitDate = dateEntered.split("-");
        int monthEntered = Integer.parseInt(splitDate[1]);
        int dayEntered = Integer.parseInt(splitDate[2]);

        if (monthEntered == MONTH_FEBRUARY) { // 28 or 29 days max
            if (dayEntered > 29) {
                return false;
            }
            else {
                // between 1 and 29 (inclusive)
                if (dayEntered > 28 && !isValidLeapYear(dateEntered)) {
                    return false;
                }
            }
        }
        else if (monthEntered == MONTH_APRIL || monthEntered == MONTH_JUNE || monthEntered == MONTH_SEPTEMBER ||
                monthEntered == MONTH_NOVEMBER) { // 30 days max
            // 30 days max
            if (dayEntered > 30) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTwentyNineFeb(String dateEntered) {
        boolean isTwentyNineFeb = false;
        String[] splitDate = dateEntered.split("-");
        int monthEntered = Integer.parseInt(splitDate[1]);
        int dayEntered = Integer.parseInt(splitDate[2]);
        if (dayEntered == 29 && monthEntered == 2) {
            return true;
        }
        return isTwentyNineFeb;
    }

    public static boolean isValidLeapYear(String dateEntered) {
        boolean isValidLeapYear;
        String[] splitDate = dateEntered.split("-");
        int yearEntered = Integer.parseInt(splitDate[0]);
        if (yearEntered % 4 == 0) {
            isValidLeapYear = true;
        } else {
            isValidLeapYear = false;
        }
        return isValidLeapYear;
    }
}
