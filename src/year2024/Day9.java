package year2024;

import utils.InputReader;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Day9 {

    private static final Integer example = null;

    public static void main(String[] args) {

        String[] input = InputReader.getLinesOfFileByParameters(2024, 9, example);
        int[] diskMap = Arrays.stream(input[0].split("")).mapToInt(Integer::parseInt).toArray();
        partA(diskMap);
        partB(diskMap);


    }

    private static void partB(int[] diskMap) {
        int[] disk = parseDisk(diskMap);

        int fileId = Arrays.stream(disk).max().orElse(0);
//        System.out.println(Arrays.stream(disk).mapToObj(e -> e == -1 ? ".." : String.format("%2d", e)).collect(Collectors.joining()));
        for (int i = fileId; i > 0; i--) {
            final int index = i;
            int occurrences = Arrays.stream(disk).filter(e -> e == index).toArray().length;
            int newPlace = -1;
            int freePlaces = 0;
            for (int j = 0; j < disk.length; j++) {
                if (disk[j] == i) break;
                if (disk[j] == -1) {
                    freePlaces++;
                } else {
                    freePlaces = 0;
                }
                if (freePlaces == occurrences) {
                    newPlace = j - occurrences + 1;
                    break;
                }
            }
            if (newPlace != -1) {
                for (int j = newPlace; j < disk.length; j++) {
                    if (j < newPlace + occurrences && disk[j] == -1) {
                        disk[j] = i;
                    } else if (disk[j] == i) {
                        disk[j] = -1;
                    }
                }
            }
//            System.out.println(Arrays.stream(disk).mapToObj(e -> e == -1 ? ".." : String.format("%2d", e)).collect(Collectors.joining()));
        }


        long checkSum = 0;
        for (int i = 1; i < disk.length; i++) {
            if (disk[i] < 1) continue;
            checkSum += (long) i * disk[i];
        }

        System.out.println("Day 9 Part B: Checksum: " + checkSum); // 6287317016845
    }

    private static void partA(int[] diskMap) {
        int[] disk = parseDisk(diskMap);
        int l = 0;
        int r = disk.length - 1;
        boolean searchSpace = true;

        while (l < r) {
            if (searchSpace) {
                if (disk[l] == -1) {
                    searchSpace = false;
                } else {
                    l++;

                }
            } else {
                if (disk[r] != -1) {
                    disk[l] = disk[r];
                    disk[r] = -1;
                    searchSpace = true;
                    l++;
                }
                r--;
            }
        }

        long checkSum = 0;
        for (int i = 1; i < disk.length; i++) {
            if (disk[i] == -1) break;
            checkSum += (long) i * disk[i];
        }

        System.out.println("Day 9 Part A: Checksum: " + checkSum); // 6262891638328
    }

    private static int[] parseDisk(int[] diskMap) {
        int diskLength = Arrays.stream(diskMap).sum();
        int[] disk = new int[diskLength];
        boolean isFile = true;
        int fileId = 0;
        int index = 0;

        for (int m = 0; m < diskMap.length; m++) {
            int v = diskMap[m];
            int n = isFile ? fileId++ : -1;
            for (int c = 0; c < v; c++) {
                disk[index++] = n;
            }
            if (isFile && v == 0) {
                fileId--;
            }
            isFile = !isFile;
        }

        return disk;
    }
}
