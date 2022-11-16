package pacman;

public class Tile {
    private final int x;
    private final int y;
    private final TileMap map;
    private boolean wall;

    public Tile(int x, int y, TileMap map, boolean wall) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.wall = wall;
    }

    public boolean isWall() {
        return wall;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    //TODO: this could be better
    public Tile getNeighbor(Direction direction) {
        var neighbor = this.map.getTile(this.x + (int) direction.x,
                this.y + (int) direction.y);
        if (neighbor == null || neighbor.isWall()) {
            return null;
        } else {
            return neighbor;
        }
    }
}