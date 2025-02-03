import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day_16_2024
{
    static char[][] map;
    static int[][] scores;

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("2024/example/16.txt"));
        parse(input);

        double start;
        start = System.nanoTime();
        System.out.println("Part One: " + partOne() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
        for(char[] a: map)
            System.out.println(a);


        start = System.nanoTime();
        System.out.println("Part Two: " + partTwo() + " <" + ((System.nanoTime() - start) / 1000000) + "ms>");
        for(char[] a: map)
            System.out.println(a);
        for(int[] a: scores)
            System.out.println(Arrays.toString(a));
    }

    static int partOne()
    {
        LinkedList<int[]> moves = new LinkedList<>();
        moves.add(new int[]{map.length - 2, 1, 0, 0});

        while(!moves.isEmpty())
        {
            int[] move = moves.getFirst();

            int[] v = {-1,0};

            for(int i = 0; i < 4; i++, v = Helper.rotate90(v))
            {
                int dir = Helper.dir(v);
                int score = move[3] + (move[2] == dir ? 0 : 1000) + 1;
                if(map[move[0]][move[1]] != '#' && map[move[0] - v[1]][move[1] + v[0]] != '#' && (scores[move[0] - v[1]][move[1] + v[0]] > score || scores[move[0] - v[1]][move[1] + v[0]] == 0))
                {
                    moves.add(new int[]{move[0] - v[1], move[1] + v[0], dir, score});
                    scores[move[0] - v[1]][move[1] + v[0]] = score;
                }
            }
            moves.removeFirst();
        }

        return scores[1][map.length - 2];
    }

    static int partTwo()
    {
        int result = 0;

        LinkedList<int[]> moves = new LinkedList<>();
        moves.add(new int[]{1, map.length - 2});

        while(!moves.isEmpty())
        {
            int[] move = moves.getFirst();
            map[move[0]][move[1]] = '#';

            int[] v = {-1,0};

            for(int i = 0; i < 4; i++, v = Helper.rotate90(v)) {
                if (map[move[0] - v[1]][move[1] + v[0]] != '#' && scores[move[0] - v[1]][move[1] + v[0]] % 1000 == scores[move[0]][move[1]] % 1000 - 1 && (scores[move[0] - v[1]][move[1] + v[0]] < scores[move[0]][move[1]] - (scores[move[0]][move[1]] == Helper.dir(v) ? 1000: 0))) {
                    moves.add(new int[]{move[0] - v[1], move[1] + v[0]});
                    scores[move[0]][move[1]] = Helper.dir(v);
                    result++;
                }
            }
            moves.removeFirst();
        }

        return result;
    }

    static void parse(Scanner input)
    {
        ArrayList<char[]> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(input.nextLine().toCharArray());
        map = a.toArray(new char[0][0]);
        scores = new int[map.length][map[0].length];
    }
}