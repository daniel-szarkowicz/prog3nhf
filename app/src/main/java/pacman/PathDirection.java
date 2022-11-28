package pacman;

/**
 * A class that bundles a {@link Direction} and a length
 * <P>
 * This is needed, so {@link pacman.controller.ClydeController} only chases
 * Pac-Man when Pac-Man is close enough
 */
public class PathDirection {
    public final int length;
    public final Direction direction;

    public PathDirection(int length, Direction direction) {
        this.length = length;
        this.direction = direction;
    }
}