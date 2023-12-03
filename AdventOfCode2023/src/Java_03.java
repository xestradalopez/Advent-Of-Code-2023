import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Java_03 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("Input03.txt"));
		
		int partOne = 0;
		int partTwo = 0;
		
		for(int i = 0; i < 1; i++)
		{
			String currentLine = input.nextLine();
		}
		
		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);

	}
}