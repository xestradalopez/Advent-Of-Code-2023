import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_12_2024
{
    static char[][] plots;
    static int[][] plotAreas;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/01.txt"));
        parse(input);

        int partOne = partOne();
        int partTwo = partTwo();

        for(int i = 0; i < plotAreas.length; i++)
            System.out.println(Arrays.toString(plotAreas[i]));

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static int partOne()
    {
        int result = 0;
        for(int i = 0; i < plots.length; i++)
            for(int j = 0; j < plots[i].length; j++)
            {
                if(plotAreas[i][j] == -1)
                    continue;

                ArrayList<int[]> pos = new ArrayList<>();
                pos.add(new int[] {i, j});

                char chr = plots[i][j];

                for (int[] po : pos)
                {
                    if (isInbounds(po[0], po[1] + 1) && plots[po[0]][po[1] + 1] == chr && plotAreas[po[0]][po[1] + 1] != -1)
                        pos.add(new int[]{po[0], po[1] + 1});
                    if (isInbounds(po[0] - 1, po[1]) && plots[po[0] - 1][po[1]] == chr && plotAreas[po[0] - 1][po[1]] != -1)
                        pos.add(new int[]{po[0] - 1, po[1]});
                    if (isInbounds(po[0], po[1] - 1) && plots[po[0]][po[1] - 1] == chr && plotAreas[po[0]][po[1] - 1] != -1)
                        pos.add(new int[]{po[0], po[1] - 1});
                    if (isInbounds(po[0] + 1, po[1]) && plots[po[0] + 1][po[1]] == chr && plotAreas[po[0] + 1][po[1]] != -1)
                        pos.add(new int[]{po[0] + 1, po[1]});

                    plotAreas[po[0]][po[1]] = -1;
                }
                int plotArea = pos.size();
                for (int[] po : pos) {
                    plotAreas[po[0]][po[1]] = plotArea;
                }
            }

        return result;
    }

    public static int partTwo()
    {
        int result = 0;

        return result;
    }

    public static void parse(Scanner input)
    {
        ArrayList<char[]> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(input.nextLine().toCharArray());
        plots = a.toArray(new char[0][0]);
        plotAreas = new int[plots.length][plots[0].length];
    }

    static boolean isInbounds(int i, int j)
    {
        return i >= 0 && j >= 0 && i < plots.length && j < plots[i].length;
    }
}
