package pacman.controller;

import java.awt.event.KeyEvent;

import pacman.model.Entity;

public abstract class EntityController {
    private final Entity model;

    public EntityController(Entity model) {
        this.model = model;
    }

    /**
     * Ticks the controller
     * This is called by the {@link GameController}
     * 
     * @param delta the delta
     */
    public final void tick(double delta) {
        this.control(delta, this.model);
    }

    /**
     * Propatates a key event to the controller
     * This is called by the {@link GameController}
     * 
     * @param e the event
     */
    public void keyPressed(KeyEvent e) {
        this.keyPressed(e, this.model);
    }

    /**
     * Controls the model
     * 
     * @param delta the delta
     * @param model the entity to control
     */
    public abstract void control(double delta, Entity model);

    /**
     * Control the model with a key event
     * 
     * @param e     the key event
     * @param model the entity to control
     */
    public void keyPressed(KeyEvent e, Entity model) {
    }
}