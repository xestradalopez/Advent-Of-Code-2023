import Helpers.Helper;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;

public class day_01_2024
{
    static int[][] lists;

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2024/input/01.txt"));
        parse(input);

        double start;
        start = System.nanoTime();
        System.out.println("Part One: " + partOne() + "\t" + ((System.nanoTime() - start) / 1000000) + "ms");
        start = System.nanoTime();
        System.out.println("Part Two: " + partTwo() + "\t" + ((System.nanoTime() - start) / 1000000) + "ms");
    }

    static int partOne()
    {
        int result = 0;
        for(int i = 0; i < lists[0].length; i++)
            result += Math.abs(lists[0][i] - lists[1][i]);
        return result;
    }

    static int partTwo()
    {
        int result = 0;
        for (Integer id : lists[0])
            result += id * getFrequency(id);
        return result;
    }

    static void parse(Scanner input)
    {
        LinkedList<Integer>[] a = new LinkedList[2];
        a[0] = new LinkedList<>();
        a[1] = new LinkedList<>();

        while(input.hasNextLine())
        {
            int[] pair = Arrays.stream(input.nextLine().split(" {3}")).mapToInt(Integer::parseInt).toArray();

            a[0].add(pair[0]);
            a[1].add(pair[1]);
        }

        lists = Helper.toArray(a);

        Arrays.sort(lists[0]);
        Arrays.sort(lists[1]);
    }

    static int getFrequency(int id)
    {
        int frequency = 0;
        for (Integer x : lists[1])
            if (x == id)
                frequency++;
        return frequency;
    }
}
