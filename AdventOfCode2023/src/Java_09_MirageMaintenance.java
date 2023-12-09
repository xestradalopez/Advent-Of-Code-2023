import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Java_09_MirageMaintenance 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		double start = System.nanoTime();
		
		Scanner input = new Scanner(new File("input\\09.txt"));
		
		int partOne = 0;
		int partTwo = 0;
		
		for(int i = 0; i < 200; i++)
		{
			String currentLine = input.nextLine();

			int[] dab = Arrays.stream(currentLine.split(" ")).mapToInt(Integer::parseInt).toArray();
			partOne += findPattern(dab, false);
			partTwo += findPattern(dab, true);
		}
		
		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);
		
		double duration = (System.nanoTime() - start) / 1000000;
		System.out.println(duration + "ms");
	}
	
	static int findPattern(int[] a, boolean isPartTwo)
	{		
		int[] ads = new int[a.length-1];
				
		for(int i = 0; i < ads.length; i++)
			ads[i] = a[i+1] - a[i];
		
		return Arrays.stream(a).allMatch(n -> n == 0) ? 0: isPartTwo ? a[a.length-1] + findPattern(ads, isPartTwo) : a[0] - findPattern(ads, isPartTwo);
	}
}