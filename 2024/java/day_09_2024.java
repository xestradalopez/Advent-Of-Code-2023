import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class day_09_2024
{
    static int[] diskMap;
    static int[] uncompactedDiskMap;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/09.txt"));
        parse(input);

        long partOne = partOne();
        long partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static long partOne()
    {
        long result = 0;
        int size = 0;
        for(int num: diskMap)
            size+=num;
        uncompactedDiskMap = new int[size];
        int count = 0;
        int huzzah = 0;
        for(int i = 0; i < diskMap.length; i++)
        {
            if(i%2 == 0)
            {
                for (int j = 0; j < diskMap[i]; j++)
                    uncompactedDiskMap[huzzah + j] = count;
                huzzah+=diskMap[i];
                count++;
            }
            else
            {
                for (int j = 0; j < diskMap[i]; j++)
                    uncompactedDiskMap[huzzah + j] = -1;
                huzzah+=diskMap[i];
            }
        }
        int[] thingy2 = Arrays.copyOf(uncompactedDiskMap, uncompactedDiskMap.length);
        for(int i = 0, j = thingy2.length - 1;i < j;)
        {
            if(thingy2[i] == -1 && thingy2[j] != -1)
            {
                thingy2[i] = thingy2[j];
                thingy2[j] = -1;
                i++;
                j--;
            }
            else
            {
                if(thingy2[i] != -1)
                    i++;
                if(thingy2[j] == -1)
                    j--;
            }
        }
        for(int i = 0; thingy2[i] != -1; i++)
            result += (long) thingy2[i] * i;
        return result;
    }

    static long partTwo()
    {
        long result = 0;

        for(int i = diskMap.length - 1; i >= 0; i-=2)
            for(int j = 1; j < i; j+=2)
                if(diskMap[j] >= diskMap[i])
                {
                    int from = getUncompactedIndex(j); //I named these in reverse
                    int to = getUncompactedIndex(i);
                    int temp = diskMap[i];
                    int utemp = uncompactedDiskMap[to];

                    for (int k = from; k < from + temp; k++)
                        uncompactedDiskMap[k] = utemp;
                    for (int k = to; k < to + temp; k++)
                        uncompactedDiskMap[k] = -1;

                    //Do not touch. It works as intended probably
                    if(i+1 < diskMap.length)
                        diskMap[i + 1] = diskMap[i + 1] + diskMap[i] + diskMap[i - 1];
                    for(int k = i; k > j + 1; k--)
                        diskMap[k] = diskMap[k - 2];
                    diskMap[j + 2] = diskMap[j] - temp;
                    diskMap[j + 1] = temp;
                    diskMap[j] = 0;

                    break;
                }

        for(int i = 0; i < uncompactedDiskMap.length; i++)
            if(uncompactedDiskMap[i] != -1)
                result += (long) uncompactedDiskMap[i] * i;

        return result;
    }

    static void parse(Scanner input)
    {
        diskMap = Arrays.stream(input.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
    }

    static int getUncompactedIndex(int compactedIndex)
    {
        int uncompactedIndex = 0;
        for(int i = 0; i < compactedIndex; i++)
            uncompactedIndex+=diskMap[i];
        return uncompactedIndex;
    }

}
