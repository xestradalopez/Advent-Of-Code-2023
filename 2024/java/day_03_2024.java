import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class day_03_2024
{
    static String[] corruptedMemory;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/03.txt"));
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
        for (String line : corruptedMemory)
        {
            int index = line.indexOf("mul(");
            while (index != -1) {
                result += getInstruction(line, index);
                index = line.indexOf("mul(", index + 1);
            }
        }
        return result;
    }

    public static int partTwo()
    {
        int result = 0;
        boolean enabled = true;
        for (String line : corruptedMemory)
            for (int j = 0; j < line.length(); j++)
            {
                if (line.substring(j).indexOf("do()") == 0)
                    enabled = true;
                if (line.substring(j).indexOf("don't()") == 0)
                    enabled = false;
                if (line.substring(j).indexOf("mul(") == 0 && enabled)
                    result += getInstruction(line, j);
            }
        return result;
    }

    public static void parse(Scanner input)
    {
        ArrayList<String> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(input.nextLine());
        corruptedMemory = a.toArray(new String[0]);
    }

    public static int getInstruction(String x, int index)
    {
        String instruction = x.substring(index+4, x.indexOf(')', index + 1));
        String[] pair = instruction.split(",");
        if(pair.length == 2 && isNumeric(pair[0]) && isNumeric(pair[1]))
            return Integer.parseInt(pair[0]) * Integer.parseInt(pair[1]);
        return 0;
    }

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }
}
