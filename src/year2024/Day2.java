package year2024;

import utils.InputReader;

import java.util.Arrays;

public class Day2 {

    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2024, 2, null);
        int counterPartA = 0;
        int counterPartB = 0;
        for (String line : input) {
            int[] levels = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean increasing = levels[0] < levels[1];
            boolean isSafe = true;
            for (int i = 0; i < levels.length - 1; i++) {
                int a = levels[i];
                int b = levels[i + 1];
                if (a == b || Math.abs(a - b) > 3 || ((a > b) && increasing) || ((a < b) && !increasing)) {
                    isSafe = false;
                    break;
                }
            }
            System.out.println(line + " in A is " + (isSafe ? "safe" : "unsafe"));

            if (isSafe) {
                counterPartA++;
                counterPartB++;
                continue;
            }
            outer:
            for (int skippingIndex = 0; skippingIndex < levels.length; skippingIndex++) {
                isSafe = true;
                int[] newLevels = new int[levels.length - 1];
                int originalIndex = 0;
                for (int i = 0; i < newLevels.length; i++) {
                    if (originalIndex == skippingIndex) originalIndex++;
                    newLevels[i] = levels[originalIndex++];
                }
//                System.out.println(String.join(" ", Arrays.stream(newLevels).mapToObj(Integer::toString).toList()));
                increasing = newLevels[0] < newLevels[1];
                for (int i = 0; i < newLevels.length - 1; i++) {

                    int a = newLevels[i];
                    int b = newLevels[i + 1];
                    if (a == b || Math.abs(a - b) > 3 || ((a > b) && increasing) || ((a < b) && !increasing)) {
                        isSafe = false;
                        break ;
                    }
                }
                if (isSafe) {
                    counterPartB++;
                    System.out.println("Got safe by skipping level index: " + skippingIndex + " with value of " + levels[skippingIndex]);
                    break outer;
                }

            }


        }

        System.out.println("Day 2 Part A: Number of safe levels: " + counterPartA);
        System.out.println("Day 2 Part B: Number of safe levels: " + counterPartB);

    }
}
