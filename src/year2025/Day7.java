package year2025;

import utils.InputReader;

public class Day7 {

    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2025, 7, 1);
//        String[] input = InputReader.getLinesOfFileByParameters(2025, 7, null);
        State[][] map = new State[input.length][input[0].length()];
        for (int y = 0; y < input.length; y++) {
            String[] split = input[y].split("");
            for (int x = 0; x < split.length; x++) {
                switch (split[x]) {
                    case ".":
                        map[y][x] = State.EMPTY;
                        break;
                    case "S":
                        map[y][x] = State.START;
                        break;
                    case "|":
                        map[y][x] = State.BEAM;
                        break;
                    case "^":
                        map[y][x] = State.SPLITTER;
                        break;
                    default:
                        System.out.println("unknown state: " + split[x]);
                }
            }
        }
        print(map);
        int hitSplitters = 0;
        for (int y = 0; y < map.length - 1; y++) {
            for (int x = 0; x < map[y].length; x++) {
                State s = map[y][x];
                if (s == State.EMPTY) continue;
                if (s == State.SPLITTER) continue;
                if (s == State.BEAM || s == State.START) {
                    if (map[y + 1][x] == State.SPLITTER) {
                        hitSplitters++;
                        map[y + 1][x - 1] = State.BEAM;
                        map[y + 1][x + 1] = State.BEAM;
                    } else {
                        map[y + 1][x] = State.BEAM;
                    }
                    continue;
                }
                System.out.println("Unknown State: " + s);

            }
        }
        print(map);

        System.out.println("Day 7 - Part A - hit splitters: " + hitSplitters); // 1678

    }


    private static void print(State[][] map) {
        for (State[] line : map) {
            for (State s : line) {
                System.out.print(stateToString(s));
            }
            System.out.println();
        }
    }

    private static String stateToString(State s) {
        if (s == State.EMPTY) return ".";
        if (s == State.BEAM) return "|";
        if (s == State.SPLITTER) return "^";
        if (s == State.START) return "S";
        return "?";

    }


    private enum State {EMPTY, START, SPLITTER, BEAM}
}
