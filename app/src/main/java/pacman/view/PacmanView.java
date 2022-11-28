package pacman.view;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.model.Entity;
import pacman.model.Pacman;

/**
 * The view for a Pac-Man
 */
public class PacmanView extends EntityView {
    private static final int MOUTH_ANGLE = 40;
    private final Color color;

    /**
     * Creates a view for the given model with a color
     * 
     * @param model the model to view
     * @param color the color of the view
     */
    public PacmanView(Entity model, Color color) {
        super(model);
        this.color = color;
    }

    @Override
    public void view(Graphics2D g, Entity model) {
        if (((Pacman) model).isStrong()) {
            g.setColor(Color.RED);
            g.fillArc((int) (model.getX() * MapView.TILE_SIZE) - 2,
                    (int) (model.getY() * MapView.TILE_SIZE) - 2,
                    MapView.TILE_SIZE + 4, MapView.TILE_SIZE + 4,
                    model.getDirection().angle + MOUTH_ANGLE / 2, 360 - MOUTH_ANGLE);
        }
        g.setColor(this.color);
        g.fillArc((int) (model.getX() * MapView.TILE_SIZE) + 2,
                (int) (model.getY() * MapView.TILE_SIZE) + 2,
                MapView.TILE_SIZE - 4, MapView.TILE_SIZE - 4,
                model.getDirection().angle + MOUTH_ANGLE / 2, 360 - MOUTH_ANGLE);
    }
}
