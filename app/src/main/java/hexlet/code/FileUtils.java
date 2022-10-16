package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static final String JSON = "json";
    public static final String YML = "yml";
    public static final String YAML = "yaml";

    public static String readFile(String pathToFile) throws Exception {

        String content;
        Path pathFile;
        //file1 = C:\Users\Sergey\java-project-71\app\src\test\resources\file1.json
        File file = new File(pathToFile);
        //absolutePath = C:\Users\Sergey\java-project-71\app\src\test\resources\file1.json
        String absolutePath = file.getAbsolutePath();

        pathFile = Path.of(absolutePath);
        //content = {"host": "hexlet.io","timeout": 50,"proxy": "123.234.53.22","follow": false}
        content = Files.readString(pathFile);
        return content;

    }


    public static String getDataFormat(String pathToFile) {
        int index = pathToFile.lastIndexOf('.'); //index: 28
        return index > 0 ? pathToFile.substring(index + 1) : "";
    }
}
