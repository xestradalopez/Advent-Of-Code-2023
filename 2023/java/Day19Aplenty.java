import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
public class Day19Aplenty
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2023/input/19.txt"));

        double start = System.nanoTime();

        HashMap<Integer, String[]> workflows = workflows(input);
        int[][] parts = parts(input);

        int partOne = partOne(workflows,parts);

        System.out.println("Part One: " + partOne);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static HashMap<Integer, String[]> workflows(Scanner input)
    {
        HashMap<Integer, String[]> test = new HashMap<>();
        while(true)
        {
            String currentLine = input.nextLine();
            if(currentLine.isEmpty())
                break;

            test.put(currentLine.substring(0,currentLine.indexOf('{')).hashCode(), parseWorkflow(currentLine.substring(currentLine.indexOf('{') + 1, currentLine.indexOf('}'))));
        }
        return test;
    }

    public static String[] parseWorkflow(String a)
    {
        String str = a + ",";
        byte count = (byte)(str.chars().filter(ch -> ch == ',').count());
        String[] result = new String[count];

        for(int i = 0; i < count; i ++)
        {
            result[i] = str.substring(0, str.indexOf(','));
            str = str.substring(str.indexOf(',') + 1);
        }

        return result;
    }

    public static int[][] parts(Scanner input)
    {
        int[][] result = new int[200][4];
        String currentLine;

        for(int i = 0; i < result.length; i++)
        {
            currentLine = input.nextLine();
            currentLine = currentLine.substring(1, currentLine.length()-1) + ",";
            for(int j = 0; j < result[i].length; j++)
            {
                result[i][j] = Integer.parseInt(currentLine.substring(currentLine.indexOf('=') + 1, currentLine.indexOf(',')));
                currentLine = currentLine.substring(currentLine.indexOf(',') + 1);
            }
        }
        return result;
    }

    public static int partOne(HashMap<Integer, String[]> a, int[][] b)
    {
        String currentString;
        int result = 0;
        for (int[] ints : b) {
            currentString = evaluate(a.get("in".hashCode()), ints);

            while (currentString.length() > 1) {
                currentString = evaluate(a.get(currentString.hashCode()), ints);
            }
            if (currentString.equals("A"))
                result += ints[0] + ints[1] + ints[2] + ints[3];
        }
        return result;
    }

    public static String evaluate(String[] a, int[] b)
    {
        HashMap<String, Integer> test = new HashMap<>();
        test.put("x",0);
        test.put("m",1);
        test.put("a",2);
        test.put("s",3);

        for(int i = 0; i < a.length - 1; i++)
        {
            int a1 = b[test.get(a[i].substring(0,1))];
            int a2 = Integer.parseInt(a[i].substring(2, a[i].indexOf(':')));
            if(a[i].charAt(1)=='>'?a1>a2:a1<a2) //I apologize for my sins
                return a[i].substring(a[i].indexOf(':') + 1);
        }
        return a[a.length-1];
    }
}
