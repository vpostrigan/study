package docs_oracle_com.javase.localdate;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
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

        timezones();

        offsetDateTime();

        instant();
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

    private static void timezones() {
        System.out.println("\ntimezones:");

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a", Locale.ENGLISH);

        // Leaving from San Francisco on July 20, 2013, at 7:30 p.m.
        LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
        ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

        try {
            String out1 = departure.format(format); // LEAVING:  Jul 20 2013  07:30 PM (America/Los_Angeles)
            System.out.printf("LEAVING:  %s (%s)%n", out1, leavingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", departure);
            throw exc;
        }

        // Flight is 10 hours and 50 minutes, or 650 minutes
        ZoneId arrivingZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone).plusMinutes(650);

        try {
            String out2 = arrival.format(format);
            System.out.printf("ARRIVING: %s (%s)%n", out2, arrivingZone); // ARRIVING: Jul 21 2013  10:20 PM (Asia/Tokyo)
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", arrival);
            throw exc;
        }

        if (arrivingZone.getRules().isDaylightSavings(arrival.toInstant()))
            System.out.printf("  (%s daylight saving time will be in effect.)%n", arrivingZone);
        else
            System.out.printf("  (%s standard time will be in effect.)%n", arrivingZone);
    }

    private static void offsetDateTime() {
        System.out.println("\noffsetDateTime:");

        // Find the last Thursday in July 2013.
        LocalDateTime localDate = LocalDateTime.of(2013, Month.JULY, 20, 19, 30, 59);
        ZoneOffset offset = ZoneOffset.of("-08:00");

        OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
        System.out.println("offsetDate: " + offsetDate);
        OffsetDateTime lastThursday = offsetDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
        System.out.printf("The last Thursday in July 2013 is the %sth.%n", lastThursday.getDayOfMonth());
    }

    private static void instant() {
        System.out.println("\ninstant:");

        Instant now = Instant.now();
        System.out.println(now);

        Instant oneHourLater = Instant.now().plus(1, ChronoUnit.HOURS);
        System.out.println(oneHourLater);

        long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(), ChronoUnit.SECONDS);
        System.out.println("secondsFromEpoch: " + secondsFromEpoch);

        Instant timestamp = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
                ldt.getYear(), ldt.getHour(), ldt.getMinute());
    }

}
