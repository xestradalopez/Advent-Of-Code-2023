import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day_12_2024
{
    static char[][] gardenPlots;
    static int[][] plotAreas;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/12.txt"));
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

        for(int i = 0; i < gardenPlots.length; i++)
            for(int j = 0; j < gardenPlots[i].length; j++)
            {
                int sides = 0;

                if (!isInbounds(i, j + 1) || gardenPlots[i][j + 1] != gardenPlots[i][j])
                    sides++;
                if (!isInbounds(i - 1, j) || gardenPlots[i - 1][j] != gardenPlots[i][j])
                    sides++;
                if (!isInbounds(i, j - 1) || gardenPlots[i][j - 1] != gardenPlots[i][j])
                    sides++;
                if (!isInbounds(i + 1, j) || gardenPlots[i + 1][j] != gardenPlots[i][j])
                    sides++;

                result += plotAreas[i][j] * sides;
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
        gardenPlots = a.toArray(new char[0][0]);
        plotAreas = new int[gardenPlots.length][gardenPlots[0].length];

        for(int i = 0; i < gardenPlots.length; i++)
            for(int j = 0; j < gardenPlots[i].length; j++)
            {
                if(plotAreas[i][j] == -1)
                    continue;

                ArrayList<int[]> pos = new ArrayList<>();
                pos.add(new int[] {i, j});

                char chr = gardenPlots[i][j];

                for(int k = 0; k < pos.size(); k++)
                {
                    int[] po = pos.get(k);

                    plotAreas[po[0]][po[1]] = -1;

                    if (isInbounds(po[0], po[1] + 1) && gardenPlots[po[0]][po[1] + 1] == chr && plotAreas[po[0]][po[1] + 1] != -1) {
                        pos.add(new int[]{po[0], po[1] + 1});
                        plotAreas[po[0]][po[1] + 1] = -1;
                    }
                    if (isInbounds(po[0] - 1, po[1]) && gardenPlots[po[0] - 1][po[1]] == chr && plotAreas[po[0] - 1][po[1]] != -1) {
                        pos.add(new int[]{po[0] - 1, po[1]});
                        plotAreas[po[0] - 1][po[1]] = -1;
                    }
                    if (isInbounds(po[0], po[1] - 1) && gardenPlots[po[0]][po[1] - 1] == chr && plotAreas[po[0]][po[1] - 1] != -1) {
                        pos.add(new int[]{po[0], po[1] - 1});
                        plotAreas[po[0]][po[1] - 1] = -1;
                    }
                    if (isInbounds(po[0] + 1, po[1]) && gardenPlots[po[0] + 1][po[1]] == chr && plotAreas[po[0] + 1][po[1]] != -1) {
                        pos.add(new int[]{po[0] + 1, po[1]});
                        plotAreas[po[0] + 1][po[1]] = -1;
                    }
                }
                int plotArea = pos.size();
                for (int[] po : pos) {
                    plotAreas[po[0]][po[1]] = plotArea;
                }
            }
    }

    static boolean isInbounds(int i, int j)
    {
        return i >= 0 && j >= 0 && i < gardenPlots.length && j < gardenPlots[i].length;
    }
}
