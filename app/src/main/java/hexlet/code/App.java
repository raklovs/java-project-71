package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "getDiff", mixinStandardHelpOptions = true, version = "getDiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String firstFilePath;
    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String secondFilePath;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested;
    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionInfoRequested;
    @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        String diff;
        try {
            diff = Differ.generate(firstFilePath, secondFilePath);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(diff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
