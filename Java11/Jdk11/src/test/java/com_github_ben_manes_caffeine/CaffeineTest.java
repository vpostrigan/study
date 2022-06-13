package com_github_ben_manes_caffeine;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * https://www.baeldung.com/java-caching-caffeine
 */
public class CaffeineTest {

    @Test
    public void test31() throws InterruptedException {
        // Populating Cache
        // 1. Manual Populating (manually put values into the cache and retrieve them later)
        Cache<String, DataObject> cache = Caffeine.newBuilder()
                .expireAfterAccess(8, TimeUnit.SECONDS)
                .maximumSize(10)
                .build();
        for (int i = 0; i < 15; i++) {
            cache.put("" + i, DataObject.of("" + i));
            Thread.sleep(250);
            System.out.println(cache.asMap());
        }

        Assertions.assertNotNull(cache.getIfPresent("7"));
        cache.invalidate("7");
        Assertions.assertNull(cache.getIfPresent("7"));
        Assertions.assertNotNull(cache.get("7", k -> DataObject.of("7")));

        Map<String, DataObject> dataObjectMap
                = cache.getAllPresent(Arrays.asList("7", "150", "5"));
        System.out.println(dataObjectMap);
        Assertions.assertEquals(2, dataObjectMap.size());

        Assertions.assertNotNull(cache.getIfPresent("11"));
        System.out.println("stop");
        for (int i = 0; i < 100; i++) {
            cache.getIfPresent("11");
            Thread.sleep(250);
            System.out.println(cache.asMap());

            // The get method performs the computation atomically.
            // This means that the computation will be made only once —
            // even if several threads ask for the value simultaneously.
            // That's why using get is preferable to getIfPresent.
            cache.get("10000", k -> DataObject.of("Data for A"));
        }
        System.out.println("stop2");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            System.out.println(cache.asMap());
        }
    }

    @Test
    public void test32() throws InterruptedException {
        // Populating Cache
        // 2. Synchronous Loading
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .expireAfterWrite(8, TimeUnit.SECONDS)
                .maximumSize(10)
                .build(k -> {
                    System.out.println("> CALL: " + "Data for " + k);
                    return DataObject.of("Data for " + k);
                });

        String key = "10";
        DataObject dataObject = cache.get(key);
        for (int i = 0; i < 20; i++) {
            DataObject dataObject2 = cache.get(key);
            System.out.println(dataObject2);
            Thread.sleep(1000);
        }

        Assertions.assertNotNull(dataObject);
        Assertions.assertEquals("Data for " + key, dataObject.getData());

        Map<String, DataObject> dataObjectMap = cache.getAll(Arrays.asList("A", "B", "C"));
        Assertions.assertEquals(3, dataObjectMap.size());
    }

    @Test
    public void test33() throws InterruptedException {
        // Populating Cache
        // 3. Asynchronous Loading
        AsyncLoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> {
                    System.out.println("> CALL: " + "Data for " + k);
                    return DataObject.of("Data for " + k);
                });

        String key = "A";

        cache.get(key).thenAccept(dataObject -> {
            Assertions.assertNotNull(dataObject);
            Assertions.assertEquals("Data for " + key, dataObject.getData());
        });

        cache.getAll(Arrays.asList("A", "B", "C"))
                .thenAccept(dataObjectMap -> Assertions.assertEquals(3, dataObjectMap.size()));
    }


    private static class DataObject {
        private final String data;

        private static int objectCounter = 0;

        private DataObject(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        @Override
        public String toString() {
            return "data='" + data + '\'';
        }

        public static DataObject of(String data) {
            objectCounter++;
            return new DataObject(data);
        }
    }

}
