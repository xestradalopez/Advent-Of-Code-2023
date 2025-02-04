package Helpers;

public class Direction
{
    public enum Dir
    {
        RIGHT,
        UP,
        LEFT,
        DOWN
    }

    public Dir opposite(Dir d)
    {
        return switch (d)
        {
            case RIGHT -> Dir.LEFT;
            case UP -> Dir.DOWN;
            case LEFT -> Dir.RIGHT;
            case DOWN -> Dir.UP;
        };
    }

    public static Dir right(Dir d)
    {
        return switch (d)
        {
            case RIGHT -> Dir.DOWN;
            case UP -> Dir.RIGHT;
            case LEFT -> Dir.UP;
            case DOWN -> Dir.LEFT;
        };
    }
}
