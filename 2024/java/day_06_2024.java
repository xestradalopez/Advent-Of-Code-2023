import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_06_2024
{
    static char[][] map;
    static char[][] map2;
    static int[] position;
    static int[] start;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/06.txt"));
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
        int result = 1;
        int direction = 1;
        map[position[0]][position[1]] = 'X';
        while(isInbounds())
        {
            if(map[position[0]][position[1]] == '.')
            {
                map[position[0]][position[1]] = 'X';
                result++;
            }

            switch (direction)
            {
                case 0:
                    position[1]++;
                    if(isInbounds())
                        if(map[position[0]][position[1]] == '#')
                        {
                            position[0]++;
                            position[1]--;
                            direction = 3;
                        }
                    break;
                case 1:
                    position[0]--;
                    if(isInbounds())
                        if(map[position[0]][position[1]] == '#')
                        {
                            position[0]++;
                            position[1]++;
                            direction = 0;
                        }
                    break;
                case 2:
                    position[1]--;
                    if(isInbounds())
                        if(map[position[0]][position[1]] == '#')
                        {
                            position[0]--;
                            position[1]++;
                            direction = 1;
                        }
                    break;
                case 3:
                    position[0]++;
                    if(isInbounds())
                        if(map[position[0]][position[1]] == '#')
                        {
                            position[0]--;
                            position[1]--;
                            direction = 2;
                        }
                    break;
            }
        }
        return result;
    }

    public static int partTwo()
    {
        int result = 0;
        for(int i = 0; i < map2.length; i++)
        {
            que: for(int j = 0; j < map2[i].length; j++)
            {
                char[][] currentMap = copy2DArray(map2);
                int[] pos = Arrays.copyOf(start, 2);
                int direction = 1;
                if(currentMap[i][j] == '#' || currentMap[i][j] == '^')
                    continue;
                currentMap[i][j] = '#';
                currentMap[pos[0]][pos[1]] = '.';

                while(true)
                {
                    if(currentMap[pos[0]][pos[1]] == '2')
                    {
                        result ++;
                        break;
                    }
                    sin: while(true)
                    {
                        switch (direction)
                        {
                            case 0:
                                if(!isInbounds2(pos[0], pos[1] + 1))
                                    continue que;
                                if(currentMap[pos[0]][pos[1] + 1] == '#')
                                {
                                    direction = 3;
                                    continue;
                                }
                                currentMap[pos[0]][pos[1]]++;
                                pos[1]++;
                                break sin;
                            case 1:
                                if(!isInbounds2(pos[0] - 1, pos[1]))
                                    continue que;
                                if(currentMap[pos[0] - 1][pos[1]] == '#')
                                {
                                    direction = 0;
                                    continue;
                                }
                                currentMap[pos[0]][pos[1]]++;
                                pos[0]--;
                                break sin;
                            case 2:
                                if(!isInbounds2(pos[0], pos[1] - 1))
                                    continue que;
                                if(currentMap[pos[0]][pos[1] - 1] == '#')
                                {
                                    direction = 1;
                                    continue;
                                }
                                currentMap[pos[0]][pos[1]]++;
                                pos[1]--;
                                break sin;
                            case 3:
                                if(!isInbounds2(pos[0] + 1, pos[1]))
                                    continue que;
                                if(currentMap[pos[0] + 1][pos[1]] == '#')
                                {
                                    direction = 2;
                                    continue;
                                }
                                currentMap[pos[0]][pos[1]]++;
                                pos[0]++;
                                break sin;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void parse(Scanner input)
    {
        ArrayList<char[]> a = new ArrayList<>();
        while(input.hasNextLine())
        {
            String currentLine = input.nextLine();
            if(currentLine.indexOf('^') != -1)
                position = new int[]{a.size(), currentLine.indexOf('^')};
            a.add(currentLine.toCharArray());
        }

        map = a.toArray(new char[0][0]);
        map2 = copy2DArray(map);
        start = Arrays.copyOf(position, position.length);
    }

    public static boolean isInbounds()
    {
        return position[0] >= 0 && position[1] >= 0 && position[0] < map.length && position[1] < map[0].length;
    }

    public static boolean isInbounds2(int i, int j)
    {
        return i >= 0 && j >= 0 && i < map.length && j < map[0].length;
    }

    public static char[][] copy2DArray(char[][] arr1)
    {
        char[][] arr2 = new char[arr1.length][];

        for (int i = 0; i < arr1.length; i++)
            arr2[i] = Arrays.copyOf(arr1[i], arr1[i].length);

        return arr2;
    }
}
