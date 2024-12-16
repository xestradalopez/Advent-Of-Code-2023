import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_15_2024
{
    static char[][] warehouseMap;
    static char[][] wideWarehouseMap;
    static char[] attemptedMoves;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/example/15.txt"));
        parse(input);

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

        int[] pos = new int[]{warehouseMap.length / 2 - 1, warehouseMap.length / 2 - 1};
        for(char attemptedMove: attemptedMoves)
        {
            warehouseMap[pos[0]][pos[1]] = '.';
            pos = attemptMove(attemptedMove, pos[0], pos[1]);
            warehouseMap[pos[0]][pos[1]] = '@';
        }

        for(int i = 0; i < warehouseMap.length; i++)
        {
            for(int j = 0; j < warehouseMap[i].length; j++)
                if(warehouseMap[i][j] == 'O')
                    result += i * 100 + j;
        }

        return result;
    }

    static int partTwo()
    {
        int result = 0;

        int[] pos = new int[]{warehouseMap.length / 2 - 1, warehouseMap.length - 2};
        for(char attemptedMove: attemptedMoves)
        {
            for(char[] a: wideWarehouseMap)
            {
                for(char b: a)
                    System.out.print(b);
                System.out.println();
            }
            System.out.println(attemptedMove);

            wideWarehouseMap[pos[0]][pos[1]] = '.';
            pos = attemptMove(attemptedMove, pos[0], pos[1]);
            wideWarehouseMap[pos[0]][pos[1]] = '@';
        }

        return result;
    }

    static void parse(Scanner input)
    {
        ArrayList<char[]> a = new ArrayList<>();
        StringBuilder b = new StringBuilder();

        while(true)
        {
            String currentLine = input.nextLine();
            if(currentLine.isEmpty())
                break;
            a.add(currentLine.toCharArray());
        }

        while(input.hasNextLine())
            b.append(input.nextLine());

        warehouseMap = a.toArray(new char[0][0]);
        attemptedMoves = b.toString().toCharArray();

        wideWarehouseMap = new char[warehouseMap.length][warehouseMap[0].length * 2];

        for(int i = 0; i < warehouseMap.length; i++)
        {
            for(int j = 0; j < warehouseMap[i].length; j++)
            {
                if(warehouseMap[i][j] == '#')
                {
                    wideWarehouseMap[i][j * 2] = '#';
                    wideWarehouseMap[i][j * 2 + 1] = '#';
                }
                else if(warehouseMap[i][j] == 'O')
                {
                    wideWarehouseMap[i][j * 2] = '[';
                    wideWarehouseMap[i][j * 2 + 1] = ']';
                }
                else if(warehouseMap[i][j] == '.')
                {
                    wideWarehouseMap[i][j * 2] = '.';
                    wideWarehouseMap[i][j * 2 + 1] = '.';
                }
                else if(warehouseMap[i][j] == '@')
                {
                    wideWarehouseMap[i][j * 2] = '@';
                    wideWarehouseMap[i][j * 2 + 1] = '.';
                }

            }
        }
    }

    static int[] attemptMove(char direction, int i, int j)
    {
        if(direction == '>')
        {
            if(warehouseMap[i][j + 1] == '#')
                return new int[]{i, j};
            if(warehouseMap[i][j + 1] == '.')
                return new int[]{i, j + 1};
            if(warehouseMap[i][j + 1] == 'O' || warehouseMap[i][j + 1] == '[' || warehouseMap[i][j + 1] == ']')
            {
                int[] newPos = attemptMove(direction, i, j + 1);
                warehouseMap[newPos[0]][newPos[1]] = warehouseMap[i][j + 1];
                return new int[]{newPos[0], newPos[1] - 1};
            }

        }

        if(direction == '^')
        {
            if(warehouseMap[i - 1][j] == '#')
                return new int[]{i, j};
            if(warehouseMap[i - 1][j] == '.')
                return new int[]{i - 1, j};
            if(warehouseMap[i - 1][j] == 'O')
            {
                int[] newPos = attemptMove(direction, i - 1, j);
                warehouseMap[newPos[0]][newPos[1]] = 'O';
                return new int[]{newPos[0] + 1, newPos[1]};
            }
        }

        if(direction == '<')
        {
            if(warehouseMap[i][j - 1] == '#')
                return new int[]{i, j};
            if(warehouseMap[i][j - 1] == '.')
                return new int[]{i, j - 1};
            if(warehouseMap[i][j - 1] == 'O' || warehouseMap[i][j - 1] == '[' || warehouseMap[i][j - 1] == ']')
            {
                int[] newPos = attemptMove(direction, i, j - 1);
                warehouseMap[newPos[0]][newPos[1]] = warehouseMap[i][j - 1];
                return new int[]{newPos[0], newPos[1] + 1};
            }
        }

        if(direction == 'v')
        {
            if(warehouseMap[i + 1][j] == '#')
                return new int[]{i, j};
            if(warehouseMap[i + 1][j] == '.')
                return new int[]{i + 1, j};
            if(warehouseMap[i + 1][j] == 'O')
            {
                int[] newPos = attemptMove(direction, i + 1, j);
                warehouseMap[newPos[0]][newPos[1]] = 'O';
                return new int[]{newPos[0] - 1, newPos[1]};
            }

        }

        return new int[]{i, j};
    }
}
