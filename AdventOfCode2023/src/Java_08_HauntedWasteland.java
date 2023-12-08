import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Java_08_HauntedWasteland {

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("input\\08.txt"));
		
		String currentLine = input.nextLine();
		int[] values = new int[271];
		String[][] maps = new String[798][3];
		
		for(int i = 0; i < 271; i++)
			if(currentLine.charAt(i) == 'L')
				values[i] = 1;
			else
				values[i] = 2;
		
		input.nextLine();
		
		for(int i = 0; i < 798; i++)
		{
			currentLine = input.nextLine();
			maps[i][0] = currentLine.substring(0,3); 
			maps[i][1] = currentLine.substring(7,10); 
			maps[i][2] = currentLine.substring(12,15); 
		}
		
		System.out.println(looper(maps, values));
	}
	
	static int looper(String[][] a, int[] b)
	{
		int index = findIndex(a, "AAA");
		int count = 0;
		while(true)
		{
			for(int i = 0; i < b.length; i++)
			{
				count++;
				if(a[findIndex(a, a[index][b[i]])][0].equals("ZZZ"))
					return count;
				else
					index = findIndex(a, a[index][b[i]]);
			}
		}
	}
	
	static int findIndex(String[][] a, String b)
	{
		for(int i = 0; i < a.length; i++)
			if(a[i][0].equals(b))
				return i;
		return -1;
	}
	
	

}
