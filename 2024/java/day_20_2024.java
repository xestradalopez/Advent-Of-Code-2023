import Helpers.Grid;
import Helpers.Point;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class day_20_2024
{
    static Grid<Character> racetrackMap;
    static Point startPosition;

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2024/input/20.txt"));
        parse(input);

        double start;
        start = System.nanoTime();
        System.out.println("Part One: " + partOne() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
        start = System.nanoTime();
        System.out.println("Part Two: " + partTwo() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
    }

    static int partOne()
    {
        int result = 0;

        HashMap<Point, Integer> path = new HashMap<>();
        Grid<Character> map = racetrackMap.clone();
        Point pos = startPosition.clone();

        int fastestTime = 0;

        while(!map.get(pos).equals('E'))
        {
            path.put(pos, fastestTime);
            map.set(pos, '#');
            pos = map.getNeighbors(pos, '#')[0];
            fastestTime++;
        }
        path.put(pos, fastestTime);

        map = racetrackMap.clone();
        for(Point p : path.keySet())
        {
           map.set(p, '#');

            for(Point q : map.getNeighbors(p, '.'))
                for(Point b : map.getNeighbors(q, '#'))
                    if(path.containsKey(b) && path.get(b) - path.get(p) - 2 >= 100) result++;

            map.set(p, '.');
        }

        return result;
    }

    static int partTwo()
    {
        int result = 0;

        return result;
    }

    static void parse(Scanner input)
    {
        LinkedList<Character[]> a = new LinkedList<>();

        while(input.hasNextLine())
        {
            String currentLine = input.nextLine();
            if(currentLine.contains("S"))
                startPosition = new Point(a.size(), currentLine.indexOf('S'));
            a.add(currentLine.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
        }

        racetrackMap = new Grid<>(a);
    }
}
