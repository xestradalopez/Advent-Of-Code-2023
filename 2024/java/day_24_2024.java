import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day_24_2024
{
    static HashMap<String, Boolean> wireStates;
    static LinkedList<String[]> gateConnections;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/24.txt"));
        parse(input);

        System.out.println("Part One: " + partOne());
        System.out.println("Part Two: " + partTwo());

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static long partOne()
    {
        long result = 0;

        while(!gateConnections.isEmpty())
        {
            String[] gateConnection = gateConnections.element();

            if(!wireStates.containsKey(gateConnection[0])||!wireStates.containsKey(gateConnection[2]))
            {
                gateConnections.add(gateConnections.pop());
                continue;
            }

            if(gateConnection[1].equals("AND")) wireStates.put(gateConnection[3], wireStates.get(gateConnection[0]) && wireStates.get(gateConnection[2]));
            if(gateConnection[1].equals("OR")) wireStates.put(gateConnection[3], wireStates.get(gateConnection[0]) || wireStates.get(gateConnection[2]));
            if(gateConnection[1].equals("XOR")) wireStates.put(gateConnection[3], wireStates.get(gateConnection[0]) ^ wireStates.get(gateConnection[2]));

            gateConnections.pop();
        }

        LinkedList<String> keySet = new LinkedList<>(wireStates.keySet().stream().toList());

        Collections.sort(keySet);

        int exp = 0;
        for(String key: keySet)
        {
            if(key.charAt(0) != 'z')
                continue;

            if(wireStates.get(key))
                result += (long) Math.pow(2, exp);

            exp++;
        }

        return result;
    }

    static int partTwo()
    {
        int result = 0;

        return result;
    }

    static void parse(Scanner input)
    {
        wireStates = new HashMap<>();
        gateConnections = new LinkedList<>();

        while(true)
        {
            String[] wireState = input.nextLine().split(": ");

            if(wireState.length == 1)
                break;

            wireStates.put(wireState[0], wireState[1].equals("1"));
        }

        while(input.hasNextLine())
            gateConnections.add(input.nextLine().split(" -> | "));
    }
}
