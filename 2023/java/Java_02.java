import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Java_02
{
	public static void main(String[] args) throws FileNotFoundException
	{
		long start = System.nanoTime();
		
		Scanner input = new Scanner(new File("Code2023\\input\\02.txt"));
		
		int partOne = 0;
		int partTwo = 0;
		
		for(int i = 0; i < 100; i++)
		{
			String currentLine = input.nextLine().substring(7) + ";";
			partOne += isPossible(currentLine, i+1, false);
			partTwo += isPossible(currentLine, i+1, true);
		}
		
		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);
		
		Long duration = (System.nanoTime() - start) / 1000000;
		System.out.println(duration + "ms");
	}
	
	static int isPossible(String s, int index, boolean isPartTwo)
	{
		int lr = 0;
		int lg = 0;
		int lb = 0;
		int currentOffset = 0;
		String currentSet = s.substring(currentOffset, s.substring(currentOffset).indexOf(";"));
		
		while(true)
		{
			if(currentSet.indexOf("red") != -1)
			{
				int a = Integer.parseInt(s.substring(currentSet.indexOf("red") + currentOffset - 3, currentSet.indexOf("red") + currentOffset - 1 ).trim());
				if(a>lr)
					lr = a;
			}
			if(currentSet.indexOf("green") != -1)
			{
				int a = Integer.parseInt(s.substring(currentSet.indexOf("green") + currentOffset - 3, currentSet.indexOf("green") + currentOffset - 1 ).trim());
				if(a>lg)
					lg = a;
			}
			if(currentSet.indexOf("blue") != -1)
			{
				int a = Integer.parseInt(s.substring(currentSet.indexOf("blue") + currentOffset - 3, currentSet.indexOf("blue") + currentOffset - 1 ).trim());
				if(a>lb)
					lb = a;
			}
			currentOffset += currentSet.length() + 1;
			if(currentOffset >= s.length())
				break;

			currentSet = s.substring(currentOffset,s.substring(currentOffset).indexOf(";") + currentOffset);
		}
		
		if(!isPartTwo)
		{
			if(lr <= 12 && lg <= 13 && lb <= 14)
				return index;
			return 0;
		}
		return lr*lg*lb;
	}
}