package utils;

public class ArrayUtils {

    public static boolean isIndexInBounds(int[] o, int index) {
        return index >= 0 && index < o.length;
    }

    public static boolean isIndexInBounds(Object[] o, int index) {
        return index >= 0 && index < o.length;
    }

    public static boolean is2dIndexInBounds(int[][] o, int firstIndex, int secondIndex) {
        if (!isIndexInBounds(o, firstIndex)) return false;
        return isIndexInBounds(o[firstIndex], secondIndex);
    }

    public static boolean is2dIndexInBounds(Object[][] o, int firstIndex, int secondIndex) {
        if (!isIndexInBounds(o, firstIndex)) return false;
        return isIndexInBounds(o[firstIndex], secondIndex);
    }

}
