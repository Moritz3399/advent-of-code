package year2024;

import utils.CoordinatePoint;
import utils.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2024, 8, null);

        HashMap<Character, List<CoordinatePoint>> antennas = new HashMap<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                char frequency = input[y].charAt(x);
                if (frequency == '.') continue;
                antennas.computeIfAbsent(frequency, k -> new ArrayList<CoordinatePoint>());
                antennas.get(frequency).add(new CoordinatePoint(x, y));
            }
        }

        System.out.println("Day 8 Part A: Unique location in bounds: " + calculateHeatMap(antennas, new int[input.length][input[0].length()], false)); // 220
        System.out.println("Day 8 Part B: Unique location in bounds: " + calculateHeatMap(antennas, new int[input.length][input[0].length()], true)); // 813


    }

    private static int calculateHeatMap(HashMap<Character, List<CoordinatePoint>> antennas, int[][] heatMap, boolean doMultiple) {
        for (char frequency : antennas.keySet()) {
            List<CoordinatePoint> frequencyAntennas = antennas.get(frequency);
            for (int i = 0; i < frequencyAntennas.size() - 1; i++) {
                for (int j = i + 1; j < frequencyAntennas.size(); j++) {
                    CoordinatePoint a = frequencyAntennas.get(i);
                    CoordinatePoint b = frequencyAntennas.get(j);

                    // a =  b + (a - b)
                    // c = b + n*(a-b) ? n *a - (n-1)b
                    // Part A for n = 2

                    if (doMultiple) {
                        int aIndex = 0;
                        try {
                            while (true) {
                                int x = aIndex * a.getX() - b.getX() * (aIndex - 1);
                                int y = aIndex * a.getY() - b.getY() * (aIndex - 1);
                                heatMap[y][x]++;
                                aIndex++;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignore) {
                        }
                        int bIndex = 0;
                        try {
                            while (true) {
                                int x = bIndex * b.getX() - a.getX() * (bIndex - 1);
                                int y = bIndex * b.getY() - a.getY() * (bIndex - 1);
                                heatMap[y][x]++;
                                bIndex++;
                            }
                        } catch (ArrayIndexOutOfBoundsException ignore) {
                        }


                    } else {


                        int antinode0X = 2 * a.getX() - b.getX();
                        int antinode0Y = 2 * a.getY() - b.getY();

                        int antinode1X = 2 * b.getX() - a.getX();
                        int antinode1Y = 2 * b.getY() - a.getY();

                        try {
                            heatMap[antinode0Y][antinode0X]++;
                        } catch (ArrayIndexOutOfBoundsException ignore) {
                        }
                        try {
                            heatMap[antinode1Y][antinode1X]++;
                        } catch (ArrayIndexOutOfBoundsException ignore) {
                        }
                    }
                }
            }
        }
        int locationCounter = 0;
        for (int y = 0; y < heatMap.length; y++) {
            for (int x = 0; x < heatMap[y].length; x++) {
                int v = heatMap[y][x];
                System.out.print(v == 0 ? "." : v);
                if (v > 0) locationCounter++;
            }
            System.out.println();
        }
        return locationCounter;

    }


}
