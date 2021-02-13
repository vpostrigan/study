package docs_oracle_com.javase.collections;

import java.util.Queue;

public class PriorityQueue {

    public static void main(String[] args) {
        Queue<String> q = new java.util.PriorityQueue<>(/*String.CASE_INSENSITIVE_ORDER*/);
        q.add("4");
        q.add("3");
        q.add("2");
        q.add("1");
        q.add("0");
        q.add("00");
        q.add("100");

        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }

}