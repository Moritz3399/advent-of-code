import java.util.Arrays;
import java.util.stream.Collectors;

public class Day02 {

    public static void main(String[] args) {
        String[] lines = InputReader.getLinesOfFile("inputs/day02.txt");
//        String[] lines = InputReader.getLinesOfFile("inputs/day02.example.1.txt");
        Game[] games = parse(lines);
        for (Game g : games) System.out.println(g);

        int validGameIdsSum = Arrays.stream(games).mapToInt(g -> g.isValid(12, 13, 14) ? g.id : 0).sum();
        System.out.println("Day 02 - Part A: " + validGameIdsSum);
        int sumOfPower = Arrays.stream(games).mapToInt(Game::powerOfFewest).sum();
        System.out.println("Day 02 - Part B: " + sumOfPower);

    }


    private static Game[] parse(String[] lines) {
        Game[] games = new Game[lines.length];
        for (int i = 0; i < games.length; i++) {
            games[i] = new Game(lines[i]);
        }
        return games;
    }

    private static class Game {
        int id;
        Play[] plays;

        Game(String line) {
            String[] a = line.split(": ");
            this.id = Integer.parseInt(a[0].replace("Game ", ""));
            this.plays = Arrays.stream(a[1].split("; ")).map(Play::new).toArray(Play[]::new);
//            System.out.println(a[0]);
//            System.out.println(a[1]);
        }

        @Override
        public String toString() {
            return String.format("Game %s: %s", id, Arrays.stream(plays).map(Play::toString).collect(Collectors.joining(";")));
        }

        boolean isValid(int gameRed, int gameGreen, int gameBlue) {
            for (Play p : plays) {
                if (!p.isValid(gameRed, gameGreen, gameBlue)) {
                    return false;
                }
            }
            return true;
        }

        int powerOfFewest() {
            int minRed = Arrays.stream(plays).mapToInt(Play::getRed).max().orElse(0);
            int minGreen = Arrays.stream(plays).mapToInt(Play::getGreen).max().orElse(0);
            int minBlue = Arrays.stream(plays).mapToInt(Play::getBlue).max().orElse(0);
//            System.out.printf("%s, %s, %s%n", minRed, minBlue, minGreen);
            return minRed * minGreen * minBlue;

        }

    }

    private static class Play {

        int red;
        int green;
        int blue;

        Play(String line) {
//            System.out.println(line);
            for (String s : line.split(", ")) {
                String[] b = s.split(" ");
                switch (b[1]) {
                    case "red":
                        this.red = Integer.parseInt(b[0]);
                        break;
                    case "green":
                        this.green = Integer.parseInt(b[0]);
                        break;
                    case "blue":
                        this.blue = Integer.parseInt(b[0]);
                        break;
                }
            }

//            System.out.println(red);
//            System.out.println(green);
//            System.out.println(blue);
        }

        @Override
        public String toString() {
            return String.format("%s red, %s green, %s blue", red, green, blue);
        }

        boolean isValid(int gameRed, int gameGreen, int gameBlue) {
            return gameRed >= this.red && gameGreen >= this.green && gameBlue >= this.blue;
        }

        public int getRed() {
            return red;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }
    }

}
