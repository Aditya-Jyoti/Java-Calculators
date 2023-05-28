package projects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static String runCalculator() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter expression: ");
        List<String> expression = new ArrayList<String>(Arrays.asList(scanner.nextLine().split(" ")));
        scanner.close();

        while (expression.size() != 1) {
            if (expression.contains("/")) {
                int opIdx = expression.indexOf("/");
                double lhs = Double.parseDouble(expression.get(opIdx - 1));
                double rhs = Double.parseDouble(expression.get(opIdx + 1));

                expression.remove(opIdx + 1);
                expression.remove(opIdx);
                expression.remove(opIdx - 1);

                expression.add(opIdx - 1, Double.toString(lhs / rhs));
                continue;

            } else if (expression.contains("*")) {
                int opIdx = expression.indexOf("*");
                double lhs = Double.parseDouble(expression.get(opIdx - 1));
                double rhs = Double.parseDouble(expression.get(opIdx + 1));

                expression.remove(opIdx + 1);
                expression.remove(opIdx);
                expression.remove(opIdx - 1);

                expression.add(opIdx - 1, Double.toString(lhs * rhs));
                continue;

            } else if (expression.contains("+")) {
                int opIdx = expression.indexOf("+");
                double lhs = Double.parseDouble(expression.get(opIdx - 1));
                double rhs = Double.parseDouble(expression.get(opIdx + 1));

                expression.remove(opIdx + 1);
                expression.remove(opIdx);
                expression.remove(opIdx - 1);

                expression.add(opIdx - 1, Double.toString(lhs + rhs));
                continue;

            } else if (expression.contains("-")) {
                int opIdx = expression.indexOf("-");
                double lhs = Double.parseDouble(expression.get(opIdx - 1));
                double rhs = Double.parseDouble(expression.get(opIdx + 1));

                expression.remove(opIdx + 1);
                expression.remove(opIdx);
                expression.remove(opIdx - 1);

                expression.add(opIdx - 1, Double.toString(lhs - rhs));
                continue;
            }
        }

        return expression.get(0);
    }
}

