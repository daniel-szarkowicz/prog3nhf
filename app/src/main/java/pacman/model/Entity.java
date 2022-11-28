package pacman.model;

import java.io.Serializable;

import pacman.Direction;

/**
 * The abstract baseclass for all entity models
 */
public abstract class Entity implements Serializable {
    private Direction direction;
    private Direction nextDirection;
    private double offset;
    private Tile tile;
    private final Tile spawnTile;
    private double respawn_timer;

    public Entity(Tile startingTile) {
        this.direction = Direction.UP;
        this.nextDirection = Direction.UP;
        this.offset = 0.0;
        this.tile = startingTile;
        this.tile.add(this);
        this.spawnTile = startingTile;
        this.respawn_timer = 0.0;
    }

    /**
     * Sets the next direction
     * 
     * @param d the next direction
     */
    public void setNextDirection(Direction d) {
        this.nextDirection = d;
    }

    /**
     * Returns the current direction
     * 
     * @return the current direction
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Returns the next direction
     * 
     * @return the next direction
     */
    public Direction getNextDirection() {
        return this.nextDirection;
    }

    /**
     * Returns the X coordinate in tile coordinates
     * 
     * @return the X coordinate in tile coordinates
     */
    public double getX() {
        return this.tile.getX() - this.direction.x * offset;
    }

    /**
     * Returns the Y coordinate in tile coordinates
     * 
     * @return the Y coordinate in tile coordinates
     */
    public double getY() {
        return this.tile.getY() - this.direction.y * offset;
    }

    /**
     * Moves the entity with a given speed and delta
     * If the entity is dead, it does not move
     * 
     * @param speed the speed to move with
     * @param delta the change in time
     */
    public void move(double speed, double delta) {
        if (this.isDead()) {
            this.respawn_timer -= delta;
            if (!this.isDead()) {
                this.respawn();
            }
        } else {
            if (this.direction.opposite() == this.nextDirection) {
                this.tile.moveEntity(this, this.nextDirection);
                this.direction = this.nextDirection;
            }
            if (this.offset <= 0.0) {
                this.offset = 0.0;
                this.tile.moveEntity(this, this.nextDirection);
                if (this.offset > 0.0) {
                    this.direction = this.nextDirection;
                } else {
                    this.tile.moveEntity(this, this.direction);
                }
            }
            this.offset -= speed * delta;
            if (this.offset <= 0.0) {
                this.offset = 0.0;
            }
        }
    }

    /**
     * Sets the current tile of the entity
     * 
     * @param tile the tile to change to
     */
    public void setTile(Tile tile) {
        this.tile.remove(this);
        this.tile = tile;
        this.tile.add(this);
        this.offset = 1.0 - this.offset;
    }

    /**
     * Returns the current tile
     * 
     * @return the current tile
     */
    public Tile getTile() {
        return this.tile;
    }

    /**
     * Kills the entity
     */
    public void die() {
        this.respawn_timer = 10.0;
    }

    /**
     * Respawns the entity, it will respawn at it's spawn tile
     */
    public void respawn() {
        this.respawn_timer = 0.0;
        this.tile.remove(this);
        this.tile = this.spawnTile;
        this.tile.add(this);
        this.offset = 0.0;
    }

    /**
     * Returns whether the entity is dead
     * 
     * @return whether the entity is dead
     */
    public boolean isDead() {
        return this.respawn_timer > 0.0;
    }

    /**
     * Returns whether the entity is moving
     * 
     * @return whether the entities offset from the tile is zero
     */
    public boolean isMoving() {
        return this.offset != 0.0;
    }

    /**
     * Collides with the given entity
     * 
     * @param entity the entity to collide with
     */
    public abstract void collideWith(Entity entity);

    /**
     * This is called by the monster when it collides on the entity
     * 
     * @param monster the monster that collided with this entity
     */
    public abstract void meetMonster(Monster monster);

    /**
     * This is called by the pacman when it collides on the entity
     * 
     * @param pacman the pacman that collided with this entity
     */
    public abstract void meetPacman(Pacman pacman);
}