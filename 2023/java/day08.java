import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class day08 {

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("2023\\input\\08.txt"));
		
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
		
		//System.out.println(looper(maps, values));
		looper2(maps, values);

	}
	
	static int looper(String[][] a, int[] b, int j)
	{
		int index = j;
		int count = 0;
		while(true)
		{
			for(int i = 0; i < b.length; i++)
			{
				count++;
				if(a[findIndex(a, a[index][b[i]])][0].charAt(2) == 'Z')
					return count;
				else
					index = findIndex(a, a[index][b[i]]);
			}
		}
	}
	
	static int looper2(String[][] a, int[] b)
	{
		int index[] = new int[6];
		int count1 = 0;
		for(int i = 0; i < 798; i++)
		{
			if(a[i][0].charAt(2) == 'A')
			{
				index[count1] = i;
				count1++;
			}
				
		}
		System.out.println(looper(a, b, index[0]));
		System.out.println(looper(a, b, index[1]));
		System.out.println(looper(a, b, index[2]));
		System.out.println(looper(a, b, index[3]));
		System.out.println(looper(a, b, index[4]));
		System.out.println(looper(a, b, index[5]));
		return -1;
	}
	
	static int findIndex(String[][] a, String b)
	{
		for(int i = 0; i < a.length; i++)
			if(a[i][0].equals(b))
				return i;
		return -1;
	}
	
	static int findIndex2(String[][] a, String b)
	{
		for(int i = 0; i < a.length; i++)
			if(a[i][0].charAt(2) == 'a')
				return i;
		return -1;
	}
	
	

}
