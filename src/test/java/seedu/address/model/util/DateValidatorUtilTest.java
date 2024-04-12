package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateValidatorUtilTest {

    @Test
    public void isToday() {
        LocalDate todayDate = LocalDate.now();
        assertTrue(DateValidatorUtil.isToday(todayDate));
    }

    @Test
    public void isBeforeToday() {
        LocalDate dayBeforeToday = LocalDate.now().minusDays(1);
        assertTrue(DateValidatorUtil.isBeforeToday(dayBeforeToday));
    }

    @Test
    public void isValidDate_febMoreThan29Days_false() {
        String invalidFebDate = "2024-02-30"; // no february has more than 29 days
        assertFalse(DateValidatorUtil.isValidDate(invalidFebDate));
    }

    @Test
    public void isValidDate_febDate29OnNonLeapYear_false() {
        String invalidLeapYearDate = "2023-02-29"; // 2023 is not a leap year, 29 Feb 2023 is invalid
        assertFalse(DateValidatorUtil.isValidDate(invalidLeapYearDate));
    }

    @Test
    public void isValidDate_aprilJuneSeptemberNovemberMoreThan30Days_false() {
        String invalidAprilDate = "2023-04-31";
        String invalidJuneDate = "2023-06-31";
        String invalidSeptemberDate = "2023-09-31";
        String invalidNovemberDate = "2023-11-31";

        assertFalse(DateValidatorUtil.isValidDate(invalidAprilDate));
        assertFalse(DateValidatorUtil.isValidDate(invalidJuneDate));
        assertFalse(DateValidatorUtil.isValidDate(invalidSeptemberDate));
        assertFalse(DateValidatorUtil.isValidDate(invalidNovemberDate));
    }

    @Test
    public void isTwentyNineFeb_trueInput_true() {
        String twentyNineFebDate = "2023-02-29";
        assertTrue(DateValidatorUtil.isTwentyNineFeb(twentyNineFebDate));
    }

    @Test
    public void isTwentyNineFeb_falseInput_false() {
        String nonTwentyNineFebDate = "2023-02-28";
        assertFalse(DateValidatorUtil.isTwentyNineFeb(nonTwentyNineFebDate));
    }

    @Test
    public void isValidLeapYear_leapYearInput_true() {
        String leapYearInput = "2024-02-29";
        assertTrue(DateValidatorUtil.isValidLeapYear(leapYearInput));
    }

    @Test
    public void isValidLeapYear_nonLeapYearInput_false() {
        String nonLeapYearInput = "2023-02-29";
        assertFalse(DateValidatorUtil.isValidLeapYear(nonLeapYearInput));
    }
}
