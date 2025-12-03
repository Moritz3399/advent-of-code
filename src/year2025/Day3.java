package year2025;

import utils.InputReader;

import java.util.Arrays;
import java.util.List;

public class Day3 {

    public static void main(String[] args) {
//        String[] input = InputReader.getLinesOfFileByParameters(2025, 3, 1);
        String[] input = InputReader.getLinesOfFileByParameters(2025, 3, null);

        List<BatteryBank> banks = Arrays.stream(input).map(BatteryBank::new).toList();
        int totalJoltage = banks.stream().mapToInt(BatteryBank::getJoltage).sum();
        System.out.println("Day 3 Part A - total joltage: " + totalJoltage); // 16946
        long totalJoltageOverroide = banks.stream().mapToLong(BatteryBank::getJoltageOverride).sum();
        System.out.println("Day 3 Part B - total Joltage Override: " + totalJoltageOverroide); // 168627047606506
    }

    private static class BatteryBank {
        private final int[] cells;
        private int joltage;
        private long joltageOverride;

        BatteryBank(String s) {
            this.cells = Arrays.stream(s.split("")).mapToInt(Integer::parseInt).toArray();
            this.calculateJoltage();
//            System.out.println(this.joltage);
            this.calculateJoltageOverride();
//            System.out.println(joltageOverride);
        }

        private void calculateJoltage() {
            int a = 0;
            int index = 0;
            int b = 0;
            for (int i = 0; i < cells.length - 1; i++) {
                if (cells[i] > a) {
                    a = cells[i];
                    index = i;
                }
            }
            for (int j = index + 1; j < cells.length; j++) {
                if (cells[j] > b) {
                    b = cells[j];
                }
            }
            joltage = a * 10 + b;

        }

        private void calculateJoltageOverride() {
            long joltage = 0L;
            int max;
            int maxIndex = -1;

            for (int digit = 1; digit <= 12; digit++) {
                int start = maxIndex +1;
                max = -1;
                maxIndex = -1;
                for (int i = start; i < cells.length - (12-digit); i++){
                    if(cells[i] > max){
                        max = cells[i];
                        maxIndex = i;
                    }
                }
                joltage = joltage * 10 + max;
            }
            this.joltageOverride = joltage;
        }

        public int getJoltage() {
            return joltage;
        }

        public long getJoltageOverride() {
            return joltageOverride;
        }
    }

}
