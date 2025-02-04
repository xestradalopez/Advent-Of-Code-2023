package Helpers;

import java.util.LinkedList;

public class Grid<T> implements Comparable<Grid<T>>
{
    public int height;
    public int width;
    transient Object[][] grid;

    public Grid(int h, int w)
    {
        grid = new Object[h][w];
    }

    public Grid(LinkedList<T[]> a)
    {
        height = a.size();
        width = a.getFirst().length;
        grid = new Object[height][width];

        for(int i = 0; i < height; i++)
            System.arraycopy(a.get(i), 0, grid[i], 0, width);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Grid)) return false;

        Grid that = (Grid)obj;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode()
    {
        return this.toString().hashCode();
    }

    @Override
    public int compareTo(Grid o) {
        return 0;
    }

    public void set(Point p, Object obj)
    {
        grid[p.i][p.j] = obj;
    }

    public Object get(Point p)
    {
        return grid[p.i][p.j];
    }

    public Grid<T> clone()
    {
        Grid<T> copy = new Grid<>(height, width);
        copy.height = this.height;
        copy.width = this.width;
        for(int i = 0; i < height; i++)
            if (width >= 0) System.arraycopy(this.grid[i], 0, copy.grid[i], 0, width);
        return copy;
    }

    public boolean contains(Point p)
    {
        return p.i >= 0 && p.j >= 0 && p.i < width && p.j < height;
    }

    public Point[] getNeighbors(Point p)
    {
        LinkedList<Point> neighbors = new LinkedList<>();

        if (p.i > 0)
            neighbors.add(new Point(p.i - 1, p.j));
        if (p.j > 0)
            neighbors.add(new Point(p.i, p.j - 1));
        if (p.i < width - 1)
            neighbors.add(new Point(p.i + 1, p.j));
        if (p.j < height - 1)
            neighbors.add(new Point(p.i, p.j + 1));

        return neighbors.toArray(new Point[0]);
    }

    public Point[] getNeighbors(Point p, char wall)
    {
        LinkedList<Point> neighbors = new LinkedList<>();

        if (p.i > 0 && !grid[p.i - 1][p.j].equals(wall))
            neighbors.add(new Point(p.i - 1, p.j));
        if (p.j > 0 && !grid[p.i][p.j - 1].equals(wall))
            neighbors.add(new Point(p.i, p.j - 1));
        if (p.i < width - 1 && !grid[p.i + 1][p.j].equals(wall))
            neighbors.add(new Point(p.i + 1, p.j));
        if (p.j < height - 1 && !grid[p.i][p.j + 1].equals(wall))
            neighbors.add(new Point(p.i, p.j + 1));

        return neighbors.toArray(new Point[0]);
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();

        for(Object[] a : grid)
        {
            for (Object b : a)
                str.append(b);
            str.append("\n");
        }

        return str.toString();
    }


}