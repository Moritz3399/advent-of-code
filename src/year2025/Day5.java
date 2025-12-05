package year2025;

import utils.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Day5 {

    public static void main(String[] args) {
//        String[] input = InputReader.getLinesOfFileByParameters(2025, 5, 1 );
        String[] input = InputReader.getLinesOfFileByParameters(2025, 5, null);
        List<Input> ids = new ArrayList<>();
        boolean isRange = true;
        for (String s : input) {
            if (s.isBlank()) {
                isRange = false;
                continue;
            }
            if (isRange) {
                ids.add(new Range(s));
            } else {
                ids.add(new Item(s));
            }
        }
        ids.sort(Input::compareTo);
        for (Input i : ids) {
            System.out.println(i.toString());
        }


        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).isRange()) {
                continue;
            }
            if (i == 0) continue;
            Item item = (Item) ids.get(i);
            for (int j = i - 1; j >= 0; j--) {
                if (ids.get(j).isItem()) {
                    continue;
                }
                Range range = (Range) ids.get(j);
                if (range.getA() > item.getA()) continue;
                if (range.getB() < item.getA()) continue;
                item.setFresh(true);
                break;
            }
        }
        List<Input> freshItems = ids.stream().filter(Input::isItem).filter(x -> (((Item) x).getFresh() != null && ((Item) x).getFresh())).toList();
        System.out.println("Day 5 - Part A - fresh items: " + freshItems.size()); // 563

        List<Range> ranges = ids.stream().filter(Input::isRange).map(x -> (Range) x).toList();
        long max = 0L;
        long totalItems = 0L;
        for(Range r : ranges){

            if(r.getA() > max){
                totalItems+= r.getB() - r.getA() + 1;
                max = r.getB();
                continue;
            }
            if(r.getB() < max) continue;
            if(r.getA() < max && max <= r.getB()){
                totalItems+= r.getB() - max;
                max = r.getB();
                continue;
            }
            if(r.getA() == max){
                totalItems+= r.getB() - r.getA();
                max = r.getB();
            }

        }
        System.out.println("Day 5 - Part B - total items: " + totalItems); // 338693411431456



    }

    private static abstract class Input {
        private final String type;
        private final Long a;

        public Input(String type, Long a) {
            this.type = type;
            this.a = a;
        }

        public String getType() {
            return type;
        }

        public Long getA() {
            return a;
        }

        public int compareTo(Input i) {
            // this could be an issue if the lowest values are an item and also a range
            // same a value
            // ranges would need to come first
            return this.a.compareTo(i.getA());
        }

        public boolean isRange() {
            return this.getType().equals("range");
        }

        public boolean isItem() {
            return this.getType().equals("item");
        }

    }

    private static class Range extends Input {
        private final Long b;

        public Range(String type, Long a) {
            super(type, a);
            this.b = 0L;
        }

        public Range(String range) {
            super("range", Long.parseLong(range.split("-")[0]));
            this.b = Long.parseLong(range.split("-")[1]);
        }

        public Long getB() {
            return b;
        }

        @Override
        public String toString() {
            return "Range: " + this.getA() + "-" + this.getB();
        }
    }

    private static class Item extends Input {
        private Boolean isFresh;

        public Item(String type, Long a) {
            super(type, a);
        }

        Item(String a) {
            super("item", Long.parseLong(a));
        }

        @Override
        public String toString() {
            return " Item: " + this.getA();
        }

        public Boolean getFresh() {
            return isFresh;
        }

        public void setFresh(Boolean fresh) {
            isFresh = fresh;
        }
    }

}
