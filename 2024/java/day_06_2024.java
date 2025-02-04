import Helpers.Direction;
import Helpers.Direction.Dir;
import Helpers.Grid;
import Helpers.Point;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.LinkedList;
import java.util.Scanner;

public class day_06_2024
{
    static Grid<Character> labMap;
    static Point startPosition;
    static LinkedList<Point> mainPath;

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2024/input/06.txt"));
        parse(input);

        double start;
        start = System.nanoTime();
        System.out.println("Part One: " + partOne() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
        start = System.nanoTime();
        System.out.println("Part Two: " + partTwo() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
    }

    public static int partOne()
    {
        int result = 0;

        Dir dir = Dir.UP;
        Grid<Character> map = labMap.clone();
        Point pos = startPosition.clone();
        Point newPos = pos;

        while(true)
        {
            if(!map.get(pos).equals('X'))
            {
                result++;
                mainPath.add(pos);
            }

            map.set(pos, 'X');
            newPos = pos.move(dir);

            if(!map.contains(newPos))
                break;
            else if(map.get(newPos).equals('#'))
                dir = Direction.right(dir);
            else
                pos = newPos;
        }

        return result;
    }

    public static int partTwo()
    {
        int result = 0;

        mainPath.removeFirst();

        for(Point p: mainPath)
        {
            Dir dir = Dir.UP;
            Grid<Character> map = labMap.clone();
            Point pos = startPosition.clone();
            Point newPos = pos;

            map.set(p, '#');

            while(true)
            {
                if(map.get(pos).equals('2'))
                {
                    result++;
                    break;
                }

                map.set(pos, (char)((char)map.get(pos) + 1));
                newPos = pos.move(dir);

                if(!map.contains(newPos))
                    break;
                else if(map.get(newPos).equals('#'))
                    dir = Direction.right(dir);
                else
                    pos = newPos;
            }
        }

        return result;
    }

    public static void parse(Scanner input)
    {
        LinkedList<Character[]> a = new LinkedList<>();
        while(input.hasNextLine())
        {
            String currentLine = input.nextLine();
            if(currentLine.contains("^"))
                startPosition = new Point(a.size(), currentLine.indexOf('^'));
            a.add(currentLine.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
        }

        labMap = new Grid<>(a);
        mainPath = new LinkedList<>();
    }
}
