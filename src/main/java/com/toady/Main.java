package com.toady;

import org.apache.commons.cli.*;
import java.util.Arrays;

import com.toady.projects.Calculator;
import com.toady.projects.CalculatorGui;

public class Main {
    public static void main(String[] args) throws ParseException{
        Options options = new Options();

        Option projectToRun = new Option("p", "project", true, "project to run");
        projectToRun.setRequired(true);
        options.addOption(projectToRun);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String project = cmd.getOptionValue("project");

        if (Arrays.asList("calculator", "calc").contains(project)) {
            Calculator.runCalculator();
        }

    }
}
