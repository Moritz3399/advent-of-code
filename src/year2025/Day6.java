package year2025;

import utils.InputReader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    public static void main(String[] args) {
//        String[] input = InputReader.getLinesOfFileByParameters(2025,6,1);
        String[] input = InputReader.getLinesOfFileByParameters(2025,6,null);
        List<String> lines = Arrays.stream(input).map(String::trim).map(x -> {String t = x; int l; do{l = t.length(); t = t.replace("  ", " ");}while (t.length() != l); return  t;}).toList();
        for(String s : lines) System.out.println(s);

        List<List<BigInteger>> numbers = lines.stream().filter(x -> !x.contains("+")).map(x -> Arrays.stream(x.split(" ")).map(BigInteger::new).toList()).toList();
        List<String> operators = lines.stream().filter(x -> x.contains("+")).map(x -> Arrays.stream(x.split(" ")).toList()).findFirst().orElse(null);
        List<BigInteger> answers = new ArrayList<>();
        if(operators == null){
            return;
        }

//        for(List<Integer> l : numbers) System.out.println(l.size());
//        System.out.println(operators.size());
        for(int i = 0; i<operators.size(); i++){
            final int index = i;
            String o = operators.get(i);
            List<BigInteger> n = numbers.stream().map(x -> x.get(index)).toList();
            BigInteger answer = switch (o) {
                case "+" -> n.stream().reduce(BigInteger.ZERO, BigInteger::add);
                case "*" -> n.stream().reduce(BigInteger.ONE, BigInteger::multiply);
                default -> BigInteger.ZERO;
            };
            answers.add(answer);
        }
        for(BigInteger i: answers) System.out.println(i);
        System.out.println("Day 6 - Part A - Grand total: "+ answers.stream().reduce(BigInteger.ZERO, BigInteger::add)); // 6891729672676

    }
}
