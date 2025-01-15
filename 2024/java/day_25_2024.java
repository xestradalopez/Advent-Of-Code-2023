import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class day_25_2024
{
    static int[][] pinHeights;
    static int[][] ridgeHeights;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/25.txt"));
        parse(input);

        int partOne = partOne();
        int partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static int partOne()
    {
        int result = 0;

        for(int[] pins: pinHeights)
            for(int[] ridges: ridgeHeights)
                if(!overlaps(pins, ridges))
                    result++;

        return result;
    }

    static int partTwo()
    {
        int result = 0;

        return result;
    }

    static void parse(Scanner input)
    {
        LinkedList<int[]> a = new LinkedList<>();
        LinkedList<int[]> b = new LinkedList<>();

        boolean isLock;

        while(input.hasNextLine())
        {
            char[][] currentSchematic = new char[7][5];
            int[] currentHeights = new int[5];

            for(int i = 0; i < 7; i++)
                currentSchematic[i] = input.nextLine().toCharArray();

            isLock = currentSchematic[0][0] == '#';

            for(int i = 0; i < 5; i++)
                for (int j = 0; j < 7; j++)
                    if(currentSchematic[j][i] != (isLock ? '#' : '.'))
                    {
                        currentHeights[i] = j;
                        break;
                    }

            (isLock ? a : b).add(currentHeights);

            if(input.hasNextLine())
                input.nextLine();
        }

        pinHeights = a.toArray(new int[0][0]);
        ridgeHeights = b.toArray(new int[0][0]);
    }

    static boolean overlaps(int[] pins, int[] ridges)
    {
        for(int i = 0; i < 5; i++)
            if(pins[i] > ridges[i])
                return  true;
        return false;
    }
}
