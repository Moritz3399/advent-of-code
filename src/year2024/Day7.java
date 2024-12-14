package year2024;

import utils.InputReader;
import utils.StopWatch;

import java.util.Arrays;

public class Day7 {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String[] input = InputReader.getLinesOfFileByParameters(2024, 7, null);
        long sum = 0;
        long sumConcat = 0;
        for (String i : input) {
            Calibration c = new Calibration(i);
            if (c.canBeTrue) {
                sum += c.checksum;
            }
            if (c.canBeTrueConcat) {
                sumConcat += c.checksum;
            }
        }

        System.out.println("Day 7 Part A: Sum of all possible checksums with two operators: " + sum); //12940396350192
        System.out.println("Day 7 Part B: Sum of all possible checksums with three operators: " + sumConcat); //12940396350192

        stopWatch.stop();
        System.out.println(stopWatch);

    }

    private static class Calibration {

        long checksum;
        long[] values;
        boolean canBeTrue = false;
        boolean canBeTrueConcat = false;

        Calibration(String input) {
            checksum = Long.parseLong(input.split(":")[0]);
            values = Arrays.stream(input.split(": ")[1].split(" ")).mapToLong(Long::parseLong).toArray();
            checkOperators(1, values[0]);
            checkOperatorsWithConcatenation(1, values[0]);
            System.out.println("This combination '" + input + "' can " + (canBeTrue ? "" : "not ") + "be true.");
            System.out.println("This combination '" + input + "' can " + (canBeTrueConcat ? "" : "not ") + "be true with concatenation.");
        }


        private void checkOperators(int index, long interimValue) {
            if (canBeTrue) return;
            if (index == values.length) {
                canBeTrue = checksum == interimValue;
                return;
            }
            checkOperators(index + 1, interimValue + values[index]);
            checkOperators(index + 1, interimValue * values[index]);
        }

        private void checkOperatorsWithConcatenation(int index, long interimValue) {
            if (canBeTrueConcat) return;
            if (index == values.length) {
                canBeTrueConcat = checksum == interimValue;
                return;
            }
            checkOperatorsWithConcatenation(index + 1, interimValue + values[index]);
            checkOperatorsWithConcatenation(index + 1, interimValue * values[index]);
            checkOperatorsWithConcatenation(index + 1, concatLongs(interimValue, values[index]));
        }

        private long concatLongs(long a, long b) {
            if (a == 0L) return b;
            return Long.parseLong(Long.toString(a) + Long.toString(b));
        }


    }

}
