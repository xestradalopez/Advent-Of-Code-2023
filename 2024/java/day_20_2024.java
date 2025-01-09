import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_20_2024
{
    static char[][] racetrackMap;
    static int[] startPosition;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/example/20.txt"));
        parse(input);

        System.out.println("Part One: " + partOne());
        System.out.println("Part Two: " + partTwo());

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static int partOne()
    {
        int result = 0;

        int baseSpeed = 0;

        char[][] map = copy2DArray(racetrackMap);

        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(startPosition);

        while(!moves.isEmpty())
        {
            racetrackMap[moves.getFirst()[0]][moves.getFirst()[1]] = '#';

            if(racetrackMap[moves.getFirst()[0]][moves.getFirst()[1] + 1] != '#')
                moves.add(new int[]{moves.getFirst()[0],moves.getFirst()[1] + 1});
            if(racetrackMap[moves.getFirst()[0] - 1][moves.getFirst()[1]] != '#')
                moves.add(new int[]{moves.getFirst()[0] - 1,moves.getFirst()[1]});
            if(racetrackMap[moves.getFirst()[0]][moves.getFirst()[1] - 1] != '#')
                moves.add(new int[]{moves.getFirst()[0],moves.getFirst()[1] - 1});
            if(racetrackMap[moves.getFirst()[0] + 1][moves.getFirst()[1]] != '#')
                moves.add(new int[]{moves.getFirst()[0] + 1,moves.getFirst()[1]});
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
        ArrayList<char[]> a = new ArrayList<>();
        while(input.hasNextLine())
        {
            String currentLine = input.nextLine();
            if(currentLine.contains("S"))
                startPosition = new int[]{a.size(), currentLine.indexOf('S'), 0};
            a.add(currentLine.toCharArray());
        }
        racetrackMap = a.toArray(new char[0][0]);
    }

    public static char[][] copy2DArray(char[][] arr1)
    {
        char[][] arr2 = new char[arr1.length][];

        for (int i = 0; i < arr1.length; i++)
            arr2[i] = Arrays.copyOf(arr1[i], arr1[i].length);

        return arr2;
    }
}
