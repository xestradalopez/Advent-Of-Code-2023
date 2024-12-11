import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class day_09_2024
{
    static int[] diskMap;
    static int[] disk;

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

        for(int i = 0, j = disk.length - 1; i <= j; i++)
            if(disk[i] != -1)
                result += (long) disk[i] * i;
            else
            {
                result += (long) disk[j] * i;
                do j--; while(disk[j] == -1);
            }

        return result;
    }

    static long partTwo()
    {
        long result = 0;

        for(int i = diskMap.length - 1; i >= 0; i-=2)
            for(int j = 1; j < i; j+=2)
                if(diskMap[j] >= diskMap[i])
                {
                    int freeSpaceIndex = getDiskIndex(j);
                    int fileBlockIndex = getDiskIndex(i);
                    int fileBlockSize = diskMap[i];
                    int fileID = disk[fileBlockIndex];

                    for(int k = freeSpaceIndex; k < freeSpaceIndex + fileBlockSize; k++)
                        disk[k] = fileID;
                    for(int k = fileBlockIndex; k < fileBlockIndex + fileBlockSize; k++)
                        disk[k] = -1;

                    if(i != diskMap.length - 1)
                        diskMap[i + 1] = diskMap[i + 1] + diskMap[i] + diskMap[i - 1];
                    for(int k = i; k > j + 1; k--)
                        diskMap[k] = diskMap[k - 2];
                    diskMap[j + 2] = diskMap[j] - fileBlockSize;
                    diskMap[j + 1] = fileBlockSize;
                    diskMap[j] = 0;

                    break;
                }

        for(int i = 0; i < disk.length; i++)
            if(disk[i] != -1)
                result += (long) disk[i] * i;

        return result;
    }

    static void parse(Scanner input)
    {
        diskMap = Arrays.stream(input.nextLine().split("")).mapToInt(Integer::parseInt).toArray();

        int diskSize = 0;
        for(int num: diskMap)
            diskSize+=num;
        disk = new int[diskSize];
        int fileID = 0;
        int fileBlockIndex = 0;
        for(int i = 0; i < diskMap.length; i++)
            if(i%2 == 0)
            {
                for(int j = 0; j < diskMap[i]; j++)
                    disk[fileBlockIndex + j] = fileID;
                fileBlockIndex += diskMap[i];
                fileID++;
            }
            else
            {
                for(int j = 0; j < diskMap[i]; j++)
                    disk[fileBlockIndex + j] = -1;
                fileBlockIndex += diskMap[i];
            }
    }

    static int getDiskIndex(int compactedIndex)
    {
        int diskIndex = 0;
        for(int i = 0; i < compactedIndex; i++)
            diskIndex += diskMap[i];
        return diskIndex;
    }
}
