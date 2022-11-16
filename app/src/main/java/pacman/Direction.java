package pacman;

public enum Direction {
    UP(0.0, -1.0, "up"),
    DOWN(0.0, 1.0, "down"),
    RIGHT(1.0, 0.0, "right"),
    LEFT(-1.0, 0.0, "left");

    public double x;
    public double y;
    public String name;

    Direction(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
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