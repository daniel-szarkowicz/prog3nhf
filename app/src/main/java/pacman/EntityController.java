package pacman;

import java.awt.event.KeyEvent;

public abstract class EntityController {
    private final Entity model;
    
    public EntityController(Entity model) {
        this.model = model;
    }
    
    public final void tick(double delta) {
        this.control(delta, this.model);
    }

    public void keyPressed(KeyEvent e) {
        this.keyPressed(e, this.model);
    }
    
    public abstract void control(double delta, Entity model);
    
    public void keyPressed(KeyEvent e, Entity model) {
    }
}