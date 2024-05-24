import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Java_10
{
	public static void main(String[] args) throws FileNotFoundException
	{
		final Scanner input = new Scanner(new File("2023\\input\\10.txt"));

		final double start = System.nanoTime();

		char[][] maze = to2DArray(input);
		boolean[][] isOnLoop = new boolean[maze.length][maze[0].length];

		int partOne = partOne(maze, isOnLoop);
		int partTwo = partTwo(maze, isOnLoop);

		System.out.println("Part One: " + partOne);
		System.out.println("Part Two: " + partTwo);

		final double runtime = (System.nanoTime() - start) / 1000000;
		System.out.println("Runtime: " + runtime + "ms");
	}

	public static char[][] to2DArray(Scanner input)
	{
		ArrayList<char[]> preResult = new ArrayList<>();
		while(input.hasNextLine()) preResult.add(input.nextLine().toCharArray());
		return preResult.toArray(new char[0][0]);
	}

	public static int partOne(char[][] input, boolean[][] isOnLoop)
	{
		int result = 0;

		int x = 0;
		int y = 0;

		int x2 = 0;
		int y2 = 0;

		int directionOne = 0;
		int directionTwo = 0;

		for(int i = 0; i < input.length; i++)
		{
			for(int j = 0; j < input[i].length; j++)
			{
				if(input[i][j] == 'S')
				{
					x=j;
					y=i;
				}
			}
		}

		if((x != input[0].length - 1) && (input[y][x+1] == '-' || input[y][x+1] == 'J' || input[y][x+1] == '7'))
		{
			x2 = x+1;
			y2 = y;
			directionOne = 2;
		} else
		if((y != input.length - 1) && (input[y+1][x] == '|' || input[y+1][x] == 'J' || input[y+1][x] == 'L'))
		{
			x2 = x;
			y2 = y+1;
			directionOne = 3;
		} else
		if((x != 0) && (input[y][x-1] == '-' || input[y][x-1] == 'L' || input[y][x-1] == 'F'))
		{
			x2 = x-1;
			y2 = y;
			directionOne = 5;
		} else
		if((y != 0) && (input[y-1][x] == '|' || input[y-1][x] == '7' || input[y-1][x] == 'F'))
		{
			x2 = x;
			y2 = y-1;
			directionOne = 7;
		}

		while(input[y2][x2] != 'S')
		{
			isOnLoop[y][x] = true;

			if(input[y2][x2] == '-')
			{
				if(x < x2)
				{
					x = x2;
					x2++;
				}else
				if(x > x2)
				{
					x = x2;
					x2--;
				}
			} else
			if(input[y2][x2] == '|')
			{
				if(y < y2)
				{
					y = y2;
					y2++;
				}else
				if(y > y2)
				{
					y = y2;
					y2--;
				}
			} else
			if(input[y2][x2] == 'L')
			{
				if(y < y2)
				{
					y = y2;
					x2++;
				}else
				if(x > x2)
				{
					x = x2;
					y2--;
				}
			} else
			if(input[y2][x2] == 'J')
			{
				if(y < y2)
				{
					y = y2;
					x2--;
				}else
				if(x < x2)
				{
					x = x2;
					y2--;
				}
			} else
			if(input[y2][x2] == '7')
			{
				if (y > y2) {
					y = y2;
					x2--;
				} else
				if (x < x2)
				{
					x = x2;
					y2++;
				}
			} else
			if(input[y2][x2] == 'F')
			{
				if(y > y2)
				{
					y = y2;
					x2++;
				}else
				if(x > x2)
				{
					x = x2;
					y2++;
				}
			}
			result++;
		}


		isOnLoop[y][x] = true;

		if(x2 < x) directionTwo = 2;
		if(y2 < y) directionTwo = 3;
		if(x2 > x) directionTwo = 5;
		if(y2 > y) directionTwo = 7;


		if(directionOne + directionTwo == 5) input[y2][x2] = 'F';
		if(directionOne + directionTwo == 7) input[y2][x2] = '-';
		if(directionOne + directionTwo == 9) input[y2][x2] = 'L';
		if(directionOne + directionTwo == 8) input[y2][x2] = '7';
		if(directionOne + directionTwo == 10) input[y2][x2] = '|';
		if(directionOne + directionTwo == 12) input[y2][x2] = 'J';

		return (result+1)/2;
	}

	public static int partTwo(char[][] input, boolean[][] isOnLoop)
	{
		double intersections;
		int result = 0;
		for(int i = 0;i < input.length - 1; i++)
			for(int j = 0;j < input[i].length - 1; j++)
			{
				intersections = 0;
				if(!isOnLoop[i][j])
					for (int k = i + 1; k < input.length; k++)
						if(isOnLoop[k][j])
						{
							if(input[k][j] ==  '-') intersections++;
							if(input[k][j] ==  'L' || input[k][j] ==  '7') intersections+=.5;
							if(input[k][j] ==  'F' || input[k][j] ==  'J') intersections-=.5;
						}
				if(intersections % 2 != 0)
					result++;
			}
		return result;
	}
}
