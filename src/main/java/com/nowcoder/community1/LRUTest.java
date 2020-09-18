package com.nowcoder.community1;

import java.util.*;

public class LRUTest {
    private static int size = 5;

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<String,String>(size, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > size;
            }
        };
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");
        System.out.println(map.toString());

        map.put("6", "6");
        System.out.println(map.toString());
        map.get("3");
        System.out.println(map.toString());
        map.put("7", "7");
        System.out.println(map.toString());
        map.get("5");
        System.out.println(map.toString());
    }
}
