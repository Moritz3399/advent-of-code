package year2024;

import utils.InputReader;
import utils.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;


public class Day11 {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String[] input = InputReader.getLinesOfFileByParameters(2024, 11, null);
        ArrayList<Stone> stones = new ArrayList<>(Arrays.stream(input[0].split(" ")).mapToInt(Integer::parseInt).mapToObj(Stone::new).toList());
        System.out.println(stones);

        int index = 0;
        int blink = 0;
        while (blink < 75) {
            System.out.println(blink);
            while (index < stones.size()) {
                Stone newStone = stones.get(index++).applyRules();
                if (newStone != null) stones.add(index++, newStone);
            }
            blink++;
            index = 0;
            if (blink == 25) {
                System.out.println("Day 11 Part A: " + stones.size()); // 175006
            }
        }
        System.out.println("Day 11 Part A: " + stones.size()); // ??
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    private static class Stone {

        private long value;

        Stone(int value) {
            this.value = value;
        }

        public Stone applyRules() {
            if (value == 0) {
                this.value = 1;
                return null;
            }
            String s = Long.toString(value);
            if (s.length() % 2 == 0) {
                String v = s.substring(0, s.length() / 2);
                String n = s.substring(s.length() / 2);
                this.value = Integer.parseInt(v);
                return new Stone(Integer.parseInt(n));
            }
            this.value *= 2024;
            return null;
        }

        @Override
        public String toString() {
            return Long.toString(value);
        }
    }

}
