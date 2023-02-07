package appI;

import javax.swing.*;
import java.util.Calendar;
import java.util.Formatter;

public class SystemOutFormat {

    public static void main(String[] args) {
        // Fig. I.2: IntegerConversionTest.java
        // Using the integer conversion characters.
        System.out.printf("%d\n", 26);
        System.out.printf("%d\n", +26);
        System.out.printf("%d\n", -26);
        System.out.printf("%o\n", 26);
        System.out.printf("%x\n", 26);
        System.out.printf("%X\n", 26);

        // Fig. I.4: FloatingNumberTest.java
        // Using floating-point conversion characters.
        System.out.printf("%e\n", 12345678.9);
        System.out.printf("%e\n", +12345678.9);
        System.out.printf("%e\n", -12345678.9);
        System.out.printf("%E\n", 12345678.9);
        System.out.printf("%f\n", 12345678.9);
        System.out.printf("%g\n", 12345678.9);
        System.out.printf("%G\n", 12345678.9);

        // Fig. I.5: CharStringConversion.java
        // Using character and string conversion characters.
        char character = 'A';  // initialize char
        String string = "This is also a string";  // String object
        Integer integer = 1234;  // initialize integer (autoboxing)

        System.out.printf("%c\n", character);
        System.out.printf("%s\n", "This is a string");
        System.out.printf("%s\n", string);
        System.out.printf("%S\n", string);
        System.out.printf("%s\n", integer); // implicit call to toString

        // Fig. I.9: DateTimeTest.java
        // Formatting dates and times with conversion characters t and T.
        // get current date and time
        Calendar dateTime = Calendar.getInstance();

        // printing with conversion characters for date/time compositions
        System.out.printf("%tc\n", dateTime);
        System.out.printf("%tF\n", dateTime);
        System.out.printf("%tD\n", dateTime);
        System.out.printf("%tr\n", dateTime);
        System.out.printf("%tT\n", dateTime);

        // printing with conversion characters for date
        System.out.printf("%1$tA, %1$tB %1$td, %1$tY\n", dateTime);
        System.out.printf("%1$TA, %1$TB %1$Td, %1$TY\n", dateTime);
        System.out.printf("%1$ta, %1$tb %1$te, %1$ty\n", dateTime);

        // printing with conversion characters for time
        System.out.printf("%1$tH:%1$tM:%1$tS\n", dateTime);
        System.out.printf("%1$tZ %1$tI:%1$tM:%1$tS %Tp", dateTime);

        // Fig. I.11: OtherConversion.java
        // Using the b, B, h, H, % and n conversion characters.
        Object test = null;
        System.out.printf("%b\n", false);
        System.out.printf("%b\n", true);
        System.out.printf("%b\n", "Test");
        System.out.printf("%B\n", test);
        System.out.printf("Hashcode of \"hello\" is %h\n", "hello");
        System.out.printf("Hashcode of \"Hello\" is %h\n", "Hello");
        System.out.printf("Hashcode of null is %H\n", test);
        System.out.printf("Printing a %% in a format string\n");
        System.out.printf("Printing a new line %nnext line starts here");

        // Fig. I.12: FieldWidthTest.java
        // Right justifying integers in fields.
        System.out.printf("%4d\n", 1);
        System.out.printf("%4d\n", 12);
        System.out.printf("%4d\n", 123);
        System.out.printf("%4d\n", 1234);
        System.out.printf("%4d\n\n", 12345); // data too large

        System.out.printf("%4d\n", -1);
        System.out.printf("%4d\n", -12);
        System.out.printf("%4d\n", -123);
        System.out.printf("%4d\n", -1234); // data too large
        System.out.printf("%4d\n", -12345); // data too large

        // Fig 29.13: PrecisionTest.java
        // Using precision for floating-point numbers and strings.
        double f = 123.94536;
        String s = "Happy Birthday";

        System.out.printf("Using precision for floating-point numbers\n");
        System.out.printf("\t%.3f\n\t%.3e\n\t%.3g\n\n", f, f, f);

        System.out.printf("Using precision for strings\n");
        System.out.printf("\t%.11s\n", s);

        // Fig 29.15: MinusFlagTest.java
        // Right justifying and left justifying values
        System.out.println("Columns:");
        System.out.println("0123456789012345678901234567890123456789\n");
        System.out.printf("%10s%10d%10c%10f\n\n", "hello", 7, 'a', 1.23);
        System.out.printf("%-10s%-10d%-10c%-10f\n", "hello", 7, 'a', 1.23);

        // Fig. I.16: PlusFlagTest.java
        // Printing numbers with and without the + flag.
        System.out.printf("%d\t%d\n", 786, -786);
        System.out.printf("%+d\t%+d\n", 786, -786);

        // Fig. I.17: SpaceFlagTest.java
        // Printing a space before non-negative values.
        System.out.printf("% d\n% d\n", 547, -547);

        // Fig. I.18: PoundFlagTest.java
        // Using the # flag with conversion characters o and x.
        int c = 31;      // initialize c
        System.out.printf("%#o\n", c);
        System.out.printf("%#x\n", c);

        // Fig. I.19: ZeroFlagTest.java
        // Printing with the 0 (zero) flag fills in leading zeros.
        System.out.printf("%+09d\n", 452);
        System.out.printf("%09d\n", 452);
        System.out.printf("% 9d\n", 452);

        // Fig. I.20: CommaFlagTest.java
        // Using the comma (,) flag to display numbers with thousands separator.
        System.out.printf("%,d\n", 58625);
        System.out.printf("%,.2f\n", 58625.21);
        System.out.printf("%,.2f", 12345678.9);

        // Fig. I.21: ParenthesesFlagTest.java
        // Using the (flag to place parentheses around negative numbers.
        System.out.printf("%(d\n", 50);
        System.out.printf("%(d\n", -50);
        System.out.printf("%(.1e\n", -50.0);

        // Fig. I.22: ArgumentIndexTest
        // Reordering output with argument indices.
        System.out.printf("Parameter list without reordering: %s %s %s %s\n",
                "first", "second", "third", "fourth");
        System.out.printf("Parameter list after reordering: %4$s %3$s %2$s %1$s\n",
                "first", "second", "third", "fourth");

        // Fig. I.24: FormatterTest.java
        // Formatting output with class Formatter.
        Formatter formatter = new Formatter();
        formatter.format("%d = %#o = %#X", 10, 10, 10);

        // display output in JOptionPane
        JOptionPane.showMessageDialog(null, formatter.toString());
    }

}
