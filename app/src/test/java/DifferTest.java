import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    public String content;

    @BeforeEach
    public  void beforeEach() throws Exception {
        Path writeFilePath = Paths.get("./src/test/resources/testFiles/comparisonResult_json.txt");
        Path absolutePathToTheFile = Paths.get(String.valueOf(writeFilePath))
                .toAbsolutePath().normalize();
        this.content = Files.readString(absolutePathToTheFile);
    }
    @Test
    public void testGenerateJsonToJson() throws Exception {
        var expected = content;
        String actual = Differ.generate("./src/test/resources/file1Test.json", "./src/test/resources/file2Test.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYmlToJYml() throws Exception {
        var expected = content;
        String actual = Differ.generate("./src/test/resources/file1Test.yml", "./src/test/resources/file2Test.yml");
        assertEquals(expected, actual);
    }

}
