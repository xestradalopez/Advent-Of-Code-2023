import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day21
{
    static int[] sCoords = {0,0};
    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2023/input/01.txt"));

        //char[][] parsedInput =

        int partOne = 0;
        int partTwo = 0;

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    //public static char[][] parsedInput(Scanner input)
    {

    }
}
