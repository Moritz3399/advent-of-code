package year2025;

import utils.InputReader;

import java.util.*;

public class Day2 {

    public static void main(String[] args) {
        String[] input = InputReader.getLinesOfFileByParameters(2025, 2, null);
        List<Range> ranges = Arrays.stream(input[0].split(",")).map(Range::new).toList();
        solveFirstPart(ranges);
        solveSecondPart(ranges);
    }

    private static void solveFirstPart(List<Range> ranges){
        long max = ranges.stream().mapToLong(Range::getEnd).max().orElse(0L);
        List<Long> invalidIds = new ArrayList<>();
        int i = 1;
        while (true) {
            long l = Long.parseLong(String.format("%s%s", i, i));
            invalidIds.add(l);
            i++;
            if (l >= max) break;
        }
        System.out.println(invalidIds.size());
        List<Long> invalidIdsInRanges = invalidIds.stream().filter(x -> isInAnyRange(ranges, x)).toList();
        long invalidIdsInRangesSum = invalidIdsInRanges.stream().mapToLong(x -> x).sum();
        System.out.println(invalidIdsInRanges.size());
        System.out.println("Day 2 Part A - Sum of invalid IDs in ranges: "+invalidIdsInRangesSum); // 13108371860
    }

    private static void solveSecondPart(List<Range> ranges){
        Set<Long> invalidIds = new HashSet<>();
        for (Range r : ranges){
            for(long i = r.getStart(); i <= r.getEnd(); i++){
                String s = Long.toString(i);
                int maxSequenceLength = s.length() / 2;
                for (int sl = 1 ; sl <= maxSequenceLength; sl++){
                    if(s.length() % sl != 0) continue;
                    if(s.substring(0, sl).repeat(s.length() / sl).equals(s)){
                        invalidIds.add(i);
                    }
                }
            }
        }
        long invalidIdsInRangesSum = invalidIds.stream().mapToLong(x -> x).sum();
        System.out.println("Day 2 Part B - Sum of invalid IDs in ranges: "+invalidIdsInRangesSum); // 22471660255
    }

    private static boolean isInAnyRange(List<Range> ranges, long value) {
        for (Range r : ranges) {
            if (r.isInRange(value)) {
                return true;
            }
        }
        return false;
    }

    private static class Range {
        final long start;
        final long end;

        Range(String rangeStr) {
            this.start = Long.parseLong(rangeStr.split("-")[0]);
            this.end = Long.parseLong(rangeStr.split("-")[1]);
        }

        boolean isInRange(long value) {
            return start <= value && value <= end;
        }

        public long getStart() {
            return start;
        }

        public long getEnd() {
            return end;
        }


    }

}
