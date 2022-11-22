package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class DotView {
    private final Entity model;

    public DotView(Entity model) {
        this.model = model;
    }
    
    public void draw(Graphics2D g) {
        if (!this.model.isDead()) {
            g.setColor(Color.YELLOW);
            g.fillOval((int) (this.model.getX() * MapView.TILE_SIZE) + 20,
                    (int) (this.model.getY() * MapView.TILE_SIZE) + 20,
                    MapView.TILE_SIZE - 40,
                    MapView.TILE_SIZE - 40);
        }
    }
}