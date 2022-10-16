package hexlet.code.formatter;

import hexlet.code.Item;

import java.util.Map;

import static hexlet.code.Item.ADDED;
import static hexlet.code.Item.DELETED;
import static hexlet.code.Item.CHANGED;
import static hexlet.code.Item.UNCHANGED;

public class Stylish {

    public static String makeStylish(Map<String, Item> differ) throws Exception {
        //на входе TreeMap differ = follow,host,proxy,timeout,verbose - это ключи,
        // а значения(oldValue,newValue,status) берутся из класса Item
        StringBuilder result = new StringBuilder();

        result.append("{"); //действия - положить в result открывающую скобку
        //в item ключ - follow, value - (oldValue,newValue,status) берутся из класса Item
        for (Map.Entry<String, Item> item : differ.entrySet()) {
            result.append("\r\n").append(" ".repeat(2)); //действия - перевод строки + повтор пробела 2 раза
            switch (item.getValue().getStatus()) { //из item получает DELETED
                case ADDED:
                    result.append("+").append(" ")
                            .append(item.getKey()).append(": ").append(item.getValue().getOldValue());
                    break;
                case DELETED:
                    result.append("-").append(" ")
                            .append(item.getKey()).append(": ").append(item.getValue().getOldValue());
                    break;
                case CHANGED:
                    result.append("-").append(" ")
                            .append(item.getKey()).append(": ").append(item.getValue().getOldValue());
                    result.append("\r\n").append(" ".repeat(2)).append("+").append(" ")
                            .append(item.getKey()).append(": ").append(item.getValue().getNewValue());
                    break;
                case UNCHANGED:
                    result.append(" ".repeat(2))
                            .append(item.getKey()).append(": ").append(item.getValue().getOldValue());
                    break;
                default:
                    throw new Exception("Incorrect status: '" + item.getValue().getStatus() + "'");

            }
        }
        return result.append("\r\n}").toString();
    }
}
