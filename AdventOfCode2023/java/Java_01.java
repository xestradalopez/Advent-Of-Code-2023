import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Java_01
{
	public static void main(String[] args) throws FileNotFoundException
	{
		double start = System.nanoTime();
		
		Scanner input = new Scanner(new File("AdventOfCode2023\\input\\01.txt"));

		int partOne = 0;
		int partTwo = 0;

		while(input.hasNextLine())
		{
			String currentLine = input.nextLine();
			partOne += parseString(currentLine, false);
			partTwo += parseString(currentLine, true);
		}
		
		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);
		
		double duration = (System.nanoTime() - start) / 1000000;
		System.out.println(duration + "ms");
	}
	
	static int parseString(String currentLine, boolean isPartTwo)
	{
		int calibrationValue = 0;

		for(int a = 0; a < currentLine.length(); a++)
			if(numAt(currentLine.substring(a), isPartTwo) != 0)
			{
				calibrationValue += numAt(currentLine.substring(a), isPartTwo) * 10;
				break;
			}
		
		for(int b = currentLine.length()-1; b >= 0; b--)
			if(numAt(currentLine.substring(b), isPartTwo) != 0)
			{
				calibrationValue += numAt(currentLine.substring(b), isPartTwo);
				break;
			}

		return calibrationValue;
	}
	
	static int numAt(String currentLineSubstring, boolean isPartTwo)
	{
		String[] writtenNumbers = {"one","two","thr","fou","fiv","six","sev","eig","nin"};

		if(Character.isDigit(currentLineSubstring.charAt(0)))
			return Integer.parseInt(currentLineSubstring.substring(0,1));
			
		if(isPartTwo)
			for(int b = 0; b < 9; b++)
				if(currentLineSubstring.indexOf(writtenNumbers[b]) == 0)
					return b + 1;
		
		return 0;
	}
}