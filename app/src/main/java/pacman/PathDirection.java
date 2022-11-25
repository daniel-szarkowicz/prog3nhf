package pacman;

public class PathDirection {
    public final int length;
    public final Direction direction;

    public PathDirection(int length, Direction direction) {
        this.length = length;
        this.direction = direction;
    }
}