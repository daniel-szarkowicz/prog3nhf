package pacman;

/**
 * The directions used in the game
 */
public enum Direction {
    UP(0.0, -1.0, 90),
    DOWN(0.0, 1.0, 270),
    RIGHT(1.0, 0.0, 0),
    LEFT(-1.0, 0.0, 180);

    /**
     * The X offset of the direction
     */
    public double x;
    /**
     * The Y offset of the direction
     */
    public double y;
    /**
     * The angle of the direction
     */
    public int angle;

    Direction(double x, double y, int angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    /**
     * Returns the opposite of a direction
     * 
     * @return the opposite direction
     */
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