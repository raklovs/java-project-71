package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "getDiff", mixinStandardHelpOptions = true, version = "getDiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    private final Integer errorCode = 123;

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String firstFilePath;
    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String secondFilePath;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested;
    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionInfoRequested;
    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;

    @Override
    public final Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(firstFilePath, secondFilePath, format));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return errorCode;
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
