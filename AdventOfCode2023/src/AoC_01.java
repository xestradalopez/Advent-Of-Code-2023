import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class AoC_01
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("Input01.txt"));

		int partOne = 0;
		int partTwo = 0;
		
		for(int i = 0; i < 1000; i++)
		{
			String currentLine = input.nextLine();
			partOne += parseString(currentLine, false);
			partTwo += parseString(currentLine, true);
		}
		
		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);
	}
	
	static int parseString(String currentLine, boolean isPartTwo)
	{
		String calibrationValue = "";

		for(int a = 0; a < currentLine.length(); a++)
		{
			if(numAt(currentLine.substring(a), isPartTwo) != '0')
			{
				calibrationValue += numAt(currentLine.substring(a), isPartTwo);
				break;
			}
		}
		
		for(int b = currentLine.length()-1; b >= 0; b--)
		{
			if(numAt(currentLine.substring(b), isPartTwo) != '0')
			{
				calibrationValue += numAt(currentLine.substring(b), isPartTwo);
				break;
			}
		}

		return Integer.parseInt(calibrationValue);
	}
	
	static char numAt(String currentLineSubstring, boolean isPartTwo)
	{
		String[] writtenNumbers = {"one","two","thr","fou","fiv","six","sev","eig","nin"};
		
		if(currentLineSubstring.charAt(0) >= 49 && currentLineSubstring.charAt(0) <= 57)
			return currentLineSubstring.charAt(0);
			
		if(isPartTwo)
		{
			for(int b = 0; b < 9; b++)
				if(currentLineSubstring.indexOf(writtenNumbers[b]) == 0)
					return (char)(b + '1');
		}
		
		return '0';
	}
}