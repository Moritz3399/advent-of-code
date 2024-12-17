package year2024;

import utils.ArrayUtils;
import utils.CoordinatePoint;
import utils.InputReader;

import java.util.*;

public class Day10 {
    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2024, 10, null);
        int[][] topographicMap = parseTopographicMap(input);
        List<CoordinatePoint> trailHeads = searchTrailHeads(topographicMap);
        int trailScoreSum = 0;
        int trailScoreDistinctWaysSum = 0;
        for (CoordinatePoint head : trailHeads) {

            List<CoordinatePoint> list = searchTrails(topographicMap, head);
            Set<CoordinatePoint> set = new HashSet<>(list);
            trailScoreSum += set.size();
            trailScoreDistinctWaysSum+= list.size();
        }
        System.out.println("Day 10: Part A: trailScoreSum: " + trailScoreSum); // 489
        System.out.println("Day 10: Part B: trailScoreDistinctWaysSum: " + trailScoreDistinctWaysSum); // 1086

    }


    private static int[][] parseTopographicMap(String[] raw) {
        int[][] m = new int[raw.length][raw[0].length()];
        for (int i = 0; i < raw.length; i++) {
            m[i] = Arrays.stream(raw[i].split("")).mapToInt(e -> Integer.parseInt(e.replace(".", "-1"))).toArray();
        }
        return m;
    }

    private static List<CoordinatePoint> searchTrails(int[][] topographicMap, CoordinatePoint point) {
        List<CoordinatePoint> trails = new ArrayList<>();
        final int x = point.getX();
        final int y = point.getY();

        int v = topographicMap[point.getY()][point.getX()];

        // y + 1
        if (ArrayUtils.is2dIndexInBounds(topographicMap, y + 1, x)) {
            if (topographicMap[y + 1][x] - 1 == v) {
                CoordinatePoint c = new CoordinatePoint(x, y + 1);
                if (v == 8) {
                    trails.add(c);
                } else {
                    trails.addAll(searchTrails(topographicMap, c));
                }
            }
        }
        // y - 1
        if (ArrayUtils.is2dIndexInBounds(topographicMap, y - 1, x)) {
            if (topographicMap[y - 1][x] - 1 == v) {
                CoordinatePoint c = new CoordinatePoint(x, y - 1);
                if (v == 8) {
                    trails.add(c);
                } else {
                    trails.addAll(searchTrails(topographicMap, c));
                }
            }
        }
        // x - 1
        if (ArrayUtils.is2dIndexInBounds(topographicMap, y, x - 1)) {
            if (topographicMap[y][x - 1] - 1 == v) {
                CoordinatePoint c = new CoordinatePoint(x - 1, y);
                if (v == 8) {
                    trails.add(c);
                } else {
                    trails.addAll(searchTrails(topographicMap, c));
                }
            }
        }
        // x + 1
        if (ArrayUtils.is2dIndexInBounds(topographicMap, y, x + 1)) {
            if (topographicMap[y][x + 1] - 1 == v) {
                CoordinatePoint c = new CoordinatePoint(x + 1, y);
                if (v == 8) {
                    trails.add(c);
                } else {
                    trails.addAll(searchTrails(topographicMap, c));
                }
            }
        }
        return trails;
    }

    private static List<CoordinatePoint> searchTrailHeads(int[][] topographicMap) {
        List<CoordinatePoint> l = new ArrayList<>();
        for (int y = 0; y < topographicMap.length; y++) {
            for (int x = 0; x < topographicMap[y].length; x++) {
                if (topographicMap[y][x] == 0) {
                    l.add(new CoordinatePoint(x, y));
                }
            }
        }
        return l;
    }


}
