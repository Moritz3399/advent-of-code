package year2024;

import utils.ArrayUtils;
import utils.InputReader;

public class Day12 {

    public static void main(String[] args) {

        String[] input = InputReader.getLinesOfFileByParameters(2024, 12, null);
        MapCell[][] mapCells = parseInput(input);
        countNeighbours(mapCells);
        int maxRegionId = assignRegionIds(mapCells);
        System.out.println("maxRegionId: " + maxRegionId + " => " + (maxRegionId + 1) + " regions");

        int totalFenceCosts = 0;
        for (int r = 0; r <= maxRegionId; r++) {
            int regionCellCount = 0;
            int regionFencedCellSides = 0;
            for (int y = 0; y < mapCells.length; y++) {
                for (int x = 0; x < mapCells[y].length; x++) {
                    MapCell c = mapCells[y][x];
                    if (c.getRegionId() == r) {
                        regionCellCount++;
                        regionFencedCellSides += c.getFencedSides();
                    }

                }
            }
            int regionPrice = regionCellCount * regionFencedCellSides;
            System.out.println("Region " + r + " has " + regionCellCount + " cells with " + regionFencedCellSides + " fence sections. Region price: " + regionPrice);
            totalFenceCosts+= regionPrice;
        }

        System.out.println("Day 12: Part A: Total fence costs: "+ totalFenceCosts); // 1375574


    }

    private static MapCell[][] parseInput(String[] input) {
        MapCell[][] m = new MapCell[input.length][input[0].length()];
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                m[y][x] = new MapCell(input[y].charAt(x));
            }
        }
        return m;
    }

    private static void countNeighbours(MapCell[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int count = 0;
                if (ArrayUtils.is2dIndexInBounds(map, y - 1, x) && map[y][x].isSameLetter(map[y - 1][x])) {
                    count++;
                }
                if (ArrayUtils.is2dIndexInBounds(map, y + 1, x) && map[y][x].isSameLetter(map[y + 1][x])) {
                    count++;
                }
                if (ArrayUtils.is2dIndexInBounds(map, y, x - 1) && map[y][x].isSameLetter(map[y][x - 1])) {
                    count++;
                }
                if (ArrayUtils.is2dIndexInBounds(map, y, x + 1) && map[y][x].isSameLetter(map[y][x + 1])) {
                    count++;
                }
                map[y][x].setNeighboursWithEqualRegionLetter(count);
            }
        }
    }

    private static int assignRegionIds(MapCell[][] map) {
        int nextId = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x].getRegionId() == null) {
                    walkRegion(map, x, y, nextId++, map[y][x].getRegionLetter());
                }
            }
        }
        return nextId - 1;
    }

    private static void walkRegion(MapCell[][] map, int x, int y, int id, char regionLetter) {
        if (!ArrayUtils.is2dIndexInBounds(map, y, x)) return;
        if (map[y][x].getRegionId() != null) return;
        if (map[y][x].getRegionLetter() == regionLetter) {
            map[y][x].setRegionId(id);
            walkRegion(map, x + 1, y, id, regionLetter);
            walkRegion(map, x - 1, y, id, regionLetter);
            walkRegion(map, x, y + 1, id, regionLetter);
            walkRegion(map, x, y - 1, id, regionLetter);
        }
    }


    private static class MapCell {

        private final char regionLetter;
        private Integer regionId = null;
        private Integer neighboursWithEqualRegionLetter = null;


        public MapCell(char regionLetter) {
            this.regionLetter = regionLetter;
        }

        private Integer getFencedSides() {
            if (neighboursWithEqualRegionLetter == null) return null;
            return 4 - neighboursWithEqualRegionLetter;
        }

        public char getRegionLetter() {
            return regionLetter;
        }

        public Integer getNeighboursWithEqualRegionLetter() {
            return neighboursWithEqualRegionLetter;
        }

        public void setNeighboursWithEqualRegionLetter(Integer neighboursWithEqualRegionLetter) {
            this.neighboursWithEqualRegionLetter = neighboursWithEqualRegionLetter;
        }

        private boolean isSameLetter(MapCell otherCell) {
            return this.regionLetter == otherCell.getRegionLetter();
        }

        public Integer getRegionId() {
            return regionId;
        }

        public void setRegionId(Integer regionId) {
            this.regionId = regionId;
        }
    }

}
