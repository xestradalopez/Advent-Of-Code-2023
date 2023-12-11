import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Java_05
{

	public static void main(String[] args) throws FileNotFoundException
	{
		double start = System.nanoTime();
		
		Scanner input = new Scanner(new File("AdventOfCode2023\\input\\05.txt"));
		
		long[][] seeds = new long[1][20];
		long[][] soil = new long[20][3];
		long[][] fertilizer = new long[47][3];
		long[][] water = new long[33][3];
		long[][] light = new long[40][3];
		long[][] temperature = new long[28][3];
		long[][] humidity = new long[47][3];
		long[][] location = new long[28][3];
		
		fill(seeds, input);
		fill(soil, input);
		fill(fertilizer, input);
		fill(water, input);
		fill(light, input);
		fill(temperature, input);
		fill(humidity, input);
		fill(location, input);
		
		FUCK(seeds);
		
		convert(seeds, soil);
		convert(seeds, fertilizer);
		convert(seeds, water);
		convert(seeds, light);
		convert(seeds, temperature);
		convert(seeds, humidity);
		convert(seeds, location);		
		
		long currentSmallest = 9999999999l;
		for(int i = 0; i < seeds[0].length; i++)
			if(seeds[0][i] < currentSmallest)
				currentSmallest = seeds[0][i];
		
		
		long partOne = currentSmallest;
		int partTwo = 0;
		
		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);
		
		double duration = (System.nanoTime() - start) / 1000000;
		System.out.println(duration + "ms");
		
		

	}
	
	static void fill(long[][] a, Scanner b)
	{
		String currentLine = b.nextLine();
		for(int i = 0; i < 1; i++)
			b.nextLine();
		
		for(int i = 0; i < a.length; i++)
		{
			currentLine = b.nextLine() + " ";
			for(int j = 0; j < a[i].length; j++)
			{
				a[i][j] = Long.parseLong(currentLine.substring(0, currentLine.indexOf(' ')));
				//System.out.println(a[i][j]);
				currentLine = currentLine.substring(currentLine.indexOf(' ') + 1) + " ";
			}
		}
	}
	
	static void FUCK(long[][] a)
	{
		long[][] b = new long[a[0].length/2][2];
		for(int i = 0; i < a[0].length; i+=2)
		{
			b[i/2][0] = a[0][i];
			b[i/2][1] = a[0][i] + a[0][i];
		}
		a = b;
	}
	
	static void convert(long[][] a, long[][] b)
	{
		for(int i = 0; i < a[0].length; i++)
			for(int j = 0; j < b.length; j++)
				if(a[0][i] >= b[j][1] && a[0][i] <= b[j][1] + b[j][2])
				{
					a[0][i] = a[0][i] - b[j][1] + b[j][0];
					break;
				}
	}
}
