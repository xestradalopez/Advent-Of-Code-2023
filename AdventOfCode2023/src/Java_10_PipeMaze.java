import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Java_10_PipeMaze {

	public static void main(String[] args) throws FileNotFoundException
	{

		final Scanner input = new Scanner(new File("input\\10.txt"));
		//82, 74
		char[][]  a = new char[140][140];
		
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
		while(a[y][x] != 'S') //a[y][x] != 'S'
		{
			count++;
			switch(a[y][x])
			{
			case '|':
				if(prevy < y)
				{
					prevy = y;
					y+=1;
				}
				else
				{
					prevy=y;
					y-=1;
				}
				break;
			case '-':
				if(prevx < x)
				{
					prevx=x;
					x++;
				}
				else
				{
					prevx=x;
					x--;
				}
				break;
			case 'L':
				if(prevy < y)
				{
					prevy=y;
					x++;
				}
				else
				{
					prevx=x;
					y--;
				}
				break;
			case 'J':
				if(prevy < y)
				{
					prevy=y;
					x--;
				}
				else
				{
					prevx=x;
					y--;
				}
				break;
			case '7':
				if(prevx < x)
				{
					prevx=x;
					y++;
				}
				else
				{
					prevy=y;
					x--;
				}
				break;
			case 'F':
				if(prevx > x)
				{
					prevx=x;
					y++;
				}
				else
				{
					prevy=y;
					x++;
				}
				break;
			default:
				System.out.println("Failed");
			}
		}
		System.out.println(count/2);
	}
}
