import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_13_2024
{
    static int[][][] clawMachinesInfo;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/13.txt"));
        parse(input);

        int partOne = partOne();
        long partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static int partOne()
    {
        int result = 0;

        for(int[][] x : clawMachinesInfo)
            if((x[1][1] * x[2][0] - x[2][1] * x[1][0]) % (x[1][1] * x[0][0] - x[0][1] * x[1][0]) == 0)
                result += (((x[1][1] * x[2][0] - x[2][1] * x[1][0]) / (x[1][1] * x[0][0] - x[0][1] * x[1][0])) * (3 * x[1][0] - x[0][0]) + x[2][0]) / x[1][0];

        return result;
    }

    public static long partTwo()
    {
        long result = 0;

        for(int[][] x : clawMachinesInfo)
            if((x[1][1] * (x[2][0] + 10000000000000L) - (x[2][1] + 10000000000000L) * x[1][0]) % ((long) x[1][1] * x[0][0] - (long) x[0][1] * x[1][0]) == 0)
                result += (((x[1][1] * (x[2][0] + 10000000000000L) - (x[2][1] + 10000000000000L) * x[1][0]) / ((long) x[1][1] * x[0][0] - (long) x[0][1] * x[1][0])) * (3L * x[1][0] - x[0][0]) + (x[2][0] + 10000000000000L)) / x[1][0];
        return result;
    }

    public static void parse(Scanner input)
    {
        ArrayList<int[][]> a = new ArrayList<>();

        while(input.hasNextLine())
        {
            int[][] b = new int[3][2];

            b[0] = Arrays.stream(input.nextLine().substring(12).split(", Y\\+")).mapToInt(Integer::parseInt).toArray();
            b[1] = Arrays.stream(input.nextLine().substring(12).split(", Y\\+")).mapToInt(Integer::parseInt).toArray();
            b[2] = Arrays.stream(input.nextLine().substring(9).split(", Y=")).mapToInt(Integer::parseInt).toArray();

            if(input.hasNextLine())
                input.nextLine();

            a.add(b);
        }

        clawMachinesInfo = a.toArray(new int[0][0][0]);
    }
}