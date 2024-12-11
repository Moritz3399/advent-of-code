package year2024;

import utils.InputReader;
import utils.StopWatch;

public class Day6 {

    private enum GuardDirection {
        up, down, left, right
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String[] input = InputReader.getLinesOfFileByParameters(2024, 6, null);
        Lab labOriginal = new Lab(input, null, null);
        System.out.println("Day 6 Part A: " + labOriginal); // Day 6 Part A: Visited places: 4977 loop:no left lab: yes

        for (Cell[] xArray : labOriginal.map) {
            for (Cell x : xArray) {
                if (x.wasVisited())

                    System.out.printf("%s", x.toString());
            }
            System.out.println();
        }
        int loopCounter = 0;
        for (int y = 0; y < labOriginal.map.length; y++) {
            for (int x = 0; x < labOriginal.map[y].length; x++) {
                if (labOriginal.map[y][x].wasVisited()) {
                    Lab l = new Lab(input, y, x);
                    if (l.isLoop) {
                        loopCounter++;
                        System.out.println("Created loop by adding obstacle @ " + x + "/" + y);
                    }
                }
            }
        }
        System.out.println("Day 6 Part B: Loops created: " + loopCounter);
        stopWatch.stop();
        System.out.println(stopWatch);
    }

    private static int newGuardPositionY(int currentY, GuardDirection direction) {
        if (direction == GuardDirection.up) return currentY - 1;
        if (direction == GuardDirection.down) return currentY + 1;
        return currentY;
    }

    private static int newGuardPositionX(int currentX, GuardDirection direction) {
        if (direction == GuardDirection.right) return currentX + 1;
        if (direction == GuardDirection.left) return currentX - 1;
        return currentX;
    }

    private static GuardDirection turn90Right(GuardDirection current) {
        if (current == GuardDirection.down) return GuardDirection.left;
        if (current == GuardDirection.left) return GuardDirection.up;
        if (current == GuardDirection.up) return GuardDirection.right;
        if (current == GuardDirection.right) return GuardDirection.down;
        return current;
    }

    private static class Cell {
        boolean isObstacle = false;
        boolean movedThroughUp = false;
        boolean movedThroughDown = false;
        boolean movedThroughLeft = false;
        boolean movedThroughRight = false;

        boolean wasVisited() {
            return movedThroughRight || movedThroughLeft || movedThroughUp || movedThroughDown;
        }

        @Override
        public String toString() {
            if (isObstacle) return "#";
            if ((movedThroughDown || movedThroughUp) && (movedThroughLeft || movedThroughRight)) return "+";
            if (movedThroughDown || movedThroughUp) return "|";
            if (movedThroughLeft || movedThroughRight) return "-";
            return " ";
        }
    }

    private static class Lab {
        int guardLocationX = 0;
        int guardLocationY = 0;
        boolean isLoop = false;
        boolean leftLab = false;
        int spotCounter = 0;
        GuardDirection guardDirection = GuardDirection.up;
        Cell[][] map;

        Lab(String[] input, Integer obstacleY, Integer obstacleX) {
            map = new Cell[input.length][input[0].length()];
            for (int y = 0; y < input.length; y++) {
                for (int x = 0; x < input[y].length(); x++) {
                    if (input[y].charAt(x) == '.') {
                        map[y][x] = new Cell();
                    } else if (input[y].charAt(x) == '#') {
                        Cell c = new Cell();
                        c.isObstacle = true;
                        map[y][x] = c;
                    } else {
                        Cell c = new Cell();
                        c.movedThroughUp = true;
                        map[y][x] = c;
                        guardLocationY = y;
                        guardLocationX = x;
//                        System.out.println("Initial guard location: (y/x): " + y + "/" + x);
//                        System.out.println("Initial guard direction:" + input[y].charAt(x));
                    }

                }
            }
            if (obstacleX != null && obstacleX == guardLocationX && obstacleY != null && obstacleY == guardLocationY) {
                System.out.println("Can't place obstacle on guard!");
                return;
            }

            if (obstacleX != null && obstacleY != null) {
                map[obstacleY][obstacleX].isObstacle = true;
            }

            while (true) {
                int newY = newGuardPositionY(guardLocationY, guardDirection);
                int newX = newGuardPositionX(guardLocationX, guardDirection);
                if (newY < 0 || newX < 0 || newY >= map.length || newX >= map[newY].length) {
                    System.out.println("Left the lab @ " + newX + "/" + newY);
                    leftLab = true;
                    break;
                }
                while (map[newY][newX].isObstacle) {
                    guardDirection = turn90Right(guardDirection);
//                    System.out.println("Guard turned - " + guardDirection);
                    newY = newGuardPositionY(guardLocationY, guardDirection);
                    newX = newGuardPositionX(guardLocationX, guardDirection);
                    if (newY < 0 || newX < 0 || newY >= map.length || newX >= map[newY].length) {
                        System.out.println("Left the lab @ " + newX + "/" + newY);
                        leftLab = true;
                        break;
                    }
                }
                guardLocationY = newY;
                guardLocationX = newX;
                if (guardLocationY < 0 || guardLocationX < 0 || guardLocationY >= map.length || guardLocationX >= map[guardLocationY].length) {
                    System.out.println("Left the lab @ " + newX + "/" + newY);
                    leftLab = true;
                    break;
                }
//                System.out.println("New guard position: " + guardLocationX + "/" + guardLocationY);
                if (guardDirection == GuardDirection.up) {
                    if (map[guardLocationY][guardLocationX].movedThroughUp) {
                        System.out.println("It is a loop!");
                        isLoop = true;
                        break;
                    }
                    map[guardLocationY][guardLocationX].movedThroughUp = true;
                    continue;
                }
                if (guardDirection == GuardDirection.down) {
                    if (map[guardLocationY][guardLocationX].movedThroughDown) {
                        System.out.println("It is a loop!");
                        isLoop = true;
                        break;
                    }
                    map[guardLocationY][guardLocationX].movedThroughDown = true;
                    continue;
                }
                if (guardDirection == GuardDirection.left) {
                    if (map[guardLocationY][guardLocationX].movedThroughLeft) {
                        System.out.println("It is a loop!");
                        isLoop = true;
                        break;
                    }
                    map[guardLocationY][guardLocationX].movedThroughLeft = true;
                    continue;
                }
                if (guardDirection == GuardDirection.right) {
                    if (map[guardLocationY][guardLocationX].movedThroughRight) {
                        System.out.println("It is a loop!");
                        isLoop = true;
                        break;
                    }
                    map[guardLocationY][guardLocationX].movedThroughRight = true;
                    continue;
                }
            }


            for (Cell[] xArray : map) {
                for (Cell x : xArray) {
                    if (x.wasVisited())
                        spotCounter++;
//                    System.out.printf("%s", x.toString()); // enable this to print map! 1/2
                }
//                System.out.println(); // enable this to print map! 2/2
            }
        }

        @Override
        public String toString() {
            return "Visited places: " + spotCounter + " loop:" + (isLoop ? "yes" : "no") + " left lab: " + (leftLab ? "yes" : "no");
        }
    }


}
