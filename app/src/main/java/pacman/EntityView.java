package pacman;

import java.awt.Graphics2D;

public abstract class EntityView {
    private final Entity model;
    
    public EntityView(Entity model) {
        this.model = model;
    }
    
    public final void draw(Graphics2D g) {
        if (!this.model.isDead()) {
            this.view(g, model);
        }
    }
    
    public abstract void view(Graphics2D g, Entity model);
}