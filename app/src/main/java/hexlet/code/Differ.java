package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath) throws IOException {

        Path path1 = Paths.get(firstFilePath).toAbsolutePath().normalize();// Формируем абсолютный путь
        String content1 = Files.readString(path1);//Читаем файл
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map1 = objectMapper.readValue(content1, new TypeReference<>(){});
        return "";
    }

    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("src/main/resources/file1.json").toAbsolutePath().normalize();// Формируем абсолютный путь: C:\Users\Sergey\java-project-71\app\src\main\resources\file1.json
        String content1 = Files.readString(path1);//Читаем файл: {"host": "hexlet.io","timeout": 50,"proxy": "123.234.53.22","follow": false}
        ObjectMapper objectMapper = new ObjectMapper();//создаем объект класса ObjectMapper
        Map<String, Object> map1 = objectMapper.readValue(content1, new TypeReference<>(){});//получаем мапу - {host=hexlet.io, timeout=50, proxy=123.234.53.22, follow=false}

    }

}
