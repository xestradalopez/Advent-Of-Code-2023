import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Java_01_Trebuchet
{
	public static void main(String[] args) throws FileNotFoundException
	{
		long start = System.nanoTime();
		
		Scanner input = new Scanner(new File("input\\01.txt"));

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
		
		Long duration = (System.nanoTime() - start) / 1000000;
		System.out.println(duration + "ms");
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
		
		if(Character.isDigit(currentLineSubstring.charAt(0)))
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