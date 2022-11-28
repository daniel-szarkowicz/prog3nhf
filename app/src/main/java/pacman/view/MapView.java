package pacman.view;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.Direction;
import pacman.model.TileMap;

/**
 * The view that displays the map
 */
public class MapView {
    public static final int TILE_SIZE = 50;
    private final TileMap model;

    /**
     * @param model the model to view
     */
    public MapView(TileMap model) {
        this.model = model;
    }

    /**
     * Draws the map
     * 
     * @param g the {@link Graphics2D} to draw on
     */
    public void draw(Graphics2D g) {
        for (var tile : model) {
            if (tile.isWall()) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(tile.getX() * TILE_SIZE,
                        tile.getY() * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE);
                g.setColor(Color.BLUE);
                var leftNeighbor = tile.getNeighbor(Direction.LEFT);
                var upNeighbor = tile.getNeighbor(Direction.UP);
                var rightNeighbor = tile.getNeighbor(Direction.RIGHT);
                var downNeighbor = tile.getNeighbor(Direction.DOWN);
                if (leftNeighbor != null && !leftNeighbor.isWall()) {
                    g.fillRect(tile.getX() * TILE_SIZE,
                            tile.getY() * TILE_SIZE,
                            3, TILE_SIZE);
                }
                if (upNeighbor != null && !upNeighbor.isWall()) {
                    g.fillRect(tile.getX() * TILE_SIZE,
                            tile.getY() * TILE_SIZE,
                            TILE_SIZE, 3);
                }
                if (rightNeighbor != null && !rightNeighbor.isWall()) {
                    g.fillRect(tile.getX() * TILE_SIZE + TILE_SIZE - 3,
                            tile.getY() * TILE_SIZE,
                            3, TILE_SIZE);
                }
                if (downNeighbor != null && !downNeighbor.isWall()) {
                    g.fillRect(tile.getX() * TILE_SIZE,
                            tile.getY() * TILE_SIZE + TILE_SIZE - 3,
                            TILE_SIZE, 3);
                }
            }
        }
    }
}