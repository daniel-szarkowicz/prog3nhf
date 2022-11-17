package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MapEditorView extends JPanel {
    private final MapEditor model;

    private final MapView map;

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
    }
}