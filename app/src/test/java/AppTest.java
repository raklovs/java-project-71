import hexlet.code.Differ;
import hexlet.code.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AppTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static String resultArrayJson;
    private  final String pathToDirectory = "app/src/test/resources/";

    private static Path getFixturePath(String fileName) {
        //получить абсолютный путь к файлу: C:\Users\Sergey\java-project-71\app\src\test\resources\fixtures\result_json.json
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    private static Path getPath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        //на вход приходит "result_json.json"
        //переход к методу getFixturePath()
        //на выходе полный путь: C:\Users\Sergey\java-project-71\app\src\test\resources\fixtures\result_json.json
        Path filePath = getFixturePath(fileName);
        //возврат значения из файла: {"follow":{"oldValue":false,"newValue":null,"status":"deleted"},
        // "host":{"oldValue":"hexlet.io","newValue":"hexlet.io","status":"unchanged"},
        // "proxy":{"oldValue":"123.234.53.22","newValue":null,"status":"deleted"},
        // "timeout":{"oldValue":50,"newValue":20,"status":"changed"},
        // "verbose":{"oldValue":true,"newValue":null,"status":"added"}}
        return Files.readString(filePath).trim();
    }

    @BeforeAll//запуск метода перед тестами
    public static void beforeAll() throws Exception {
        //получение информации из файлов
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
        resultArrayJson = readFixture("result_json2.txt");
    }
    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        //вначале на вход приходит формат: "json"
        //получить абсолютный путь к файлу с форматом "json"
        //C:\Users\Sergey\java-project-71\app\src\test\resources\file1.json
        String filePath1 = getPath("file1." + format).toString();
        //C:\Users\Sergey\java-project-71\app\src\test\resources\file2.json
        String filePath2 = getPath("file2." + format).toString();

        //переход к методу generate()
        assertEquals(resultStylish, Differ.generate(filePath1, filePath2));
        assertEquals(resultArrayJson, Differ.generate(filePath1, filePath2));
        assertEquals(resultStylish, Differ.generate(filePath1, filePath2, "stylish"));
        assertEquals(resultPlain, Differ.generate(filePath1, filePath2, "plain"));

        String actualJson = Differ.generate(filePath1, filePath2, "json");
        JSONAssert.assertEquals(resultJson, actualJson, false);

    }

    @Test
    public void testGetDataFormatYml() {
        //app/src/test/resources/file1.yml
        var filePath1 = pathToDirectory + "file1.yml";
        //получить формат: "yml"
        String actual = FileUtils.getDataFormat(filePath1);
        String expected = "yml";
        //сравнить
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void testGetDataFormatJson() {
        //app/src/test/resources/file1.json
        var filePath1 = pathToDirectory + "file1.json";
        //получить формат: "json"
        String actual = FileUtils.getDataFormat(filePath1);
        String expected = "json";
        //сравнить
        assertThat(actual).isEqualTo(expected);

    }
}
