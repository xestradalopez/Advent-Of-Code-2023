import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day21
{
    final static int STEPS = 64;
    static int places = 1;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2023/input/21.txt"));

        char[][] parsedInput = parsedInput(input);

        int partOne = partOne(parsedInput);
        int partTwo = 0;

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static int partOne(char[][] input)
    {
        ArrayList<int[]> instructions = new ArrayList<>();
        instructions.add(new int[]{input.length/2,input.length/2}); // The S is always in the center

        for(int i = 0; i < STEPS; i++)
        {
            ArrayList<int[]> newInstructions = new ArrayList<>();
            while(!instructions.isEmpty())
            {
                newInstructions.addAll(compute(instructions.getFirst(), input));
                instructions.removeFirst();
            }
            instructions = newInstructions;
        }
        return places;
    }

    public static char[][] parsedInput(Scanner input)
    {
        ArrayList<char[]> result = new ArrayList<>();

        for(int i = 0; input.hasNextLine(); i++)
        {
            String currentLine = input.nextLine();
            char[] currentList = new char[currentLine.length()];
            for(int j = 0; j < currentLine.length(); j++)
            {
                currentList[j] = currentLine.charAt(j);
            }
            result.add(currentList);
        }

        return result.toArray(new char[0][0]);
    }

    public static ArrayList<int[]> compute(int[] coords, char[][] input)
    {
        ArrayList<int[]> result = new ArrayList<>();

        if(input[coords[0]+1][coords[1]] == '.')
        {
            input[coords[0] + 1][coords[1]] = 'O';
            result.add(new int[] {coords[0]+1, coords[1]});
            places++;
        }
        if(input[coords[0]-1][coords[1]] == '.')
        {
            input[coords[0] - 1][coords[1]] = 'O';
            result.add(new int[] {coords[0]-1, coords[1]});
            places++;
        }
        if(input[coords[0]][coords[1]+1] == '.')
        {
            input[coords[0]][coords[1]+1] = 'O';
            result.add(new int[] {coords[0], coords[1]+1});
            places++;
        }
        if(input[coords[0]][coords[1]-1] == '.')
        {
            input[coords[0]][coords[1]-1] = 'O';
            result.add(new int[] {coords[0], coords[1]-1});
            places++;
        }
        input[coords[0]][coords[1]] = '.';
        places--;
        return result;
    }


}
