import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_05_2024
{
    static int[][] pageOrderingRules;
    static int[][] updates;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/05.txt"));
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
        what: for(int i = 0; i < updates.length; i++)
        {
            for(int j = 0; j < pageOrderingRules.length; j++)
            {
                ArrayList<Integer> why = new ArrayList<>();
                for(int k = 0; k < updates[i].length; k++)
                    if (updates[i][k] == pageOrderingRules[j][1] || updates[i][k] == pageOrderingRules[j][0])
                        why.add(updates[i][k]);
                //System.out.println(why);
                if(why.size() == 2 && why.getFirst() == pageOrderingRules[j][1])
                    continue what;
            }
            //System.out.println(updates[i][updates[i].length / 2]);
            result += updates[i][updates[i].length / 2];
        }

        return result;
    }

    public static int partTwo()
    {
        int result = 0;
        what: for(int i = 0; i < updates.length; i++)
        {
            boolean isCool = false;
            ArrayList<Integer> crazy = new ArrayList<>();
            for(int j = 0; j < pageOrderingRules.length; j++)
            {
                ArrayList<Integer> why = new ArrayList<>();
                for(int k = 0; k < updates[i].length; k++)
                {
                    if (updates[i][k] == pageOrderingRules[j][1] || updates[i][k] == pageOrderingRules[j][0])
                    {
                        if(why.isEmpty())
                            crazy.add(j);
                        why.add(updates[i][k]);
                    }
                }

                if(why.size() == 2 && why.getFirst() == pageOrderingRules[j][1])
                    isCool = true;
            }
            if(!isCool)
                continue;
            while(isCool)
            {
                isCool = false;
                for(int j = 0; j < crazy.size();j++)
                {
                    int index = 0;
                    int jndex = 0;
                    ArrayList<Integer> why = new ArrayList<>();
                    for(int k = 0; k < updates[i].length; k++)
                    {

                        if (updates[i][k] == pageOrderingRules[crazy.get(j)][1])
                        {
                            index = k;
                            why.add(updates[i][k]);
                        } else if(updates[i][k] == pageOrderingRules[crazy.get(j)][0])
                        {
                            jndex = k;
                            why.add(updates[i][k]);
                        }
                    }
                    if(why.size() == 2 && why.getFirst() == pageOrderingRules[crazy.get(j)][1])
                    {
                        isCool = true;
                        int temp = updates[i][index];
                        updates[i][index] = updates[i][jndex];
                        updates[i][jndex] = temp;
                    }
                }
            }
            result += updates[i][updates[i].length / 2];
        }
        return result;
    }

    public static void parse(Scanner input)
    {
        ArrayList<int[]> a = new ArrayList<>();
        ArrayList<int[]> b = new ArrayList<>();
        while(input.hasNextLine())
        {
            String currentLine = input.nextLine();
            if(currentLine.isEmpty())
                break;
            a.add(Arrays.stream(currentLine.split("\\|")).mapToInt(Integer::parseInt).toArray());
        }

        while(input.hasNextLine())
            b.add(Arrays.stream(input.nextLine().split(",")).mapToInt(Integer::parseInt).toArray());

        pageOrderingRules = a.toArray(new int[0][0]);
        updates = b.toArray(new int[0][0]);
    }

}
