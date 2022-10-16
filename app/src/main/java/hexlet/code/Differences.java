package hexlet.code;

import java.util.*;

import static hexlet.code.Item.*;

public class Differences {

    public static Map<String, Item> getDiff(Map<String, Object> dataFileOne, Map<String, Object> dataFileTwo) {
        //на вход приходят две мапы - клю-значение
        Map<String, Item> differ = new TreeMap<>();

        Set<String> allKey = new TreeSet<>();
        allKey.addAll(dataFileOne.keySet());//добавлены ключи из 1 мапы - follow,host,proxy,timeout
        allKey.addAll(dataFileTwo.keySet());//добавлены ключи из 2 мапы - host,timeout,verbose
        //на выходе получили упорядоченные ключи без дубликатов - allKey = follow,host,proxy,timeout,verbose
        for (String key : allKey) {//1key = follow,2key=host,3key=proxy,4key=timeout,5key=verbose
            Object oldValue = dataFileOne.get(key);//полчение 1 ключа follow(false) и т.д.
            Object newValue = dataFileTwo.get(key);//полчение 1 ключа follow(null) и т.д.

            if (!dataFileOne.containsKey(key)) {
                differ.put(key, new Item(newValue, ADDED));
            } else if (!dataFileTwo.containsKey(key)) {
                differ.put(key, new Item(oldValue, DELETED));
            } else if (Objects.equals(oldValue, newValue)) {
                differ.put(key, new Item(oldValue, newValue, UNCHANGED));
            } else {
                differ.put(key, new Item(oldValue, newValue, CHANGED));
            }
        }
        return differ;//на выходе получили тримап
    }
}
