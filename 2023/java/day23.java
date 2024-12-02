import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class day23
{
    static ArrayList<Integer> pathLengths = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2023/example/23.txt"));

        char[][] parsedInput = parsedInput(input);

        int partOne = partOne(parsedInput);
        int partTwo = 0;

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
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

    public static int partOne(char[][] input)
    {
        input[0][1] = '#';
        return partOne(input, new int[] {1,1}, 1);
    }

    public static int partOne(char[][] input, int[] location, int pathLength)
    {
        char[][] currentMap = copy2DArray(input);
        int[] currentlocation = Arrays.copyOf(location, location.length);

        while(!(currentlocation[0] == input.length-1 ||currentlocation[1] == input.length-2))
        {
            ArrayList<int[]> possibleLocations = evaluateSpace(currentlocation, currentMap);

            if (possibleLocations.size() == 1)
                currentlocation = possibleLocations.getFirst();
            //else
                
        }
        return -1;
    }

    public static ArrayList<int[]> evaluateSpace(int[] currentLocation, char[][] currentMap)
    {
        ArrayList<int[]> possibleLocations = new ArrayList<>();

        if(currentMap[currentLocation[0]+1][currentLocation[0]] != '#')
            possibleLocations.add(new int[]{currentLocation[0]+1, currentLocation[0]});
        if(currentMap[currentLocation[0]-1][currentLocation[0]] != '#')
            possibleLocations.add(new int[]{currentLocation[0]-1, currentLocation[0]});
        if(currentMap[currentLocation[0]][currentLocation[0]+1] != '#')
            possibleLocations.add(new int[]{currentLocation[0], currentLocation[0]+1});
        if(currentMap[currentLocation[0]][currentLocation[0]-1] != '#')
            possibleLocations.add(new int[]{currentLocation[0], currentLocation[0]-1});

        return possibleLocations;
    }

    public static char[][] copy2DArray(char[][] arr1)
    {
        char[][] arr2 = new char[arr1.length][];

        for (int i = 0; i < arr1.length; i++)
            arr2[i] = Arrays.copyOf(arr1[i], arr1[i].length);

        return arr2;
    }
}
