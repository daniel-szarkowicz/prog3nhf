package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class MapView {
    public static final int TILE_SIZE = 50;
    private final TileMap model;

    public MapView(TileMap model) {
        this.model = model;
    }

    public void draw(Graphics2D g) {
        for (var tile : model) {
            if (tile.isWall()) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(tile.getX() * TILE_SIZE,
                tile.getY() * TILE_SIZE,
                TILE_SIZE, TILE_SIZE);
                g.setColor(Color.BLUE);
                if (tile.getNeighbor(Direction.LEFT) != null) {
                    g.fillRect(tile.getX() * TILE_SIZE,
                            tile.getY() * TILE_SIZE,
                            3, TILE_SIZE);
                }
                if (tile.getNeighbor(Direction.UP) != null) {
                    g.fillRect(tile.getX() * TILE_SIZE,
                            tile.getY() * TILE_SIZE,
                            TILE_SIZE, 3);
                }
                if (tile.getNeighbor(Direction.RIGHT) != null) {
                    g.fillRect(tile.getX() * TILE_SIZE + TILE_SIZE - 3,
                            tile.getY() * TILE_SIZE,
                            3, TILE_SIZE);
                }
                if (tile.getNeighbor(Direction.DOWN) != null) {
                    g.fillRect(tile.getX() * TILE_SIZE,
                            tile.getY() * TILE_SIZE + TILE_SIZE - 3,
                            TILE_SIZE, 3);
                }
            }
        }
    }
}