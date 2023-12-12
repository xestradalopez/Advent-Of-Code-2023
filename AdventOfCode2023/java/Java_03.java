import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
public class Java_03
{
	public static void main(String[] args) throws FileNotFoundException
	{
		double start = System.nanoTime();

		Scanner input = new Scanner(new File("AdventOfCode2023/input/03.txt"));
		char[][] parsedInput = new char[142][142];

		parseInput(parsedInput, input);

		int partOne = partOne(parsedInput);
		int partTwo = 0;

		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);

		double duration = (System.nanoTime() - start) / 1000000;
		System.out.println("Runtime: " + duration + "ms");
	}
	static void parseInput(char[][] a, Scanner input)
	{
		String currentLine = "";
		for(int i = 0; i < 142; i++)
		{
			if(i != 0 && i != 141)
				currentLine = input.nextLine();

			for (int j = 0; j < 142; j++)
				a[i][j] = (i == 0 || i == 141 || j == 0 || j == 141) ? '.' : currentLine.charAt(j-1);
		}
	}
	static int partOne(char[][] stuff)
	{
		StringBuilder temp = new StringBuilder();
		int count = 0;
		boolean thing = false;
		for(int i = 1; i < stuff.length; i++)
			for (int j = 1; j < stuff[i].length; j++)
			{

				if("0123456789".indexOf(stuff[i][j]) > -1)
				{
					char[] radius = new char[] {stuff[i-1][j-1], stuff[i-1][j], stuff[i-1][j+1], stuff[i][j-1], stuff[i][j+1], stuff[i+1][j-1], stuff[i+1][j], stuff[i+1][j+1]};
					temp.append(stuff[i][j]);
					for(char a: radius)
						if(!("0123456789.".indexOf(a) > -1))
						{
							thing = true;
							break;
						}
				}

				if(!Character.isDigit(stuff[i][j]))
				{
					if(thing)
						count += Integer.parseInt(temp.toString());
					temp = new StringBuilder();
					thing = false;
				}
			}
		return count;
	}
}
	
	