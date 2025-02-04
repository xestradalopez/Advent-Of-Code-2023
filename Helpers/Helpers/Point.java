package Helpers;
import Helpers.Direction.Dir;

public class Point implements Comparable<Point>
{
    public int i;
    public int j;

    public Point(int ex, int why)
    {
        i = ex;
        j = why;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Point that)) return false;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode()
    {
        return this.toString().hashCode();
    }

    @Override
    public int compareTo(Point o) {
        return 0;
    }

    public Point move(Dir d)
    {
        return switch (d)
        {
            case RIGHT -> new Point(i, j + 1);
            case UP -> new Point(i - 1, j);
            case LEFT -> new Point(i, j - 1);
            case DOWN -> new Point(i + 1, j);
        };
    }

    public Point clone()
    {
        return new Point(i, j);
    }

    public String toString()
    {
        return "[" + i + ", " + j + "]";
    }
}
