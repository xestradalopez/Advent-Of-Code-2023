import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class day_01_2024
{
    static int[] leftList;
    static int[] rightList;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/01.txt"));
        parse(input);

        int partOne = partOne();
        int partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static int partOne()
    {
        int result = 0;
        for(int i = 0; i < leftList.length; i++)
            result+= Math.abs(leftList[i] - rightList[i]);
        return result;
    }

    public static int partTwo()
    {
        int result = 0;
        for (Integer id : leftList)
            result += id * getFrequency(id);
        return result;
    }

    public static void parse(Scanner input)
    {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        while(input.hasNextLine())
        {
            int[] pair = Arrays.stream(input.nextLine().split(" {3}")).mapToInt(Integer::parseInt).toArray();
            a.add(pair[0]);
            b.add(pair[1]);
        }

        leftList = a.stream().mapToInt(Integer::intValue).toArray();
        rightList = b.stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(leftList);
        Arrays.sort(rightList);
    }

    public static int getFrequency(int id)
    {
        int frequency = 0;
        for (Integer x : rightList)
            if (x == id)
                frequency++;
        return frequency;
    }
}
