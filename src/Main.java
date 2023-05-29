import java.util.Arrays;

import org.apache.commons.cli.*;

import projects.Calculator;
import projects.CalculatorGui;

public class Main {
    public static void main(String[] args) throws ParseException {
        Options options = new Options();

        Option projectToRun = new Option("p", "project", true, "project to run (required)");
        projectToRun.setRequired(true);
        options.addOption(projectToRun);

        Option projectHelp = new Option("h", "help", false, "displays this help information");
        projectHelp.setRequired(false);
        options.addOption(projectHelp);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        String project = " ";

        try {
            CommandLine cmd = parser.parse(options, args);
            project = cmd.getOptionValue("project");
        } catch (ParseException e) {
            formatter.printHelp("help", options);
        }

        if (Arrays.asList("calculator", "calc").contains(project.toLowerCase())) {
            System.out.println("Result: " + Calculator.main());
        } else if (Arrays.asList("gui", "calc-gui", "calcgui", "calculator-gui").contains(project.toLowerCase())) {
            CalculatorGui.startGui();
        }
    }
}
