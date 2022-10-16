package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.FileUtils.*;

public class Parser {

    public static Map<String, Object> getData(String content, String dataFormat) throws Exception {
        //на вход пришел dataFormat = json
        switch (dataFormat) {
            case YAML, YML -> {
                return parseYml(content);
            }
            case JSON -> {//сработал этот метод
                return parseJson(content);//переход к методу parseJson()
            }
            default -> throw new Exception("Unknown format: '" + dataFormat + "'");
        }

    }

    public static Map<String, Object> parseYml(String content) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(content, new TypeReference<TreeMap<String, Object>>() {
        });

    }

    public static Map<String, Object> parseJson(String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();//на вход пришел content: {"host": "hexlet.io","timeout": 50,"proxy": "123.234.53.22","follow": false}
        return objectMapper.readValue(content, new TypeReference<TreeMap<String, Object>>() {
        });
    }
}
