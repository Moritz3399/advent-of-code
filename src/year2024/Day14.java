package year2024;

import utils.InputReader;

import java.util.Arrays;
import java.util.List;

public class Day14 {
    public static void main(String[] args) {
        EasterBunnyHeadQuarter example1 = new EasterBunnyHeadQuarter(7, 11, InputReader.getLinesOfFileByParameters(2024, 14, 1));
        example1.partA(); // 12
        EasterBunnyHeadQuarter quest = new EasterBunnyHeadQuarter(103, 101, InputReader.getLinesOfFileByParameters(2024, 14, null));
        quest.partA(); // 222062148
        EasterBunnyHeadQuarter questB = new EasterBunnyHeadQuarter(103, 101, InputReader.getLinesOfFileByParameters(2024, 14, null));
        questB.partB(); // 7520

    }

    private static class EasterBunnyHeadQuarter {

        private final int lengthY;
        private final int lengthX;
        private final List<Robot> robots;

        public EasterBunnyHeadQuarter(int lengthY, int lengthX, String[] input) {
            this.lengthY = lengthY;
            this.lengthX = lengthX;
            this.robots = Arrays.stream(input).map(x -> new Robot(x, lengthY, lengthX)).toList();
            System.out.println(String.join("\n", robots.stream().map(Robot::toString).toList()));
        }

        private void partA() {
            this.continueTime(100);
            this.printHeatMap();
            int[] quadrants = this.countRobotsInQuadrants();
            System.out.println();
            System.out.println("Day 14: Part A: Quadrants: " + String.join(",", Arrays.stream(quadrants).boxed().map(i -> "" + i).toList()) + " => " + (quadrants[0] * quadrants[1] * quadrants[2] * quadrants[3]));
        }

        private void partB() {
//            int max = 20;
//            this.continueTime(7500);
//            for (int i = 0; i < max; i++) {
//                System.out.println("Time: " + (i) + "s");
//                this.continueTime(1);
//                this.printHeatMapSimplified();
//                System.out.println();
//            }
            // The above part war printing it to the log and looking it through
            this.continueTime(7520);
            this.printHeatMapSimplified();
        }


        private void continueTime(int seconds) {
            for (Robot r : robots) {
                r.move(seconds);
            }
        }

        private int[][] heatMap() {
            int[][] m = new int[lengthY][lengthX];
            for (Robot r : robots) {
                m[r.y][r.x]++;
            }
            return m;
        }

        private void printHeatMap() {
            int[][] m = heatMap();
            for (int y = 0; y < lengthY; y++) {
                for (int x = 0; x < lengthX; x++) {
                    int v = m[y][x];
                    System.out.print(v == 0 ? "." : v);
                }
                System.out.println();
            }
        }

        private void printHeatMapSimplified() {
            int[][] m = heatMap();
            for (int y = 0; y < lengthY; y++) {
                for (int x = 0; x < lengthX; x++) {
                    int v = m[y][x];
                    System.out.print(v == 0 ? " " : "#");
                }
                System.out.println();
            }
        }

        private int[] countRobotsInQuadrants() {
            int[] c = new int[4];
            int[][] m = heatMap();
            int quadrantYBorder = (lengthY / 2);
            int quadrantXBorder = (lengthX / 2);
            System.out.println("quadrantYBorder: " + quadrantYBorder);
            System.out.println("quadrantXBorder: " + quadrantXBorder);
            for (int y = 0; y < quadrantYBorder; y++) {
                for (int x = 0; x < quadrantXBorder; x++) {
                    c[0] += m[y][x];
                }
            }
            for (int y = quadrantYBorder + 1; y < lengthY; y++) {
                for (int x = 0; x < quadrantXBorder; x++) {
                    c[1] += m[y][x];
                }
            }
            for (int y = 0; y < quadrantYBorder; y++) {
                for (int x = quadrantXBorder + 1; x < lengthX; x++) {
                    c[2] += m[y][x];
                }
            }
            for (int y = quadrantYBorder + 1; y < lengthY; y++) {
                for (int x = quadrantXBorder + 1; x < lengthX; x++) {
                    c[3] += m[y][x];
                }
            }
            return c;
        }


    }

    private static class Robot {
        private final int lengthY;
        private final int lengthX;
        private int x;
        private int y;
        private final int velocityX;
        private final int velocityY;

        Robot(String input, int lengthY, int lengthX) {
            this.x = Integer.parseInt(input.substring(2, input.indexOf(",")));
            this.y = Integer.parseInt(input.substring(input.indexOf(",") + 1, input.indexOf(" ")));
            this.velocityX = Integer.parseInt(input.substring(input.indexOf("v") + 2, input.lastIndexOf(",")));
            this.velocityY = Integer.parseInt(input.substring(input.lastIndexOf(",") + 1));
            this.lengthY = lengthY;
            this.lengthX = lengthX;
        }

        @Override
        public String toString() {
            return "p=" + x + "," + y + " v=" + velocityX + "," + velocityY;
        }

        private void move(int seconds) {
            int newX = (x + velocityX * seconds) % lengthX;
            if (newX < 0) newX += lengthX;
            int newY = (y + velocityY * seconds) % lengthY;
            if (newY < 0) newY += lengthY;
            this.x = newX;
            this.y = newY;
        }

    }

}
