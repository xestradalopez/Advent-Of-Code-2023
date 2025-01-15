import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day_23_2024
{
    static Map<String, Set<String>> neighbors;
    static Set<String> vertices;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/23.txt"));
        parse(input);

        int partOne = partOne();
        String partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static int partOne()
    {
        int result = 0;
        HashSet<HashSet<String>> a = new HashSet<>();

        for(String vertex: vertices)
            for(String n1: neighbors.get(vertex))
                for(String n2: neighbors.get(n1))
                    for(String n3: neighbors.get(n2))
                        if(n3.equals(vertex))
                            a.add(new HashSet<>(Arrays.asList(vertex, n1, n2)));

        for(HashSet<String> b: a)
            for (String c : b)
                if (c.charAt(0) == 't')
                {
                    result++;
                    break;
                }

        return result;
    }

    static String partTwo()
    {
        StringBuilder result = new StringBuilder();

        Set<Set<String>> maximalCliques = new HashSet<>();

        BronKerbosch(new HashSet<>(), new HashSet<>(vertices), new HashSet<>(), maximalCliques);

        Set<String> maximalClique = new HashSet<>();

        for(Set<String> potentialMaximalClique: maximalCliques)
            if(potentialMaximalClique.size() > maximalClique.size())
                maximalClique = potentialMaximalClique;

        List<String> sortedMaximalClique = new ArrayList<>(maximalClique.stream().toList());
        Collections.sort(sortedMaximalClique);

        for(String x: sortedMaximalClique)
        {
            if(!result.isEmpty())
                result.append(",");
            result.append(x);
        }

        return result.toString();
    }

    static void parse(Scanner input)
    {
        neighbors = new HashMap<>();
        vertices = new HashSet<>();

        while(input.hasNextLine())
        {
            String[] compCon = input.nextLine().split("-");

            if(!neighbors.containsKey(compCon[0])) neighbors.put(compCon[0], new HashSet<>());
            if(!neighbors.containsKey(compCon[1])) neighbors.put(compCon[1], new HashSet<>());

            neighbors.get(compCon[0]).add(compCon[1]);
            neighbors.get(compCon[1]).add(compCon[0]);

            vertices.add(compCon[0]);
            vertices.add(compCon[1]);
        }
    }

    static void BronKerbosch(Set<String> potentialClique, Set<String> remainingNodes, Set<String> skipNodes, Set<Set<String>> maximalCliques)
    {
        if(remainingNodes.isEmpty() && skipNodes.isEmpty())
        {
            maximalCliques.add(potentialClique);
            return;
        }

        for(Iterator<String> iterator = remainingNodes.iterator(); iterator.hasNext();)
        {
            String node = iterator.next();

            Set<String> newPotentialClique = union(potentialClique, node);
            Set<String> newRemainingNodes = intersection(remainingNodes, node);
            Set<String> newSkipNodes = intersection(skipNodes, node);

            BronKerbosch(newPotentialClique, newRemainingNodes, newSkipNodes, maximalCliques);

            iterator.remove();
            skipNodes.add(node);
        }
    }

    static Set<String> union(Set<String> a, String b)
    {
        Set<String> result = new HashSet<>(a);
        result.add(b);
        return result;
    }

    static Set<String> intersection(Set<String> a, String b)
    {
        Set<String> result = new HashSet<>(a);
        result.retainAll(neighbors.get(b));
        return result;
    }
}