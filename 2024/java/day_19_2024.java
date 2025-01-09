import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day_19_2024
{
    static String[] towelPatterns;
    static String[] desiredDesigns;
    static HashMap<String, Long> memo;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/19.txt"));
        parse(input);

        System.out.println("Part One: " + partOne());
        System.out.println("Part Two: " + partTwo());

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static int partOne()
    {
        int result = 0;

        for(String desiredDesign : desiredDesigns)
            if(isPossible(desiredDesign)) result++;

        return result;
    }

    static long partTwo()
    {
        long result = 0;

        for(String desiredDesign : desiredDesigns)
            result += getPermutations(desiredDesign);

        return result;
    }

    static void parse(Scanner input)
    {
        towelPatterns = input.nextLine().split(", ");

        input.nextLine();

        ArrayList<String> a = new ArrayList<>();

        while(input.hasNextLine())
            a.add(input.nextLine());

        desiredDesigns = a.toArray(new String[0]);
        memo = new HashMap<>();
    }

    static boolean isPossible(String desiredDesign)
    {
        if(desiredDesign.isEmpty()) return true;

        for(String towelPattern : towelPatterns)
            if(desiredDesign.indexOf(towelPattern) == 0 && isPossible(desiredDesign.substring(towelPattern.length()))) return true;

        return false;
    }

    static long getPermutations(String desiredDesign)
    {
        if(desiredDesign.isEmpty()) return 1;

        long result = 0;

        for(String towelPattern : towelPatterns)
            if(desiredDesign.indexOf(towelPattern) == 0)
                if(memo.containsKey(desiredDesign.substring(towelPattern.length())))
                    result += memo.get(desiredDesign.substring(towelPattern.length()));
                else
                {
                    long permuations = getPermutations(desiredDesign.substring(towelPattern.length()));
                    memo.put(desiredDesign.substring(towelPattern.length()), permuations);
                    result += permuations;
                }

        return result;
    }
}
