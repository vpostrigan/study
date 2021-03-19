package docs_oracle_com.javase.localdate;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Instant          2013-08-20T15:16:26.355Z
 * LocalDate  	    2013-08-20
 * LocalDateTime  	2013-08-20T08:16:26.937
 * ZonedDateTime   	2013-08-21T00:16:26.941+09:00[Asia/Tokyo]
 * LocalTime   	    08:16:26.943
 * MonthDay         --08-20
 * Year    	        2013
 * YearMonth 	  	2013-08
 * Month    	    AUGUST
 * OffsetDateTime   2013-08-20T08:16:26.954-07:00
 * OffsetTime       08:16:26.957-07:00
 * Duration    	    PT20H (20 hours)
 * Period    	    P10D (10 days)
 */
public class LocalDateTests {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate payday = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
        System.out.println(payday);

        // //

        LocalDate dateOfBirth = LocalDate.of(2012, Month.MAY, 14);
        System.out.println(dateOfBirth);

        LocalDate firstBirthday = dateOfBirth.plusYears(1);
        System.out.println(firstBirthday);

        // //

        dayOfWeekAndMonth_Enums();

        dateClasses();

        localTime();

        zoneIdAndZoneOffset();
    }

    private static void dayOfWeekAndMonth_Enums() {
        System.out.printf("MONDAY + 3 = %s%n", DayOfWeek.MONDAY.plus(3));

        System.out.printf("MONDAY + 3 = %s%n", DayOfWeek.MONDAY.plus(3)
                .getDisplayName(TextStyle.FULL, Locale.getDefault()));
        System.out.printf("MONDAY + 3 = %s%n", DayOfWeek.MONDAY.plus(3)
                .getDisplayName(TextStyle.NARROW, Locale.getDefault()));
        System.out.printf("MONDAY + 3 = %s%n", DayOfWeek.MONDAY.plus(3)
                .getDisplayName(TextStyle.SHORT, Locale.getDefault()));

        // //

        System.out.printf("Month.FEBRUARY: maxLength: %d%n", Month.FEBRUARY.maxLength());

        System.out.println(Month.FEBRUARY.getDisplayName(TextStyle.FULL, Locale.getDefault()));
        System.out.println(Month.FEBRUARY.getDisplayName(TextStyle.NARROW, Locale.getDefault()));
        System.out.println(Month.FEBRUARY.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
    }

    private static void dateClasses() {
        LocalDate date = LocalDate.of(2021, Month.MARCH, 10);
        LocalDate nextWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        System.out.printf("%s ; nextWed %s%n", date, nextWed); // 2021-03-10 ; nextWed 2021-03-17

        DayOfWeek dotw = LocalDate.of(2012, Month.JULY, 9).getDayOfWeek();
        System.out.printf("%s%n", dotw); // MONDAY

        // //

        YearMonth ym = YearMonth.now();
        System.out.printf("%s: %d%n", ym, ym.lengthOfMonth());

        // //

        MonthDay md = MonthDay.of(Month.FEBRUARY, 29);
        boolean validLeapYear = md.isValidYear(2010);
        System.out.println(md + ": " + validLeapYear);

        // //

        validLeapYear = Year.of(2012).isLeap();
        System.out.println(Year.of(2012) + ": " + validLeapYear);
    }

    private static void localTime() {
        System.out.println("\nLocalTime:");
        LocalTime now = LocalTime.now();
        System.out.println("LocalTime: " + now);

        // //

        System.out.printf("LocalDateTime now: %s%n", LocalDateTime.now());
        System.out.printf("Apr 15, 1994 @ 11:30am: %s%n", LocalDateTime.of(1994, Month.APRIL, 15, 11, 30));
        System.out.printf("now (from Instant): %s%n", LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        System.out.printf("6 months from now: %s%n", LocalDateTime.now().plusMonths(6));
        System.out.printf("6 months ago: %s%n", LocalDateTime.now().minusMonths(6));
    }

    private static void zoneIdAndZoneOffset() {
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        LocalDateTime dt = LocalDateTime.now();

        List<String> zoneList = new ArrayList<>(allZones);
        Collections.sort(zoneList);

        for (String s : zoneList) {
            ZoneId zone = ZoneId.of(s);
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset offset = zdt.getOffset();
            int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
            String out = String.format("%35s %10s%n", zone, offset);

            // Write only time zones that do not have a whole hour offset
            // to standard out.
            if (secondsOfHour != 0) {
                System.out.printf(out);
            }
        }
    }

}
