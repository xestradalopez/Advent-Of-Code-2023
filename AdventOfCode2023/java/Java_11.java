import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Java_11
{
    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("AdventOfCode2023/example/11.txt"));

        int partOne = 0;
        int partTwo = 0;

        while(input.hasNextLine())
        {
            parseInput(input);
        }

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }
    static int parseInput(Scanner a)
    {
        String currentLine = a.nextLine();

        String[] springs = Arrays.stream(currentLine.substring(0,currentLine.indexOf(" ")).split("")).toArray(size -> new String[size]);
        int[] groupSizes = Arrays.stream(currentLine.substring(currentLine.indexOf(" ") + 1).split(",")).mapToInt(Integer::parseInt).toArray();

        //System.out.print(Arrays.toString(springs) + " : " + Arrays.toString(groupSizes));

        int numberOfDamaged = 0;
        int numberOfUnknown = 0;
        boolean startOfRow = false;
        int count = 1;
        int sumOfGroupSizes = 0;
        int numberOfGroupSizes = 0;
        int index = 0;
        for(int i = 0; i < springs.length; i++)
        {
            if(!springs[i].equals("."))
                startOfRow = true;

            if(startOfRow)
            {
                if(springs[i].equals("#"))
                    numberOfDamaged++;
                if(springs[i].equals("?"))
                    numberOfUnknown++;

                if(index < groupSizes.length && groupSizes[index] + sumOfGroupSizes + numberOfGroupSizes <= numberOfUnknown + numberOfDamaged)
                {
                    sumOfGroupSizes += groupSizes[index];
                    numberOfGroupSizes++;
                    index++;
                }

                if(springs[i].equals(".") || i == springs.length - 1)
                {
                    startOfRow = false;
                    if(numberOfUnknown != 0)
                        count *= numberOfUnknown / (sumOfGroupSizes - numberOfDamaged + numberOfGroupSizes - 1);

                    numberOfGroupSizes = 0;
                    sumOfGroupSizes = 0;
                    numberOfUnknown = 0;
                    numberOfDamaged = 0;
                }
            }
            System.out.print(numberOfGroupSizes + ", ");
        }
        System.out.println(" : " + count);
        return -1;
    }

}