import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class day_16_2024
{
    static char[][] map;
    static int[][] scores;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/example/16.txt"));
        parse(input);

        int partOne = partOne();
        int partTwo = partTwo();

        for(int[] a: scores)
        {
           System.out.println(Arrays.toString(a));
        }
        System.out.println();

        for(char[] a: map)
            System.out.println(a);

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static int partOne()
    {
        int result;

        result = getPathScore(new int[]{map.length - 2, 1, 0, 0}); //i, j, direction, score

        return result;
    }

    static int partTwo()
    {
        int result = 0;

        return result;
    }

    static void parse(Scanner input)
    {
        ArrayList<char[]> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(input.nextLine().toCharArray());
        map = a.toArray(new char[0][0]);
        scores = new int[map.length][map[0].length];
    }

    static int getPathScore(int[] startMove)
    {
        map[startMove[0]][startMove[1]] = '#';

        char[][] snapshot = copy2DArray(map);

        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(startMove);

        while(true)
        {
            int[] move = moves.getFirst();

            map[move[0]][move[1]] = '#';
/*

            for(char[] a: map)
                System.out.println(a);
            System.out.println();
*/

            if(move[0] == 1 && move[1] == map[0].length - 2)
                return scores[move[0]][move[1]];

            if(map[move[0]][move[1] + 1] != '#')
                if(1 + scores[move[0]][move[1]] + (move[2] == 0 ? 0 : 1000) < scores[move[0]][move[1] + 1] || scores[move[0]][move[1] + 1] == 0)
                {
                    scores[move[0]][move[1] + 1] = 1 + scores[move[0]][move[1]] + (move[2] == 0 ? 0 : 1000);
                    moves.add(new int[]{move[0], move[1] + 1, 0, 1 + scores[move[0]][move[1]] + (move[2] == 0 ? 0 : 1000)});
                }
                else
                    return - 1;
            if(map[move[0] - 1][move[1]] != '#')
                if(1 + scores[move[0]][move[1]] + (move[2] == 1 ? 0 : 1000) < scores[move[0] - 1][move[1]] || scores[move[0] - 1][move[1]] == 0)
                {
                    scores[move[0] - 1][move[1]] = 1 + scores[move[0]][move[1]] + (move[2] == 1 ? 0 : 1000);
                    moves.add(new int[]{move[0] - 1, move[1], 1, 1 + scores[move[0]][move[1]] + (move[2] == 1 ? 0 : 1000)});
                }
                else
                    return -1;
            if(map[move[0]][move[1] - 1] != '#')
                if(1 + scores[move[0]][move[1]] + (move[2] == 2 ? 0 : 1000) < scores[move[0]][move[1] - 1] || scores[move[0]][move[1] - 1] == 0)
                {
                    scores[move[0]][move[1] - 1] = 1 + scores[move[0]][move[1]] + (move[2] == 2 ? 0 : 1000);
                    moves.add(new int[]{move[0], move[1] - 1, 2, 1 + scores[move[0]][move[1]] + (move[2] == 2 ? 0 : 1000)});
                }
                else
                    return -1;
            if(map[move[0] + 1][move[1]] != '#')
                if(1 + scores[move[0]][move[1]] + (move[2] == 3 ? 0 : 1000) < scores[move[0] + 1][move[1]] || scores[move[0] + 1][move[1]] == 0)
                {
                    scores[move[0] + 1][move[1]] = 1 + scores[move[0]][move[1]] + (move[2] == 3 ? 0 : 1000);
                    moves.add(new int[]{move[0] + 1, move[1], 3, 1 + scores[move[0]][move[1]] + (move[2] == 3 ? 0 : 1000)});
                }
                else
                    return -1;

            moves.removeFirst();

            if(moves.isEmpty())
            {
                //map = snapshot;
                return -1;
            }
            else if(moves.size() > 1)
            {
                ArrayList<Integer> pathLengths = new ArrayList<>();

                for(int[] branch: moves)
                {
                    int branchScore = getPathScore(branch);
                    if(branchScore != -1) pathLengths.add(branchScore);
                    map = snapshot;
                }

                return pathLengths.isEmpty() ? -1 : Collections.min(pathLengths);
            }
        }
    }

    public static char[][] copy2DArray(char[][] arr1)
    {
        char[][] arr2 = new char[arr1.length][];

        for (int i = 0; i < arr1.length; i++)
            arr2[i] = Arrays.copyOf(arr1[i], arr1[i].length);

        return arr2;
    }
}
