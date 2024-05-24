import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
public class Java_15
{
    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2023/input/15.txt"));
        String uwu = input.nextLine();

        int partOne = partOne(uwu);
        int partTwo = partTwo(uwu);

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    public static int hash(String str)
    {
        int result = 0;
        for(int i = 0; i < str.length(); i++)
        {
            result+=str.charAt(i);
            result*=17;
            result%=256;
        }
        return result;
    }

    public static int partOne(String input)
    {
        String str = input + ",";
        int result = 0;
        while(!str.isEmpty())
        {
            result+=hash(str.substring(0,str.indexOf(',')));
            str = str.substring(str.indexOf(',') + 1);
        }
        return result;
    }

    public static int analyze(String a) //sjw3kys5sugma9
    {
        int result = 0;
        if(!a.isEmpty())
        {
            int boxNumber = hash(a.substring(0,a.indexOf(',') - 1)) + 1;
            for(int i = 0; i < a.length(); i+=3)
                result+= (boxNumber) * ((i/3) + 1) * (Integer.parseInt(a.substring(i+2,i+3)));
        }
        return result;
    }

    public static int partTwo(String input)
    {
        HashMap<Integer, String> test = new HashMap<>();

        String str = input + ',';
        while(!str.isEmpty())
        {
            boolean isEqual = str.indexOf('=') < str.indexOf('-');
            if(str.indexOf('-') == -1)
                isEqual=true;
            String label = isEqual ? str.substring(0, str.indexOf('=')) : str.substring(0, str.indexOf('-'));
            String currentStr = label + str.charAt(label.length() + 1);
            int currentStrHash = hash(label);

            String hashValue = test.getOrDefault(currentStrHash, "");
            String hashValueStart = test.containsKey(currentStrHash) && hashValue.contains(label) ? hashValue.substring(0, hashValue.indexOf(label)) : "";
            String hashValueEnd = test.containsKey(currentStrHash) && hashValue.contains(label) ? hashValue.substring(hashValue.indexOf(label) + 3) : "";

            if(isEqual)
                if(!test.containsKey(currentStrHash))
                    test.put(currentStrHash,currentStr);
                else if(hashValue.contains(str.substring(0,2)))
                    test.replace(currentStrHash, hashValueStart + currentStr + hashValueEnd);
                else
                    test.replace(currentStrHash,test.get(currentStrHash) + currentStr);
            else if(test.containsKey(currentStrHash) && hashValue.contains(str.substring(0, 2)))
                test.replace(currentStrHash, hashValueStart + hashValueEnd);

            System.out.println("after: " + label);
            System.out.println(test.values());
            str = str.substring(str.indexOf(',') + 1);
        }

        String[] testicles = test.values().toArray(new String[0]);

        int result = 0;
        for(int i = 0; i < testicles.length; i++)
        {
            result+= analyze(testicles[i]);
        }

        return result;
    }

}

