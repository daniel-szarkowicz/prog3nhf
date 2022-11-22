package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class MonsterView extends EntityView {
    private final Color color;

    public MonsterView(Entity model, Color color) {
        super(model);
        this.color = color;
    }

    @Override
    public void view(Graphics2D g, Entity model) {
            g.setColor(this.color);
            g.fillRect((int) ((model.getX()) * MapView.TILE_SIZE) + 2,
                    (int) ((model.getY()) * MapView.TILE_SIZE) + 2,
                    MapView.TILE_SIZE - 4, MapView.TILE_SIZE - 4);
    }
}
