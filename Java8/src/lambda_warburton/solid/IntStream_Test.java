/* (c) Copyright Selerity, Inc. 2009-2020. All rights reserved. This source code is confidential 
and proprietary information of Selerity Inc. and may be used only by a recipient designated 
by and for the purposes permitted by Selerity Inc. in writing.  Reproduction of, dissemination 
of, modifications to or creation of derivative works from this source code, whether in source 
or binary forms, by any means and in any form or manner, is expressly prohibited, except with 
the prior written permission of Selerity Inc..  THIS CODE AND INFORMATION ARE PROVIDED "AS IS"
WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO IMPLIED
WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE. This notice may not be 
removed from the software by any user thereof.*/
package solid;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntStream_Test {

    public static void main(String[] args) {
        test1();
        test2();
        test3();

        // int upTo = 10000;
        // long c = IntStream.range(1, upTo).filter(SingleResp::isPrime).count();
        // System.out.println(c);
    }

    private static void test1() {
        int[] array = IntStream.range(1, 5).toArray();
        System.out.println("test1: " + Arrays.toString(array));
    }

    private static void test2() {
        int count = IntStream.of(1, 2, 3, 4)
                .filter(e -> e > 2)
                .peek(e -> System.out.println("Filtered value0: " + e))
                .map(e -> e * e)
                .peek(e -> System.out.println("Filtered value1: " + e))
                .sum();
        System.out.println("test2: " + count);
    }

    private static void test3() {
        int c = IntStream.range(1, 5).reduce(0, (all, i) -> all + i);
        System.out.println("test3: " + c);
    }

}
