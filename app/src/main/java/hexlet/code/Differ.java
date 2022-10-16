package hexlet.code;

import hexlet.code.formatter.Formatter;

import java.util.Map;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) throws Exception {
        //на вход приходит полный путь к двум файлам
        //переход к методу readFile() и возврат результата
        //content1 = {"host": "hexlet.io","timeout": 50,"proxy": "123.234.53.22","follow": false}
        String content1 = FileUtils.readFile(firstFilePath);
        //content2 = {"timeout": 20,"verbose": true,"host": "hexlet.io"}
        String contain2 = FileUtils.readFile(secondFilePath);
        //переход к методу getDataFormat()
        String extension1 = FileUtils.getDataFormat(firstFilePath); //extension1 = "json"
        String extension2 = FileUtils.getDataFormat(secondFilePath); //extension2 = "json"
        //переход к методу getData()
        Map<String, Object> data1 = Parser.getData(content1, extension1);
        Map<String, Object> data2 = Parser.getData(contain2, extension2);
        //differ(TreeMap) = follow,host,proxy,timeout,verbose
        Map<String, Item> differ = Differences.getDiff(data1, data2);
        return Formatter.getFormat(differ, format);

    }

    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        //на вход приходит полный путь к двум файлам
        //перегрузка и перевод к методу generate() вверх
        return generate(firstFilePath, secondFilePath, "stylish");

    }
}
