package year2024;

import utils.InputReader;

public class Day4 {
    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2024, 4, null);

        String[] diagonalTopLeftToLowerRight = new String[input.length];
        String[] diagonalTopRightToLowerLeft = new String[input.length];
        for (int i = 0; i < diagonalTopLeftToLowerRight.length; i++) {
            if (i == 0 || i == (diagonalTopLeftToLowerRight.length - 1)) {
                if (i == 0) {
                    diagonalTopLeftToLowerRight[i] = String.format("%s%" + (diagonalTopLeftToLowerRight.length - 1) + "s", input[i], "");
                    diagonalTopRightToLowerLeft[i] = String.format("%" + (diagonalTopLeftToLowerRight.length - 1) + "s%s", "", input[i]);
                } else {
                    diagonalTopLeftToLowerRight[i] = String.format("%" + i + "s%s", "", input[i]);
                    diagonalTopRightToLowerLeft[i] = String.format("%s%" + i + "s", input[i], "");
                }
            } else {
                diagonalTopLeftToLowerRight[i] = String.format("%" + i + "s%s%" + (diagonalTopLeftToLowerRight.length - i - 1) + "s", "", input[i], "");
                diagonalTopRightToLowerLeft[i] = String.format("%" + (diagonalTopLeftToLowerRight.length - i - 1) + "s%s%" + i + "s", "", input[i], "");
            }
        }

//        for (String s : diagonalTopLeftToLowerRight) {
//            System.out.println("'" + s + "' - " + s.length());
//        }
//        System.out.println();
//        for (String s : diagonalTopRightToLowerLeft) {
//            System.out.println("'" + s + "' - " + s.length());
//        }

        String[] inverted = new String[input[0].length()];
        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < input[x].length(); y++) {
                if (x == 0) {
                    inverted[y] = "";
                }
                char c = input[x].charAt(y);
                inverted[y] += c;
            }
        }
//        System.out.println("Inverted");
//        for (String s : inverted) {
//            System.out.println("'" + s + "' - " + s.length());
//        }

        String[][] arrays = new String[][]{input, inverted, diagonalTopRightToLowerLeft, diagonalTopLeftToLowerRight};

        int counter = 0;
        String searchText = "XMAS";
        for (String[] a : arrays) {
            for (int x = 0; x < a[0].length(); x++) {
                for (int y = 0; y < a.length - 3; y++) {
                    String s = "" + a[y].charAt(x) + a[y + 1].charAt(x) + a[y + 2].charAt(x) + a[y + 3].charAt(x);
                    String sr = "" + a[y + 3].charAt(x) + a[y + 2].charAt(x) + a[y + 1].charAt(x) + a[y].charAt(x);
                    if (searchText.equals(s)) {
                        counter++;
                    }
                    if (searchText.equals(sr)) {
                        counter++;
                    }
                }
            }
        }
        System.out.println("Day 4 Part A: " + counter); // 2536

        counter = 0;
        for (int y = 1; y < input.length - 1; y++) {
            for (int x = 1; x < input[y].length() - 1; x++) {
                if (input[y].charAt(x) == 'A'
                        && (
                        (input[y - 1].charAt(x - 1) == 'M' && input[y + 1].charAt(x + 1) == 'S')
                                || (input[y - 1].charAt(x - 1) == 'S' && input[y + 1].charAt(x + 1) == 'M')
                ) && (
                        (input[y + 1].charAt(x - 1) == 'M' && input[y - 1].charAt(x + 1) == 'S')
                                || (input[y + 1].charAt(x - 1) == 'S' && input[y - 1].charAt(x + 1) == 'M')
                )
                ) {
                    counter++;
                }
            }
        }
        System.out.println("Day 4 Part B: " + counter); // 1875

    }
}
