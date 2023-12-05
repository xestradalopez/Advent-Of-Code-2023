import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Java_03 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		/*
		Scanner input = new Scanner(new File("Input03.txt"));
		String BlankLine = ".............................................................................................................................";
		
		
		String[] inputArray = new String[140];
		
		for(int i = 0; i < 140; i++)
		{
			inputArray[i] = input.nextLine();
		}
		
		int[] numbers = partNumberSum(inputArray);
		
		for(int i = 0; i < 1216; i++)
		{
			System.out.println(numbers[i]);
		}
		
		int partOne = 0;
		int partTwo = 0;

		for(int i = 0; i < 1; i++)
		{

		}
		
		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);
		*/
	}
	static int[] partNumberSum(String[] s)
	{
		/*
		int currentPower = 0;
		int index = 0;
		int currentNumber = 0;
		int[][][] numbers = new int[1216][3][140];
		for(int i = 0; i < 140; i++)
		{
			for(int j = 139; j >=0; j--)
			{
				if(Character.isDigit(s[i].charAt(j)))
				{
					currentNumber += Integer.parseInt(s[i].substring(j,j+1)) * Math.pow(10, currentPower);
					currentPower++;
				}
				else if(currentPower > 0)
				{
					numbers[index][0][i] = currentNumber;
					numbers[index][1][i] = j;
					numbers[index][2][i] = currentPower;
					currentNumber = 0;
					currentPower = 0;
					index++;
				}
			}
		}
	*/
		return new int[] {-1};
	}
}
	
	