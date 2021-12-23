package docs_oracle_com.javase;

/**
 * https://javarush.ru/groups/posts/operatory-java-logicheskie-arifmeticheskie-pobitovye
 * https://www.javatpoint.com/operators-in-java
 * <p>
 * Operator Type | Category             | Precedence
 * -----------------------------------------------
 * Unary	     | postfix              | expr++ expr--
 * _             | prefix               | ++expr --expr +expr -expr ~ !
 * (Унарные арифметические операторы)
 * -----------------------------------------------
 * Arithmetic	 | multiplicative       | * / %
 * _             | additive             | + -
 * (Бинарные арифметические операторы)
 * -----------------------------------------------
 * Shift	     | shift	            | << >> >>>
 * -----------------------------------------------
 * Relational    | comparison           | < > <= >= instanceof
 * _             | equality             | == !=
 * (Операторы сравнения)
 * -----------------------------------------------
 * Bitwise       | bitwise AND          | &
 * _             | bitwise exclusive OR | ^
 * _             | bitwise inclusive OR	| |
 * -----------------------------------------------
 * Logical       | logical AND          | &&
 * _             | logical OR           | ||
 * -----------------------------------------------
 * Ternary       | ternary              | ? :
 * -----------------------------------------------
 * Assignment    | assignment           | = += -= *= /= %= &= ^= |= <<= >>= >>>=
 * (присвоение числового значения)
 */
public class Operators {

    public static void main(String[] args) throws Exception {

        { // Java Arithmetic Operator Example
            int a = 10;
            int b = 5;
            System.out.println(a + b); //15
            System.out.println(a - b); //5
            System.out.println(a * b); //50
            System.out.println(a / b); //2
            System.out.println(a % b); //0
            System.out.println(24 % 7); // result 3
        }
        { // Assignment
            int x = 0;
            x += 10; // x = 0 + 10 => x = 10
            x -= 5; // x = 10 - 5 => x = 5
            x *= 5; // x = 5 * 5 => x = 25
            x /= 5; // x = 25 / 5 => x = 5
            x %= 3; // x = 5 % 3 => x = 2;
        }
        { // Unary
            int x = 0;
            x = (+5) + (+15);
            System.out.println("x = " + x); // x = 20

            int y = -x;
            System.out.println("y = " + y); // y = -20

            x = 9;
            x++;
            System.out.println(x); // 10

            y = 21;
            y--;
            System.out.println(y); // 20
        }
        { // Relational
            int a = 1;
            int b = 2;
            System.out.println("a == b :" + (a == b)); // false
            System.out.println("a != b :" + (a != b)); // true
            System.out.println("a >  b :" + (a > b)); // false
            System.out.println("a >= b :" + (a >= b)); // false
            System.out.println("a <  b :" + (a < b)); // true
            System.out.println("a <= b :" + (a <= b)); // true
        }
        { // Bitwise
            System.out.println("NOT EXAMPLE:");
            System.out.println("NOT false = " + !false); // true
            System.out.println("NOT true  = " + !true); // false
            System.out.println();
            System.out.println("AND EXAMPLE:");
            System.out.println("false AND false = " + (false & false)); // false
            System.out.println("false AND true  = " + (false & true)); // false
            System.out.println("true  AND false = " + (true & false)); // false
            System.out.println("true  AND true  = " + (true & true)); // true
            System.out.println();
            System.out.println("OR EXAMPLE:");
            System.out.println("false OR false = " + (false | false)); // false
            System.out.println("false OR true  = " + (false | true)); // true
            System.out.println("true  OR false = " + (true | false)); // true
            System.out.println("true  OR true  = " + (true | true)); // true
            System.out.println();
            System.out.println("XOR EXAMPLE:");
            System.out.println("false XOR false = " + (false ^ false)); // false
            System.out.println("false XOR true  = " + (false ^ true)); // true
            System.out.println("true  XOR false = " + (true ^ false)); // true
            System.out.println("true  XOR true  = " + (true ^ true)); // false
            System.out.println();
        }

    }

}
