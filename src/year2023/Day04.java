package year2023;

import java.util.Arrays;

import utils.*;

public class Day04 {
    public static void main(String[] args) {
        String[] lines = InputReader.getLinesOfFile("inputs/day04.txt");
//        String[] lines = utils.InputReader.getLinesOfFile("inputs/day04.example.1.txt");

        Card[] cards = Arrays.stream(lines).map(Card::new).toArray(Card[]::new);
//        Arrays.stream(cards).mapToInt(c -> c.getMyWinningNumbers().length).filter(i -> i > 0).map(i -> (int) Math.pow(2, i - 1)).forEach(System.out::println);
        int sum = Arrays.stream(cards).mapToInt(c -> c.getMyWinningNumbers().length).filter(i -> i > 0).map(i -> (int) Math.pow(2, i - 1)).sum();
        System.out.println("Day 04 - Part A - " + sum); // 18619

        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[i].getMyWinningNumbers().length; j++) {
                cards[i + j + 1].increaseInstanceBy(cards[i].instances);
            }
        }
//        Arrays.stream(cards).forEach(c -> System.out.println(c.id + " " + c.instances));
        int totalCards = Arrays.stream(cards).mapToInt(Card::getInstances).sum();
        System.out.println("Day 04 - Part B - " + totalCards); // 8063216

    }


    private static class Card {
        int id;
        int[] winningNumbers;
        int[] myNumbers;

        int[] myWinningNumbers;

        int instances = 1;

        Card(String line) {
            this.id = Integer.parseInt(line.replace("Card", "").trim().split(":")[0]);
            String n = line.split(":")[1];
            this.winningNumbers = Arrays.stream(n.split("\\|")[0].trim().replace("  ", " ").split(" ")).mapToInt(Integer::parseInt).toArray();
            this.myNumbers = Arrays.stream(n.split("\\|")[1].trim().replace("  ", " ").split(" ")).mapToInt(Integer::parseInt).toArray();
            this.myWinningNumbers = Arrays.stream(myNumbers).filter(e -> Arrays.stream(winningNumbers).filter(el -> el == e).toArray().length > 0).toArray();
        }

        int[] getMyWinningNumbers() {
            return myWinningNumbers;
        }

        void increaseInstanceBy(int by) {
            this.instances += by;
        }

        int getInstances() {
            return this.instances;
        }

    }
}
