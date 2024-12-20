package year2024;

import utils.InputReader;
import utils.StopWatch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Day11 {

    private static Map<String, BigInteger> cache = new HashMap<>();

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String[] input = InputReader.getLinesOfFileByParameters(2024, 11, null);
        ArrayList<Stone> stones = new ArrayList<>(Arrays.stream(input[0].split(" ")).mapToInt(Integer::parseInt).mapToObj(Stone::new).toList());
        System.out.println(stones);

        int index = 0;
        int blink = 0;
        while (blink < 25) {
            while (index < stones.size()) {
                Stone newStone = stones.get(index++).applyRules();
                if (newStone != null) stones.add(index++, newStone);
            }
            blink++;
            index = 0;
//            System.out.println(blink + " " + stones.size());
        }
        System.out.println("Day 11 Part A: " + stones.size()); // 175006


        stones = new ArrayList<>(Arrays.stream(input[0].split(" ")).mapToInt(Integer::parseInt).mapToObj(Stone::new).toList());

        BigInteger count = new BigInteger("0");
        for (Stone s : stones) {
            BigInteger c = iterateStone(s, 0);
//            System.out.println(c);
            count = count.add(c);
        }


        System.out.println("Day 11 Part B: " + count); // 207961583799296
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    private static BigInteger iterateStone(Stone stone, int blink) {
        if (stone == null) return new BigInteger("0");
        int remainingBlinks = 75 - blink;
        if (remainingBlinks == 0) return new BigInteger("1");

        String cacheRemainingKey = stone + "x" + remainingBlinks;
        BigInteger c = cache.get(cacheRemainingKey);
        if (c != null) {
            return c;
        }
        Stone newStone = stone.applyRules();
        BigInteger n = iterateStone(stone, blink + 1).add(iterateStone(newStone, blink + 1));
        cache.put(cacheRemainingKey, n);
        return n;
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
