import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class day_11_2024
{
    static long[] stones;
    static HashMap<Long, HashMap<Integer, Long>> memo = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/11.txt"));
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
        long result;
        result = blink(25);
        return result;
    }

    public static long partTwo()
    {
        long result;
        result = blink(75);
        return result;
    }

    public static void parse(Scanner input)
    {
        stones = Arrays.stream(input.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
    }

    static long blink(int steps)
    {
        long result = 0;
        for (long stone : stones)
            result += blink(stone, steps);
        return result;
    }

    static long blink(long x, int steps)
    {
        long result;

        if(steps == 0)
            return 1;

        if(memo.containsKey(x) && memo.get(x).containsKey(steps))
            return memo.get(x).get(steps);

        if(x == 0)
        {
            result = blink(1, steps - 1);
            memoization(x, steps, result);
            return result;
        }
        else if((""  + x).length() % 2 == 0)
        {
            result = blink(Long.parseLong(("" + x).substring(("" + x).length() / 2)), steps - 1) + blink(Long.parseLong(("" + x).substring(0, ("" + x).length() / 2)), steps - 1);
            memoization(x, steps, result);
            return result;
        }
        else
        {
            result = blink(x * 2024, steps - 1);
            memoization(x, steps, result);
            return result;
        }
    }

    static void memoization(long x, int steps, long result)
    {
        if(memo.containsKey(x))
            memo.get(x).put(steps, result);
        else
        {
            HashMap<Integer, Long> temp = new HashMap<>();
            temp.put(steps, result);
            memo.put(x, temp);
        }
    }
}
