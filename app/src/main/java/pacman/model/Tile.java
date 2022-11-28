package pacman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import pacman.Direction;
import pacman.PathDirection;

/**
 * The tile the map is made up from
 */
public class Tile implements Serializable {
    private final int x;
    private final int y;
    private final TileMap map;
    private boolean wall;
    private final List<Entity> entities;
    private long pacmanTime;

    /**
     * Create a tile at the give position
     * 
     * @param x    the X coordinate of the tile
     * @param y    the Y coordinate of the tile
     * @param map  the map the tile is in
     * @param wall whether the tile is a wall
     */
    public Tile(int x, int y, TileMap map, boolean wall) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.wall = wall;
        this.entities = new ArrayList<>();
        this.pacmanTime = 0;
    }

    /**
     * Returns whether the tile is a wall
     * 
     * @return whether the tile is a wall
     */
    public boolean isWall() {
        return this.wall;
    }

    /**
     * Sets whether the tile is a wall
     * 
     * @param wall the new state
     */
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    /**
     * Returns the X coordinate of the tile
     * 
     * @return the X coordinate of the tile
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the Y coordinate of the tile
     * 
     * @return the Y coordinate of the tile
     */
    public int getY() {
        return this.y;
    }

    /**
     * Adds an entity to the tile, the entity collides with all other entities on
     * the tile
     * 
     * @param entity the entity
     */
    public void add(Entity entity) {
        for (var e : this.entities) {
            entity.collideWith(e);
        }
        this.entities.add(entity);
    }

    /**
     * Removes an entity from the tile
     * 
     * @param entity the entity to remove
     */
    public void remove(Entity entity) {
        this.entities.remove(entity);
    }

    /**
     * Tries to move an entity in a direction
     * 
     * @param entity    the entity to move
     * @param direction the direction to move in
     */
    public void moveEntity(Entity entity, Direction direction) {
        var nextTile = this.map.getTile(this.x + (int) direction.x,
                this.y + (int) direction.y);
        if (nextTile != null && !nextTile.isWall()) {
            entity.setTile(nextTile);
        }
    }

    /**
     * Gets the neighbor of the tile in a given direction
     * 
     * @param direction the direction
     */
    public @Nullable Tile getNeighbor(Direction direction) {
        return this.map.getTile(this.x + (int) direction.x,
                this.y + (int) direction.y);
    }

    /**
     * Sets the last time pacman was on the tile to the current time
     */
    public void timeStampPacman() {
        this.pacmanTime = System.currentTimeMillis();
    }

    /**
     * Gets the direction of Pac-Man's path
     * 
     * @return the direction of Pac-Man's path or null if there is no path
     */
    public @Nullable Direction getPacmanPathDirection() {
        List<Direction> movableDirections = new ArrayList<>();
        for (var direction : Direction.values()) {
            var neighbor = this.getNeighbor(direction);
            if (neighbor != null && !neighbor.isWall() && neighbor.pacmanTime > this.pacmanTime) {
                movableDirections.add(direction);
            }
        }

        if (movableDirections.isEmpty()) {
            return null;
        }

        Direction nextDirection = movableDirections.remove(0);
        for (var direction : movableDirections) {
            if (this.getNeighbor(nextDirection).pacmanTime < this.getNeighbor(direction).pacmanTime) {
                nextDirection = direction;
            }
        }
        return nextDirection;
    }

    /**
     * Gets the next direction of the path to the closest Pac-Man
     * 
     * @return the direction
     */
    public PathDirection getPathToClosestPacman() {
        var frontier = new ArrayList<Tile>();
        var directions = new HashMap<Tile, Direction>();
        frontier.add(this);
        directions.put(this, null);
        Tile foundPacman = null;
        while (!frontier.isEmpty()) {
            var curr = frontier.remove(0);
            if (this.map.getPacmanTiles().contains(curr)) {
                foundPacman = curr;
                break;
            }
            for (var d : Direction.values()) {
                var neighbor = curr.getNeighbor(d);
                if (neighbor != null && !neighbor.isWall()) {
                    if (!directions.containsKey(neighbor)) {
                        frontier.add(neighbor);
                        directions.put(neighbor, d);
                    }
                }
            }
        }
        if (foundPacman != null) {
            var dir = Direction.UP;
            var len = 0;
            var current = foundPacman;
            while (current != this) {
                len += 1;
                dir = directions.get(current);
                current = current.getNeighbor(dir.opposite());
            }
            return new PathDirection(len, dir);
        }
        return null;
    }

    /**
     * Sets the pacman times of tiles that are older than this tile to this tile's
     * pacman time. This is used when Pac-Man dies, so Blinky can start to chase
     * another Pac-Man
     */
    public void resetPacmanTimes() {
        for (var tile : this.map) {
            if (tile.pacmanTime < this.pacmanTime) {
                tile.pacmanTime = this.pacmanTime;
            }
        }
    }
}