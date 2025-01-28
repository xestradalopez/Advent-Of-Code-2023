import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class day_08_2024
{
    static char[][] antennaMap;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/08.txt"));
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

        char[][] antinodeMap = new char[antennaMap.length][antennaMap[0].length];

        for(int i = 0; i < antennaMap.length; i++)
            for(int j = 0; j < antennaMap.length; j++)
                if(antennaMap[i][j] != '.')
                    for(int v = j == antennaMap.length - 1 ? i + 1 : i; v < antinodeMap.length; v++)
                        for(int w = v == i ? j + 1 : 0; w < antinodeMap.length; w++)
                            if (antennaMap[i][j] == antennaMap[v][w])
                            {
                                if (isInbounds(2 * i - v, 2 * j - w)) antinodeMap[2 * i - v][2 * j - w] = '#';
                                if (isInbounds(2 * v - i, 2 * w - j)) antinodeMap[2 * v - i][2 * w - j] = '#';
                            }

        for(char[] a: antinodeMap)
            for (char b : a)
                if(b == '#')
                    result++;

        return result;
    }

    static int partTwo()
    {
        int result = 0;

        char[][] antinodeMap = new char[antennaMap.length][antennaMap[0].length];

        for(int i = 0; i < antennaMap.length; i++)
            for(int j = 0; j < antennaMap.length; j++)
                if(antennaMap[i][j] != '.')
                    for(int v = j == antennaMap.length - 1 ? i + 1 : i; v < antinodeMap.length; v++)
                        for(int w = v == i ? j + 1 : 0; w < antinodeMap.length; w++)
                            if (antennaMap[i][j] == antennaMap[v][w])
                            {
                                int y = v - i;
                                int x = w - j;

                                for(int a = 0; isInbounds(i - a * y, j - a * x); a++) antinodeMap[i - a * y][j - a * x] = '#';
                                for(int a = 0; isInbounds(v + a * y, w + a * x); a++) antinodeMap[v + a * y][w + a * x] = '#';
                            }

        for(char[] a: antinodeMap)
            for (char b : a)
                if (b == '#')
                    result++;

        return result;
    }

    static void parse(Scanner input)
    {
        LinkedList<char[]> a = new LinkedList<>();

        while(input.hasNextLine())
            a.add(input.nextLine().toCharArray());

        antennaMap = a.toArray(new char[0][0]);
    }

    static boolean isInbounds(int i, int j)
    {
        return i >= 0 && j >= 0 && i < antennaMap.length && j < antennaMap.length;
    }
}
