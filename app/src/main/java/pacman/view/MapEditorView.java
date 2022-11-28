package pacman.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import pacman.model.MapEditor;

/**
 * The main view of the map editor
 */
public class MapEditorView extends JPanel {
    private final MapEditor model;

    private MapView map;

    /**
     * Creates a view with the given model
     * 
     * @param model the model to view
     */
    public MapEditorView(MapEditor model) {
        this.model = model;
        this.map = new MapView(this.model.map);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.setBackground(Color.BLACK);
        super.paintComponent(g);

        var g2d = (Graphics2D) g;
        this.map.draw(g2d);
        var pacmanSpawn = this.model.map.getPacmanSpawn();
        g.setColor(Color.YELLOW);
        g.fillOval(pacmanSpawn.getX() * MapView.TILE_SIZE,
                pacmanSpawn.getY() * MapView.TILE_SIZE,
                MapView.TILE_SIZE, MapView.TILE_SIZE);
        var monsterSpawn = this.model.map.getMonsterSpawn();
        g.setColor(Color.RED);
        g.fillOval(monsterSpawn.getX() * MapView.TILE_SIZE,
                monsterSpawn.getY() * MapView.TILE_SIZE,
                MapView.TILE_SIZE, MapView.TILE_SIZE);
    }

    /**
     * Renews the {@link MapView} when the map changed
     */
    public void renewMap() {
        this.map = new MapView(this.model.map);
    }
}