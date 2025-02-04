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
        Scanner input = new Scanner(new File("2024/example/22.txt"));
        parse(input);

        double start;
        start = System.nanoTime();
        System.out.println("Part One: " + partOne() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
        start = System.nanoTime();
        System.out.println("Part Two: " + partTwo() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
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
            secretNumber = (secretNumber ^ (secretNumber / 32));
            secretNumber = (secretNumber ^ (secretNumber * 2048)) % 16777216;
        }

        return secretNumber;
    }
}
