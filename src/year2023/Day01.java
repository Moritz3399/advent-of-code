package year2023;

import java.util.ArrayList;

public class Day01 {


    public static void main(String[] args) {
        String[] lines = InputReader.getLinesOfFile("inputs/day01.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day01.example.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day01.example.2.txt");
        int sum = solve(lines);
        System.out.println("Part A:" + sum);
        int partB = solve(preprocessInput(lines));
        System.out.println("Part B: " + partB);
    }

    private static int solve(String[] input) {

        ArrayList<Integer> calibrationValues = new ArrayList<>();
        for (String line : input) {
            char[] chars = line.toCharArray();
            ArrayList<Integer> digits = new ArrayList<>();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    Integer i = Integer.parseInt(Character.toString(c));
                    digits.add(i);
                }
            }
            int size = digits.size();
            if (size > 0) {
                int calibrationValue = 10 * digits.get(0) + digits.get(size - 1);
                System.out.println(calibrationValue + " " + line);
                calibrationValues.add(calibrationValue);
            } else {
                System.out.println("no digit in " + line);
            }
        }
        int sum = calibrationValues.stream().mapToInt(Integer::intValue).sum();
        return sum;
    }

    private static String[] preprocessInput(String[] input) {
        String[] processed = new String[input.length];

        for (int i = 0; i < processed.length; i++) {
            processed[i] = input[i]
                    .replace("one", "one1one")
                    .replace("two", "two2two")
                    .replace("three", "three3three")
                    .replace("four", "four4four")
                    .replace("five", "five5five")
                    .replace("six", "six6six")
                    .replace("seven", "seven7seven")
                    .replace("eight", "eight8eight")
                    .replace("nine", "nine9nine");
        }

        return processed;
    }
}
