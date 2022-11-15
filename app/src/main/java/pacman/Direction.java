package pacman;

public enum Direction {
    UP(0, 1, "up"), DOWN(0, -1, "down"), RIGHT(1, 0, "right"), LEFT(-1, 0, "left");

    public double x;
    public double y;
    public String name;

    Direction(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
}