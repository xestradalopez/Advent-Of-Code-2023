import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Java_12
{
    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        //Scanner input = new Scanner(new File("2023/example/12.txt"));

        //int partOne = 0;
        //int partTwo = 0;

        //while(input.hasNextLine())
        {
            //parseInput(input);
        }
        System.out.println(arrangements(new int[]{2,1},5, new int[]{1,4}, 0, 0));

        //System.out.println("Part One: " + partOne);
        //System.out.println("Part Two: " + partTwo);

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

    static int arrangements(int[] blocks, int spaces)
    {
        if(blocks.length == 1)
            return spaces - blocks[0] + 1;
        else
        {
            int a = 0;

            for(int i = 0; true; i++)
            {
                int[] blocks2 = new int[blocks.length - 1];
                System.arraycopy(blocks, 1, blocks2, 0, blocks2.length);
                if(fits(blocks2, spaces - blocks[0] - 1 - i))
                    a += arrangements(blocks2, spaces - blocks[0] - 1 - i);
                else
                    break;
            }

            return a;
        }
    }

    static int arrangements(int[] blocks, int spaces, int[] indexes, int offset, int number)
    {
        int tempOffset;
        int z = number + 1;
        if(blocks.length == 1)
            return indexes[0] - offset < blocks[0] ? spaces - blocks[0] + 1 : 0;
        else
        {
            int a = 0; // Sum total of all possibilites


            for(int i = 0; true; i++)
            {
                tempOffset = i + offset + blocks[0] - 1 + z; // I need to keep track of how many blocks its on
                System.out.println(z);
                int[] blocks2 = new int[blocks.length - 1];
                System.arraycopy(blocks, 1, blocks2, 0, blocks2.length);

                int[] indexes2 = new int[indexes.length - 1];
                System.arraycopy(indexes, 1, indexes2, 0, indexes2.length);

                if(fits(blocks2, spaces - blocks[0] - 1 - i) && indexes[0] - offset < blocks[0])
                {
                    a += arrangements(blocks2, spaces - blocks[0] - 1 - i, indexes2, tempOffset, z);
                }
                else
                    break;
            }

            return a;
        }
    }

    static boolean fits(int[] blocks, int spaces)
    {
        int sum = 0;
        for(int i = 0; i < blocks.length; i++)
            sum+=blocks[i];
        if(sum + blocks.length -1 <= spaces)
            return true;
        return false;
    }
}