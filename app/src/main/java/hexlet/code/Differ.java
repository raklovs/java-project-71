package hexlet.code;

import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath) throws Exception {

        String firstFileContent = Parser.workingWithFilePaths(firstFilePath);
        String secondFileContent = Parser.workingWithFilePaths(secondFilePath);

        var contentKeyValueFirstMap = Parser.convertingStringOfTextToDataJson(firstFileContent);
        var contentKeyValueSecondMap = Parser.convertingStringOfTextToDataJson(secondFileContent);

        Set<String> commonKeys = new TreeSet<>();
        //Объекты хранятся в отсортированном и возрастающем порядке - commonKeys = [follow, host, proxy, timeout]
        commonKeys.addAll(contentKeyValueFirstMap.keySet());
        commonKeys.addAll(contentKeyValueSecondMap.keySet());

        // Варианты условий
        //1 - ключ есть в обоих файлах, и его значения совпадают - отсутствует плюс или минус(host: hexlet.io)
        //2 - ключ есть в обоих файлах, и его значения не совпадают -
        // отсутствует плюс или минус(- timeout: 50, + timeout: 20)
        //3 - ключ присутствует в первом файле, но отсутствует во втором файле - (- follow: false)
        //4 - ключ отсутствует в первом файле, но присутствует во втором файле - (+ verbose: true)
        StringBuilder resultOfComparingTwoFiles = new StringBuilder("{\r\n");
        for (String key : commonKeys) {

            String str1 = key + ": " + contentKeyValueFirstMap.get(key) + "\r\n";
            String str2 = key + ": " + contentKeyValueSecondMap.get(key) + "\r\n";

            if (contentKeyValueFirstMap.containsKey(key) && !contentKeyValueSecondMap.containsKey(key)) {
                resultOfComparingTwoFiles.append("- ").append(str1);
            } else if (contentKeyValueFirstMap.containsKey(key) && contentKeyValueSecondMap.containsKey(key)
                    && contentKeyValueFirstMap.get(key).equals(contentKeyValueSecondMap.get(key))) {
                resultOfComparingTwoFiles.append("  ").append(str1);
            } else if (!contentKeyValueFirstMap.containsKey(key) && contentKeyValueSecondMap.containsKey(key)) {
                resultOfComparingTwoFiles.append("+ ").append(str2);
            } else {
                resultOfComparingTwoFiles.append("- ").append(str1);
                resultOfComparingTwoFiles.append("+ ").append(str2);
            }
        }
        return resultOfComparingTwoFiles + "}";
    }
}
