package year2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 {

    public static void main(String[] args) {
        String[] lines = InputReader.getLinesOfFile("inputs/day03.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day03.example.1.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day03.example.2.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day03.example.3.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day03.example.4.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day03.example.5.txt");
        int[] partNumbers = getPartNumbers(lines);
        int sumOfPartNumbers = Arrays.stream(partNumbers).sum();
        System.out.println("Day 03 - Part A: " + sumOfPartNumbers); // 517021

        int[] gearRatios = getGearRatios(lines);
        int sumOfRatios = Arrays.stream(gearRatios).sum();
        System.out.println("Day 03 - Part B: " + sumOfRatios); // 81296995
    }


    static int[] getPartNumbers(String[] lines) {
        ArrayList<Integer> partNumbers = new ArrayList<>();

        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];

            String numberStr = "";

            for (int column = 0; column < line.length(); column++) {
                char c = line.charAt(column);
                if (Character.isDigit(c)) {
                    numberStr += c;
                }

                if (!numberStr.isEmpty() && (!Character.isDigit(c) || column + 1 == line.length())) {
                    boolean isPart = false;
                    int columnStart = Math.max(column - numberStr.length() - (column + 1 == line.length() && Character.isDigit(c) ? 0 : 1), 0);
                    int columnEnd = Math.min(column + 1, line.length());

//                    if (numberStr.equals("755")) System.out.printf("%s %s\n", columnStart, columnEnd);

                    for (int j = Math.max(row - 1, 0); j < Math.min(row + 2, lines.length); j++) {
                        for (int i = columnStart; i < columnEnd; i++) {
                            char e = lines[j].charAt(i);
                            if (!Character.isDigit(e) && e != '.') {
//                                System.out.printf("%s, row: %s, column %s\n", numberStr, j, i);
                                isPart = true;
                                break;
                            }
                        }
                    }

                    if (isPart) {
                        partNumbers.add(Integer.parseInt(numberStr));
//                        System.out.println("valid:" + numberStr);
                    } //else {
//                        System.out.println("invalid:" + numberStr);
//                    }
                    numberStr = "";

                }
            }
        }


        return partNumbers.stream().mapToInt(i -> i).toArray();
    }

    static int[] getGearRatios(String[] lines) {
        ArrayList<Integer> ratios = new ArrayList<>();

        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];
            for (int column = 0; column < line.length(); column++) {
                char c = line.charAt(column);

                if (c == '*') {
                    ArrayList<Integer> partNumbers = new ArrayList<>();
//                    System.out.println(row + " / " + column);
                    if (column - 1 >= 0 && Character.isDigit(line.charAt(column - 1))) { // left same line
//                        System.out.println("Part to the left");
                        int index = 1;
                        String number = "";
                        while (column - index >= 0 && Character.isDigit(line.charAt(column - index))) {
                            number += line.charAt(column - index);
                            index++;
                        }
                        number = new StringBuilder(number).reverse().toString();
                        partNumbers.add(Integer.parseInt(number));

                    }
                    if (column + 1 < line.length() && Character.isDigit(line.charAt(column + 1))) { // right same line
//                        System.out.println("Part to the right");
                        int index = 1;
                        String number = "";
                        while (column + index < line.length() && Character.isDigit(line.charAt(column + index))) {
                            number += line.charAt(column + index);
                            index++;
                        }
                        partNumbers.add(Integer.parseInt(number));
                    }
                    if (row - 1 >= 0) {
                        String topRow = lines[row - 1];
                        int min = column;
                        for (int i = column - 1; i >= 0; i--) {
                            if (Character.isDigit(topRow.charAt(i))) {
                                min = i;
                            } else {
                                break;
                            }
                        }
                        int max = column;
                        for (int i = column + 1; i < topRow.length(); i++) {
                            if (Character.isDigit(topRow.charAt(i))) {
                                max = i;
                            } else {
                                break;
                            }
                        }
                        String partialString = topRow.substring(min, max + 1);
//                        System.out.println(partialString);
                        int[] n = Arrays.stream(partialString.split("\\D")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
                        for (int i : n) partNumbers.add(i);
                    }
                    if (row + 1 < lines.length) {
                        String bottomRow = lines[row + 1];
                        int min = column;
                        for (int i = column - 1; i >= 0; i--) {
                            if (Character.isDigit(bottomRow.charAt(i))) {
                                min = i;
                            } else {
                                break;
                            }
                        }
                        int max = column;
                        for (int i = column + 1; i < bottomRow.length(); i++) {
                            if (Character.isDigit(bottomRow.charAt(i))) {
                                max = i;
                            } else {
                                break;
                            }
                        }
                        String partialString = bottomRow.substring(min, max + 1);
//                        System.out.println(partialString);
                        int[] n = Arrays.stream(partialString.split("\\D")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
                        for (int i : n) partNumbers.add(i);
                    }
                    if (partNumbers.size() == 2) {
                        ratios.add(partNumbers.get(0) * partNumbers.get(1));
                    }
                }
            }
        }


        return ratios.stream().mapToInt(i -> i).toArray();
    }

}
