import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day10PipeMaze
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

		int oldX = 0;
		int oldY = 0;

		int newX = 0;
		int newY = 0;

		int directionOne = 0;
		int directionTwo = 0;

		i: for(int i = 0; i < input.length; i++)
			for(int j = 0; j < input[i].length; j++)
				if(input[i][j] == 'S')
				{
					oldX = j;
					oldY = i;
					break i;
				}

		if((oldX != input[0].length - 1) && (input[oldY][oldX+1] == '-' || input[oldY][oldX+1] == 'J' || input[oldY][oldX+1] == '7'))
		{
			newX = oldX+1;
			newY = oldY;
			directionOne = 2;
		} else
		if((oldY != input.length - 1) && (input[oldY+1][oldX] == '|' || input[oldY+1][oldX] == 'J' || input[oldY+1][oldX] == 'L'))
		{
			newX = oldX;
			newY = oldY+1;
			directionOne = 3;
		} else
		if((oldX != 0) && (input[oldY][oldX-1] == '-' || input[oldY][oldX-1] == 'L' || input[oldY][oldX-1] == 'F'))
		{
			newX = oldX-1;
			newY = oldY;
			directionOne = 5;
		} else
		if((oldY != 0) && (input[oldY-1][oldX] == '|' || input[oldY-1][oldX] == '7' || input[oldY-1][oldX] == 'F'))
		{
			newX = oldX;
			newY = oldY-1;
			directionOne = 7;
		}

		while(input[newY][newX] != 'S')
		{
			isOnLoop[oldY][oldX] = true;

			if(input[newY][newX] == '-')
			{
				if(oldX < newX)
				{
					oldX = newX;
					newX++;
				}else
				if(oldX > newX)
				{
					oldX = newX;
					newX--;
				}
			} else
			if(input[newY][newX] == '|')
			{
				if(oldY < newY)
				{
					oldY = newY;
					newY++;
				}else
				if(oldY > newY)
				{
					oldY = newY;
					newY--;
				}
			} else
			if(input[newY][newX] == 'L')
			{
				if(oldY < newY)
				{
					oldY = newY;
					newX++;
				}else
				if(oldX > newX)
				{
					oldX = newX;
					newY--;
				}
			} else
			if(input[newY][newX] == 'J')
			{
				if(oldY < newY)
				{
					oldY = newY;
					newX--;
				}else
				if(oldX < newX)
				{
					oldX = newX;
					newY--;
				}
			} else
			if(input[newY][newX] == '7')
			{
				if (oldY > newY) {
					oldY = newY;
					newX--;
				} else
				if (oldX < newX)
				{
					oldX = newX;
					newY++;
				}
			} else
			if(input[newY][newX] == 'F')
			{
				if(oldY > newY)
				{
					oldY = newY;
					newX++;
				}else
				if(oldX > newX)
				{
					oldX = newX;
					newY++;
				}
			}
			result++;
		}


		isOnLoop[oldY][oldX] = true;

		if(newX < oldX) directionTwo = 2;
		if(newY < oldY) directionTwo = 3;
		if(newX > oldX) directionTwo = 5;
		if(newY > oldY) directionTwo = 7;


		if(directionOne + directionTwo == 5) input[newY][newX] = 'F';
		if(directionOne + directionTwo == 7) input[newY][newX] = '-';
		if(directionOne + directionTwo == 9) input[newY][newX] = 'L';
		if(directionOne + directionTwo == 8) input[newY][newX] = '7';
		if(directionOne + directionTwo == 10) input[newY][newX] = '|';
		if(directionOne + directionTwo == 12) input[newY][newX] = 'J';

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
