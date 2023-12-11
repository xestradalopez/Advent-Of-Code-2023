import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Java_10_PipeMaze {

	public static void main(String[] args) throws FileNotFoundException
	{
		final double start = System.nanoTime();
		final Scanner input = new Scanner(new File("input\\10.txt"));
		//82, 74
		char[][]  a = new char[140][140];
		int[][] qwerty = new int[13546][2];
		
		for(int i = 0; i < 140; i++)
		{
			String currentLine = input.nextLine();
			for(int j = 0; j < 140; j++)
				a[i][j] = currentLine.charAt(j);
		}
		int count = 1;
		int y = 83;
		int x = 74;
		int prevy = 82;
		int prevx = 74;
		int count2 = 0;
		while(a[y][x] != 'S') //a[y][x] != 'S'
		{
			count++;
			switch(a[y][x])
			{
			case '|':
				if(prevy < y)
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevy = y;
					y+=1;
				}
				else
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevy=y;
					y-=1;
				}
				break;
			case '-':
				if(prevx < x)
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevx=x;
					x++;
				}
				else
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevx=x;
					x--;
				}
				break;
			case 'L':
				if(prevy < y)
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevy=y;
					x++;
				}
				else
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevx=x;
					y--;
				}
				break;
			case 'J':
				if(prevy < y)
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevy=y;
					x--;
				}
				else
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevx=x;
					y--;
				}
				break;
			case '7':
				if(prevx < x)
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevx=x;
					y++;
				}
				else
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevy=y;
					x--;
				}
				break;
			case 'F':
				if(prevx > x)
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevx=x;
					y++;
				}
				else
				{
					qwerty[count2][0] = y;
					qwerty[count2][1] = x;
					count2++;
					prevy=y;
					x++;
				}
				break;
			}
		}
		qwerty[count2][0] = y;
		qwerty[count2][1] = x;
		
		System.out.println(count/2);
		int[] huh = new int[] {85,74};
		
		int count3 = 0;
		int count4 = 0;
		
		for(int i = 0; i < 140; i++)
		{
			for(int j = 0; j < 140; j++)
			{			
				int[] kys = new int[] {i,j};
				if(a[i][j] == '.' || !isValid(qwerty, kys))
					for(int k = i+1; k < 140; k++)
					{
						int[] help = new int[] {k,j};

						if(isValid(qwerty, help))
						{
							switch(a[k][j])
							{
							case '-':
								count3+=2;
								break;
							case 'L':
							case '7':
							case 'S':
								count3++;
								break;
							case 'F':
							case 'J':
								count3--;
								break;
							case '|':
								break;
							}
						}
					}
				if(count3 % 4 == 2 || count3 % 4 == -2) 
				{
					count4++;
				}
				
				count3 = 0;
			}
		}
		System.out.println("count 4: " + count4);
		final double runtime = (System.nanoTime() - start) / 1000000;
		System.out.println("Runtime: " + runtime + "ms");
	}
	static boolean isValid(int[][] a, int[] b)
	{
		for(int i = 0; i < a.length; i++)
		{
			if(Arrays.equals(b, a[i]))
				return true;
		}
		return false;
	}
}
