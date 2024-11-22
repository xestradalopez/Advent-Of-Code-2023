import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day20
{
    static int lowPulses = 0;
    static int highPulses = 0;

    public static void main(String[] args)  throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2023/input/20.txt"));

        int partOne = partOne(input);
        int partTwo = 0;

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static int partOne(Scanner sc)
    {
        String[][] input = parseInput(sc); // {{Source Module 1, Destination Module 1, ...}, ...}
        Object[] lookupTable = createLookupTable(input);
        ArrayList<String[]> instructions; // {[signal Destination 1, signal, Signal Source], ...}
        // {Flip-Flop Module 1 State, ...} false = Off | true = On                          // For both of these they are at the same index as their corresponding
        // {{input Module 1, Input Module Most Recent Signal}, ...} false = Off | true = On // module in input, all other spaces are filled with null

        for(int i = 0; i < 1000; i++)
        {
            lowPulses++;
            instructions = findBroadcaster(input);
            while (!instructions.isEmpty())
            {
                instructions.addAll(compute(instructions.getFirst(), input, lookupTable));
                instructions.removeFirst();
            }
        }

        return lowPulses * highPulses;
    }

    public static String[][] parseInput(Scanner input)
    {
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        while(input.hasNextLine())
        {
            ArrayList<String> currentLine = new ArrayList<>();
            currentLine.add(input.next());
            input.next();
            currentLine.addAll(new ArrayList<>(java.util.Arrays.asList(input.nextLine().trim().split(", "))));
            result.add(currentLine);
        }

        return result.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
    }

    public static Object[] createLookupTable(String[][] input)
    {
        ArrayList<Object> result = new ArrayList<>();

        for (String[] strings : input)
            if (strings[0].charAt(0) == '%')
                result.add(false);
            else if(strings[0].charAt(0) == '&')
            {
                String currentValue = strings[0].substring(1);

                HashMap<String, Boolean> inputPulses = new HashMap<>();

                //God is dead
                for (String[] currentLine : input)
                    for (int k = 1; k < currentLine.length; k++)
                        if (currentLine[k].equals(currentValue))
                            inputPulses.put(currentLine[0].substring(1), false);

                result.add(inputPulses);
            }
            else
                result.add(null);

        return result.toArray();
    }

    public static ArrayList<String[]> findBroadcaster(String[][] input)
    {
        ArrayList<String[]> result = new ArrayList<>();

        for (String[] currentLine : input)
            if (currentLine[0].equals("broadcaster"))
                for (int j = 1; j < currentLine.length; j++)
                    result.add(new String[]{currentLine[j], "low", "broadcaster"});

        lowPulses += result.size();

        return result;
    }

    public static ArrayList<String[]> compute(String[] signal, String[][] input, Object[] lookupTable)
    {
        int index = 0;
        boolean operator = true; // true == flip-flop, false == conjunction;

        for(int i = 0; i < input.length; i++)
            if(input[i][0].substring(1).equals(signal[0]))
            {
                index = i;
                operator = input[i][0].charAt(0) == '%';
                break;
            }

        if(operator)
            return computeFlipFlop(signal, input, lookupTable, index);
        else
            return computeConjunction(signal, input, lookupTable, index);
    }

    public static ArrayList<String[]> computeFlipFlop(String[] signal, String[][] input, Object[] flipFlopStates, int index)
    {
        ArrayList<String[]> result = new ArrayList<>();

        String returnSignal; // false == low, true == high

        if(signal[1].equals("high") || flipFlopStates[index] == null) return result;

        if((Boolean)flipFlopStates[index])
            returnSignal = "low";
        else
            returnSignal = "high";

        flipFlopStates[index] = !(Boolean)flipFlopStates[index];

        for(int i = 1; i < input[index].length; i++)
            result.add(new String[]{input[index][i], returnSignal, signal[0]});

        if(returnSignal.equals("low"))
            lowPulses += result.size();
        else
            highPulses += result.size();

        return result;
    }

    public static ArrayList<String[]> computeConjunction(String[] signal, String[][] input, Object[] conjunctionPulses, int index)
    {
        ArrayList<String[]> result = new ArrayList<>();

        String returnSignal;

        ((HashMap<String, Boolean>)conjunctionPulses[index]).replace(signal[2], signal[1].equals("high"));

        if(isAllHighPulses((HashMap<String, Boolean>)conjunctionPulses[index]))
            returnSignal = "low";
        else
            returnSignal = "high";

        for(int i = 1; i < input[index].length; i++)
            result.add(new String[]{input[index][i], returnSignal, signal[0]});

        if(returnSignal.equals("low"))
            lowPulses += result.size();
        else
            highPulses += result.size();

        return result;
    }

    public static boolean isAllHighPulses(HashMap<String, Boolean> pulses)
    {
        for (Boolean pulse : pulses.values())
            if(!pulse)
                return false;
        return true;
    }
}
