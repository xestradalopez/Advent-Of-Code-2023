import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class day_21_2024
{
    static char[][] doorCodes;
    static HashMap<Character, int[]> numericKeypad = new HashMap<>();
    static HashMap<Character, int[]> directionalKeypad = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/example/21.txt"));
        parse(input);

        System.out.println(getDirectionalKeySequence("<Av<A>>^A".toCharArray()));
        System.out.println(getDirectionalKeySequence("v<<A>^A>A".toCharArray()));

        int partOne = partOne();
        int partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static int partOne()
    {
        int result = 0;

        for(char[] doorCode: doorCodes)
        {
            char[] a = getNumericKeySequence(doorCode);

            char[] b = getDirectionalKeySequence(a);

            char[] c = getDirectionalKeySequence(b);

            System.out.println(c.length + " * " + Integer.parseInt(new String(doorCode).substring(0, 3)));
            System.out.println(doorCode);
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);

            result += c.length * Integer.parseInt(new String(doorCode).substring(0, 3));
        }



        return result;
    }

    static int partTwo()
    {
        int result = 0;

        return result;
    }

    static void parse(Scanner input)
    {
        doorCodes = new char[5][4];

        for(int i = 0; input.hasNextLine(); i++)
            doorCodes[i] = input.nextLine().toCharArray();

        numericKeypad.put('A', new int[]{2, 0});
        numericKeypad.put('0', new int[]{1, 0});
        numericKeypad.put('1', new int[]{0, 1});
        numericKeypad.put('2', new int[]{1, 1});
        numericKeypad.put('3', new int[]{2, 1});
        numericKeypad.put('4', new int[]{0, 2});
        numericKeypad.put('5', new int[]{1, 2});
        numericKeypad.put('6', new int[]{2, 2});
        numericKeypad.put('7', new int[]{0, 3});
        numericKeypad.put('8', new int[]{1, 3});
        numericKeypad.put('9', new int[]{2, 3});

        directionalKeypad.put('A', new int[]{2, 1});
        directionalKeypad.put('<', new int[]{0, 0});
        directionalKeypad.put('v', new int[]{1, 0});
        directionalKeypad.put('>', new int[]{2, 0});
        directionalKeypad.put('^', new int[]{1, 1});
    }

    static char[] getDirectionalKeySequence(char[] keySequence)
    {
        StringBuilder directionalKeySequence = new StringBuilder();

        int[] pos = new int[]{2, 1};

        for(char key : keySequence)
        {
            int x = directionalKeypad.get(key)[0] - pos[0];
            int y = directionalKeypad.get(key)[1] - pos[1];

            pos = directionalKeypad.get(key);

            if(pos[0] == 0 && y == -1)
            {
                directionalKeySequence.append("v");
                directionalKeySequence.append("<".repeat(x * -1));
                directionalKeySequence.append('A');
                continue;
            }

            if(x < 0)
                directionalKeySequence.append("<".repeat(x * -1));
            if(y > 0)
                directionalKeySequence.append("^");
            if(x > 0)
                directionalKeySequence.append(">".repeat(x));
            if(y < 0)
                directionalKeySequence.append("v");


            directionalKeySequence.append('A');
        }

        return directionalKeySequence.toString().toCharArray();
    }

    static char[] getNumericKeySequence(char[] keySequence)
    {
        StringBuilder numericKeySequence = new StringBuilder();

        int[] pos = new int[]{2, 0};

        for(char key : keySequence)
        {
            int x = numericKeypad.get(key)[0] - pos[0];
            int y = numericKeypad.get(key)[1] - pos[1];

            pos = numericKeypad.get(key);

            if(pos[0] == 0 && pos[1] == y)
            {
                numericKeySequence.append("^".repeat(y));
                numericKeySequence.append("<".repeat((x * - 1)));
                numericKeySequence.append("A");
                continue;
            }

            if(x < 0)
                numericKeySequence.append("<".repeat(x * -1));
            if(x > 0)
                numericKeySequence.append(">".repeat(x));
            if(y < 0)
                numericKeySequence.append("v".repeat(y * -1));
            if(y > 0)
                numericKeySequence.append("^".repeat(y));

            numericKeySequence.append('A');
        }

        return numericKeySequence.toString().toCharArray();
    }
}
