package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class MonsterView {
    private final Entity model;
    private final Color color;

    public MonsterView(Entity model, Color color) {
        this.model = model;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        if (!this.model.isDead()) {
            g.setColor(this.color);
            g.fillRect((int) ((this.model.getX()) * MapView.TILE_SIZE) + 2,
                    (int) ((this.model.getY()) * MapView.TILE_SIZE) + 2,
                    MapView.TILE_SIZE - 4, MapView.TILE_SIZE - 4);
        }
    }
}
