package utils;

import java.util.Objects;

public class CoordinatePoint {

    private final int x;
    private final int y;

    public CoordinatePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CoordinatePoint c = (CoordinatePoint) obj;
        return this.x == c.getX() && this.y == c.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x + "/" + y);
    }
}
