import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.IntStream;

public class day_02_2024
{
    static int[][] reports;

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2024/input/02.txt"));
        parse(input);

        double start;
        start = System.nanoTime();
        System.out.println("Part One: " + partOne() + "\t" + ((System.nanoTime() - start) / 1000000) + "ms");
        start = System.nanoTime();
        System.out.println("Part Two: " + partTwo() + "\t" + ((System.nanoTime() - start) / 1000000) + "ms");
    }

    public static int partOne()
    {
        int result = 0;
        for(int[] report : reports)
            if (isGradual(report) && isMonotonic(report))
                result++;
        return result;
    }

    public static int partTwo()
    {
        int result = 0;
        for (int[] report : reports)
            for(int i = 0; i < report.length; i++)
            {
                int I = i;
                int[] dampReport = IntStream.range(0, report.length).filter(j -> j != I).map(j -> report[j]).toArray(); //Creates a new array from report without the element at index i
                if(isGradual(dampReport) && isMonotonic(dampReport))
                {
                    result++;
                    break;
                }
            }
        return result;
    }

    public static void parse(Scanner input)
    {
        LinkedList<int[]> a = new LinkedList<>();
        while(input.hasNextLine())
            a.add(Arrays.stream(input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        reports = a.toArray(new int[0][0]);
    }

    public static boolean isGradual(int[] report)
    {
        for(int i = 0; i < report.length - 1; i++)
            if(Math.abs(report[i] - report[i+1]) < 1 || Math.abs(report[i] - report[i+1]) > 3)
                return false;
        return true;
    }

    public static boolean isMonotonic(int[] report)
    {
        int monotonicity = (int)Math.signum(report[1] - report[0]);
        for(int i = 1; i < report.length - 1; i++)
            if((int)Math.signum(report[i+1] - report[i]) != monotonicity)
                return false;
        return true;
    }
}
