package pacman.model;

/**
 * The state of the map editor
 */
public class MapEditor {
    public TileMap map;
    public boolean active;

    public MapEditor() {
        this.map = new TileMap(20);
        this.active = true;
    }
}