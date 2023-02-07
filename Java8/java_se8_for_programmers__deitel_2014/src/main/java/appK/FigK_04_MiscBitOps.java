package appK;

import java.util.Scanner;

// Fig. K.4: MiscBitOps.java
// Using the bitwise operators.
public class FigK_04_MiscBitOps {

    public static void main(String[] args) {
        int choice = 0; // store operation type
        int first = 0; // store first input integer
        int second = 0; // store second input integer
        int result = 0; // store operation result
        Scanner scanner = new Scanner(System.in); // create Scanner

        // continue execution until user exit
        while (true) {
            // get selected operation
            System.out.println("\n\nPlease choose the operation:");
            System.out.printf("%s%s", "1--AND\n2--Inclusive OR\n",
                    "3--Exclusive OR\n4--Complement\n5--Exit\n");
            choice = scanner.nextInt();

            // perform bitwise operation
            switch (choice) {
                case 1: // AND
                    System.out.print("Please enter two integers:");
                    first = scanner.nextInt(); // get first input integer
                    display(first);
                    second = scanner.nextInt(); // get second input integer
                    display(second);
                    result = first & second; // perform bitwise AND
                    System.out.printf(
                            "\n\n%d & %d = %d", first, second, result);
                    display(result);
                    break;
                case 2: // Inclusive OR
                    System.out.print("Please enter two integers:");
                    first = scanner.nextInt(); // get first input integer
                    display(first);
                    second = scanner.nextInt(); // get second input integer
                    display(second);
                    result = first | second; // perform bitwise inclusive OR
                    System.out.printf(
                            "\n\n%d | %d = %d", first, second, result);
                    display(result);
                    break;
                case 3: // Exclusive OR
                    System.out.print("Please enter two integers:");
                    first = scanner.nextInt(); // get first input integer
                    display(first);
                    second = scanner.nextInt(); // get second input integer
                    display(second);
                    result = first ^ second; // perform bitwise exclusive OR
                    System.out.printf(
                            "\n\n%d ^ %d = %d", first, second, result);
                    display(result);
                    break;
                case 4: // Complement
                    System.out.print("Please enter one integer:");
                    first = scanner.nextInt(); // get input integer
                    display(first);
                    result = ~first; // perform bitwise complement on first
                    System.out.printf("\n\n~%d = %d", first, result);
                    display(result);
                    break;
                case 5:
                default:
                    System.exit(0); // exit application
            }
        }
    }

    // Fig N.5: BitRepresentation.java
    // Utility class that display bit representation of an integer.
    // display bit representation of specified int value
    public static void display(int value) {
        System.out.printf("\nBit representation of %d is: \n", value);

        // create int value with 1 in leftmost bit and 0s elsewhere
        int displayMask = 1 << 31;

        // for each bit display 0 or 1
        for (int bit = 1; bit <= 32; bit++) {
            // use displayMask to isolate bit
            System.out.print((value & displayMask) == 0 ? '0' : '1');

            value <<= 1; // shift value one position to left

            if (bit % 8 == 0)
                System.out.print(' '); // display space every 8 bits
        }
    }

}
