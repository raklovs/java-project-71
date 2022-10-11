package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static String workingWithFilePaths(String filePath) throws Exception {
        Path absolutePathToTheFile = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(absolutePathToTheFile);
    }

    public static Map<String, Object> convertingStringOfTextToDataJson(String fileContent)
            throws JsonProcessingException {
        //создаем объект класса ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileContent, new TypeReference<>() {
        });
    }

    public static Map<String, Object> convertingStringOfTextToDataYAML(String fileContent)
            throws JsonProcessingException {
        //создаем объект класса ObjectMapper
        ObjectMapper objectMapper = new YAMLMapper();
        return objectMapper.readValue(fileContent, new TypeReference<>() {
        });
    }
}
