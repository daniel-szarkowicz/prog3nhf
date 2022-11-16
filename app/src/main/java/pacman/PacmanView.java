package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class PacmanView {
    private final Pacman model;
    private final Color color;

    public PacmanView(Pacman model, Color color) {
        this.model = model;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        g.setColor(this.color);
        g.fillRect((int)((this.model.getX()) * MapView.TILE_SIZE) + 2,
                (int)((this.model.getY()) * MapView.TILE_SIZE) + 2,
                MapView.TILE_SIZE - 4, MapView.TILE_SIZE - 4);
    }
}
