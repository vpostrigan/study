package docs_oracle_com.javase.localdate;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

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
    }

}
