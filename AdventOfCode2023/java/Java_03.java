import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
public class Java_03
{
	public static void main(String[] args) throws FileNotFoundException
	{
		double start = System.nanoTime();

		Scanner input = new Scanner(new File("AdventOfCode2023/input/03.txt"));

		String[][] stuff = new String[142][142];

		Arrays.fill(stuff[0],".");
		for(int i = 1; input.hasNextLine(); i++)
		{
			stuff[i] = Arrays.stream(("."+input.nextLine()+".").split("")).toArray(size -> new String[size]);
		}
		Arrays.fill(stuff[141],".");
		String temp = "";
		int count = 0;
		for(int i = 1; i < stuff.length - 1; i++)
		{
			for (int j = 1; j < stuff[i].length - 1; j++)
			{

				if("0123456789".contains(stuff[i][j]))
				{
					boolean thing = false;
					temp = "";
					String[] radius = new String[] {stuff[i-1][j-1],stuff[i-1][j],stuff[i-1][j+1],stuff[i][j-1],stuff[i][j+1],stuff[i+1][j-1],stuff[i+1][j],stuff[i+1][j+1]};
					temp+=stuff[i][j];
					for(String a: radius)
						if(!"0123456789.".contains(a))
						{
							thing = true;
							break;
						}



					if(thing)
						count+=Integer.parseInt(temp);
				}
			}
		}
		System.out.println(count);
		double duration = (System.nanoTime() - start) / 1000000;
		System.out.println(duration + "ms");
	}
}
	
	