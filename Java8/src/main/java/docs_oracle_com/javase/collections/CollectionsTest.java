package docs_oracle_com.javase.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectionsTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "1");
        map.put("d", "3");
        map.values().removeAll(Collections.singleton("1"));
        System.out.println(map.toString());
    }

}
