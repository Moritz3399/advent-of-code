package year2024;

import utils.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13 {
    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2024, 13, null);
        List<ClawMachine> machines = parseInput(input);
        int tokensToWin = 0;
        for (ClawMachine m : machines) {
            int tokens = m.minTokensToWin();
            if(tokens == -1){
                System.out.println("Not possible to win!");
            } else {
                tokensToWin += tokens;
                System.out.println(tokens + " tokens to win");
            }
            System.out.println();
        }
        System.out.println("Day 13: Part A: Tokens required to win all possible prices: " + tokensToWin); // 37686
    }

    private static List<ClawMachine> parseInput(String[] input) {

        List<ClawMachine> list = new ArrayList<>();
        int ax = 0;
        int ay = 0;
        int bx = 0;
        int by = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < input.length; i++) {
            switch (i % 4) {
                case 0:
                    String axs = input[i].substring(11, input[i].indexOf(","));
                    String ays = input[i].substring(input[i].indexOf(",") + 3);
//                    System.out.println("A x: '" + axs + "' y: '" + ays + "'");
                    ax = Integer.parseInt(axs);
                    ay = Integer.parseInt(ays);
                    break;
                case 1:
                    String bxs = input[i].substring(11, input[i].indexOf(","));
                    String bys = input[i].substring(input[i].indexOf(",") + 3);
//                    System.out.println("B x: '" + bxs + "' y: '" + bys + "'");
                    bx = Integer.parseInt(bxs);
                    by = Integer.parseInt(bys);
                    break;
                case 2:
                    String xs = input[i].substring(9, input[i].indexOf(","));
                    String ys = input[i].substring(input[i].indexOf(",") + 4);
//                    System.out.println("P x: '" + xs + "' y: '" + ys + "'");
                    x = Integer.parseInt(xs);
                    y = Integer.parseInt(ys);
                    list.add(new ClawMachine(ax, ay, bx, by, x, y));
                    ax = 0;
                    ay = 0;
                    bx = 0;
                    by = 0;
                    x = 0;
                    y = 0;
                    break;
                case 3:
            }
        }
        return list;
    }


    private static class ClawMachine {

        private final int ax;
        private final int ay;
        private final int bx;
        private final int by;
        private final int x;
        private final int y;

        public ClawMachine(int ax, int ay, int bx, int by, int x, int y) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.x = x;
            this.y = y;
        }

        public int minTokensToWin() {
            Map<Integer, Integer> tokens = new HashMap<>();

            for (int a = 100; a >= 0; a--) {
                int dx = x - a * ax;
                if (dx % bx != 0) continue;
                int dy = y - a * ay;
                if (dy % by != 0) continue;
                if (dx / bx != dy / by) continue;
                int b = dx / bx;
                if (b < 0) continue;
                if (b > 100) continue;
                tokens.put(a, b);


            }
            int minToken = Integer.MAX_VALUE;
            for (Integer a : tokens.keySet()) {
                int sum = (3 * a) + tokens.get(a);
                if (sum < minToken) minToken = sum;
                System.out.println(a + " A-tokens and " + tokens.get(a) + " B-tokens => " + sum);
            }

            return minToken == Integer.MAX_VALUE ? -1 : minToken;
        }
    }

}
