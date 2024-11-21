import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class day20
{

    static int lowPulses = 0;
    static int highPulses = 0;
    public static void main(String[] args)  throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2023/example/20.txt"));

        int partOne = partOne(input);
        int partTwo = 0;


        System.out.println(lowPulses);
        System.out.println(highPulses);
        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }
    public static int partOne(Scanner sc)
    {
        ArrayList<ArrayList<String>> input = parseInput(sc);
        ArrayList<String[]> instructions;
        ArrayList<Boolean> flipFlopStates = setFlipFlopStates(input);
        ArrayList<ArrayList<Boolean>> conjunctionPulses = setConjunctionPulses(input);


        for(int i = 0; i < 4; i++) //if I set this number to 2 it should be 128 but it isn't
        {
            instructions = findBroadcaster(input);
            System.out.println(i + ": ");
            while (!instructions.isEmpty()) {
                instructions.addAll(compute(instructions.get(0), input, flipFlopStates, conjunctionPulses));
                System.out.println("-"+instructions.get(0)[1]+"-> " + instructions.get(0)[0]);
                instructions.remove(0);
            }

            //System.out.println(flipFlopStates);
            //System.out.println(conjunctionPulses);

            resetPulses(conjunctionPulses);
        }
        return lowPulses * highPulses;

    }

    public static ArrayList<ArrayList<String>> parseInput(Scanner input)
    {
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        while(input.hasNextLine())
        {
            ArrayList<String> currentLine = new ArrayList<>();
            currentLine.add(input.next());
            input.next();
            currentLine.addAll(new ArrayList<>(Arrays.asList(input.nextLine().trim().split(", "))));
            result.add(currentLine);
        }

        return result;
    }

    public static ArrayList<String[]> findBroadcaster(ArrayList<ArrayList<String>> input)
    {
        ArrayList<String[]> result = new ArrayList<>();

        for (ArrayList<String> strings : input)
            if (strings.get(0).equals("broadcaster"))
                for (int j = 1; j < strings.size(); j++)
                    result.add(new String[]{strings.get(j), "low"});

        lowPulses += result.size();

        return result;
    }

    public static ArrayList<Boolean> setFlipFlopStates(ArrayList<ArrayList<String>> input)
    {
        ArrayList<Boolean> result = new ArrayList<>();

        for (ArrayList<String> strings : input)
            if (strings.get(0).charAt(0) == '%')
                result.add(false);
            else
                result.add(null);

        return result;
    }

    public static ArrayList<ArrayList<Boolean>> setConjunctionPulses(ArrayList<ArrayList<String>> input)
    {
        ArrayList<ArrayList<Boolean>> result = new ArrayList<>();

        for (ArrayList<String> strings : input)
            if (strings.get(0).charAt(0) == '&')
                result.add(new ArrayList<Boolean>());
            else
                result.add(null);

        return result;
    }

    public static ArrayList<String[]> compute(String[] signal, ArrayList<ArrayList<String>> input, ArrayList<Boolean> flipFlopStates, ArrayList<ArrayList<Boolean>> conjunctionPulses)
    {
        int index = 0;
        boolean operator = true; // true == flip-flop, false = conjunction;

        for(int i = 0; i < input.size(); i++)
            if(input.get(i).get(0).substring(1).equals(signal[0]))
            {
                index = i;
                operator = input.get(i).get(0).charAt(0) == '%';
                break;
            }

        if(operator)
            return computeFlipFlop(signal, input, flipFlopStates, index);
        else
            return computeConjunction(signal, input, conjunctionPulses, index);
    }

    public static ArrayList<String[]> computeFlipFlop(String[] signal, ArrayList<ArrayList<String>> input, ArrayList<Boolean> flipFlopStates, int index)
    {
        ArrayList<String[]> result = new ArrayList<>();

        String returnSignal; // false == low, true == high

        if(signal[1].equals("high")) return result;

        if(flipFlopStates.get(index))
            returnSignal = "low";
        else
            returnSignal = "high";

        flipFlopStates.set(index, !flipFlopStates.get(index));

        for(int i = 1; i < input.get(index).size(); i++)
            result.add(new String[]{input.get(index).get(i), returnSignal});

        if(returnSignal.equals("low"))
            lowPulses += result.size();
        else
            highPulses += result.size();

        return result;
    }

    public static ArrayList<String[]> computeConjunction(String[] signal, ArrayList<ArrayList<String>> input, ArrayList<ArrayList<Boolean>> conjunctionPulses, int index)
    {
        ArrayList<String[]> result = new ArrayList<>();

        String returnSignal; // false == low, true == high

        if(isAllHighPulses(conjunctionPulses.get(index)))
            returnSignal = "low";
        else
            returnSignal = "high";

        conjunctionPulses.get(index).add(signal[1].equals("high"));

        for(int i = 1; i < input.get(index).size(); i++)
            result.add(new String[]{input.get(index).get(i), returnSignal});

        if(returnSignal.equals("low"))
            lowPulses += result.size();
        else
            highPulses += result.size();

        return result;
    }

    public static boolean isAllHighPulses(ArrayList<Boolean> pulses)
    {
        for(Boolean pulse: pulses)
            if(!pulse)
                return false;
        return true;
    }

    public static void resetPulses(ArrayList<ArrayList<Boolean>> pulses)
    {
        for(int i = 0; i < pulses.size(); i++)
        {
            if(pulses.get(i) != null)
                pulses.set(i, new ArrayList<Boolean>(5));
        }
    }



}
