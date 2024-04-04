package seedu.address.model.util;

import java.time.LocalDate;

/**
 * Utility class performing date-checking functions.
 */
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

    /**
     * Checks if the date entered is the same as the current date (today's date).
     * @param dateEntered date provided by user
     * @return boolean indicating true or false
     */
    public static boolean isToday(LocalDate dateEntered) {
        LocalDate todayDate = LocalDate.now();
        return dateEntered.isEqual(todayDate);
    }

    /**
     * Checks if the date entered is before the current date (before today's date)
     * @param dateEntered date provided by user
     * @return boolean indicating true or false
     */
    public static boolean isBeforeToday(LocalDate dateEntered) {
        LocalDate todayDate = LocalDate.now();
        return dateEntered.isBefore(todayDate);
    }

    /**
     * Checks if the date entered is a valid date according to the number of days each month should have.
     * Also checks for valid leap year.
     * @param dateEntered date provided by user
     * @return boolean indicating true or false
     */
    public static boolean isValidDate(String dateEntered) {
        String[] splitDate = dateEntered.split("-");
        int monthEntered = Integer.parseInt(splitDate[1]);
        int dayEntered = Integer.parseInt(splitDate[2]);

        if (monthEntered == MONTH_FEBRUARY) { // 28 or 29 days max
            if (dayEntered > 29) {
                return false;
            } else {
                // between 1 and 29 (inclusive)
                if (dayEntered > 28 && !isValidLeapYear(dateEntered)) {
                    return false;
                }
            }
        } else if (monthEntered == MONTH_APRIL || monthEntered == MONTH_JUNE || monthEntered == MONTH_SEPTEMBER
                || monthEntered == MONTH_NOVEMBER) { // 30 days max
            // 30 days max
            if (dayEntered > 30) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the date entered is the 29th of February.
     * @param dateEntered date entered by user
     * @return boolean indicating true or false
     */
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

    /**
     * Checks if year is a leap year (is leap year if year is divisible by 4 with no remainder).
     * @param dateEntered date entered by user
     * @return boolean indicating true or false
     */
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
