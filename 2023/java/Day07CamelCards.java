import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day07CamelCards
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("2023\\input\\07.txt"));
		
		String[][] ranks = new String[1000][3];
		
		int partOne = 0;
		
		for(int i = 0; i < 1000; i++)
			ranks[i] = rank(input);
		
		
		sort(ranks);
		
		for(int i = 0; i < 1000; i++)
			partOne += Integer.parseInt(ranks[i][2]) * (i+1);
		
		System.out.println(partOne);
	}
	
	static String[] rank(Scanner a)
	{
		String line = a.nextLine();
		String currentLine = line.substring(0,5);
		String bet = line.substring(6);
		int[] stuff = new int[13];
		
		for(int i = 0; i < 5; i++)	
			stuff[getValue(currentLine.charAt(i))-1]++;
		
		int greatest = 0;
		int secondgreatest = 0;
		for(int i = 1; i < 13; i++)
			if(stuff[i] > greatest)
			{
				secondgreatest = greatest;
				greatest = stuff[i];
			}
			else if(stuff[i] >= secondgreatest && stuff[i] <= greatest)
				secondgreatest = stuff[i];
		
		double extra = secondgreatest == 2? .5: 0;
		double result = greatest + stuff[0] + extra;

		return new String[] {result + "", currentLine, bet};
	}
	
	static void sort(String[][] a)
	{
		String[] temp = new String[3];
		
		for(int i = 1; i < a.length; i++)
			for(int j = i; j > 0 && isBigger(a[j-1][0] + " " + a[j-1][1], a[j][0] + " " + a[j][1]); j--)
			{
				temp = a[j];
				a[j] = a[j-1];
				a[j-1] = temp;
			}
	}
	
	static boolean isBigger(String a, String b)
	{
		double rankA = Double.parseDouble(a.substring(0,3));
		double rankB = Double.parseDouble(b.substring(0,3));
		
		if(rankA > rankB)
			return true;
		else if(rankA == rankB)
			for(int i = 0; i < 5; i++)
				if(getValue(a.charAt(i + 4)) > getValue(b.charAt(i + 4)))
					return true;
				else if(getValue(a.charAt(i + 4)) < getValue(b.charAt(i + 4)))
					return false;
		return false;
	}
	
	static int getValue(char a)
	{
		if(Character.isDigit(a))
			return a - 48;
		
		switch(a)
		{
			case 'J':
				return 1;
			case 'T':
				return 10;
			case 'Q':
				return 11;
			case 'K':
				return 12;
			case 'A':
				return 13;
		}
		
		return -1;
	}
}