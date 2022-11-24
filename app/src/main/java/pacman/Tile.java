package pacman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tile implements Serializable {
    private final int x;
    private final int y;
    private final TileMap map;
    private boolean wall;
    private final List<Entity> entities;
    private long pacmanTime;

    public Tile(int x, int y, TileMap map, boolean wall) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.wall = wall;
        this.entities = new ArrayList<>();
        this.pacmanTime = 0;
    }

    public boolean isWall() {
        return this.wall;
    }
    
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public void add(Entity entity) {
        for (var e : this.entities) {
            entity.collideWith(e);
        }
        this.entities.add(entity);
    }
    
    public void remove(Entity entity) {
        this.entities.remove(entity);
    }
    
    public void moveEntity(Entity entity, Direction direction) {
        var nextTile = this.map.getTile(this.x + (int) direction.x,
                this.y + (int) direction.y);
        if (nextTile != null && !nextTile.isWall()) {
            entity.setTile(nextTile);
        }
    }

    public Tile getNeighbor(Direction direction) {
        return this.map.getTile(this.x + (int) direction.x,
                this.y + (int) direction.y);
    }

    public void timeStampPacman() {
        this.pacmanTime = System.currentTimeMillis();
    }
    
    public Direction getPacmanPathDirection() {
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
        for (var direction: movableDirections) {
            if (this.getNeighbor(nextDirection).pacmanTime < this.getNeighbor(direction).pacmanTime) {
                nextDirection = direction;
            }
        }
        return nextDirection;
    }
    
    public void resetPacmanTimes() {
        for (var tile: this.map) {
            if (tile.pacmanTime < this.pacmanTime) {
                tile.pacmanTime = this.pacmanTime;
            }
        }
    }
}