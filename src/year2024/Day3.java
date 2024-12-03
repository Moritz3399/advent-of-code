package year2024;

import utils.InputReader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2024, 3, null);
        String program = String.join("", input);
        Pattern pattern = Pattern.compile("mul\\(([0-9]{1,3}),([0-9]{1,3})\\)");
        Matcher matcher = pattern.matcher(program);
        int sum = 0;
        while (matcher.find()) {
            String fullGroup = matcher.group();
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            sum += a * b;
        }
        System.out.println("Day 3 Part A: " + sum);
        sum = 0;
        ArrayList<String> subPrograms = new ArrayList<>();
        int dontIndex = -1;
        int doIndex = 0;
        while ((dontIndex = program.indexOf("don't()", doIndex)) > -1) {
            String subProgram = program.substring(doIndex, dontIndex);
            subPrograms.add(subProgram);
            doIndex = program.indexOf("do()", dontIndex);
        }
        if (doIndex > -1) {
            String subProgram = program.substring(doIndex);
            subPrograms.add(subProgram);
        }
        for (String s : subPrograms) {
            Matcher m = pattern.matcher(s);
            while (m.find()) {
                String fullGroup = m.group();
                int a = Integer.parseInt(m.group(1));
                int b = Integer.parseInt(m.group(2));
                sum += a * b;
            }
        }
        System.out.println("Day 3 Part B: " + sum);
    }
}
