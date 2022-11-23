package pacman;

public class MapEditor {
    public TileMap map;
    public boolean active;
    
    public MapEditor() {
        this.map = new TileMap(20);
        this.active = true;
    }
}