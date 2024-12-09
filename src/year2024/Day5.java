package year2024;

import utils.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2024, 5, null);
        boolean parsedRules = false;
        Map<Integer, List<Integer>> rules = new HashMap<>();
        List<Map<Integer, Integer>> updates = new ArrayList<>();

        for (String line : input) {
            if (line.isEmpty()) {
                parsedRules = true;
                continue;
            }
            if (parsedRules) {
                List<Integer> l = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).boxed().toList();
                Map<Integer, Integer> u = new HashMap<>();
                for (int i = 0; i < l.size(); i++) {
                    u.put(l.get(i), i);
                }
                updates.add(u);
                continue;
            }

            String[] s = line.split("\\|");
            Integer before = Integer.parseInt(s[0]);
            Integer after = Integer.parseInt(s[1]);
            List<Integer> afters = rules.computeIfAbsent(before, k -> new ArrayList<>());
            afters.add(after);
        }


        int sumOfValidUpdates = 0;
        int sumOfInvalidUpdates = 0;
        for (Map<Integer, Integer> u : updates) {
            boolean isValid = true;
            rules:
            for (Integer rk : rules.keySet()) {
                // check if update does not have the first rule value (key of rule)
                if (u.get(rk) == null) continue;
                Integer index = u.get(rk);
                for (Integer after : rules.get(rk)) {
                    Integer updateIndex = u.get(after);
                    if (updateIndex == null) continue;
                    if (updateIndex < index) {
                        isValid = false;
                        break rules;
                    }
                }
            }
            System.out.println("Update is " + (isValid ? "" : "in") + "valid");
            if (isValid) {
                Integer middleIndex = u.size() / 2;
                for (Integer key : u.keySet()) {
                    if (u.get(key).equals(middleIndex)) {
                        System.out.println("middle number is " + key);
                        sumOfValidUpdates += key;
                        break;
                    }
                }
            } else {
                // fix it
                Integer[] updateArray = new Integer[u.size()];
                for (Integer k : u.keySet()) {
                    updateArray[u.get(k)] = k;
                }
                List<Integer> sortedUpdateList = Arrays.asList(updateArray);
                sortedUpdateList.sort((a, b) -> {
                    if (rules.get(a) != null) {
                        for (int c : rules.get(a)) {
                            if (c == b) return -1;
                        }
                    }
                    if (rules.get(b) != null) {
                        for (int c : rules.get(b)) {
                            if (c == a) return 1;
                        }
                    }
                    return 0;
                });
                System.out.println("Sorted to: " + sortedUpdateList);
                sumOfInvalidUpdates += sortedUpdateList.get(sortedUpdateList.size() / 2);

            }

        }
        System.out.println("Day 5 Part A: Sum of all valid middle update page numbers is: " + sumOfValidUpdates); // 4135
        System.out.println("Day 5 Part B: Sum of all invalid and fixed middle update page numbers is: " + sumOfInvalidUpdates); //


    }
}
