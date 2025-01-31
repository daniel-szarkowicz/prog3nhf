package pacman.view;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.model.Entity;

/**
 * The view for a dot
 */
public class DotView extends EntityView {

    /**
     * @param model the model to view
     */
    public DotView(Entity model) {
        super(model);
    }

    @Override
    public void view(Graphics2D g, Entity model) {
        g.setColor(Color.YELLOW);
        g.fillOval((int) (model.getX() * MapView.TILE_SIZE) + 20,
                (int) (model.getY() * MapView.TILE_SIZE) + 20,
                MapView.TILE_SIZE - 40,
                MapView.TILE_SIZE - 40);
    }
}