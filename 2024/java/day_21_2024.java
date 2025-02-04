import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.Scanner;

import Helpers.Point;

public class day_21_2024
{
    static char[][] doorCodes;
    static HashMap<Character, Point> numPad = new HashMap<>();
    static HashMap<Character, Point> dirPad = new HashMap<>();
    static HashMap<Character, HashMap<Character, HashMap<Integer, Long>>> cache = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2024/input/21.txt"));
        parse(input);

        double start;
        start = System.nanoTime();
        System.out.println("Part One: " + partOne() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
        start = System.nanoTime();
        System.out.println("Part Two: " + partTwo() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
    }

    static int partOne()
    {
        int result = 0;

        for(char[] code: doorCodes)
            result += charSequenceLength(getNumericKeySequence(code), 3) * Integer.parseInt(new String(code).substring(0, 3));

        return result;
    }

    static long partTwo()
    {
        long result = 0;

        for(char[] code: doorCodes)
            result += charSequenceLength(getNumericKeySequence(code), 26) * Integer.parseInt(new String(code).substring(0, 3));

        return result;
    }

    static void parse(Scanner input)
    {
        doorCodes = new char[5][4];

        for(int i = 0; input.hasNextLine(); i++)
            doorCodes[i] = input.nextLine().toCharArray();

        numPad.put('7', new Point(0, 3)); numPad.put('8', new Point(1, 3)); numPad.put('9', new Point(2, 3));
        numPad.put('4', new Point(0, 2)); numPad.put('5', new Point(1, 2)); numPad.put('6', new Point(2, 2));
        numPad.put('1', new Point(0, 1)); numPad.put('2', new Point(1, 1)); numPad.put('3', new Point(2, 1));
                                          numPad.put('0', new Point(1, 0)); numPad.put('A', new Point(2, 0));

                                          dirPad.put('^', new Point(1, 1)); dirPad.put('A', new Point(2, 1));
        dirPad.put('<', new Point(0, 0)); dirPad.put('v', new Point(1, 0)); dirPad.put('>', new Point(2, 0));
    }

    static char[] getDirectionalKeySequence(char[] keySequence, Point start)
    {
        StringBuilder directionalKeySequence = new StringBuilder();

        Point from;
        Point to = start;

        for(char key : keySequence)
        {
            from = to;
            to = dirPad.get(key);

            int x = to.i - from.i;
            int y = to.j - from.j;

            if(from.i == 0 && to.j == 1)
            {
                directionalKeySequence.repeat(">", x);
                directionalKeySequence.repeat("^", y);
                directionalKeySequence.append("A");
                continue;
            }

            if((from.j == 1 && to.i == 0))
            {
                directionalKeySequence.repeat("v", -y);
                directionalKeySequence.repeat("<", -x);
                directionalKeySequence.append("A");
                continue;
            }

            if(x < 0)
                directionalKeySequence.append("<".repeat(-x));
            if(y < 0)
                directionalKeySequence.append("v");
            if(y > 0)
                directionalKeySequence.append("^");
            if(x > 0)
                directionalKeySequence.append(">".repeat(x));

            directionalKeySequence.append('A');
        }

        return directionalKeySequence.toString().toCharArray();
    }

    static char[] getNumericKeySequence(char[] keySequence)
    {
        StringBuilder numericKeySequence = new StringBuilder();

        Point from;
        Point to = new Point(2, 0);

        for(char key : keySequence)
        {
            from = to;
            to = numPad.get(key);

            int x = to.i - from.i;
            int y = to.j - from.j;

            if(from.j == 0 && to.i == 0)
            {
                numericKeySequence.append("^".repeat(y));
                numericKeySequence.append("<".repeat((-x)));
                numericKeySequence.append("A");
                continue;
            }

            if(from.i == 0 && to.j == 0)
            {
                numericKeySequence.append(">".repeat(-y));
                numericKeySequence.append("v".repeat(x));
                numericKeySequence.append("A");
                continue;
            }

            if(x < 0)
                numericKeySequence.append("<".repeat(-x));
            if(y < 0)
                numericKeySequence.append("v".repeat(-y));
            if(y > 0)
                numericKeySequence.append("^".repeat(y));
            if(x > 0)
                numericKeySequence.append(">".repeat(x));

            numericKeySequence.append('A');
        }

        return numericKeySequence.toString().toCharArray();
    }

    static long charSequenceLength(char[] x, int depth)
    {
        if(depth == 0) return 1;

        long result = 0;

        char prevKey = 'A';
        long temp;
        for(char key: x)
        {
            if(cache.containsKey(key) && cache.get(key).containsKey(prevKey) && cache.get(key).get(prevKey).containsKey(depth))
                temp = cache.get(key).get(prevKey).get(depth);
            else
            {
                temp = charSequenceLength(getDirectionalKeySequence(new char[]{key}, dirPad.get(prevKey)), depth - 1);

                if(cache.containsKey(key) && cache.get(key).containsKey(prevKey))
                    cache.get(key).get(prevKey).put(depth, temp);
                else
                {
                    HashMap<Integer, Long> penis3 = new HashMap<>();
                    penis3.put(depth, temp);

                    if(cache.containsKey(key))
                        cache.get(key).put(prevKey, penis3);
                    else
                    {
                        HashMap<Character, HashMap<Integer, Long>> penis2 = new HashMap<>();
                        penis2.put(prevKey, penis3);

                        cache.put(key, penis2);
                    }
                }
            }

            result += temp;
            prevKey = key;
        }
        return result;
    }
}
