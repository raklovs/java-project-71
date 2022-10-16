package hexlet.code.formatter;

import hexlet.code.Item;

import java.util.Map;

public class Formatter {
    public static String getFormat(Map<String, Item> differ, String format) throws Exception {
        //на входе TreeMap differ = follow,host,proxy,timeout,verbose format = stylish
        return switch (format) {

            case "plain" -> Plain.makePlain(differ);
            case "json" -> Json.makeJson(differ);
            //переходим в метод makeStylish() и вернулось {- follow: false host: hexlet.io - proxy: 123.234.53.22 -
            // timeout: 50 + timeout: 20 + verbose: tru}
            case "stylish" -> Stylish.makeStylish(differ);
            default -> throw new Exception("Formatting error");

        };
    }
}
