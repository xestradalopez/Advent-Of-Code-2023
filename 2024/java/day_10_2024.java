import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_10_2024
{
    static int[][] topographicMap;
    static int[][] topographicMap2;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/10.txt"));
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
        for(int i = 0; i < topographicMap.length; i++)
        {
            for(int j = 0; j < topographicMap[i].length; j++)
            {
                if(topographicMap[i][j] == 0)
                {
                    topographicMap2 = copy2DArray(topographicMap);
                    result += getTrailheadScore(i, j);
                }
            }
        }
        return result;
    }

    static int partTwo()
    {
        int result = 0;
        for(int i = 0; i < topographicMap.length; i++)
        {
            for(int j = 0; j < topographicMap[i].length; j++)
            {
                if(topographicMap[i][j] == 0)
                {
                    topographicMap2 = copy2DArray(topographicMap);
                    result += getTrailheadScore2(i, j);
                }
            }
        }
        return result;
    }

    static void parse(Scanner input)
    {
        ArrayList<int[]> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(Arrays.stream(input.nextLine().split("")).mapToInt(Integer::parseInt).toArray());
        topographicMap = a.toArray(new int[0][0]);
    }

    static int getTrailheadScore(int i, int j)
    {

        int trailheadScore = 0;

        if(topographicMap2[i][j] == 9)
        {
            topographicMap2[i][j] = -1;
            return 1;
        }

        if(isInbounds(i + 1) && topographicMap2[i + 1][j] == topographicMap2[i][j] + 1)
            trailheadScore += getTrailheadScore(i + 1, j);
        if(isInbounds(i - 1) && topographicMap2[i - 1][j] == topographicMap2[i][j] + 1)
            trailheadScore += getTrailheadScore(i - 1, j);
        if(isInbounds(j + 1) && topographicMap2[i][j + 1] == topographicMap2[i][j] + 1)
            trailheadScore += getTrailheadScore(i, j + 1);
        if(isInbounds(j - 1) && topographicMap2[i][j - 1] == topographicMap2[i][j] + 1)
            trailheadScore += getTrailheadScore(i, j - 1);

        return trailheadScore;
    }

    static int getTrailheadScore2(int i, int j)
    {

        int trailheadScore = 0;

        if(topographicMap2[i][j] == 9)
            return 1;

        if(isInbounds(i + 1) && topographicMap2[i + 1][j] == topographicMap2[i][j] + 1)
            trailheadScore += getTrailheadScore2(i + 1, j);
        if(isInbounds(i - 1) && topographicMap2[i - 1][j] == topographicMap2[i][j] + 1)
            trailheadScore += getTrailheadScore2(i - 1, j);
        if(isInbounds(j + 1) && topographicMap2[i][j + 1] == topographicMap2[i][j] + 1)
            trailheadScore += getTrailheadScore2(i, j + 1);
        if(isInbounds(j - 1) && topographicMap2[i][j - 1] == topographicMap2[i][j] + 1)
            trailheadScore += getTrailheadScore2(i, j - 1);

        return trailheadScore;
    }

    static boolean isInbounds(int i)
    {
        return i >= 0 && i < topographicMap.length;
    }

    public static int[][] copy2DArray(int[][] arr1)
    {
        int[][] arr2 = new int[arr1.length][];

        for (int i = 0; i < arr1.length; i++)
            arr2[i] = Arrays.copyOf(arr1[i], arr1[i].length);

        return arr2;
    }

}
