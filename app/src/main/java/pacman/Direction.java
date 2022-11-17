package pacman;

public enum Direction {
    UP(0.0, -1.0, 90),
    DOWN(0.0, 1.0, 270),
    RIGHT(1.0, 0.0, 0),
    LEFT(-1.0, 0.0, 180);

    public double x;
    public double y;
    public int angle;

    Direction(double x, double y, int angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public Direction opposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                throw new RuntimeException("Unreachable");
        }
    }
}