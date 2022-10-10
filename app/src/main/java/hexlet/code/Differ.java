package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        // Формируем абсолютный путь: C:\Users\Sergey\java-project-71\app\src\main\resources\file1.json
        Path firstAbsolutePathToTheFile = Paths.get(firstFilePath).toAbsolutePath().normalize();
        Path secondAbsolutePathToTheFile = Paths.get(secondFilePath).toAbsolutePath().normalize();
        //Читаем файл: {"host": "hexlet.io","timeout": 50,"proxy": "123.234.53.22","follow": false}
        String firstFileContent = Files.readString(firstAbsolutePathToTheFile);//Читаем файл
        String secondFileContent = Files.readString(secondAbsolutePathToTheFile);
        //создаем объект класса ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        //получаем contentKeyValueFirstMap = {host=hexlet.io, timeout=50, proxy=123.234.53.22, follow=false}
        Map<String, Object> contentKeyValueFirstMap = objectMapper.readValue(firstFileContent, new TypeReference<>() {
        });
        Map<String, Object> contentKeyValueSecondMap = objectMapper.readValue(secondFileContent, new TypeReference<>() {
        });
        Set<String> commonKeys = new TreeSet<>();
        //Объекты хранятся в отсортированном и возрастающем порядке - commonKeys = [follow, host, proxy, timeout]
        commonKeys.addAll(contentKeyValueFirstMap.keySet());
        commonKeys.addAll(contentKeyValueSecondMap.keySet());

        // Варианты условий
        //1 - ключ есть в обоих файлах, и его значения совпадают - отсутствует плюс или минус(host: hexlet.io)
        //2 - ключ есть в обоих файлах, и его значения не совпадают - отсутствует плюс или минус(- timeout: 50, + timeout: 20)
        //3 - ключ присутствует в первом файле, но отсутствует во втором файле - (- follow: false)
        //4 - ключ отсутствует в первом файле, но присутствует во втором файле - (+ verbose: true)
        StringBuilder resultOfComparingTwoFiles = new StringBuilder("{\n");
        for (String key : commonKeys) {

            String str1 = key + ": " + contentKeyValueFirstMap.get(key) + "\n";
            String str2 = key + ": " + contentKeyValueSecondMap.get(key) + "\n";

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

    public static void main(String[] args) throws IOException {
        // Варианты условий
        //1 - ключ есть в обоих файлах, и его значения совпадают - отсутствует плюс или минус(host: hexlet.io)
        //2 - ключ есть в обоих файлах, и его значения не совпадают - отсутствует плюс или минус(- timeout: 50, + timeout: 20)
        //3 - ключ присутствует в первом файле, но отсутствует во втором файле - (- follow: false)
        //4 - ключ отсутствует в первом файле, но присутствует во втором файле - (+ verbose: true)
        // Формируем абсолютный путь: C:\Users\Sergey\java-project-71\app\src\main\resources\file1.json
        Path path1 = Paths.get("app/src/main/resources/file1.json").toAbsolutePath().normalize();
        Path path2 = Paths.get("app/src/main/resources/file2.json").toAbsolutePath().normalize();
        //Читаем файл: {"host": "hexlet.io","timeout": 50,"proxy": "123.234.53.22","follow": false}
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);
        //создаем объект класса ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        //получаем map1 = {host=hexlet.io, timeout=50, proxy=123.234.53.22, follow=false}
        Map<String, Object> map1 = objectMapper.readValue(content1, new TypeReference<>() {
        });
        //получаем map2 = {host=hexlet.io, timeout=20, verbose=true}
        Map<String, Object> map2 = objectMapper.readValue(content2, new TypeReference<>() {
        });

        Set<String> commonKeys = new TreeSet<>();
        //Объекты хранятся в отсортированном и возрастающем порядке - commonKeys = [follow, host, proxy, timeout]
        commonKeys.addAll(map1.keySet());
        commonKeys.addAll(map2.keySet());
        //System.out.println(commonKeys);//[follow, host, proxy, timeout, verbose]
        StringBuilder result = new StringBuilder("{\n");
        for (String key : commonKeys) {
            String str1 = key + ": " + map1.get(key) + "\n";
            String str2 = key + ": " + map2.get(key) + "\n";
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("- ").append(str1);
            } else if (map1.containsKey(key) && map2.containsKey(key) && map1.get(key).equals(map2.get(key))) {
                result.append("  ").append(str1);
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("+ ").append(str2);
            } else {
                result.append("- ").append(str1);
                result.append("+ ").append(str2);
            }
        }
        System.out.println(result + "}");
    }
}
