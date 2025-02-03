import java.util.LinkedList;

public class Grid<T>
{
    int width;
    int length;
    transient Object[][] grid;

    public Grid(int w, int l)
    {
        grid = new Object[w][l];
    }

    public Point[] getNeighbors(Point p)
    {
        LinkedList<Point> neighbors = new LinkedList<>();

        if (p.x > 0)
            neighbors.add(new Point(p.x - 1, p.y));
        if (p.y > 0)
            neighbors.add(new Point(p.x, p.y - 1));
        if (p.x < width - 1)
            neighbors.add(new Point(p.x + 1, p.y));
        if (p.y < length - 1)
            neighbors.add(new Point(p.x, p.y + 1));

        return neighbors.toArray(new Point[0]);
    }
}