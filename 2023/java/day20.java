import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
@SuppressWarnings("SequencedCollectionMethodCanBeUsed")
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


        System.out.println(lowPulses);
        System.out.println(highPulses);
        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static int partOne(Scanner sc)
    {
        ArrayList<ArrayList<String>> input = parseInput(sc);                                    // {{Source Module 1, Destination Module 1, ...}, ...}
        Object[] lookupTable = createLookupTable(input);
        ArrayList<String[]> instructions;                                                       // {[signal Destination 1, signal, Signal Source], ...}
        // {Flip-Flop Module 1 State, ...} false = Off | true = On                          // For both of these they are at the same index as their corresponding
        // {{input Module 1, Input Module Most Recent Signal}, ...} false = Off | true = On // module in input, all other spaces are filled with null



        for(int i = 0; i < 1000; i++)
        {
            //System.out.println((i+1) + ": ");
            //System.out.println("button -low-> broadcaster");
            lowPulses++;
            instructions = findBroadcaster(input);
            while (!instructions.isEmpty())
            {
                //System.out.println(instructions.get(0)[2] + " -"+instructions.get(0)[1]+"-> " + instructions.get(0)[0]);
                instructions.addAll(compute(instructions.get(0), input, lookupTable));
                instructions.remove(0);
            }
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
                    result.add(new String[]{strings.get(j), "low","broadcaster"});

        lowPulses += result.size();

        return result;
    }

    public static Object[] createLookupTable(ArrayList<ArrayList<String>> input)
    {
        ArrayList<Object> result = new ArrayList<>();

        for (ArrayList<String> strings : input)
            if (strings.get(0).charAt(0) == '%')
                result.add(false);
            else if(strings.get(0).charAt(0) == '&')
            {
                String currentValue = strings.get(0).substring(1);

                HashMap<String, Boolean> inputPulses = new HashMap<>();

                //God is dead
                for (ArrayList<String> stringArrayList : input)
                    for (int k = 1; k < stringArrayList.size(); k++)
                        if (stringArrayList.get(k).equals(currentValue))
                            inputPulses.put(stringArrayList.get(0).substring(1), false);

                result.add(inputPulses);
            }
            else
                result.add(null);

        return result.toArray();
    }

    public static ArrayList<String[]> compute(String[] signal, ArrayList<ArrayList<String>> input, Object[] lookupTable)
    {
        int index = 0;
        boolean operator = true; // true == flip-flop, false == conjunction;

        for(int i = 0; i < input.size(); i++)
            //noinspection SequencedCollectionMethodCanBeUsed
            if(input.get(i).get(0).substring(1).equals(signal[0]))
            {
                index = i;
                operator = input.get(i).get(0).charAt(0) == '%';
                break;
            }

        if(operator)
            return computeFlipFlop(signal, input, lookupTable, index);
        else
            return computeConjunction(signal, input, lookupTable, index);
    }

    public static ArrayList<String[]> computeFlipFlop(String[] signal, ArrayList<ArrayList<String>> input, Object[] flipFlopStates, int index)
    {
        ArrayList<String[]> result = new ArrayList<>();

        String returnSignal; // false == low, true == high

        if(signal[1].equals("high") || flipFlopStates[index] == null) return result;

        if((Boolean)flipFlopStates[index])
            returnSignal = "low";
        else
            returnSignal = "high";

        flipFlopStates[index] = !(Boolean)flipFlopStates[index];

        for(int i = 1; i < input.get(index).size(); i++)
            result.add(new String[]{input.get(index).get(i), returnSignal, signal[0]});

        if(returnSignal.equals("low"))
            lowPulses += result.size();
        else
            highPulses += result.size();

        return result;
    }

    public static ArrayList<String[]> computeConjunction(String[] signal, ArrayList<ArrayList<String>> input, Object[] conjunctionPulses, int index)
    {
        ArrayList<String[]> result = new ArrayList<>();

        String returnSignal;

        ((HashMap<String, Boolean>)conjunctionPulses[index]).replace(signal[2], signal[1].equals("high"));

        if(isAllHighPulses((HashMap<String, Boolean>)conjunctionPulses[index]))
            returnSignal = "low";
        else
            returnSignal = "high";

        for(int i = 1; i < input.get(index).size(); i++)
            result.add(new String[]{input.get(index).get(i), returnSignal, signal[0]});

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
