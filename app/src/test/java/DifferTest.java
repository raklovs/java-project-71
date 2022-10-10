import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    public  String beforeEach() throws Exception {
        Path writeFilePath = Paths.get("./src/test/resources/testFiles/comparisonResult_json.txt");
        Path absolutePathToTheFile = Paths.get(String.valueOf(writeFilePath))
                .toAbsolutePath().normalize();
        return Files.readString(absolutePathToTheFile);
    }
    @Test
    public void testGenerate() throws Exception {
        var expected = beforeEach();
        String actual = Differ.generate("./src/test/resources/file1Test.json", "./src/test/resources/file2Test.json");
        assertEquals(expected, actual);
    }

}
