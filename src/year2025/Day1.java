package year2025;

import utils.InputReader;

public class Day1 {

    private static int zeroClick = 0;

    private static int walk(int startValue, String rotation) {
        int rotationValue = Integer.parseInt(rotation.substring(1));
        int step = rotation.startsWith("R") ? 1 : -1;
        int value = startValue;
        for (int i = 0; i < rotationValue; i++) {
            value += step;
            if (value == -1) {
                value = 99;
            }
            if (value == 100) {
                value = 0;
            }
            if (value == 0) {
                zeroClick++;
            }
        }
        return value;
    }

    private static int rotate(int startValue, String rotation) throws IllegalAccessException {
        int rotationValue = Integer.parseInt(rotation.substring(1));
        if (rotation.startsWith("R")) {
            int newValue = startValue + rotationValue;
            while (newValue > 99) {
                newValue -= 100;
            }

            return newValue;
        }
        if (rotation.startsWith("L")) {
            int newValue = startValue - rotationValue;

            while (newValue < 0) {
                newValue += 100;
            }
            return newValue;
        }
        throw new IllegalAccessException("Unknown rotation: " + rotation);


    }

    private static void solveFirstPart(String[] input) throws IllegalAccessException {
        int startValue = 50;
        int zeroCounter = 0;
        for (String rotation : input) {
            startValue = rotate(startValue, rotation);
            if (startValue == 0) {
                zeroCounter++;
            }
        }
        System.out.println("Day 1 Part A - zero counter: " + zeroCounter);
    }

    private static void solveSecondPart(String[] input) {
        int startValue = 50;
        int zeroCounter = 0;
        for (String rotation : input) {
            startValue = walk(startValue, rotation);
            if (startValue == 0) {
                zeroCounter++;
            }
        }
        System.out.println("Day 1 Part A - zero counter: " + zeroCounter); //962
        System.out.println("Day 1 Part B - zero clicks: " + zeroClick); //5782
    }


    public static void main(String[] args) throws IllegalAccessException {
        String[] input = InputReader.getLinesOfFileByParameters(2025, 1, null);
        solveFirstPart(input); // 962
        input = InputReader.getLinesOfFileByParameters(2025, 1, null);
        solveSecondPart(input); // 5765 < x < 6139
    }

}
