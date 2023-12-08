import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Java_07_CamelCards 
{

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("input\\07.txt"));
		
		String[][] ranks = new String[1000][3];
		
		int partOne = 0;
		
		for(int i = 0; i < 1000; i++)
			ranks[i] = rank(input);
		
		
		sort(ranks);
		
		for(int i = 0; i < 1000; i++)
		{
			System.out.println((i+1) + ": " + ranks[i][0] + " " + ranks[i][1] + " " + ranks[i][2] + ": " + Integer.parseInt(ranks[i][2]) * (i+1));
			partOne += Integer.parseInt(ranks[i][2]) * (i+1);
		}
		
		System.out.println(partOne);

	}
	
	static String[] rank(Scanner a)
	{
		String line = a.nextLine();
		String currentLine = line.substring(0,5);
		String bet = line.substring(6);
		int[] stuff = new int[13];
		
		for(int i = 0; i < 5; i++)	
			stuff[getValue(currentLine.charAt(i))-2]++;
		
		int greatest = 0;
		int secondgreatest = 0;
		for(int i = 0; i < 13; i++)
			if(stuff[i] > greatest)
				greatest = stuff[i];
			else if(stuff[i] >= secondgreatest && stuff[i] <= greatest)
				secondgreatest = stuff[i];
		
		switch(greatest)
		{
		case 5:
			return new String[] {6 + "",currentLine, bet};
		case 4:
			return new String[] {5 + "",currentLine, bet};
		case 3:
			if(secondgreatest == 2)
				return new String[] {4 + "",currentLine, bet};
			else
				return new String[] {3 + "",currentLine, bet};
		case 2:
			if(secondgreatest == 2)
				return new String[] {2 + "",currentLine, bet};
			else
				return new String[] {1 + "",currentLine, bet};
		default:
			return new String[] {0 + "",currentLine, bet};
		}
	}
	
	static void sort(String[][] a)
	{
		String[] temp = new String[3];
		
		for(int i = 1; i < a.length; i++)
			for(int j = i;j > 0 && isBigger(a[j-1][0] + " " + a[j-1][1], a[j][0] + " " + a[j][1]); j--)
			{
				temp = a[j];
				a[j] = a[j-1];
				a[j-1] = temp;
			}
	}
	
	static boolean isBigger(String a, String b)
	{
		if(a.charAt(0) > b.charAt(0))
			return true;
		else if(a.charAt(0) == b.charAt(0))
			for(int i = 0; i < 5; i++)
				if(getValue(a.charAt(i + 2)) > getValue(b.charAt(i + 2)))
					return true;
				else if(getValue(a.charAt(i + 2)) < getValue(b.charAt(i + 2)))
					return false;
		return false;
	}
	
	static int getValue(char a)
	{
		if(Character.isDigit(a))
			return a - 48;
		
		switch(a)
		{
			case 'T':
				return 10;
			case 'J':
				return 11;
			case 'Q':
				return 12;
			case 'K':
				return 13;
			case 'A':
				return 14;
		}
		
		return -1;
	}
}