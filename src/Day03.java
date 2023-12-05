import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day03 {

    public static void main(String[] args) {
        String[] lines = InputReader.getLinesOfFile("inputs/day03.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day03.example.1.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day03.example.2.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day03.example.3.txt");
        int[] partNumbers = getPartNumbers(lines);
        int sumOfPartNumbers = Arrays.stream(partNumbers).sum();
        System.out.println("Day 03 - Part A: " + sumOfPartNumbers); // not 519225 (too high) // not 514350 (too low)
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

                    if (numberStr.equals("755")) System.out.printf("%s %s\n", columnStart, columnEnd);

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
                        System.out.println("valid:" + numberStr);
                    } else {
                        System.out.println("invalid:" + numberStr);

                    }
                    numberStr = "";

                }
            }
        }


        return partNumbers.stream().mapToInt(i -> i).toArray();
    }

}
