package pacman;

public class Entity {
    private Direction direction;
    private Direction nextDirection;
    private double offset;
    private Tile tile;
    private boolean dead = false;

    public Entity(Tile startingTile) {
        this.direction = Direction.UP;
        this.nextDirection = Direction.UP;
        this.offset = 0.0;
        this.tile = startingTile;
    }

    public void setNextDirection(Direction d) {
        if (d == this.direction.opposite()) {
            this.direction = d;
            this.offset = 1 - this.offset;
            this.tile.remove(this);
            this.tile = this.tile.getNeighbor(d);
            this.tile.add(this);
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
        if (!this.dead) {
            if (this.offset <= 0.0) {
                this.tile.moveEntity(this, this.nextDirection);
                if (this.offset > 0.0) {
                    this.direction = this.nextDirection;
                } else {
                    this.tile.moveEntity(this, this.direction);
                }
            }
            this.offset -= amount;
            if (this.offset <= 0.0) {
                this.offset = 0.0;
            }
        }
    }

    public void setTile(Tile tile) {
        this.tile.remove(this);
        this.tile = tile;
        this.tile.add(this);
        this.offset = 1.0;
    }

    public void die() {
        this.dead = true;
    }
    
    public boolean isDead() {
        return this.dead;
    }
}