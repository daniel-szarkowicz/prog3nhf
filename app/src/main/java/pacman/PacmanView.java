package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class PacmanView extends EntityView {
    private static final int MOUTH_ANGLE = 40;
    private final Color color;

    public PacmanView(Entity model, Color color) {
        super(model);
        this.color = color;
    }

    @Override
    public void view(Graphics2D g, Entity model) {
        if(((Pacman) model).isStrong()) {
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
