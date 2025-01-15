import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_18_2024
{
    static int[][] byteCoords;
    static char[][] memorySpace;
    static final int MEMORY_SPACE_SIZE = 71;
    static final int FIRST_N_BYTES = 1024;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/18.txt"));
        parse(input);

        int partOne = partOne();
        String partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static int partOne()
    {
        int result;

        for(int i = 0; i < FIRST_N_BYTES; i++)
            memorySpace[byteCoords[i][1] + 1][byteCoords[i][0] + 1] = '#';

        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{1, 1});

        a: for(int i = 0; true; i++)
        {
            ArrayList<int[]> newMoves = new ArrayList<>();

            while(!moves.isEmpty())
            {
                if(memorySpace[moves.getFirst()[0]][moves.getFirst()[1]] == 'O')
                {
                    moves.removeFirst();
                    continue;
                }

                if (moves.getFirst()[0] == MEMORY_SPACE_SIZE && moves.getFirst()[1] == MEMORY_SPACE_SIZE)
                {
                    result = i;
                    break a;
                }

                if (memorySpace[moves.getFirst()[0]][moves.getFirst()[1] + 1] == '.')
                    newMoves.add(new int[]{moves.getFirst()[0], moves.getFirst()[1] + 1});
                if (memorySpace[moves.getFirst()[0] - 1][moves.getFirst()[1]] == '.')
                    newMoves.add(new int[]{moves.getFirst()[0] - 1, moves.getFirst()[1]});
                if (memorySpace[moves.getFirst()[0]][moves.getFirst()[1] - 1] == '.')
                    newMoves.add(new int[]{moves.getFirst()[0], moves.getFirst()[1] - 1});
                if (memorySpace[moves.getFirst()[0] + 1][moves.getFirst()[1]] == '.')
                    newMoves.add(new int[]{moves.getFirst()[0] + 1, moves.getFirst()[1]});

                memorySpace[moves.getFirst()[0]][moves.getFirst()[1]] = 'O';

                moves.removeFirst();
            }

            moves.addAll(newMoves);
        }

        return result;
    }

    static String partTwo()
    {
        String result = "";

        a: for(int i = FIRST_N_BYTES; i < byteCoords.length; i++)
        {
            memorySpace[byteCoords[i][1] + 1][byteCoords[i][0] + 1] = '#';

            ArrayList<int[]> moves = new ArrayList<>();
            moves.add(new int[]{MEMORY_SPACE_SIZE, MEMORY_SPACE_SIZE});

            while(true)
            {
                ArrayList<int[]> newMoves = new ArrayList<>();

                while(!moves.isEmpty())
                {
                    if(memorySpace[moves.getFirst()[0]][moves.getFirst()[1]] == i)
                    {
                        moves.removeFirst();
                        continue;
                    }

                    if (moves.getFirst()[0] == 1 && moves.getFirst()[1] == 1)
                        continue a;

                    if (memorySpace[moves.getFirst()[0]][moves.getFirst()[1] + 1] != '#' && memorySpace[moves.getFirst()[0]][moves.getFirst()[1] + 1] != i)
                        newMoves.add(new int[]{moves.getFirst()[0], moves.getFirst()[1] + 1});
                    if (memorySpace[moves.getFirst()[0] - 1][moves.getFirst()[1]] != '#' && memorySpace[moves.getFirst()[0] - 1][moves.getFirst()[1]] != i)
                        newMoves.add(new int[]{moves.getFirst()[0] - 1, moves.getFirst()[1]});
                    if (memorySpace[moves.getFirst()[0]][moves.getFirst()[1] - 1] != '#' && memorySpace[moves.getFirst()[0]][moves.getFirst()[1] - 1] != i)
                        newMoves.add(new int[]{moves.getFirst()[0], moves.getFirst()[1] - 1});
                    if (memorySpace[moves.getFirst()[0] + 1][moves.getFirst()[1]] != '#' && memorySpace[moves.getFirst()[0] + 1][moves.getFirst()[1]] != i)
                        newMoves.add(new int[]{moves.getFirst()[0] + 1, moves.getFirst()[1]});

                    memorySpace[moves.getFirst()[0]][moves.getFirst()[1]] = (char) i;

                    moves.removeFirst();
                }

                moves.addAll(newMoves);

                if(moves.isEmpty())
                {
                    result = byteCoords[i][0] + "," + byteCoords[i][1];
                    break a;
                }
            }
        }

        return result;
    }

    static void parse(Scanner input)
    {
        ArrayList<int[]> a = new ArrayList<>();

        while(input.hasNextLine())
            a.add(Arrays.stream(input.nextLine().split(",")).mapToInt(Integer::parseInt).toArray());

        byteCoords = a.toArray(new int[0][0]);

        memorySpace = new char[MEMORY_SPACE_SIZE + 2][MEMORY_SPACE_SIZE + 2];

        for(int i = 0; i < MEMORY_SPACE_SIZE + 2; i++)
            for(int j = 0; j < MEMORY_SPACE_SIZE + 2; j++)
                if(i == 0 || i == MEMORY_SPACE_SIZE + 1 || j == 0 || j == MEMORY_SPACE_SIZE + 1)
                    memorySpace[i][j] = '#';
                else
                    memorySpace[i][j] = '.';
    }
}
