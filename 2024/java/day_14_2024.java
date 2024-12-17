import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day_14_2024
{
    static Robot[] robots;
    static final int ROOM_HEIGHT = 103;

    public static void main(String[] args) throws FileNotFoundException
    {
        double start = System.nanoTime();

        Scanner input = new Scanner(new File("2024/input/14.txt"));
        parse(input);

        int partOne = partOne();
        int partTwo = partTwo();

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);

        double duration = (System.nanoTime() - start) / 1000000;
        System.out.println(duration + "ms");
    }

    static int partOne()
    {
        int result;

        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;

        for(Robot robot: robots)
            switch(robot.getQuadrantAfterMove())
            {
                case 1:
                    q1++;
                    break;
                case 2:
                    q2++;
                    break;
                case 3:
                    q3++;
                    break;
                case 4:
                    q4++;
                    break;
            }

        result = q1 * q2 * q3 * q4;

        return result;
    }

    static int partTwo()
    {
        int result;

        for(int i = 1; true; i++)
        {
            Set<Integer> robotPositions = new HashSet<>();
            boolean positionsAreUnique = true;

            for(Robot robot: robots)
            {
                robot.move();
                int robotPositon = ROOM_HEIGHT * robot.posY + robot.posX;
                if(robotPositions.contains(robotPositon))
                    positionsAreUnique = false;
                robotPositions.add(robotPositon);
            }

            if(positionsAreUnique)
            {
                result = i;
                break;
            }
        }

        return result;
    }

    static void parse(Scanner input)
    {
        ArrayList<Robot> a = new ArrayList<>();
        while(input.hasNextLine())
            a.add(new Robot(Arrays.stream(input.nextLine().substring(2).split(",| v=")).mapToInt(Integer::parseInt).toArray()));
        robots = a.toArray(new Robot[0]);
    }
}

class Robot
{
    final int ROOM_WIDTH = 101;  //example: 11, input: 101
    final int ROOM_HEIGHT = 103; //example: 7, input 103;

    int posX;
    int posY;
    int velX;
    int velY;

    Robot(int[] a)
    {
        posX = a[0];
        posY = a[1];
        velX = a[2];
        velY = a[3];
    }

    int getQuadrantAfterMove()
    {
        int newPosX = (posX + 100 * velX) % ROOM_WIDTH;
        if(newPosX < 0) newPosX += ROOM_WIDTH;
        int newPosY = (posY + 100 * velY) % ROOM_HEIGHT;
        if(newPosY < 0) newPosY += ROOM_HEIGHT;

        if(newPosX > ROOM_WIDTH / 2 && newPosY < ROOM_HEIGHT / 2)
            return 1;
        if(newPosX < ROOM_WIDTH / 2 && newPosY < ROOM_HEIGHT / 2)
            return 2;
        if(newPosX < ROOM_WIDTH / 2 && newPosY > ROOM_HEIGHT / 2)
            return 3;
        if(newPosX > ROOM_WIDTH / 2 && newPosY > ROOM_HEIGHT / 2)
            return 4;
        return 0;
    }

    void move()
    {
        posX = (posX + velX) % ROOM_WIDTH;
        if(posX < 0) posX += ROOM_WIDTH;
        posY = (posY + velY) % ROOM_HEIGHT;
        if(posY < 0) posY += ROOM_HEIGHT;
    }
}
