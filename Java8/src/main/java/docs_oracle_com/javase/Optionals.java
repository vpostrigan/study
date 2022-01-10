package docs_oracle_com.javase;

import java.util.Optional;

/**
 * https://www.youtube.com/watch?v=1ia8B3SUie8
 * JAVA 8 | OPTIONAL | КАК ИЗБАВИТЬСЯ ОТ IF ELSE
 */
public class Optionals {

    public static void main(String[] args) {
        Optional o1 = Optional.ofNullable(null);
        // System.out.println(o1.get()); java.util.NoSuchElementException: No value present
        System.out.println(o1.orElse(null)); // null

        Optional o2 = Optional.ofNullable(new Object());
        System.out.println(o2.get()); // java.lang.Object@27f674d

        // //

        // Optional o21 = Optional.of(null); java.lang.NullPointerException
        Optional o22 = Optional.of(new Object());
        System.out.println(o2.get()); // java.lang.Object@27f674d

        // //

        // before Optional
        String str = "value";
        if (str != null && !str.isEmpty() && str.contains("value")) {
            System.out.println(str);
        } else {
            throw new RuntimeException();
        }

        // with Optional
        String result = Optional.ofNullable(str)
                .filter(s -> !s.isEmpty())
                .filter(s -> s.contains("value"))
                .orElseThrow(RuntimeException::new);
        System.out.println(result);

        result = Optional.ofNullable(str)
                .filter(s -> !s.isEmpty())
                .filter(s -> s.contains("value"))
                .orElse("default value"); // return default value
        System.out.println(result);

        Optional.ofNullable(str)
                .filter(s -> !s.isEmpty())
                .filter(s -> s.contains("value"))
                .ifPresent(System.out::println); // ifPresent - вызов метода
    }

}
