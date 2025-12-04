package year2025;

import utils.InputReader;

public class Day4 {

    private static int[][] storage;

    public static void main(String[] args) {
//        String[] input = InputReader.getLinesOfFileByParameters(2025, 4, 1);
        String[] input = InputReader.getLinesOfFileByParameters(2025, 4, null);
        storage = new int[input.length][input[0].length()];
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                storage[y][x] = input[y].charAt(x) == '@' ? 1 : 0;
            }
        }
        solvePartA();
        solvePartB();
    }

    private static void solvePartA() {
        int accessibleRoles = 0;
        for (int y = 0; y < storage.length; y++) {
            for (int x = 0; x < storage[y].length; x++) {
                if (storage[y][x] == 0) continue;
                int neighbours =
                        getStorageLocationValue(x - 1, y - 1) +
                                getStorageLocationValue(x - 1, y) +
                                getStorageLocationValue(x - 1, y + 1) +
                                getStorageLocationValue(x, y - 1) +
                                getStorageLocationValue(x, y + 1) +
                                getStorageLocationValue(x + 1, y - 1) +
                                getStorageLocationValue(x + 1, y) +
                                getStorageLocationValue(x + 1, y + 1);
                if (neighbours < 4) {
                    accessibleRoles++;
                }
            }
        }
        System.out.println("Day 4 Part A - accessible roles: " + accessibleRoles); // 1376
    }

    private static void solvePartB() {
        int removedRoles = 0;
        int accessibleRoles;
        do {
            accessibleRoles = 0;
            for (int y = 0; y < storage.length; y++) {
                for (int x = 0; x < storage[y].length; x++) {
                    if (storage[y][x] == 0) continue;
                    int neighbours =
                            getStorageLocationValue(x - 1, y - 1) +
                                    getStorageLocationValue(x - 1, y) +
                                    getStorageLocationValue(x - 1, y + 1) +
                                    getStorageLocationValue(x, y - 1) +
                                    getStorageLocationValue(x, y + 1) +
                                    getStorageLocationValue(x + 1, y - 1) +
                                    getStorageLocationValue(x + 1, y) +
                                    getStorageLocationValue(x + 1, y + 1);
                    if (neighbours < 4) {
                        accessibleRoles++;
                        storage[y][x] = 2;
                    }
                }
            }
            for (int y = 0; y < storage.length; y++) {
                for (int x = 0; x < storage[y].length; x++) {
                    if (storage[y][x] == 2) {
                        storage[y][x] = 0;
                    }
                }
            }
            removedRoles += accessibleRoles;
            System.out.println("removed roles: " + accessibleRoles);
        } while (accessibleRoles > 0);
        System.out.println("Day 4 Part B - removed roles: " + removedRoles); // 8587
    }

    private static int getStorageLocationValue(int x, int y) {
        if (x < 0 || y < 0) return 0;
        if (y >= storage.length) return 0;
        if (x >= storage[y].length) return 0;
        return storage[y][x] > 0 ? 1 : 0;
    }

}
