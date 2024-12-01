package year2024;

import utils.InputReader;

import java.util.ArrayList;
import java.util.HashMap;

public class Day1 {

    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2024, 1, null);

        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();

        for (String s : input) {
            String[] p = s.split("   ");
            leftList.add(Integer.parseInt(p[0]));
            rightList.add(Integer.parseInt(p[1]));
        }
        leftList.sort(Integer::compare);
        rightList.sort(Integer::compare);

//        System.out.println(leftList.size() + " " + rightList.size());

        ArrayList<Integer> distances = new ArrayList<>();
        for (int i = 0; i < leftList.size(); i++) {
            distances.add(Math.abs(leftList.get(i) - rightList.get(i)));
        }
        System.out.println("Day 1 Part A: Total Distance: " + distances.stream().mapToInt(a -> a).sum());

        HashMap<Integer, Integer> rightCounter = new HashMap<>();
        for (Integer r : rightList) {
            Integer c = rightCounter.get(r);
            if (c == null) c = 0;
            rightCounter.put(r, c + 1);
        }
        int similarityScore = 0;
        for (Integer l : leftList){
            Integer c = rightCounter.get(l);
            if(c == null) continue;
            similarityScore += c * l;
        }
        System.out.println("Day 1 Part B: similarity score: " + similarityScore);

    }

}
