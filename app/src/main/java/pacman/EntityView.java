package pacman;

import java.awt.Graphics2D;

public abstract class EntityView {
    private final Entity model;

    public EntityView(Entity model) {
        this.model = model;
    }

    /**
     * Draws the entity, this is called by the {@link GameView}
     * 
     * @param g the Graphics2D to draw on
     */
    public final void draw(Graphics2D g) {
        if (!this.model.isDead()) {
            this.view(g, model);
        }
    }

    /**
     * Draws the entity
     * 
     * @param g     the Graphics2D to draw on
     * @param model the entity to draw
     */
    public abstract void view(Graphics2D g, Entity model);
}