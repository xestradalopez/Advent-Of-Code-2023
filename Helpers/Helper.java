import java.util.List;

public class Helper
{
    public static int[][] toArray(List<Integer>[] a)
    {
        int[][] result = new int[a.length][];
        for(int i = 0; i < a.length; i++)
            result[i] = a[i].stream().mapToInt(Integer::intValue).toArray();
        return result;
    }

    public static int[] rotate90(int[] a)
    {
        int[] b = new int[2];
        b[0] = -a[1];
        b[1] =  a[0];
        return b;
    }

    public static int[] rotate45(int[] a)
    {
        int[] b = new int[2];
        b[0] = a[0] == -a[1] ? a[0] : (a[0] - a[1]);
        b[1] = a[0] ==  a[1] ? a[0] : (a[0] + a[1]);
        return b;
    }

    public static int dir(int[] a)
    {
        if(a[0] == 1 && a[1] == 0)
            return 0;
        if(a[0] == 0 && a[1] == 1)
            return 1;
        if(a[0] == -1 && a[1] == 0)
            return 2;
        if(a[0] == 0 && a[1] == -1)
            return 3;
        return -1;
    }
}
