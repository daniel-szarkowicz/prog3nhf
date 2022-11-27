package pacman.view;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.model.Entity;

public class LargeDotView extends EntityView {

    public LargeDotView(Entity model) {
        super(model);
    }

    @Override
    public void view(Graphics2D g, Entity model) {
        g.setColor(Color.YELLOW);
        g.fillOval((int) (model.getX() * MapView.TILE_SIZE) + 15,
                (int) (model.getY() * MapView.TILE_SIZE) + 15,
                MapView.TILE_SIZE - 30,
                MapView.TILE_SIZE - 30);
    }
}