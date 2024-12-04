import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day_04_2024
{
    static char[][] wordSearch;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/04.txt"));
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
        for(int i = 0; i < wordSearch.length; i++)
            for(int j = 0; j < wordSearch[0].length; j++)
                result+= evaluate(i,j);
        return result;
    }

    public static int partTwo()
    {
        int result = 0;
        for(int i = 0; i < wordSearch.length; i++)
            for(int j = 0; j < wordSearch[0].length; j++)
                if(wordSearch[i][j] == 'A')
                    result += evaluate2(i, j);
        return result;
    }

    public static void parse(Scanner input)
    {
        ArrayList<char[]> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(input.nextLine().toCharArray());
        wordSearch = a.toArray(new char[0][0]);
    }

    public static char getChar(int i, int j)
    {
        if(i < 0 || j < 0 || i >= wordSearch.length || j >= wordSearch[0].length)
            return '.';
        else
            return wordSearch[i][j];
    }

    public static int evaluate(int i, int j)
    {

        char[] xmas = {'X','M','A','S'};
        int result = 0;
        for(int k = 0;  k < 4; k++)
            if(getChar(i + k, j) != xmas[k])
                break;
            else if(k == 3)
                result++;
        for(int k = 0;  k < 4; k++)
            if(getChar(i + k, j + k) != xmas[k])
                break;
            else if(k == 3)
                result++;
        for(int k = 0;  k < 4; k++)
            if(getChar(i - k, j) != xmas[k])
                break;
            else if(k == 3)
                result++;
        for(int k = 0;  k < 4; k++)
            if(getChar(i - k, j - k) != xmas[k])
                break;
            else if(k == 3)
                result++;
        for(int k = 0;  k < 4; k++)
            if(getChar(i, j + k) != xmas[k])
                break;
            else if(k == 3)
                result++;
        for(int k = 0;  k < 4; k++)
            if(getChar(i, j - k) != xmas[k])
                break;
            else if(k == 3)
                result++;
        for(int k = 0;  k < 4; k++)
            if(getChar(i - k, j + k) != xmas[k])
                break;
            else if(k == 3)
                result++;
        for(int k = 0;  k < 4; k++)
            if(getChar(i + k, j - k) != xmas[k])
                break;
            else if(k == 3)
                result++;
        return result;
    }

    public static int evaluate2(int i, int j)
    {
        if(((getChar(i+1,j+1) == 'M' && getChar(i-1,j-1) == 'S') || (getChar(i+1,j+1) == 'S' && getChar(i-1,j-1) == 'M')) && ((getChar(i-1,j+1) == 'M' && getChar(i+1,j-1) == 'S') || (getChar(i-1,j+1) == 'S' && getChar(i+1,j-1) == 'M')))
            return 1;
        return 0;
    }

}
