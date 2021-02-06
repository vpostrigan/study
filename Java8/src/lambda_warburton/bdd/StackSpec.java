/* (c) Copyright Selerity, Inc. 2009-2018. All rights reserved. This source code is confidential 
and proprietary information of Selerity Inc. and may be used only by a recipient designated 
by and for the purposes permitted by Selerity Inc. in writing.  Reproduction of, dissemination 
of, modifications to or creation of derivative works from this source code, whether in source 
or binary forms, by any means and in any form or manner, is expressly prohibited, except with 
the prior written permission of Selerity Inc..  THIS CODE AND INFORMATION ARE PROVIDED "AS IS"
WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO IMPLIED
WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE. This notice may not be 
removed from the software by any user thereof.*/
package bdd;

import java.util.Stack;
import static bdd.Description.describe;

public class StackSpec {
    {
        describe("a stack", it -> {
            it.should("be empty when created", expect -> {
               // expect.that(new Stack()).isEmpty();
            });
            it.should("push new elements onto the top of the stack", expect -> {
                Stack<Integer> stack = new Stack<>();
                stack.push(1);
                //expect.that(stack.get(0)).isEqualTo(1);
            });
            it.should("pop the last element pushed onto the stack", expect -> {
                Stack<Integer> stack = new Stack<>();
                stack.push(2);
                stack.push(1);
                //expect.that(stack.pop()).isEqualTo(2);
            });
        });
    }
}
