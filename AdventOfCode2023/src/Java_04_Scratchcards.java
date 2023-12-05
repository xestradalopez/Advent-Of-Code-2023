import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Java_04_Scratchcards {

	public static void main(String[] args) throws FileNotFoundException
	{
		long start = System.nanoTime();
		
		Scanner input = new Scanner(new File("input\\04.txt"));
		
		int partOne = 0;
		int partTwo = 0;
		
		int[] cards = new int[212];
		Arrays.fill(cards, 1);
		
		for(int i = 0; i < 212; i++)
		{
			String currentInput = input.nextLine().substring(10);
			partOne += pointCalculator(currentInput, false);
			for(int j = 1; j <=pointCalculator(currentInput, true); j++)
			{
				if(i + j == 212)
					break;
				cards[i + j] += 1 * cards[i];
			}
			partTwo += cards[i];
		}
		
		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);
		
		Long duration = (System.nanoTime() - start) / 1000000;
		System.out.println(duration + "ms");
	}
	
	static int pointCalculator(String s, boolean isPartTwo)
	{
		int currentMultiplier = -1;
		String winningNumbers = s.substring(31);
		
		
		for(int i= 0; i < 10; i++)
		{
			;
			int currentNumber = Integer.parseInt(s.substring(3*i,3*i+2).trim());
			for(int j = 0; j < 25; j++)
			{
				
				int otherNumber = Integer.parseInt(winningNumbers.substring(3*j+1,3*j+3).trim());
				
				if(currentNumber == otherNumber)
				{
					
					currentMultiplier++;
				}
			}
			
		}
		if(isPartTwo)
			return(currentMultiplier+1);	
		return (int)Math.pow(2, currentMultiplier);
	}
}