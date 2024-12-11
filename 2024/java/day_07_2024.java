import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_07_2024
{
    static long[][] calibrationEquations;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/07.txt"));
        parse(input);

        long partOne = partOne();
        long partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static long partOne()
    {
        long result = 0;
        for(long[] calibrationEquation: calibrationEquations)
            for(int i = 0; i < Math.pow(2, calibrationEquation.length - 2); i++)
            {
                long currentTotal = calibrationEquation[1];
                for(int j = 2; j < calibrationEquation.length; j++)
                    if((i / (int)Math.pow(2, calibrationEquation.length - 1 - j)) % 2 == 0)
                        currentTotal += calibrationEquation[j];
                    else
                        currentTotal *= calibrationEquation[j];

                if(currentTotal == calibrationEquation[0])
                {
                        result+=currentTotal;
                        break;
                }
            }
        return result;
    }

    public static long partTwo()
    {
        long result = 0;
        for(long[] calibrationEquation: calibrationEquations)
            for(int i = 0; i < Math.pow(3, calibrationEquation.length - 2); i++)
            {
                long currentTotal = calibrationEquation[1];
                for(int j = 2; j < calibrationEquation.length; j++)
                    if((i / (int)Math.pow(3, calibrationEquation.length - 1 - j)) % 3 == 0)
                        currentTotal += calibrationEquation[j];
                    else if((i / (int)Math.pow(3, calibrationEquation.length - 1 - j)) % 3 == 1)
                        currentTotal *= calibrationEquation[j];
                    else
                    {
                        currentTotal = currentTotal * (int)Math.pow(10, Math.floor(Math.log10(calibrationEquation[j])+1)) + calibrationEquation[j];
                    }

                if(currentTotal == calibrationEquation[0])
                {
                    result+=currentTotal;
                    break;
                }
            }
        return result;
    }

    public static void parse(Scanner input)
    {
        ArrayList<long[]> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(Arrays.stream(input.nextLine().split(" |: ")).mapToLong(Long::parseLong).toArray());
        calibrationEquations = a.toArray(new long[0][0]);
    }
}
