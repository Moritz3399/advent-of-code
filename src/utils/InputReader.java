package utils;

import java.io.*;
import java.util.ArrayList;

public class InputReader {

    /**
     *
     * @param year
     * @param day
     * @param exampleNumber if null, the real data is used
     * @return
     */
    public static String[] getLinesOfFileByParameters(int year, int day, Integer exampleNumber) {
        String e = "";
        if(exampleNumber != null){
            e = ".example." + exampleNumber;
        }
        return getLinesOfFile(String.format("inputs/%s/day%s%s.txt", year, day, e));
    }


    public static String[] getLinesOfFile(String path) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines.toArray(new String[0]);

        } catch (IOException e) {
            System.err.println("File does not exist: " + path);
        }
        return new String[0];
    }

}