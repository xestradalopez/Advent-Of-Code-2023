import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_22_2024
{
    static int[] secretNumbers;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/22.txt"));
        parse(input);

        long partOne = partOne();
        int partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static long partOne()
    {
        long result = 0;

        for(int secretNumber: secretNumbers)
            result += get2000thSecretNumber(secretNumber);

        return result;
    }

    static int partTwo()
    {
        int result = 0;

        return result;
    }

    static void parse(Scanner input)
    {
        ArrayList<Integer> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(Integer.parseInt(input.nextLine()));
        secretNumbers = a.stream().mapToInt(Integer::intValue).toArray();
    }

    static long get2000thSecretNumber(long secretNumber)
    {
        for(int i = 0; i < 2000; i++)
        {
            secretNumber = (secretNumber ^ (secretNumber * 64)) % 16777216;
            secretNumber = (secretNumber ^ (secretNumber / 32)) % 16777216;
            secretNumber = (secretNumber ^ (secretNumber * 2048)) % 16777216;
        }

        return secretNumber;
    }
}
