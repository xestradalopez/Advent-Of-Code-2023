import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;
public class day25
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2023/input/25.txt"));

        HashMap<String, Integer> results = countConnections(input);

        Set<String> s = results.keySet();

        for (String x : s)
        {
            System.out.println(x + ": " + results.get(x));
        }


    }

    public static HashMap<String, Integer> countConnections(Scanner input)
    {
        HashMap<String, Integer> results = new HashMap<String, Integer>();
        String currentLine;
        String mainConnector;
        String betaConnector;
        while(input.hasNext())
        {
            currentLine = input.nextLine();
            mainConnector = currentLine.substring(0,3);
            currentLine = currentLine.substring(5);


            if(results.containsKey(mainConnector))
            {
                results.put(mainConnector, results.get(mainConnector) + 1);
            }
            else
            {
                results.put(mainConnector, 0);
            }


            while(!currentLine.isEmpty())
            {
                betaConnector = currentLine.substring(0,3);
                if(results.containsKey(betaConnector))
                {
                    results.put(betaConnector, results.get(betaConnector) + 1);
                }
                else
                {
                    results.put(betaConnector, 0);
                }
                results.put(mainConnector, results.get(mainConnector) + 1);

                if(currentLine.length() >=4)
                    currentLine = currentLine.substring(4);
                else
                    currentLine = "";
            }
        }
        return results;
    }
}
