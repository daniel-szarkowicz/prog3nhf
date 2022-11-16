package pacman;

public class Pacman {
    private Direction direction;
    private Direction nextDirection;
    private double offset;
    private Tile tile;

    public Pacman(Tile startingTile) {
        this.direction = Direction.UP;
        this.nextDirection = Direction.UP;
        this.offset = 0.0;
        this.tile = startingTile;
    }

    public void setNextDirection(Direction d) {
        if (d == this.direction.opposite()) {
            this.direction = d;
            this.offset = 1 - this.offset;
            this.tile = this.tile.getNeighbor(d);
        }
        this.nextDirection = d;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Direction getNextDirection() {
        return this.nextDirection;
    }

    // X coordinate in tile coordinates
    public double getX() {
        return this.tile.getX() - this.direction.x * offset;
    }

    // y coordinate in tile coordinates
    public double getY() {
        return this.tile.getY() - this.direction.y * offset;
    }

    // TODO: maybe move to PacmanController
    public void move(double amount) {
        if (this.offset <= 0) {
            var nextTile = this.tile.getNeighbor(this.nextDirection);
            if (nextTile != null) {
                this.direction = this.nextDirection;
                this.tile = nextTile;
                this.offset = 1.0;
            } else {
                nextTile = this.tile.getNeighbor(this.direction);
                if (nextTile != null) {
                    // this.nextDirection = this.direction;
                    this.tile = nextTile;
                    this.offset = 1.0;
                }
            }
        }
        this.offset -= amount;
        if (this.offset <= 0.0) {
            this.offset = 0.0;
        }
    }
}