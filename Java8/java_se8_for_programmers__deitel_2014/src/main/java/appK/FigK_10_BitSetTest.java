package appK;

import java.util.BitSet;
import java.util.Scanner;

// Fig. K.10: BitSetTest.java
// Using a BitSet to demonstrate the Sieve of Eratosthenes.
public class FigK_10_BitSetTest {

    public static void main(String[] args) {
        // get input integer
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer from 2 to 1023");
        int input = scanner.nextInt();

        // perform Sieve of Eratosthenes
        BitSet sieve = new BitSet(1024);
        int size = sieve.size();

        // set all bits from 2 to 1023
        for (int i = 2; i < size; i++)
            sieve.set(i);

        // perform Sieve of Eratosthenes
        int finalBit = (int) Math.sqrt(size);

        for (int i = 2; i < finalBit; i++) {
            if (sieve.get(i)) {
                for (int j = 2 * i; j < size; j += i)
                    sieve.clear(j);
            }
        }

        int counter = 0;

        // display prime numbers from 2 to 1023
        for (int i = 2; i < size; i++) {
            if (sieve.get(i)) {
                System.out.print(String.valueOf(i));
                System.out.print(++counter % 7 == 0 ? "\n" : "\t");
            }
        }

        // display result
        if (sieve.get(input))
            System.out.printf("\n%d is a prime number", input);
        else
            System.out.printf("\n%d is not a prime number", input);
    }

}
